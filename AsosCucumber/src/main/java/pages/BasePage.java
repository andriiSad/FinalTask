package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitVisibilityOfElement(long timeToWait, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(timeToWait)).until(
                ExpectedConditions.visibilityOf(element));
    }

    public void waitElementToBeClickable(long timeToWait, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(timeToWait)).until(
                ExpectedConditions.elementToBeClickable(element));
    }

    public void clickButton(WebElement button) {
        button.click();
    }

    public void moveCursorToElement(WebElement webElement) {
        new Actions(driver).moveToElement(webElement).build().perform();
    }

    public void fillTextToInput(WebElement input, String text) {
        input.clear();
        input.sendKeys(text);
    }

    public void selectOptionByVisibleText(WebElement select, String textToBeVisible) {
        new Select(select).selectByVisibleText(textToBeVisible);
    }
}

