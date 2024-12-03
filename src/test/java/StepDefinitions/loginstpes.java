package StepDefinitions;



import Pages.LoginPage;

import io.cucumber.java.en.Given;



import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class loginstpes {


    private WebDriver driver = managers.DriverManager.getDriver();



    @Given("admin login with valid username and password")
    public void user_enters_valid_username_and_password() {

        driver.get("https://sang.greenboard.online/login");
        LoginPage login=new LoginPage(driver);
        login.performLogin("admin@greenboard.com","string");




    }


    @Given("a")
    public void a() {
System.out.println("mostafa");





    }








}
