package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUI extends NavigationUI {

    static {
        BACK_BUTTON = "id:Back";
        MY_LISTS_LINK = "id:Saved";
        LOG_IN_TO_SYNC = "id:Log in to sync your saved articles";
        CLOSE_LOG_IN = "xpath://XCUIElementTypeButton[@name='close']";
    }

    public IOSNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
