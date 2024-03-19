package API_function;

import REQ_function.orderCreateReq;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static API_function.commonAPI.comReq;
import static API_function.commonAPI.getReq;
import static API_function.constant.CREATE_ORDER_ENDPOINT;

public class order {
    @Step("Cоздание курьера")
    public static Response createOrder(orderCreateReq body){
        return comReq(body, CREATE_ORDER_ENDPOINT);
    }

    @Step("Получение даных по заказу")
    public static Response getOrder(Map<String, Object> body){
        return getReq(body, CREATE_ORDER_ENDPOINT);
    }
}
