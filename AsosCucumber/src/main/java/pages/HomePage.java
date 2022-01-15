package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    @FindBy(xpath = "//input[@id = 'chrome-search']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@data-testid = 'search-button-inline']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[@data-id = 'c223e1a9-dc0f-42f5-afca-5cf5988c716b']")
    private WebElement salesNavigationButton;

    @FindBy(xpath = "//button[@aria-label = 'My Account']")
    private WebElement myAccountIcon;

    @FindBy(xpath = "//a[@data-testid = 'signin-link']")
    private WebElement signInButton;

    @FindBy(xpath = "//button[@data-testid = 'signout-link']")
    private WebElement signOutButton;

    @FindBy(xpath = "//div[@class = '_2jtZkvR']//button[@data-testid = 'country-selector-btn']")
    private WebElement countrySelectorButton;

    @FindBy(xpath = "//a[@class = '_1cjL45H _2Y7IAa_ CLdGn9X _1XjY6Zd _11swdTI']")
    private List<WebElement> dropDownSaleMenuOptions;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getSalesNavigationButton() {
        return salesNavigationButton;
    }

    public WebElement getBestOfSaleOption() {
        return dropDownSaleMenuOptions.get(6);
    }

    public WebElement getMyAccountIcon() {
        return myAccountIcon;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public WebElement getCountrySelectorButton() {
        return countrySelectorButton;
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public void enterTextToSearchField(final String searchText) {
        searchField.clear();
        searchField.sendKeys(searchText);
    }
}
