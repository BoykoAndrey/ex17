package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    private static final String
            LOGIN_BUTTON = "xpath://div/a[text()='Log in']",
            LOGIN_INPUT = "xpath://input[@id='wpName1']",
            PASSWORD_INPUT = "css:input[name=wpPassword]",
            SUBMIT_BUTTON = "css:button[name=wploginattempt]";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
        this.waitForElementPresent(LOGIN_BUTTON, "cannot find login button", 30);
        this.waitForElementAndClick(LOGIN_BUTTON, "cannot find and click login button", 30);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "cannot find login input", 30);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "cannot find password input", 30);
    }

    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "cannot find submit button", 30);
    }
}
