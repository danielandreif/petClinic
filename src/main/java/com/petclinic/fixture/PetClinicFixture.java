package com.petclinic.fixture;

import com.petclinic.client.OwnerClient;
import com.petclinic.client.PetClient;
import com.petclinic.client.PetTypeClient;
import com.petclinic.client.VisitClient;
import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.PetType;
import com.petclinic.model.Visit;
import com.petclinic.testData.TestDataProvider;
import io.restassured.response.Response;
import lombok.Getter;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.is;


public class PetClinicFixture {

    private OwnerClient ownerClient = new OwnerClient();
    private PetClient petClient = new PetClient();
    private TestDataProvider dataProvider = new TestDataProvider();
    private PetTypeClient petTypeClient = new PetTypeClient();
    private VisitClient visitClient = new VisitClient();

    @Getter
    private Owner owner;
    @Getter
    private PetType pettype;
    @Getter
    private Pet pet;
    @Getter
    private Visit visit;

    public PetClinicFixture createOwner() {
        owner = dataProvider.getOwner();
        Response response = ownerClient.createOwnerResponse(owner);
        response.then().statusCode(HttpStatus.SC_CREATED);

        long id = response.body().jsonPath().getLong("id");
        owner.setId(id);

        return this;
    }

    public PetClinicFixture createPetType() {
        pettype = dataProvider.getType();
        Response response = petTypeClient.createPetType(pettype);
        response.then().statusCode(HttpStatus.SC_CREATED);

        long id = response.body().jsonPath().getLong("id");
        pettype.setId(id);

        return this;
    }

    public PetClinicFixture createPet() {
        pet = dataProvider.getPet(getOwner(), getPettype());
        Response response = petClient.createPetResponse(pet);
        response.then().statusCode(HttpStatus.SC_CREATED);

        long id = response.body().jsonPath().getLong("id");
        pet.setId(id);

        return this;
    }

    public PetClinicFixture createVisit() {
        visit = dataProvider.getVisit(getOwner(), getPettype(), getPet());
        Response response = visitClient.createVisitResponse(visit);
        response.then().statusCode(HttpStatus.SC_CREATED);

        long id = response.body().jsonPath().getLong("id");
        visit.setId(id);

        return this;
    }

    public PetClinicFixture getPetList() {
        pet = dataProvider.getPet(getOwner(), getPettype());

        Response createPetResponse = petClient.createPetResponse(pet);
        Long petId = createPetResponse.body().jsonPath().getLong("id");
        Response getResponse = petClient.getPetListResponse();
        getResponse.then()
                .statusCode(HttpStatus.SC_OK)
                .body("find{it -> it.id == %s}.name", withArgs(petId), is(pet.getName()));

        return this;
    }

}
