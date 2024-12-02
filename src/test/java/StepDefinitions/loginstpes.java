package StepDefinitions;



import Pages.LoginPage;

import io.cucumber.java.en.When;


import org.openqa.selenium.WebDriver;



public class loginstpes {


    private WebDriver driver = managers.DriverManager.getDriver();



    @When("admin login username and password")
    public void user_enters_valid_username_and_password() {

        driver.get("https://sang.greenboard.online/login");
        LoginPage login=new LoginPage(driver);
        login.performLogin("admin@greenboard.com","string");

    }











}
