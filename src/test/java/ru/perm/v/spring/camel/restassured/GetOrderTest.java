package ru.perm.v.spring.camel.restassured;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("GetOrderTest")
class GetOrderTest {
    private static final String GET_ORDER_PATH = VARS.HOST + "/getOrder";

    @BeforeEach
    void resetDB() {
        given().when().get(VARS.HOST + "/reset_db");
    }

    @Test
    void getOrderByIdStatusOk() {
        String ID_ORDER = "70";
        given().when().get(GET_ORDER_PATH + "/" + ID_ORDER).then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    void getOrderByIdStatusNotFound() {
        String NOT_EXIST_ID_ORDER = "-100";
        given().when().get(GET_ORDER_PATH + "/" + NOT_EXIST_ID_ORDER).then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    void getOrderByIdCheckBody() {
        String ID_ORDER = "70";
        OrderDTO orderDTO = given().when().get(GET_ORDER_PATH + "/" + ID_ORDER).as(OrderDTO.class);

        assertEquals(new OrderDTO(70, "Shoes", 70000), orderDTO);
    }


}
