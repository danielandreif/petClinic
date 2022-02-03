package petclinic.owner;

import com.petclinic.model.Owner;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import petclinic.BaseClassTest;

public class GetOwnerByIdTest extends BaseClassTest {

    @Test
    public void shouldGetOwnerById() {
        Owner owner = fixture.createOwner().getOwner();
        Response createOwnerResponse = client.createOwnerResponse(owner);

        Long ownerId = createOwnerResponse.body().jsonPath().getLong("id");
        Response response = client.getOwnerByIdResponse(ownerId);
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void shouldThrow404() {
        Owner owner = fixture.createOwner().getOwner();
        Response createOwnerResponse = client.createOwnerResponse(owner);

        Long ownerId = createOwnerResponse.body().jsonPath().getLong("id");
        Response responseDelete = client.deleteOwnerByIdResponse(ownerId);
        responseDelete.then().statusCode(HttpStatus.SC_NO_CONTENT);

        Response responseGet = client.getOwnerByIdResponse(ownerId);
        responseGet.then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
