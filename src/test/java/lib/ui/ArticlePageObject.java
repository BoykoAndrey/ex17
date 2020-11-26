package lib.ui;

import lib.Platform;
import lib.ui.android.AndroidMyListsPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String TITLE,
            FOOTER_ELEMENT,
            SAVE_ARTICLE_BUTTON,
            ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            REMOVE_FROM_MY_LIST_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 30);
    }

    public String getArticleTitle() {
        if (Platform.getInstance().isAndroid()) {
            return waitForTitleElement().getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return waitForTitleElement().getAttribute("name");
        } else {
            return waitForTitleElement().getText();
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }
    }

    public void addArticleToMyList(String nameOfFolder) {
        this.waitForElementPresent(SAVE_ARTICLE_BUTTON, "cannot find save article button", 20).click();
        this.waitForElementPresent(ADD_TO_MY_LIST_BUTTON, "cannot find add to my list button", 20).click();
        this.waitForElementPresent(ADD_TO_MY_LIST_OVERLAY, "cannot find add to my list overlay", 20).click();
        this.waitForElementPresent(MY_LIST_NAME_INPUT, "cannot find my list name input", 20).clear();
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, nameOfFolder, "cand find and send keys my list name input", 20);
        this.waitForElementPresent(MY_LIST_OK_BUTTON, "cannot find my list ok button", 20).click();
    }

    public void addArticleToExistingList(String nameOfFolder) {
        AndroidMyListsPageObject androidMyListsPageObject = new AndroidMyListsPageObject(driver);
        this.waitForElementPresent(SAVE_ARTICLE_BUTTON, "cannot find article button", 5).click();
        this.waitForElementPresent(ADD_TO_MY_LIST_BUTTON, "cannot find add to my list button", 5).click();
        this.waitForElementPresent(androidMyListsPageObject.getFolderXpathByName(nameOfFolder), "cannot find folder " + nameOfFolder, 5).click();
    }


    public void closeArticle() {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementPresent(CLOSE_ARTICLE_BUTTON, "cannot find close article button", 5).click();
        } else {
            System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void addArticlesToMySaved() {
        if (Platform.getInstance().isMW()) {
            removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(SAVE_ARTICLE_BUTTON, "cannot find save article button", 30);
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(REMOVE_FROM_MY_LIST_BUTTON, "cannot find and click remove button", 5);
            this.waitForElementPresent(SAVE_ARTICLE_BUTTON, "cannot find add button", 5);
        }
    }
}
