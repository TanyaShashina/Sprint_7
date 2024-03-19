import static API_function.constant.*;
import io.restassured.RestAssured;
import org.junit.Before;
public class BasikTest {
    @Before
    public void setUP(){
        RestAssured.baseURI = BASE_URI;
    }
}
