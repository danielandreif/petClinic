package petclinic.owner;

import com.petclinic.model.Owner;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import petclinic.BaseClassTest;

import java.util.List;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class GetOwnerListTest extends BaseClassTest {

    @Test
    public void shouldGetOwnerList() {
        Owner owner = fixture.createOwner().getOwner();
        Response createOwnerResponse = client.createOwnerResponse(owner);

        Long ownerId = createOwnerResponse.body().jsonPath().getLong("id");
        Response response = client.getOwnerListResponse();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("find{it -> it.id == %s}.firstName", withArgs(ownerId), is(owner.getFirstName()));

        Owner actualOwner = response.body().jsonPath().param("id", ownerId).getObject("find{it -> it.id == id}", Owner.class);
        assertThat(actualOwner, is(owner));

        List<Owner> ownerList = response.body().jsonPath().getList("", Owner.class);
        assertThat(ownerList, hasItem(owner));
    }
}
