package manager;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageFactoryManager {

    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public SearchResultsPage getSearchResultsPage() {
        return new SearchResultsPage(driver);
    }

    public ProductPage getProductPage() {
        return new ProductPage(driver);
    }

    public CountrySelectorPage getCountrySelectorPage() {
        return new CountrySelectorPage(driver);
    }

    public WishListPage getWishListPage() {
        return new WishListPage(driver);
    }

    public LogInPage getLogInPage() {
        return new LogInPage(driver);
    }
}
