package Api.models.Users;

import groovy.lang.GString;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.RandomDataUtil;

import static io.restassured.RestAssured.given;

public class UpdateUser {

    public static String baseURI =AddUser.BASE_URI;
    public static String accessToken =AddUser.getAccessToken();
    public static String UserId=AddUser.getUserId();
    public static  String randomUserName =AddUser.getRandomUserName() ;
    public static String randomPassword =AddUser.getRandomPassword() ;
    public static String randomEmail =AddUser.getRandomEmail() ;
    public static String randomPhone =AddUser.getRandomPhone() ;
    public static  String randomFullName =AddUser.getRandomFullName() ;
    public static String randomFullNameAr =AddUser.getRandomFullNameAr() ;
    public static String randomBirthDate =AddUser.getRandomBirthDate() ;
    public static   String refreshToken =AddUser.getRefreshToken();
    public static String randomImage =AddUser.getRandomImage() ;
    public static String randomNationalNo =AddUser.getRandomNationalNo() ;
    public static void UpdateCreatedUser() {


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

        RestAssured.baseURI = baseURI;
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
public static void UpdateStatus(){



    RestAssured.baseURI = baseURI;

    // Make the PUT request
    Response response = RestAssured
            .given()
            .log().all()
            .header("language", "ar")
            .header("Accept", "text/plain")
            .header("Authorization", "Bearer " +accessToken)
            .header("Cookie", "refreshToken=" +refreshToken)
            .when()
            .put("/api/users/UpdateStatus?Id="+UserId);
    response.then().statusCode(200);
    // Print the response status and body
    System.out.println("Response Status Code: " + response.getStatusCode());
    System.out.println("Response Body: " + response.getBody().asString());
}
public static void ChangePassword(){

    RestAssured.baseURI = baseURI;
    // Define the request body
    String requestBody = String.format("""
        {
          "userId": "%s",
          "oldPassword": "%s",
          "newPassword": "%s"
        }
        """, UserId, randomPassword, randomPassword);

    // Make the PUT request
    Response response = RestAssured
            .given()
            .log().all()
            .header("language", "ar")
            .header("Content-Type", "application/json")
            .header("Accept", "text/plain")
            .header("Authorization", "Bearer " +accessToken)
            .header("Cookie", "refreshToken=" +refreshToken)
            .body(requestBody)
            .when()
            .put("/api/users/ChangePassword");

    // Print the response status and body
    System.out.println("Response Status Code: " + response.getStatusCode());
    System.out.println("Response Body: " + response.getBody().asString());



}
}
