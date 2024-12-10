package StepDefinitions;

import Api.models.UserApisService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class UserApis {


    @Given("I authenticate with valid credentials")
    public void i_authenticate_with_valid_credentials() {
        // Write code here that turns the phrase above into concrete actions
        UserApisService.authenticate();

    }

    @When("I create a new user with random data")
    public void i_create_a_new_user_with_random_data() {
        // Write code here that turns the phrase above into concrete actions
        UserApisService.createUser();

    }
    @When("I verify the created user exists")
    public void I_verify_the_created_user_exists() {
        // Write code here that turns the phrase above into concrete actions
        UserApisService.getUsers();
        UserApisService.validateCreatedUser();



    }
    @When("I verify Update created user")
    public void I_verify_Update_created_user() {
        // Write code here that turns the phrase above into concrete actions

        UserApisService.UpdateCreatedUser();


    }
    @When("Delete Created User")
    public void Delete_Created_Users() {
        // Write code here that turns the phrase above into concrete actions


        UserApisService.DeleteCreatedUser();


    }
    @When("Verify Delete Created User")
    public void Verify_Delete_Created_Users() {
        // Write code here that turns the phrase above into concrete actions

        UserApisService.verifyDeleteCreatedUser();

    }
}
