package ru.perm.v.animals.restassured;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@DisplayName("GetAllOrdersTest")
class GetAllOrdersTest {

    private static String GET_ORDERS_PATH = VARS.HOST + "/getOrders";

    @Test
    void getAllOrdersStatus() {
        given().when().get(GET_ORDERS_PATH).then().statusCode(HttpStatus.SC_OK);
    }
}
