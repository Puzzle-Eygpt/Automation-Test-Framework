package StepDefinitions;

import Api.models.APIService;
import Api.models.UserApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.RandomDataUtil;

public class Apis {






    @Given("I authenticate with valid credentials")
    public void i_authenticate_with_valid_credentials() {
        // Write code here that turns the phrase above into concrete actions
        UserApi.authenticate();

    }

    @When("I create a new user with random data")
    public void i_create_a_new_user_with_random_data() {
        // Write code here that turns the phrase above into concrete actions
        UserApi.createUser();




    }
    @When("I verify the created user exists")
    public void I_verify_the_created_user_exists() {
        // Write code here that turns the phrase above into concrete actions
        UserApi.getCreatedUser();




    }

}
