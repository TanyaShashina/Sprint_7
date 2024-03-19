import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import REQ_function.courierLogReq;
import REQ_function.courierCreateReq;
import RESP_function.courierLogResp;

import static API_function.constant.*;
import static org.junit.Assert.*;

import static API_function.courier.*;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;

@RunWith(Parameterized.class)
public class Courier_LogWOParamTest extends BasikTest{

    private final String login;
    private final String password;
    private final String firstName = RandomStringUtils.randomNumeric(5);

    public Courier_LogWOParamTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters()
    public static Object[][] switchBetweenParamsCourierLogin() {
        return new Object[][]{
                //Запрос с пустым логином
                {null, "MeGaPaSs123" + RandomStringUtils.randomNumeric(3)},
                //Запрос с пустым паролем
                {"courierlogin" + RandomStringUtils.randomAlphabetic(2), ""},
                //полностью пустая пара
                {null, ""}
        };
    }

    @Test
    @DisplayName("Метод для проверки невозможности авторизации курьера без одного из параметров ")
    @Description("На вход передается два набора параметров с пустым логином и паролем и проверяет корректность ответа метода")
    public void checkCourierLoginWithoutSomeParams() {
        courierCreateReq parametrizedCourierCreateReq = new courierCreateReq(login,password,firstName);
        courierLogReq parametrizedCourierLoginReq = new courierLogReq(
                parametrizedCourierCreateReq.getLogin(),
                parametrizedCourierCreateReq.getPassword());


        Response response = courierLogin(parametrizedCourierLoginReq);
        response.then()
                .assertThat()
                .statusCode(SC_BAD_REQUEST); // status code 400

        assertEquals(
                "Сообщение об ошибке отличается",
                COURIER_LOGIN_BAD_REQ,
                response.as(courierLogResp.class).getMessage());

    }

}