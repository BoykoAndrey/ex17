package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String BACK_BUTTON,
            MY_LISTS_LINK,
            LOG_IN_TO_SYNC,
            CLOSE_LOG_IN,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickMyLists() {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(MY_LISTS_LINK, "cannot find my lists link", 5);
        } else {
            this.waitForElementPresent(MY_LISTS_LINK, "cannot find my lists link", 30).click();
        }
    }

    public void clickBackButton() {
        this.waitForElementPresent(BACK_BUTTON, "cannot find back button", 20).click();
    }

    public void goToMainPage() {
        if (Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(LOG_IN_TO_SYNC, "cannot find log in to sync button", 20);
            this.waitForElementAndClick(CLOSE_LOG_IN, "cannot find close log in button", 5);
        }
        this.waitForElementPresent(BACK_BUTTON, "cannot find back button", 20).click();
    }

    public void openNavigation() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "cannot find and click open navigation button", 5);
        } else {
            System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
}
