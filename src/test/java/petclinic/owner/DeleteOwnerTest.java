package petclinic.owner;

import com.petclinic.model.Owner;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import petclinic.BaseClassTest;

public class DeleteOwnerTest extends BaseClassTest {

    @Test
    public void shouldDeleteOwner() {
        Owner owner = fixture.createOwner().getOwner();
        Response createOwnerResponse = client.createOwnerResponse(owner);

        Long ownerId = createOwnerResponse.body().jsonPath().getLong("id");
        Response response = client.deleteOwnerByIdResponse(ownerId);
        response.then().statusCode(HttpStatus.SC_NO_CONTENT);
    }

}
