package StepDefinitions;


import Api.models.Users.AddUser;
import Api.models.Users.DeleteUser;
import Api.models.Users.GetUser;
import Api.models.Users.UpdateUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class UserApis {


    @Given("I authenticate with valid credentials")
    public void i_authenticate_with_valid_credentials() {
        // Write code here that turns the phrase above into concrete actions
       AddUser.authenticate();

    }

    @When("I create a new user with random data")
    public void i_create_a_new_user_with_random_data() {
        // Write code here that turns the phrase above into concrete actions
        AddUser.createUser();

    }
    @When("I verify the created user exists")
    public void I_verify_the_created_user_exists() {

        GetUser.validateCreatedUser();


    }
    @When("I verify Update created user")
    public void I_verify_Update_created_user() {
        // Write code here that turns the phrase above into concrete actions

   UpdateUser.UpdateCreatedUser();



    }
    @When("verify Update Status")
    public void verify_update_status() {
        // Write code here that turns the phrase above into concrete actions
        UpdateUser.UpdateStatus();

    }

    @When("Verify that the password has been changed")
    public void verify_that_the_password_has_been_changed() {
        // Write code here that turns the phrase above into concrete actions
        UpdateUser.ChangePassword();
    }
    @When("Delete Created User")
    public void Delete_Created_Users() {
        // Write code here that turns the phrase above into concrete actions


        DeleteUser.DeleteCreatedUser();


    }
    @When("Verify Delete Created User")
    public void Verify_Delete_Created_Users() {
        // Write code here that turns the phrase above into concrete actions

        DeleteUser.verifyDeleteCreatedUser();

    }
    @When("Verify My Profile")
    public void verify_my_profile() {
        // Write code here that turns the phrase above into concrete actions
        GetUser.GetMyProfile();
    }
}
