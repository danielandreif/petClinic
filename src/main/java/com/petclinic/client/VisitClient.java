package com.petclinic.client;

import com.petclinic.model.PetType;
import com.petclinic.model.Visit;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class VisitClient extends BaseClient {

    public Response createVisitResponse(Visit visit) {
        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .body(visit)
                .when()
                .post("api/visits");
    }

    public Response createVisitResponse(Visit visit, Visit visittwo) {
        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .body(visit)
                .when()
                .post("api/visits");
    }

    public Response getVisitResponse(Long id) {
        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .get("api/visits/{id}");
    }

    public Response deleteVisitResponse(Long id) {
        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .delete("api/visits/{id}");
    }

    public Response getVisitListResponse() {
        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .get("api/visits/");
    }


}
