package ru.perm.v.animals.restassured;

import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("GetAllOrdersTest")
class GetAllOrdersTest {

    private static String GET_ORDERS_PATH = VARS.HOST + "/getOrders";

    @Test
    void getAllOrdersStatus() {
        given().when().get(GET_ORDERS_PATH).then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    void getAllOrdersResultAsArray() {
        OrderDTO[] orders = given().when().get(GET_ORDERS_PATH).as(OrderDTO[].class);

        assertEquals(4, orders.length);
    }
    @Test
    void getAllOrdersResultAsList() {
        List<OrderDTO> orders = given().when().get(GET_ORDERS_PATH).jsonPath().getList(".", OrderDTO.class);

        assertEquals(4, orders.size());
    }

}
