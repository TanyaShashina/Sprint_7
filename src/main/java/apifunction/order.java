package apifunction;

import reqfunction.orderCreateReq;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static apifunction.commonAPI.comReq;
import static apifunction.commonAPI.getReq;
import static apifunction.constant.CREATE_ORDER_ENDPOINT;

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
