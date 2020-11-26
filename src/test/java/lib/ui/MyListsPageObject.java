package lib.ui;

import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class MyListsPageObject extends MainPageObject {

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    protected static String FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE,
            REMOVE_FROM_SAVED_BUTTON;

    public String getFolderXpathByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    private static String getRemoveButtonByTitle(String articleTitle) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }

    public void openFolderByName(String nameOfFolder) {
        String folderNameXpath = getSavedArticleXpathByTitle(nameOfFolder);
        this.waitForElementAndClick(folderNameXpath, "Cannot find folder by name " + nameOfFolder, 5);
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementPresent(articleXpath, "Cannot find saved article by title " + articleTitle, 15);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementNotPresent(articleXpath, "Saved article still present with title " + articleTitle, 15);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(articleXpath,
                    "cannot find 'article' element");
        } else {
            String removeLocator = getRemoveButtonByTitle(articleTitle);
            System.out.println(removeLocator);
            this.waitForElementAndClick(removeLocator, "cannot find locator remove article", 5);
        }
        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(articleXpath, "Cannot find saved article");
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(articleTitle);

    }

    public int getAmountArticles() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        By by = mainPageObject.getLocatorByString(ARTICLE);
        waitForElementPresent(ARTICLE, "not found element " + ARTICLE, 10);
        List elements = driver.findElements(by);
        return elements.size();
    }
}
