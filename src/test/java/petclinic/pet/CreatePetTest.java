package petclinic.pet;

import com.petclinic.model.Pet;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import petclinic.BaseClassTest;

public class CreatePetTest extends BaseClassTest {

    @Test
    public void shouldCreatePetGivenValidData() {
        fixture.createOwner()
                .createPetType();
        Pet pet = fixture.createPet().getPet();

        Response response = petClient.createPetResponse(pet);
        response.then().statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void shouldNotCreatePetGivenNoBirthdate() {
        fixture.createOwner()
                .createPetType();
        Pet pet = fixture.createPet().getPet();
        pet.setBirthDate("");

        Response response = petClient.createPetResponse(pet);
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }


    //Bug?
    @Test
    public void shouldNotCreatePetGivenBirthdateInFuture() {
        fixture.createOwner()
                .createPetType();
        Pet pet = fixture.createPet().getPet();
        pet.setBirthDate("22/03/2039");

        Response response = petClient.createPetResponse(pet);
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldNotCreatePetGivenNoName() {
        fixture.createOwner()
                .createPetType();
        Pet pet = fixture.createPet().getPet();
        pet.setName("");

        Response response = petClient.createPetResponse(pet);
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
