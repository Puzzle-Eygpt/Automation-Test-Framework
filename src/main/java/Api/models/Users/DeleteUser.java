package Api.models.Users;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteUser {

    public static String baseURI =AddUser.BASE_URI;
    public static   String accessToken =AddUser.getAccessToken();
    public static String UserId=AddUser.getUserId();
    public static void  DeleteCreatedUser() {
//        String baseURI =AddUser.BASE_URI;

        // Set the base URI for the API
        RestAssured.baseURI = baseURI;



        // Perform the DELETE request
        Response response = given()
                .log().all()
                .header("language", "ar")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete("/api/users?id=" + UserId);  // Pass the userId as a query parameter

        // Print the status code and response body
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Assert the response (e.g., expect a 200 status code)
        response.then().statusCode(200);  // Adjust based on expected response code

    }
    public static void  verifyDeleteCreatedUser() {

        // Set the base URI for the API
        RestAssured.baseURI =  baseURI;


        Response getResponse= RestAssured
                .given()
                .log().all()
                .header("language", "ar")
                .header("Accept", "text/plain")

                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/api/users/GetById?Id=" +UserId) // Dynamically inserting ID
                .then()
                .statusCode(200) // Assuming that user info is still returned even after deletion, so expect a 200 status
                .body("result.isDeleted", equalTo(true)) // Assert that the user is marked as deleted
                .extract()
                .response();

        // Print the response body for debugging
        System.out.println("Get Response after deletion: " + getResponse.asString());


    }
}
