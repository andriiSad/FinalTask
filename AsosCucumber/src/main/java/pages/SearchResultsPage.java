package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends BasePage {
    @FindBy(xpath = "//button[@data-auto-id = 'saveForLater']")
    private List<WebElement> saveForLaterButtonList;

    @FindBy(xpath = "//a[@data-testid= 'savedItemsIcon']")
    private WebElement savedItemsIcon;

    @FindBy(xpath = "//li[@data-auto-id = 'sort']")
    private WebElement sortMenuButton;

    @FindBy(xpath = "//li[@id = 'plp_web_sort_price_low_to_high']")
    private WebElement lowToHighSortMenuOption;

    @FindBy(xpath = "//div[@data-auto-id='productTileDescription']")
    private List<WebElement> productTitleDescriptionButtonsList;

    @FindBy(xpath = "//span[@class = '_16nzq18']")
    private List<WebElement> productOldPricesList;

    @FindBy(xpath = "//h2[@class = 'grid-text__title ']")
    private List<WebElement> nothingMatchesErrorMessageList;

    @FindBy(xpath = "//span[@class = '_3VjzNxC']")
    private List<WebElement> productNewPricesList;

    private List<String> descriptionTitles;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getDescriptionTitles() {
        setDescriptionTitles();
        return descriptionTitles;
    }

    public void setDescriptionTitles() {
        descriptionTitles =
                productTitleDescriptionButtonsList
                        .stream()
                        .map(WebElement::getText)
                        .map(String::toLowerCase)
                        .collect(Collectors.toList());
    }

    public WebElement getNothingMatchesErrorMessage() {
        return nothingMatchesErrorMessageList.get(0);
    }

    public WebElement getSaveForLaterButtonForFirstProduct() {
        return saveForLaterButtonList.get(0);
    }

    public WebElement getProductTitleDescriptionButtonForFirstProduct() {
        return productTitleDescriptionButtonsList.get(0);
    }

    public WebElement getFirstItem() {
        return productOldPricesList.get(0);
    }

    public String getFirstItemCurrency() {
        return String.valueOf(productOldPricesList.get(0).getText().charAt(productOldPricesList.get(0).getText().length() - 1));
    }

    public WebElement getSortMenuButton() {
        return sortMenuButton;
    }

    public WebElement getLowToHighSortMenuOption() {
        return lowToHighSortMenuOption;
    }

    public WebElement getSavedItemsIcon() {
        return savedItemsIcon;
    }

    public List<WebElement> getProductOldPricesList() {
        return productOldPricesList;
    }

    public List<WebElement> getProductNewPricesList() {
        return productNewPricesList;
    }

    public List<Double> parseStringPriceToDoublePrices(List<WebElement> webElementList, int amountOfProducts) {
        return webElementList
                .stream()
                .limit(amountOfProducts) //ліміт потрібен, бо якщо перевіряти всі елементи перевірка займає 1хв 29с.
                .map(webElement -> Double.parseDouble(new StringBuilder(webElement.getText()).deleteCharAt(0).toString()))
                .collect(Collectors.toList());

    }

    public boolean checkThatListIsSortedInDescOrder(List<Double> priceList) {
        return priceList.stream()
                .sorted()
                .collect(Collectors.toList())
                .equals(priceList);
    }
}
