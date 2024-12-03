package utils;


import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


public class ElementAction {
    public WebDriver driver;
    public JavascriptExecutor js;

    public WebDriverWait wait;

    public ElementAction(WebDriver driver) {
        this.driver =driver;
        js =(JavascriptExecutor)driver;
        wait =new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void clickButton(WebElement button) {
        wait.until(ExpectedConditions.elementToBeClickable(button)); // Wait until the button is clickable
        button.click();
    }

    public void setTextElementText(WebElement textElement, String value) {
        wait.until(ExpectedConditions.visibilityOf(textElement)); // Wait until the element is visible
        textElement.sendKeys(value);
    }

    public void scrollToBottom() {
        js.executeScript("scrollBy(0,2500)");
    }

    public void scrollIntoViewElement(WebElement element) {

        js.executeScript("arguments[0].scrollIntoView();", element);
    }


}