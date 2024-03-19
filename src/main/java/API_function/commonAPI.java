package API_function;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class commonAPI {
    public static Response comReq(Object body,String endpoint){
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint);
    }
    public static Response delReq(String endpoint){
        return given()
                .when()
                .delete(endpoint);
    }
    public static Response getReq(Map<String, Object> map, String endpoint){
        return given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .get(endpoint);
    }
}
