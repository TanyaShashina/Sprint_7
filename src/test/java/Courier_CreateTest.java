import RESP_function.courierCreateResp;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import REQ_function.courierLogReq;
import REQ_function.courierCreateReq;
import RESP_function.courierLogResp;

import static API_function.courier.*;
import static API_function.constant.*;
import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Courier_CreateTest extends BasikTest{
    private courierCreateReq createCourierReq;

    @Before
    public void createCourierForTest(){
        createCourierReq = new courierCreateReq(
                "testlogin" + RandomStringUtils.randomAlphabetic(2),
                "MeGaPaSs123" + RandomStringUtils.randomNumeric(3),
                "NamelessCourier" +RandomStringUtils.randomNumeric(3)
        );
    }

    @Test
    @DisplayName("Метод проверки создания курьера")
    @Description("Проверка вызова метода и корретности статускода (ожидается 201)")
    public void checkCreateCourierResponse201Test(){
        Response response = createCourier(createCourierReq);

        response.then()
                .assertThat()
                .statusCode(SC_CREATED);

        assertTrue("Ошибка при создании курьера",
                response.as(courierCreateResp.class).isOk());
    }

    @Test
    @DisplayName("Метод для проверки невозможности создания двух одинаковых курьеров")
    @Description("Метод проверяет, что невозможно создать два одинаковых курьера и корректность ответа метода")
    public void checkCreateCourierGeminiTest(){
        createCourier(createCourierReq);

        Response response = createCourier(createCourierReq);
        response.then()
                .assertThat()
                .statusCode(SC_CONFLICT); // status code 409

        assertEquals("Ожидается иной текст ошибки",
                COURIER_CREATE_GEMINI,
                response.as(courierLogResp.class).getMessage());
    }

    @After
    @DisplayName("Метод удаления курьера ")
    @Description("Удаляет созданного курьера после теста")
    public void deleteCourierAfterCreate(){
        Response response = courierLogin(
                new courierLogReq(
                        createCourierReq.getLogin(),
                        createCourierReq.getPassword()));
        delCourier(response.jsonPath().get("id").toString());

    }

}
