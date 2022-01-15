package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CountrySelectorPage extends BasePage {
    @FindBy(xpath = "//select[@id = 'country']")
    private WebElement shopInMenuButton;

    @FindBy(xpath = "//button[@data-testid = 'save-country-button']")
    private WebElement updatePreferencesButton;

    public CountrySelectorPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getShopInMenuButton() {
        return shopInMenuButton;
    }

    public WebElement getUpdatePreferencesButton() {
        return updatePreferencesButton;
    }
}
