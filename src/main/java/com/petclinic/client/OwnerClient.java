package com.petclinic.client;

import com.petclinic.model.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OwnerClient extends BaseClient {

    public Response createOwnerResponse(Owner owner) {
        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .body(owner)
                .when()
                .post("api/owners");
    }

    public Response getOwnerByIdResponse(Long id) {
        return getBasicRestConfig()
                .pathParam("id", id)
                .get("api/owners/{id}");
    }

    public Response getOwnerListResponse() {
        return getBasicRestConfig()
                .get("api/owners/");
    }

    public Response deleteOwnerByIdResponse(Long id) {
        return getBasicRestConfig()
                .pathParam("id", id)
                .delete("api/owners/{id}");
    }

    public Response updateOwnerByIdResponse(Long id, Owner owner) {
        return getBasicRestConfig()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(owner)
                .put("api/owners/{id}");
    }
}
