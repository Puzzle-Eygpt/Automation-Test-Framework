package Api.models.Users;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.RandomDataUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class AddUser {
    public static final String BASE_URI = "https://portal-api.greenboard.online";
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
    public static String getRandomUserName() {
        return randomUserName;
    }

    public static void setRandomUserName(String randomUserName) {
        AddUser.randomUserName = randomUserName;
    }

    public static String getRandomPassword() {
        return randomPassword;
    }

    public static void setRandomPassword(String randomPassword) {
        AddUser.randomPassword = randomPassword;
    }

    public static String getRandomEmail() {
        return randomEmail;
    }

    public static void setRandomEmail(String randomEmail) {
        AddUser.randomEmail = randomEmail;
    }

    public static String getRandomPhone() {
        return randomPhone;
    }

    public static void setRandomPhone(String randomPhone) {
        AddUser.randomPhone = randomPhone;
    }

    public static String getRandomFullName() {
        return randomFullName;
    }

    public static void setRandomFullName(String randomFullName) {
        AddUser.randomFullName = randomFullName;
    }

    public static String getRandomFullNameAr() {
        return randomFullNameAr;
    }

    public static void setRandomFullNameAr(String randomFullNameAr) {
        AddUser.randomFullNameAr = randomFullNameAr;
    }

    public static String getRandomBirthDate() {
        return randomBirthDate;
    }

    public static void setRandomBirthDate(String randomBirthDate) {
        AddUser.randomBirthDate = randomBirthDate;
    }

    public static String getRandomImage() {
        return randomImage;
    }

    public static void setRandomImage(String randomImage) {
        AddUser.randomImage = randomImage;
    }

    public static String getRandomNationalNo() {
        return randomNationalNo;
    }

    public static void setRandomNationalNo(String randomNationalNo) {
        AddUser.randomNationalNo = randomNationalNo;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        AddUser.accessToken = accessToken;
    }

    public static String getUserId() {
        return UserId;
    }

    public static void setUserId(String userId) {
        AddUser.UserId = userId;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }

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
}
