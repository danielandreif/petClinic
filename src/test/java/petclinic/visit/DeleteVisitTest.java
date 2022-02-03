package petclinic.visit;

import com.petclinic.model.Owner;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import petclinic.BaseClassTest;

public class DeleteVisitTest extends BaseClassTest {

    @Test
    public void shouldDeleteVisit() {
        fixture.createOwner()
                .createPetType()
                .createPet();

        var visit = fixture.createVisit().getVisit();

        Response response = visitClient.createVisitResponse(visit);
        response.then().statusCode(HttpStatus.SC_CREATED);

        Long visitId = response.body().jsonPath().getLong("id");

        Response delResponse = visitClient.deleteVisitResponse(visitId);
        delResponse.then().statusCode(HttpStatus.SC_NO_CONTENT);

    }

    @Test
    public void shouldNotDeleteNonExistantVisitId() {
        fixture.createOwner()
                .createPetType()
                .createPet();

        var visit = fixture.createVisit().getVisit();

        Response response = visitClient.createVisitResponse(visit);
        response.then().statusCode(HttpStatus.SC_CREATED);

        Long visitId = response.body().jsonPath().getLong("id");
        Response delOneResponse = visitClient.deleteVisitResponse(visitId);
        delOneResponse.then().statusCode(HttpStatus.SC_NO_CONTENT);

        Response delTwoResponse = visitClient.deleteVisitResponse(visitId);
        delTwoResponse.then().statusCode(HttpStatus.SC_NOT_FOUND);

    }
}
