package ru.perm.v.animals.restassured;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        assertEquals(new OrderDTO(67, "Mobile", 6700), orders.get(0));
        assertEquals(new OrderDTO(68, "Book", 6800), orders.get(1));
        assertEquals(new OrderDTO(69, "AC", 6900), orders.get(2));
        assertEquals(new OrderDTO(70, "Shoes", 70000), orders.get(3));
    }

}
