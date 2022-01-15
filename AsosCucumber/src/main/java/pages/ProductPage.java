package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {
    @FindBy(xpath = "//select[@data-id = 'sizeSelect']")
    private WebElement sizeSelectMenuButton;

    @FindBy(xpath = "//button[@aria-label = 'Add to bag']")
    private WebElement addToBagButton;

    @FindBy(xpath = "//span[@data-test-id = 'miniBagItemCount']")
    private WebElement miniBagItemCount;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSizeSelectMenuButton() {
        return sizeSelectMenuButton;
    }

    public WebElement getAddToBagButton() {
        return addToBagButton;
    }

    public WebElement getMiniBagItemCount() {
        return miniBagItemCount;
    }

    public WebElement getClickableOptionFromSizeSelectMenu() {
        WebElement clickableOption = null;
        for (WebElement option : new Select(sizeSelectMenuButton).getOptions()) {
            if (!option.getText().contains("Please select") && !option.getText().contains("Out of stock")) {
                clickableOption = option;
                break;
            }
        }
        return clickableOption;
    }
}
