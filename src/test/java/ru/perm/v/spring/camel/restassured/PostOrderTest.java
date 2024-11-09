package ru.perm.v.spring.camel.restassured;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PostOrderTest")
class PostOrderTest {
    private static final String ADD_ORDER_PATH = VARS.HOST + "/addOrder";

    @BeforeEach
    void resetDB() {
        given().when().get(VARS.HOST + "/reset_db");
    }

    @Test
    void postOkWithCheckRestAssuredBody() {
        OrderDTO orderDto = new OrderDTO(100, "ORDER_100", 6700);
        ValidatableResponse result = given().log().body().contentType("application/json").body(orderDto)
                .when().post(ADD_ORDER_PATH).then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .and().body("id", equalTo(100))
                .and().body("name", equalTo("ORDER_100"))
                .and().body("price", equalTo(6700.00F));
    }

    @Test
    void postOkTestTraditional() {
        OrderDTO orderDto = new OrderDTO(100, "ORDER_100", 6700);
        ValidatableResponse result = given().log().body().contentType("application/json").body(orderDto)
                .when().post(ADD_ORDER_PATH).then().log().body();

        // SECOND variant checks
        assertEquals(HttpStatus.SC_OK, result.extract().statusCode());

        String resultBody = result.extract().body().asString();
        ObjectMapper mapper = new ObjectMapper();
        OrderDTO receivedOrderDTO = null;
        try {
            receivedOrderDTO = mapper.readValue(resultBody, OrderDTO.class);
        } catch (JsonProcessingException e) {
            fail();
        }
        assertEquals("{\"id\":100,\"name\":\"ORDER_100\",\"price\":6700.0}", resultBody);
        assertEquals(new OrderDTO(100, "ORDER_100", 6700), receivedOrderDTO);
    }

    @Test
    void postWithNotCorrectedPrice() {
        OrderDTO orderDto = new OrderDTO(100, "ORDER_100", -100);

        Response result = given().log().body().contentType("application/json").body(orderDto)
                .when().post(ADD_ORDER_PATH);

        String s = result.getBody().asString();
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertTrue(s.contains("Price must be higher than 1"));
    }

    @Test
    void postWithNotCorrectedEmptyName() {
        OrderDTO orderDto = new OrderDTO(10, "", 100);

        Response result = given().log().body().contentType("application/json").body(orderDto)
                .when().post(ADD_ORDER_PATH);

        String s = result.getBody().asString();
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertTrue(s.contains("The name must be longer than 5 characters"));
    }

    @Test
    void postWithNotCorrectedShortName() {
        OrderDTO orderDto = new OrderDTO(10, "ABC", 100);

        Response result = given().log().body().contentType("application/json").body(orderDto)
                .when().post(ADD_ORDER_PATH);

        String s = result.getBody().asString();
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertTrue(s.contains("The name must be longer than 5 characters"));
    }
}
