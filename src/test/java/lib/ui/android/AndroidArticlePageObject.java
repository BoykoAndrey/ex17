package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "xpath:(//*[@resource-id='pcs']//*[@class='android.view.View'])[2]";
        FOOTER_ELEMENT = "xpath://*[@content-desc='View article in browser']";
        SAVE_ARTICLE_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/article_menu_bookmark']";
        ADD_TO_MY_LIST_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/snackbar_action']";
        ADD_TO_MY_LIST_OVERLAY = "xpath://*[@resource-id='org.wikipedia:id/onboarding_button']";
        MY_LIST_NAME_INPUT = "xpath://*[@resource-id='org.wikipedia:id/text_input']";
        MY_LIST_OK_BUTTON = "xpath://*[@resource-id='android:id/button1']";
        CLOSE_ARTICLE_BUTTON = "xpath://*[@content-desc='Navigate up']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
