package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefinitionSteps {

    private static final long DEFAULT_TIMEOUT = 30;

    WebDriver driver;

    HomePage homePage;
    SearchResultsPage searchResultsPage;
    ProductPage productPage;
    PageFactoryManager pageFactoryManager;
    WishListPage wishListPage;
    LogInPage logInPage;
    CountrySelectorPage countrySelectorPage;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @And("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @When("User makes search by keyword {string}")
    public void userMakesSearchByKeywordKeyword(final String keyword) {
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getSearchField());
        homePage.enterTextToSearchField(keyword);
    }

    @And("User clicks search button")
    public void userClicksSearchButton() {
        homePage.clickButton(homePage.getSearchButton());
    }

    @And("User clicks 'Add to WishList' button on  first product")
    public void userClicksAddToWishListButtonOnFirstProduct() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getSaveForLaterButtonForFirstProduct());
        searchResultsPage.clickButton(searchResultsPage.getSaveForLaterButtonForFirstProduct());
    }

    @And("User clicks 'Saved items' icon")
    public void userClicksSavedItemsIcon() {
        searchResultsPage.clickButton(searchResultsPage.getSavedItemsIcon());
    }

    @Then("User checks that wishlist header is {string}")
    public void userChecksThatWishlistHeaderIsHeader(final String header) {
        wishListPage = pageFactoryManager.getWishListPage();
        wishListPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, wishListPage.getWishListHeader());

        assertEquals(header, wishListPage.getWishListHeaderText());
    }

    @And("User clicks 'Remove Item' button")
    public void userClicksRemoveItemButton() {
        wishListPage = pageFactoryManager.getWishListPage();
        wishListPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, wishListPage.getRemoveItemButton());
        wishListPage.clickButton(wishListPage.getRemoveItemButton());
    }

    @Then("User checks that 'You have no Saved Items' message is displayed")
    public void userChecksThatYouHaveNoSavedItemsMessageIsDisplayed() {
        wishListPage = pageFactoryManager.getWishListPage();
        wishListPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, wishListPage.getWishListNoItemsTitle());

        assertTrue(wishListPage.getWishListNoItemsTitle().isDisplayed());
    }

    @And("User clicks 'Product Title Description' button on  first product")
    public void userClicksProductTitleDescriptionButtonOnFirstProduct() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getProductTitleDescriptionButtonForFirstProduct());
        searchResultsPage.clickButton(searchResultsPage.getProductTitleDescriptionButtonForFirstProduct());
    }

    @And("User clicks 'Size Select' dropDown Menu")
    public void userClicksSizeSelectDropDownMenu() {
        productPage = pageFactoryManager.getProductPage();
        productPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productPage.getSizeSelectMenuButton());
        productPage.clickButton(productPage.getSizeSelectMenuButton());
    }

    @And("User selects first clickable option, excludes 'Please select' option")
    public void userSelectsFirstClickableOption() {
        productPage.clickButton(productPage.getClickableOptionFromSizeSelectMenu());
    }

    @And("User clicks 'Add to Bag' button")
    public void userClicksAddToBagButton() {
        productPage.clickButton(productPage.getAddToBagButton());
    }

    @Then("User checks that item quantity in popUp shopping cart menu is {string}")
    public void userChecksThatItemQuantityInPopUpShoppingCartMenuIsItemQuantity(final String itemQuantity) {
        productPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productPage.getMiniBagItemCount());

        assertTrue(productPage.getMiniBagItemCount().getText().contains(itemQuantity));
    }

    @Then("User checks that product titles contains {string}")
    public void userChecksThatProductTitlesContainsKeyword(final String keyWord) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getProductTitleDescriptionButtonForFirstProduct());

        for (String title : searchResultsPage.getDescriptionTitles()) {
            assertTrue(title.contains(keyWord.toLowerCase()));
        }
    }

    @And("User moves cursor to 'Sales' navigation button")
    public void userMovesCursorToSalesNavigationButton() {
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getSalesNavigationButton());
        homePage.moveCursorToElement(homePage.getSalesNavigationButton());
    }

    @And("User clicks 'Best of sale' option from dropDown menu")
    public void userClicksBestOfSaleOptionFromDropDownMenu() {
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getBestOfSaleOption());
        homePage.clickButton(homePage.getBestOfSaleOption());
    }

    @Then("User checks that sale prices is lower than regular prices in {int} first items")
    public void userChecksThatSalePricesIsLowerThanRegularPrices(final int amountOfProducts) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getProductTitleDescriptionButtonForFirstProduct());

        for (int i = 0; i < amountOfProducts; i++) {
            assertTrue(
                    searchResultsPage.parseStringPriceToDoublePrices(searchResultsPage.getProductOldPricesList(), amountOfProducts).get(i)
                            >
                            searchResultsPage.parseStringPriceToDoublePrices(searchResultsPage.getProductNewPricesList(), amountOfProducts).get(i)
            );
        }
    }

    @And("User moves cursor to 'My account' icon")
    public void userMovesCursorToMyAccountIcon() {
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getMyAccountIcon());
        homePage.moveCursorToElement(homePage.getMyAccountIcon());
    }

    @And("User clicks 'Sign In' option in dropDown menu")
    public void userClicksSignInOptionInDropDownMenu() {
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getSignInButton());
        homePage.clickButton(homePage.getSignInButton());
    }

    @And("User fills {string} in 'User name' input")
    public void userFillsUserNameInUserNameInput(final String userName) {
        logInPage = pageFactoryManager.getLogInPage();
        logInPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, logInPage.getUserNameInput());
        logInPage.fillTextToInput(logInPage.getUserNameInput(), userName);
    }

    @And("User fills {string} in 'Password' input")
    public void userFillsPasswordInPasswordInput(final String password) {
        logInPage.fillTextToInput(logInPage.getPasswordInput(), password);
    }

    @And("User clicks 'Sign In' button")
    public void userClicksSignInButton() {
        logInPage.clickButton(logInPage.getSignInButton());
    }

    @Then("User checks that 'NOTHING MATCHES YOUR SEARCH' message displayed")
    public void userChecksThatErrorMessageDisplayed() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getNothingMatchesErrorMessage());

        assertEquals("NOTHING MATCHES YOUR SEARCH", searchResultsPage.getNothingMatchesErrorMessage().getText());
    }

    @Then("User checks that 'Incorrect email or password' message displayed")
    public void userChecksThatIncorrectEmailOrPasswordMessageDisplayed() {
        logInPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, logInPage.getLoginErrorMessage());

        assertTrue(logInPage.getLoginErrorMessage().isDisplayed());
    }

    @Then("User checks that 'Hey, we need a password here' message displayed")
    public void userChecksThatHeyWeNeedAPasswordHereMessageDisplayed() {
        logInPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, logInPage.getPasswordErrorMessage());

        assertTrue(logInPage.getPasswordErrorMessage().isDisplayed());
    }

    @And("User clicks 'Country Selector' button")
    public void userClicksCountrySelectorButton() {
        homePage = pageFactoryManager.getHomePage();
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getCountrySelectorButton());
        homePage.clickButton(homePage.getCountrySelectorButton());
    }

    @And("User clicks 'Shop In' drop down menu")
    public void userClicksShopInDropDownMenu() {
        countrySelectorPage = pageFactoryManager.getCountrySelectorPage();
        countrySelectorPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, countrySelectorPage.getShopInMenuButton());
        countrySelectorPage.clickButton(countrySelectorPage.getShopInMenuButton());
    }

    @And("User clicks {string} option")
    public void userClicksCountryOption(final String country) {
        countrySelectorPage.selectOptionByVisibleText(countrySelectorPage.getShopInMenuButton(), country);
    }

    @And("User clicks 'Update Preferences' button")
    public void userClicksSaveCountryButton() {
        countrySelectorPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, countrySelectorPage.getUpdatePreferencesButton());
        countrySelectorPage.clickButton(countrySelectorPage.getUpdatePreferencesButton());
    }

    @Then("User checks that price is with {string} currency")
    public void userChecksThatPriceIsWithCurrencyCurrency(final String expectedCurrency) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getFirstItem());

        assertEquals(searchResultsPage.getFirstItemCurrency(), expectedCurrency);
    }

    @And("User clicks 'Sort' button")
    public void userClicksSortButton() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitElementToBeClickable(DEFAULT_TIMEOUT, searchResultsPage.getSortMenuButton());
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getSortMenuButton());
        searchResultsPage.clickButton(searchResultsPage.getSortMenuButton());
    }

    @And("User clicks 'Price low to high' sort  option")
    public void userClicksPriceLowToHighSortOption() {
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getLowToHighSortMenuOption());
        searchResultsPage.clickButton(searchResultsPage.getLowToHighSortMenuOption());
    }

    @And("User checks that first {int} prices is displayed in descent order")
    public void userChecksThatFirstAmountOfProductsPricesIsDisplayedInDescentOrder(final int amountOfProducts) {

        assertTrue(
                searchResultsPage.checkThatListIsSortedInDescOrder(
                        searchResultsPage.parseStringPriceToDoublePrices(
                                searchResultsPage.getProductNewPricesList(), amountOfProducts))
        );
    }

    @Then("User checks that 'Price low to high' sort  option is highlighted")
    public void userChecksThatPriceLowToHighSortOptionIsHighlighted() {
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getLowToHighSortMenuOption());

        assertEquals("true", searchResultsPage.getLowToHighSortMenuOption().getAttribute("aria-selected"));
    }
}
