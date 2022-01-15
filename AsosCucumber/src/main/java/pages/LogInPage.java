package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage {
    @FindBy(xpath = "//input[@name = 'Username']")
    private WebElement userNameInput;

    @FindBy(xpath = "//input[@name = 'Password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id = 'signin']")
    private WebElement signInButton;

    @FindBy(xpath = "//li[@id = 'loginErrorMessage']")
    private WebElement loginErrorMessage;

    @FindBy(xpath = "//span[@id = 'Password-error']")
    private WebElement passwordErrorMessage;

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getUserNameInput() {
        return userNameInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public WebElement getLoginErrorMessage() {
        return loginErrorMessage;
    }

    public WebElement getPasswordErrorMessage() {
        return passwordErrorMessage;
    }
}
