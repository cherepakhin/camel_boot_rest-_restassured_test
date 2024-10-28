package ru.perm.v.spring.camel.restassured;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("PostOrderTest")
public class PostOrderTest {
    private static final String ADD_ORDER_PATH = VARS.HOST + "/addOrder";

    @Test
    void postOk() {
        OrderDTO orderDto = new OrderDTO(100, "ORDER_100", 6700);
        ValidatableResponse result = given().log().body().contentType("application/json").body(orderDto)
                .when().post(ADD_ORDER_PATH).then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .and().body("id", equalTo(100))
                .and().body("name", equalTo("ORDER_100"))
                .and().body("price", equalTo(6700.00F));

//        assertEquals(HttpStatus.SC_OK, result.statusCode(HttpStatus.SC_OK));
//        String s = result.body().asString();
//        assertEquals("", s);
//        assertEquals(new OrderDTO(100, "ORDER_100", 6700), receivedDTO);
    }

    //TODO: POST verify price < 0
    //TODO: POST verify name is empty
}
