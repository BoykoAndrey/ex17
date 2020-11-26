package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    private static final String nameOfFolder = "Learning programming",
            login = "TestMobileWeb",
            password = "6M5$H*J5Jg2gC3B";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWitchSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(nameOfFolder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject authorizationPageObject = new AuthorizationPageObject(driver);
            authorizationPageObject.clickAuthButton();
            authorizationPageObject.enterLoginData(login, password);
            authorizationPageObject.submitForm();

            articlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login", articleTitle, articlePageObject.getArticleTitle());

            articlePageObject.addArticlesToMySaved();
        }

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(nameOfFolder);
        }
        myListsPageObject.swipeByArticleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticleToMyList() {
        String firstArticle = "Java (programming language)";
        String secondArticle = "JavaScript";
        String nameOfFolder = "Learning programming";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(firstArticle);
        searchPageObject.clickByArticleWitchSubstring(firstArticle);

        articlePageObject.waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(nameOfFolder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }
        articlePageObject.closeArticle();

        navigationUI.goToMainPage();

        if (Platform.getInstance().isIOS()) {
            searchPageObject.clickSearchLine();
        }

        if (Platform.getInstance().isAndroid()) {
            searchPageObject.clickSearchLine();
            searchPageObject.typeSearchLine(secondArticle);
        }
        searchPageObject.clickByArticleWitchSubstring(secondArticle);

        articlePageObject.waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToExistingList(nameOfFolder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isIOS()) {
            articlePageObject.closeArticle();
        }

        if (Platform.getInstance().isAndroid()) {
            navigationUI.clickBackButton();
            navigationUI.clickBackButton();
            navigationUI.clickBackButton();
        }

        navigationUI.clickMyLists();

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(nameOfFolder);
        }

        int articleCountBeforeDelete = myListsPageObject.getAmountArticles();

        assertEquals("Expected 2 elements, receiver " + articleCountBeforeDelete, 2, articleCountBeforeDelete);

        myListsPageObject.swipeByArticleToDelete(firstArticle);

        int articleCountAfterDelete = myListsPageObject.getAmountArticles();

        assertEquals("Expected 1 elements, receiver " + articleCountAfterDelete,
                1, articleCountAfterDelete);

        myListsPageObject.waitForArticleToDisappearByTitle(firstArticle);
        myListsPageObject.waitForArticleToAppearByTitle(secondArticle);
    }
}