package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import java.util.HashMap;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Linkin Park Discography";
        searchPageObject.typeSearchLine(searchLine);
        int amountOfSearchResults = searchPageObject.getAmountOfFoundsArticles();
        assertTrue("We found too few results!", amountOfSearchResults > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "sfgnfgnfgn";
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testSearchArticleByTitleAndDescription() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        String searchLine = "Java";
        HashMap<String, String> values = new HashMap<>();
        values.put("Java", "Indonesian island");
        values.put("JavaScript", "High-level programming language");
        values.put("Java (programming language)", "Object-oriented programming language");

        if (Platform.getInstance().isAndroid()) {
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchLine(searchLine);
        } else if (Platform.getInstance().isIOS()) {
            values.replace("Java", "Island of Indonesia");
            values.replace("JavaScript", "Programming language");
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchLine(searchLine);
        }

        values.forEach(searchPageObject::waitForElementByTitleAndDescription);
    }
}