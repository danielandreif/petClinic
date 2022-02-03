package petclinic.visit;

import com.petclinic.model.Pet;
import com.petclinic.model.Visit;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import petclinic.BaseClassTest;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.is;

public class GetVisitListTest extends BaseClassTest {


    @Test
    public void shouldGetValidVisitList() {

        fixture.createOwner()
                .createPetType()
                .createPet();

        Visit visit = fixture.createVisit().getVisit();

        var response = visitClient.createVisitResponse(visit);
        response.then().statusCode(HttpStatus.SC_CREATED);

        Long visitId = response.body().jsonPath().getLong("id");

        Response listResponse = visitClient.getVisitListResponse();
        listResponse.then()
                .statusCode(HttpStatus.SC_OK)
                .body("find{it -> it.id == %s}.date", withArgs(visitId), is(visit.getDate()));
    }
}
