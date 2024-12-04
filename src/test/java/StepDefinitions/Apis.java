package StepDefinitions;

import Api.models.UserApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import utils.RandomDataUtil;

public class Apis {


    private String token;
    @Given("I authenticate with valid credentials")
    public void i_authenticate_with_valid_credentials() {
        token = UserApi.authenticate();
        Assert.assertNotNull(token, "Authentication token should not be null");
        System.out.println("Authentication token: " + token);
    }

    @When("I create a new user with random data")
    public void i_create_a_new_user_with_random_data() {
        // Write code here that turns the phrase above into concrete actions
        UserApi.createUser();

    }


}
