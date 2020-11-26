package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "xpath://div[@class='page-heading']/h1";
        FOOTER_ELEMENT = "css:mw-footer minerva-footer";
        SAVE_ARTICLE_BUTTON = "xpath://*[@id='ca-watch']";
        REMOVE_FROM_MY_LIST_BUTTON="css:a.mw-ui-icon-wikimedia-unStar-progressive";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
