package Api.models;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.RandomDataUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class UserApi {

    // Base URI and endpoints
    private static final String BASE_URI = "https://portal-api.greenboard.online";
    private static final String AUTH_ENDPOINT = "/api/auth/authenticate";
    private static final String USER_ENDPOINT = "/api/users";

    // Authentication credentials
    private static final String USERNAME = "sayed";
    private static final String PASSWORD = "Lms@123456";

    // Method to authenticate and get the token
    public static String authenticate() {
        String authBody = String.format("{\n" +
                "  \"username\": \"%s\",\n" +
                "  \"password\": \"%s\",\n" +
                "  \"rememberMe\": true\n" +
                "}", USERNAME, PASSWORD);

        Response authResponse = given()
                .log().all()
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
            return authResponse.jsonPath().getString("token");
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
        String randomId = RandomDataUtil.generateRandomId();
        String randomUserName = RandomDataUtil.generateRandomUserName();
        String randomPassword = RandomDataUtil.generateRandomPassword();
        String randomEmail = RandomDataUtil.generateRandomEmail();
        String randomPhone = RandomDataUtil.generateRandomPhone();
        String randomFullName = RandomDataUtil.generateRandomFullName();
        String randomFullNameAr = RandomDataUtil.generateRandomFullNameAr();
        String randomBirthDate = RandomDataUtil.generateRandomBirthDate();
        String randomImage = RandomDataUtil.generateRandomImage();
        String randomNationalNo = RandomDataUtil.generateRandomNationalNo();
        String randomCreatedDate = RandomDataUtil.generateRandomCreatedDate();
        String randomModifiedDate = RandomDataUtil.generateRandomModifiedDate();
        String randomUserId = RandomDataUtil.generateRandomUserId();
        String randomRoleId = RandomDataUtil.generateRandomRoleId();
        String randomRole = RandomDataUtil.generateRandomRole();

        // Replace placeholders in the template with actual random values
        String requestBody = requestBodyTemplate
                .replace("{randomId}", randomId)
                .replace("{randomUserName}", randomUserName)
                .replace("{randomPassword}", randomPassword)
                .replace("{randomEmail}", randomEmail)
                .replace("{randomPhone}", randomPhone)
                .replace("{randomFullName}", randomFullName)
                .replace("{randomFullNameAr}", randomFullNameAr)
                .replace("{randomBirthDate}", randomBirthDate)
                .replace("{randomImage}", randomImage)
                .replace("{randomNationalNo}", randomNationalNo)
                .replace("{randomCreatedDate}", randomCreatedDate)
                .replace("{randomModifiedDate}", randomModifiedDate)
                .replace("{randomUserId}", randomUserId)
                .replace("{randomRoleId}", randomRoleId)
                .replace("{randomRole}", randomRole);

        return requestBody;
    }

    // Method to create a user
    public static void createUser() {
        RestAssured.baseURI = BASE_URI;

        try {
            // Step 1: Authenticate and get the token
            String token = authenticate();
            System.out.println("Token: " + token);

            // Step 2: Generate the request body with random data
            String requestBody = generateRequestBody();

            // Step 3: Perform the POST request to create a new user
            Response response = given()
                    .log().all()
                    .header("Authorization", "Bearer " + token)
                    .header("accept", "text/plain")
                    .header("language", "ar")
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post(USER_ENDPOINT) .then()
                    .log().all() // Logs the entire response
                    .extract()
                    .response();


            // Step 4: Log the response for debugging purposes


            // Handle different response statuses
            if (response.statusCode() != 201) {
                System.out.println("User creation failed. Status Code: " + response.statusCode());
            } else {
                System.out.println("User created successfully!");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to read or process the request body.");
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Authentication failed.");
        }
    }
}
