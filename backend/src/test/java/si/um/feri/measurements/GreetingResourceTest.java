package si.um.feri.measurements;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {
    @Test
    /**
     * Tests the "/hello" endpoint of the RESTEasy Reactive application.
     *
     * This method sends a GET request to the "/hello" endpoint and verifies that
     * the response status code is 200 (OK) and that the response body matches
     * the expected string "Hello from RESTEasy Reactive".
     *
     * @throws AssertionError if the response status code is not 200 or if the
     *         response body does not match the expected value.
     */
    void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }

}