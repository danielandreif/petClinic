package petclinic.owner;

import com.petclinic.model.Owner;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import petclinic.BaseClassTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateOwnerTest extends BaseClassTest {

    @Test
    public void shouldCreateOwnerGivenValidData() {
        Owner owner = fixture.createOwner().getOwner();
        Response response = client.createOwnerResponse(owner);
        response.then().body("id", is(notNullValue()));

        long id = response.body().jsonPath().getLong("id");
        Owner actualOwnerInDB = db.getOwnerById(id);
        assertThat(actualOwnerInDB, is(owner));
    }

    @Test
    public void shouldFailToCreateOwnerGivenEmptyFirstName() {
        Owner owner = provider.getOwner();
        owner.setFirstName("");

        Response response = client.createOwnerResponse(owner);
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldFailToCreateOwnerGivenFewDigitsTelephone() {
        Owner owner = provider.getOwner();
        owner.setTelephone(provider.generateNumberBetween(0, 0));

        Response response = client.createOwnerResponse(owner);
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldFailToCreateOwnerGivenTooManyDigitsTelephone() {
        Owner owner = provider.getOwner();
        owner.setTelephone(provider.generateNumberBetween(11, 100));

        Response response = client.createOwnerResponse(owner);
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
