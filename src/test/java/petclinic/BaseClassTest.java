package petclinic;

import com.petclinic.client.OwnerClient;
import com.petclinic.client.PetClient;
import com.petclinic.client.PetTypeClient;
import com.petclinic.client.VisitClient;
import com.petclinic.extensions.TestReporterExtension;
import com.petclinic.fixture.PetClinicFixture;
import com.petclinic.services.DBService;
import com.petclinic.testData.TestDataProvider;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestReporterExtension.class)
public class BaseClassTest {

    protected OwnerClient client = new OwnerClient() {};
    protected PetClient petClient = new PetClient() {};
    protected TestDataProvider provider = new TestDataProvider();
    protected PetClinicFixture fixture = new PetClinicFixture();
    protected PetTypeClient petTypeClient = new PetTypeClient() {};
    protected VisitClient visitClient = new VisitClient() {};
    protected DBService db = new DBService();
}
