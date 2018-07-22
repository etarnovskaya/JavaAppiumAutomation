import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeWorks {
    AppiumDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "SGNote5");
        capabilities.setCapability("platformVersion", "7.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "main.MainActivity");
        capabilities.setCapability("app", "C:/Users/Elena/Documents/STQA/mobile auto/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    /*
Method, should check presence of text “Search…”
in the 'Search' field before typing text
*/
    public void HW2IsSearchFieldHasCorrectText(){
waitForElementAndClick(
        By.id("fragment_onboarding_skip_button"),
        "can not find Element 'Skip'",
        5);
waitForElementAndClick(
        By.id("search_container"),
        "can not find Element with text 'Search Wikipedia'",
        5);
String fieldText = waitElementPresent(
        By.id("search_src_text"),
        "can not find Element with text 'Search Wikipedia'",
        5).getText();

       Assert.assertEquals(
               "The text of the field is not  a same as expected",
               "Search…",
               fieldText);
    }

    @Test
    /*test should:
1. search any word
2. To check, that articles  are found
3. cancel search
4. To check, search result not present
 */
    public  void HW3SearchByWordAndCancel(){
        //skip first page
        waitForElementAndClick(
                By.id("fragment_onboarding_skip_button"),
                "can not find Element 'Skip'",
                5);
        //Type word to 'search'  field
        waitForElementAndSendKeys(By.id("search_container"),
                "interface",
                "can not find Element with text 'Search Wikipedia'",
                5);

        Assert.assertEquals(true, isElementPresent(By.id("page_list_item_description")));

        //cancel search by tapping on the 'x' button
        TapOnElement(By.id("search_close_btn"));

        Assert.assertEquals(false, isElementPresent(By.id("page_list_item_description")));

     }

    @After
    public  void tearDown(){
        driver.quit();
    }


    //______________________methods_____________________________________________________
    private WebElement waitElementPresent(By locator,
                                          String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");

        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private WebElement waitElementPresent(By locator, String errorMessage) {
        return waitElementPresent(locator, errorMessage, 5);
    }

    private  WebElement waitForElementAndClick(By locator,
                                               String errorMessage, long timeOutInSeconds){
        WebElement element = waitElementPresent(locator, errorMessage, 5);
        element.click();
        return element;
    }

    private  WebElement waitForElementAndSendKeys(
            By locator, String text, String errorMessage, long timeOutInSeconds){
        WebElement element = waitElementPresent(locator, errorMessage, 5);
        element.sendKeys(text);
        return element;
    }

    private void TapOnElement(By locator) {
        driver.findElement(locator).click();
    }

private boolean isElementPresent(By locator) {
       try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
//        return waitElementPresent(locator, "not found"  );
//    }



