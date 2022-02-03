package com.petclinic.client;

import com.petclinic.model.PetType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetTypeClient extends BaseClient {

    public Response createPetType(PetType pettype) {
        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .body(pettype)
                .when()
                .post("api/pettypes");
    }
}
