package Api.models.Users;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class GetUser {
    public static String baseURI =AddUser.BASE_URI;
    public static   String accessToken =AddUser.getAccessToken();
    public static   String refreshToken =AddUser.getRefreshToken();
    public static String UserId=AddUser.getUserId();

    public  static void  getUsers() {


        RestAssured.baseURI = baseURI;
        // Make the GET request
        Response response = given()
                .log().all()
                .header("Authorization", "Bearer " + accessToken)
                .header("accept", "text/plain")
                .header("language", "en")
                .queryParam("IsActive", "true")
                .queryParam("PageNumber", "1")
                .queryParam("PageSize", "10")
                .when()
                .get("/api/users/search");
        response.then().statusCode(200);

        // Optionally: Assert that response body contains specific values
        // For example, checking if the response contains some expected keys
        response.then().body("result", notNullValue());

        // Print response details for debugging
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }
    public static void validateCreatedUser() {

        // Sending GET request
        Response response = RestAssured
                .given()
                .log().all()
                .header("language", "ar")
                .header("Accept", "text/plain")

                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/api/users/GetById?Id=" +UserId) // Dynamically inserting ID
                .then()
                .log().all() // Logs the response
                .extract()
                .response();

        // Print the response status code
        System.out.println("Status Code: " + response.getStatusCode());

        // Print the response body
        System.out.println("Response Body: " + response.getBody().asString());
    }
    public static void GetMyProfile() {
        RestAssured.baseURI = baseURI;

        // Send the request and get the response
        Response response = RestAssured
                .given()
                .log().all()
                .header("language", "ar")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + accessToken)
                .header("Cookie", "refreshToken="+ refreshToken)
                .when()
                .get("/api/users/MyProfile")
                .then()
                .log().all()  // Log all response details
                .extract()
                .response();
        response.then().statusCode(200);
        // Print the response
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

    }
}
