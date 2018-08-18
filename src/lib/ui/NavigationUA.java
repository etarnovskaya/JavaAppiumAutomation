package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUA extends MainPageObject {

    private static final String
            CLOSE_PAGE_BUTTON = "//*[@content-desc='Navigate up']",
    MY_LISTS_BUTTON = "//android.widget.FrameLayout[@content-desc='My lists']";

    public NavigationUA(AppiumDriver driver) {
        super(driver);
    }

    public void closePage() {
        waitForElementAndClick(By.xpath(CLOSE_PAGE_BUTTON),
                "not possible to close page using 'x'",
                10);
    }

    public void goToSavedLists() {
        waitForElementAndClick(By.xpath(MY_LISTS_BUTTON),
                "err",
                15);
    }
}
