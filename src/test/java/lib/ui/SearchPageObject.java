package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SKIP_BUTTON,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    private static String getSearchResultByTitleAndDescriptionElement(String title, String description) {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }

    public void initSearchInput() {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(SKIP_BUTTON, "cannot find skip button", 5);
        }
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 15);
        if (Platform.getInstance().isIOS()) {
            this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 5);
        }
    }

    public void typeSearchLine(String searchLine) {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndSendKeys(SEARCH_INPUT, searchLine, "cannot find and type into search input", 20);
        } else {
            this.waitForElementAndSendKeys(SEARCH_INIT_ELEMENT, searchLine, "cannot find and type into search input", 20);
        }
    }

    public void clickSearchLine() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "cannot find search input", 20);
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(searchResultXpath, "Cannot find search result with substring " + substring, 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button!", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present!", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 10);
    }

    public void clickByArticleWitchSubstring(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultXpath, "Cannot find and click search result with substring " + substring, 20);
    }

    public int getAmountOfFoundsArticles() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request", 5);
        return getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementsNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        waitForElementPresent(getSearchResultByTitleAndDescriptionElement(title, description),
                "no article found by title " + title + " or description " + description,
                5);
    }
}
