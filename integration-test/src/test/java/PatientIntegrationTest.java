import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

class PatientIntegrationTest {

    @BeforeAll
    static void setup(){
        RestAssured.baseURI = "http://localhost:4004";
    }

    @Test
    void shouldReturnPatientsWithValidToken(){
//     1. arrange
//     2. act
//     3. assert
        String loginPayLoad = """
            {
                "email": "testuser@test.com",
                "password": "password123"
            }
         """;

        String token = given()
                .contentType("application/json")
                .body(loginPayLoad)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("token");

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/patients")
                .then()
                .statusCode(200)
                .body("patients", notNullValue());

        System.out.println("Generated token: " + token);
    }
}
