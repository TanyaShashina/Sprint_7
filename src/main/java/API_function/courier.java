package API_function;

import REQ_function.courierLogReq;
import REQ_function.courierCreateReq;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static API_function.commonAPI.*;
import static API_function.constant.*;


public class courier {
    @Step("Создание курьера")
    public static Response createCourier(courierCreateReq body){
        return comReq(body, CREATE_COURIER_ENDPOINT);
    }

    @Step("Вход под логином и паролем курьера")
    public static Response courierLogin (courierLogReq body){
        return comReq(body, COURIER_LOGIN_ENDPOINT);
    }
    @Step("Удаление курьера")
    public static void delCourier (String id){
        delReq(DELETE_COURIER_ENDPOINT + id);
    }

}

