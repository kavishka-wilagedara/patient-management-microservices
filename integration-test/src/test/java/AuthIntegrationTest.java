import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

class AuthIntegrationTest {

    @BeforeAll
    static void setup(){
        RestAssured.baseURI = "http://localhost:4004";
    }

    @Test
    void shouldReturnOKWithValidToken(){
//     1. arrange
//     2. act
//     3. assert
        String loginPayLoad = """
            {
                "email": "testuser@test.com",
                "password": "password123"
            }
         """;

        Response response = given()
                .contentType("application/json")
                .body(loginPayLoad)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .response();

        System.out.println("Generated code: " +response.jsonPath().getString("token"));
    }

    @Test
    void shouldReturnUnauthorizedOnInvalidLogin(){
        String loginPayLoad = """
            {
                "email": "invaliduser@test.com",
                "password": "wrongpassword"
            }
         """;

        given()
                .contentType("application/json")
                .body(loginPayLoad)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401);
    }
}
