package StepDefinitions;



import Pages.LoginPage;
import io.cucumber.java.en.And;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class loginstpes {


    private WebDriver driver = managers.DriverManager.getDriver();


    @And("user is on login page")
    public void user_is_on_login_page() {
        driver.get("https://sang.greenboard.online/login");
System.out.println("mostafa");
    }
    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        LoginPage login=new LoginPage(driver);
        login.performLogin("admin@greenboard.com","string");

    }
    @When("clicks on login button")
    public void clicks_on_login_button() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("user is navigated to the home page")
    public void user_is_navigated_to_the_home_page() {
        // Write code here that turns the phrase above into concrete actions

    }










}
