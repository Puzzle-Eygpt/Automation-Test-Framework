package Api.models;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class APIService {

    private static final String BASE_URI = "https://portal-api.greenboard.online/api";

    // Fetch roles from the API
    public static List<Map<String, Object>> fetchRoles() {
        RestAssured.baseURI = BASE_URI;
         String accessToken = UserApi.getAccessToken();
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .header("accept", "text/plain")
                .header("language", "en")
                .when()
                .get("/roles/GetRoleDropDown")
                .then()
                .statusCode(200) // Ensure the request was successful
                .extract()
                .response();

        // Parse the result field from the response
        List<Map<String, Object>> roles = response.jsonPath().getList("result");
        if (roles == null || roles.isEmpty()) {
            throw new RuntimeException("No roles were fetched from the server.");
        }
        return roles;
    }

}

