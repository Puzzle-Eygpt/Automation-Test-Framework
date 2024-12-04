package StepDefinitions;



import Api.models.UserApi;
import Pages.LoginPage;

import io.cucumber.java.en.Given;



import org.openqa.selenium.WebDriver;


public class AddCategories {


    private WebDriver driver = managers.DriverManager.getDriver();



    @Given("admin login with valid username and password")
    public void user_enters_valid_username_and_password() {

//        driver.get("https://sang.greenboard.online/login");
//        LoginPage login=new LoginPage(driver);
//        login.performLogin("admin@greenboard.com","string");





    }




    @Given("admin in categories page")
    public void admin_in_categories_page() {
        // Write code here that turns the phrase above into concrete actions
        UserApi.authenticate();
        UserApi.createUser();

    }





}
