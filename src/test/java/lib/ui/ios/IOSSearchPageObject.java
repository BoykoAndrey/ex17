package lib.ui.ios;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT="";
        SKIP_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeButton[@name='Close']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[contains(@name, '{TITLE}')][contains(@name, '{DESCRIPTION}')]";
    }

    public IOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
