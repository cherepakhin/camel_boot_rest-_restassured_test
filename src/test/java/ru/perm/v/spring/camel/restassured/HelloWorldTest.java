package ru.perm.v.spring.camel.restassured;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("HelloWorldTest")
class HelloWorldTest {

    private static String HELLO_WORLD_PATH = VARS.HOST + "/hello-world";

    @Test
    void helloWorldStatus() {
        given().when().get(HELLO_WORLD_PATH).then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    void helloWorldResult() {
        String result = given().when().get(HELLO_WORLD_PATH).then().extract().body().asString();
        assertEquals("\"Hello world\"", result);
    }

}
