package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementAction;

public class LoginPage {
WebDriver driver;
    ElementAction Action=new ElementAction(driver);
    private By usernameField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[@type=\"submit\"]");
//private By courses =By.xpath()

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Fluent methods
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);

    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);

    }

    public void clickLogin() {
        driver.findElement(loginButton).click();

    }

    public void performLogin(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        Action.clickButton(driver.findElement(loginButton));

    }
}
