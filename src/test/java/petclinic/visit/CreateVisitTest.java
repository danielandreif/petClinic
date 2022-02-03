package petclinic.visit;

import com.petclinic.model.Visit;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import petclinic.BaseClassTest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class CreateVisitTest extends BaseClassTest {

    @Test
    public void shouldCreateVisitGivenValidData() {
        fixture.createOwner()
                .createPetType()
                .createPet();

        Visit visit = fixture.createVisit().getVisit();

        var response = visitClient.createVisitResponse(visit);
        response.then().statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void shouldNotCreateVisitGivenEmptyDescription() {
        fixture.createOwner()
                .createPetType()
                .createPet();

        var visit = fixture.createVisit().getVisit();
        visit.setDescription("");

        Response response = visitClient.createVisitResponse(visit);
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldNotCreateVisitGivenEmptyDate() {
        fixture.createOwner()
                .createPetType()
                .createPet();

        var visit = fixture.createVisit().getVisit();
        visit.setDate("");

        Response response = visitClient.createVisitResponse(visit);
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldCreateMultipleVisitsForPet() {
        fixture.createOwner()
                .createPetType()
                .createPet();

        Visit visit = fixture.createVisit().getVisit();
        Visit secondVisit = fixture.createVisit().getVisit();

        Integer firstVisitId = Math.toIntExact(visit.getId());
        Integer secondVisitId = Math.toIntExact(secondVisit.getId());

        var response = visitClient.createVisitResponse(visit, secondVisit);
        response.then().statusCode(HttpStatus.SC_CREATED);

        Response listResponse = visitClient.getVisitListResponse();
        List<Integer> jsonResponse = listResponse.jsonPath().getList("id");
        assertThat( jsonResponse, hasItems(firstVisitId,secondVisitId));
    }
}
