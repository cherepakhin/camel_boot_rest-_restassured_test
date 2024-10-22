package ru.perm.v.animals.restassured;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

@DisplayName("HelloWorldTest")
public class HelloWorldTest {
    private static String HELLO_WORLD_PATH = VARS.HOST + "/hello-world";
    @Test
    void helloWorldStatus() {
        given().when().get(HELLO_WORLD_PATH).then().statusCode(HttpStatus.SC_OK);
    }
}
