import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {
    AppiumDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "androidTestDevice");
        capabilities.setCapability("platformVersion", "7.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "main.MainActivity");
        capabilities.setCapability("app", "C:/Users/Elena/Documents/STQA/mobile auto/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    //__________methods_from_FirstTest_class_________________________________________________________

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        action.press(x, startY).waitAction(timeOfSwipe).moveTo(x, endY).release().perform();
    }

    protected void quickSwipeUp() {
        swipeUp(200);
    }

    protected void swipeToElement(By by, String message, int maxSwipes) {
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
    protected WebElement waitElementPresent(By locator,
                                            String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");

        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    protected WebElement waitElementPresent(By locator, String errorMessage) {
        return waitElementPresent(locator, errorMessage, 5);
    }

    protected WebElement waitForElementAndClick(By locator,
                                                String errorMessage, long timeOutInSeconds) {
        WebElement element = waitElementPresent(locator, errorMessage, 5);
        element.click();
        return element;
    }

    protected WebElement waitForElementAndSendKeys(
            By locator, String text, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitElementPresent(locator, errorMessage, 5);
        element.sendKeys(text);
        return element;
    }

    protected void TapOnElement(By locator) {
        driver.findElement(locator).click();
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean waitElementNotPresent(By locator, String errorMessage, long timeOutInSeconds) {
        return !isElementPresent(locator);
    }

    protected void swipeElementToLeft(By by, String errorMessage) {
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

    protected WebElement waitForElementAndClear(By locator, String errorMessage) {
        WebElement element = waitElementPresent(locator, errorMessage, 5);
        element.clear();
        return element;

    }
}
