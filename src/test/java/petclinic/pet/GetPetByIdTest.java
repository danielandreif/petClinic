package petclinic.pet;

import com.petclinic.model.Pet;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import petclinic.BaseClassTest;

public class GetPetByIdTest extends BaseClassTest {

    @Test
    public void getPetById() {
        fixture.createOwner()
                .createPetType();
        Pet pet = fixture.createPet().getPet();

        Response postResponse = petClient.createPetResponse(pet);

        Long petId = postResponse.body().jsonPath().getLong("id");
        Response getResponse = petClient.getPetByIdResponse(petId);
        getResponse.then().statusCode(HttpStatus.SC_OK);
    }
}
