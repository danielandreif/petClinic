package com.petclinic.client;

import com.petclinic.model.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetClient extends BaseClient {

    public Response createPetResponse(Pet pet) {
        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("api/pets");
    }

    public Response getPetByIdResponse(Long id) {
        return getBasicRestConfig()
                .pathParam("id", id)
                .get("api/pets/{id}");
    }

    public Response getPetListResponse() {
        return getBasicRestConfig()
                .get("api/pets/");
    }

    public Response deletePetByIdResponse(Long id) {
        return getBasicRestConfig()
                .pathParam("id", id)
                .delete("api/pets/{id}");
    }

    public Response updatePetByIdResponse(Long id, Pet pet) {
        return getBasicRestConfig()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(pet)
                .put("api/pets/{id}");
    }
}
