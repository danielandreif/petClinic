package petclinic.pet;

import com.petclinic.model.Pet;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import petclinic.BaseClassTest;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.is;

public class GetPetList extends BaseClassTest {

    @Test
    public void shouldGetValidPetList() {

        fixture.createOwner()
                .createPetType();
        Pet pet = fixture.createPet().getPet();

        Response postResponse = petClient.createPetResponse(pet);
        Long petId = postResponse.body().jsonPath().getLong("id");

        Response response = petClient.getPetListResponse();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("find{it -> it.id == %s}.name", withArgs(petId), is(pet.getName()));
    }
}
