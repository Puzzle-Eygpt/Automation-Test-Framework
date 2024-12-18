package Api.models;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.RandomDataUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserApisService {

    // Base URI and endpoints
    private static final String BASE_URI = "https://portal-api.greenboard.online";
    private static final String AUTH_ENDPOINT = "/api/auth/authenticate";
    private static final String USER_ENDPOINT = "/api/users";
    private static String randomUserName = RandomDataUtil.generateRandomUserName();
    private static  String randomPassword = RandomDataUtil.generateRandomPassword();
    private static  String randomEmail = RandomDataUtil.generateRandomEmail();
    private static  String randomPhone = RandomDataUtil.generateRandomPhone();
    private static String randomFullName = RandomDataUtil.generateRandomFullName();
    private static String randomFullNameAr = RandomDataUtil.generateRandomFullNameAr();
    private static String randomBirthDate = RandomDataUtil.generateRandomBirthDate();
    private static String randomImage = RandomDataUtil.generateRandomImage();
    private static String randomNationalNo = RandomDataUtil.generateRandomNationalNo();
    // Authentication credentials
    private static final String USERNAME = "sayed";
    private static final String PASSWORD = "Sayed@123456";
    private static String accessToken;
    private static String UserId;
    private static String refreshToken;
    // Method to authenticate and get the token





    // Method to create a user



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

    public static void UpdateCreatedUser() {
        RestAssured.baseURI =BASE_URI;

        // JSON body containing user data
        String requestBody = String.format("""
                {
                    "id":"%s",
                    "userName": "%s",
                    "passwordHash": "%s",
                    "email": "%s",
                    "phoneNumber": "%s",
                    "fullName": "%s",
                    "fullNameAr": "%s",
                    "birthDate": "%s",
                    "image": "%s",
                    "nationalNo": "%s"
                }
                """,UserId, randomUserName, randomPassword, randomEmail, randomPhone, randomFullName, randomFullNameAr, randomBirthDate, randomImage, randomNationalNo);


        Response response = given()
                .log().all()
                .header("language", "ar")
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("Authorization", "Bearer " + accessToken)

                .body(requestBody)
                .when()
                .put("/api/users/");
        response.then().statusCode(200);  // Replace with the expected status code

        // Assert response body (example)




        // Print status code and response body for debugging
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }

    public static void  DeleteCreatedUser() {

            // Set the base URI for the API
            RestAssured.baseURI = BASE_URI;



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
        RestAssured.baseURI = BASE_URI;


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

