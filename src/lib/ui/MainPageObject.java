package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitElementPresent(By locator,
                                         String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");

        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public String waitForElementAndGetText(By locator) {
        return waitElementPresent(
                locator,
                "can not get text from Element ",
                5).getText();
    }
    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        action.press(x, startY).waitAction(timeOfSwipe).moveTo(x, endY).release().perform();
    }

    public void quickSwipeUp() {
        swipeUp(200);
    }

    public void swipeToElement(By by, String message, int maxSwipes) {
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped > maxSwipes) {
                waitElementPresent(By.xpath(""),
                        "element not present on the page",
                        0);
            }
            quickSwipeUp();
            alreadySwiped++;
        }
    }


    //______________________methods from HW_class_____________________________________________________



    public WebElement waitElementPresent(By locator, String errorMessage) {
        return waitElementPresent(locator, errorMessage, 10);
    }

    public WebElement waitForElementAndClick(By locator,
                                                String errorMessage, long timeOutInSeconds) {
        WebElement element = waitElementPresent(locator, errorMessage, 5);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(
            By locator, String text, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitElementPresent(locator, errorMessage, 5);
        element.sendKeys(text);
        return element;
    }

    public void tapOnElement(By locator) {
        driver.findElement(locator).click();
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean waitElementNotPresent(By locator, String errorMessage, long timeOutInSeconds) {
        return !isElementPresent(locator);
    }

    public void swipeElementToLeft(By by, String errorMessage) {
        TouchAction action = new TouchAction(driver);

        WebElement element = waitElementPresent(by, errorMessage, 15);
        int leftX = element.getLocation().getX();//left point
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        action
                .press(rightX, middleY)
                .waitAction(700)
                .moveTo(leftX, middleY)
                .release()
                .perform();
    }

    public WebElement waitElementPresentByXPath(String xPath, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage("error message" + "\n");
        By by = By.xpath(xPath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(By locator, String errorMessage) {
        WebElement element = waitElementPresent(locator, errorMessage, 5);
        element.clear();
        return element;

    }

    public void skipIntroducePage() {
        if(!isElementPresent(By.id("fragment_onboarding_skip_button")))
        waitForElementAndClick(
                By.id("fragment_onboarding_skip_button"),
                "can not find Element 'Skip'",
                5);
    }

    public boolean isArticlePresent() {
        return isElementPresent(By.id("page_list_item_description"));
    }
}
