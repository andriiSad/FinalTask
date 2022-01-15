package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishListPage extends BasePage {
    @FindBy(xpath = "//div[@class = 'itemCount_3vWat']")
    private WebElement wishListHeader;

    @FindBy(xpath = "//h2[@class = 'noItemsTitle_zbzNG title_3EWj2']")
    private WebElement wishListNoItemsTitle;

    @FindBy(xpath = "//button[@aria-label = 'Delete']")
    private WebElement removeItemButton;

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getWishListHeader() {
        return wishListHeader;
    }

    public WebElement getWishListNoItemsTitle() {
        return wishListNoItemsTitle;
    }

    public WebElement getRemoveItemButton() {
        return removeItemButton;
    }

    public String getWishListHeaderText() {
        return wishListHeader.getText();
    }
}
