package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {

    static {
        BACK_BUTTON = "xpath://*[@id='mw-mf-page-center']/header/form/div[1]/a/span/img";
        MY_LISTS_LINK = "css:a[data-event-name='menu.unStar']";
        LOG_IN_TO_SYNC = "id:Log in to sync your saved articles";
        CLOSE_LOG_IN = "xpath://XCUIElementTypeButton[@name='close']";
        OPEN_NAVIGATION="css:#mw-mf-main-menu-button";
    }

    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
