package petclinic.visit;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import petclinic.BaseClassTest;


public class GetVisitByIdTest extends BaseClassTest {

    @Test
    public void shouldGetVisitById() {

        fixture.createOwner()
                .createPetType()
                .createPet();

        var visit = fixture.createVisit().getVisit();

        Response response = visitClient.createVisitResponse(visit);
        response.then().statusCode(HttpStatus.SC_CREATED);

        Long visitId = response.body().jsonPath().getLong("id");
        Response getResponse = visitClient.getVisitResponse(visitId);
        getResponse.then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void shouldNotGetVisitedAfterDeleted() {
        fixture.createOwner()
                .createPetType()
                .createPet();

        var visit = fixture.createVisit().getVisit();

        Response response = visitClient.createVisitResponse(visit);
        response.then().statusCode(HttpStatus.SC_CREATED);

        Long visitId = response.body().jsonPath().getLong("id");

        Response delResponse = visitClient.deleteVisitResponse(visitId);
        delResponse.then().statusCode(HttpStatus.SC_NO_CONTENT);

        Response getResponse = visitClient.getVisitResponse(visitId);
        getResponse.then().statusCode(HttpStatus.SC_NOT_FOUND);

    }

}
