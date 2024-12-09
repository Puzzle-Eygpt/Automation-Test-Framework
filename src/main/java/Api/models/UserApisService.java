package Api.models;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.RandomDataUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

import static io.restassured.RestAssured.given;

public class UserApisService {

    // Base URI and endpoints
    private static final String BASE_URI = "https://portal-api.greenboard.online";
    private static final String AUTH_ENDPOINT = "/api/auth/authenticate";
    private static final String USER_ENDPOINT = "/api/users";

    // Authentication credentials
    private static final String USERNAME = "sayed";
    private static final String PASSWORD = "Sayed@123456";
    private static String accessToken;
    private static String id;
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

    public static String getAccessToken() {
        if (accessToken == null || accessToken.isEmpty()) {
            authenticate(); // Re-authenticate if the token is null or empty
        }
        return accessToken;
    }

    // Getter for the saved refresh token
    public static String getRefreshToken() {
        if (refreshToken == null || refreshToken.isEmpty()) {
            authenticate(); // Re-authenticate if necessary
        }
        return refreshToken;
    }
    // Method to generate request body by replacing placeholders with random data
    private static String generateRequestBody() throws IOException {
        // Read JSON template from file
        String requestBodyTemplate = new String(Files.readAllBytes(Paths.get("src/main/java/Api/builders/usersbody.json")));

        // Generate random data

        String randomUserName = RandomDataUtil.generateRandomUserName();
        String randomPassword = RandomDataUtil.generateRandomPassword();
        String randomEmail = RandomDataUtil.generateRandomEmail();
        String randomPhone = RandomDataUtil.generateRandomPhone();
        String randomFullName = RandomDataUtil.generateRandomFullName();
        String randomFullNameAr = RandomDataUtil.generateRandomFullNameAr();
        String randomBirthDate = RandomDataUtil.generateRandomBirthDate();
        String randomImage = RandomDataUtil.generateRandomImage();
        String randomNationalNo = RandomDataUtil.generateRandomNationalNo();



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
            // Step 1: Authenticate and get the token





            // Step 2: Generate the request body with random data
            String requestBody = generateRequestBody();

            // Step 3: Perform the POST request to create a new user
            Response response = given()
                    .log().all()
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


            // Step 4: Log the response for debugging purposes


            // Handle different response statuses
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.asString());

            if (response.jsonPath().getBoolean("isSuccess")) {

            } else {
                String errorMessage = response.jsonPath().getString("message");
                System.err.println("User creation failed. Reason: " + errorMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  static void  getCreatedUser() {
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



    public static void validateCreatedUser(Response response) {
        // Validate the response
        response.then()
                .statusCode(200) // Ensure the status code is 200
                .body("data", notNullValue()) // Ensure "data" field is present
                .body("data.size()", greaterThan(0)); // Ensure there are users in the data
    }
}
