package StepDefinitions;



import Pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class loginstpes {


    private WebDriver driver = hooks.Hooks.getDriver();



    @Given("admin login with valid username and password")
    public void user_enters_valid_username_and_password() {

        driver.get("https://sang.greenboard.online/login");
        LoginPage login=new LoginPage(driver);
        login.performLogin("admin@greenboard.com","string");

        String expectedText = "Successfully Updated";
        Assert.assertEquals(text,expectedText);

    }











}
