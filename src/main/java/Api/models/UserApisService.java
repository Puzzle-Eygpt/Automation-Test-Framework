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
    public static void authenticate() {

        String authBody = String.format("{\n" +
                "  \"username\": \"%s\",\n" +
                "  \"password\": \"%s\",\n" +
                "  \"rememberMe\": true\n" +
                "}", USERNAME, PASSWORD);

        Response authResponse = given()

                .header("accept", "text/plain")
                .header("language", "en")
                .contentType(ContentType.JSON)
                .body(authBody)
                .post(BASE_URI+AUTH_ENDPOINT)
                .then()
                .log().all() // Logs the entire response
                .extract()
                .response();

        if (authResponse.statusCode() == 200) {
            accessToken = authResponse.jsonPath().getString("result.access_token");
            refreshToken = authResponse.jsonPath().getString("result.refresh_token");

            System.out.println("Access Token: " + accessToken);
            System.out.println("Refresh Token: " + refreshToken);

        } else {
            throw new RuntimeException("Authentication failed. Status Code: " + authResponse.statusCode() +
                    ", Response Body: " + authResponse.getBody().asString());
        }

    }


    // Method to generate request body by replacing placeholders with random data
    private static String generateRequestBody() throws IOException {
        // Read JSON template from file
        String requestBodyTemplate = new String(Files.readAllBytes(Paths.get("src/main/java/Api/builders/usersbody.json")));

        // Generate random data





        // Replace placeholders in the template with actual random values
        String requestBody = requestBodyTemplate

                .replace("{randomUserName}", randomUserName)
                .replace("{randomPassword}", randomPassword)
                .replace("{randomEmail}", randomEmail)
                .replace("{randomPhone}", randomPhone)
                .replace("{randomFullName}", randomFullName)
                .replace("{randomFullNameAr}", randomFullNameAr)
                .replace("{randomBirthDate}", randomBirthDate)
                .replace("{randomImage}", randomImage)
                .replace("{randomNationalNo}", randomNationalNo);


        return requestBody;
    }

    // Method to create a user
    public static void createUser() {
        RestAssured.baseURI = BASE_URI;

        try {






            // Step 1: Generate the request body with random data
            String requestBody = generateRequestBody();

            // Step 2: Perform the POST request to create a new user
            Response response = given()
                    .header("Authorization", "Bearer " + accessToken)
                    .header("accept", "text/plain")
                    .header("language", "ar")
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post(BASE_URI+USER_ENDPOINT) .then()
                    .log().all() // Logs the entire response
                    .extract()
                    .response();


            // Step 3: Log the response for debugging purposes


            // Handle different response statuses
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.asString());
             UserId = response.jsonPath().getString("result.id");
            System.out.println("user id: " + UserId);
            if (response.jsonPath().getBoolean("isSuccess")) {

            } else {
                String errorMessage = response.jsonPath().getString("message");
                System.err.println("User creation failed. Reason: " + errorMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  static void  getUsers() {
        RestAssured.baseURI =BASE_URI;
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



//    public static void validateCreatedUser(Response response) {
//        // Validate the response
//        response.then()
//                .statusCode(200) // Ensure the status code is 200
//                .body("data", notNullValue()) // Ensure "data" field is present
//                .body("data.size()", greaterThan(0)); // Ensure there are users in the data
//    }

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

