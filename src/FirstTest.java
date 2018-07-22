import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "androidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "main.MainActivity");
        capabilities.setCapability("app", "C:/Users/Elena/Documents/STQA/mobile auto/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void firstTest() {
        WebElement elementInitSearch = waitElementPresentByXPath(
                "//*[contains(@text, 'Search Wikipedia')]",
                "element can not find",
                3);
                //driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
        elementInitSearch.click();
        //System.out.println("First test run.");

        WebElement elementTypeInSearchField = driver.findElementById("search_src_text");
        elementTypeInSearchField.sendKeys("Appium");

    }

    private WebElement waitElementPresentByXPath(String xPath, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage("error message" +"\n" );
        By by = By.xpath(xPath);
        return  wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @Test
    public  void searchForJavaArticles(){
        WebElement elementInitSearch = waitElementPresentByXPath(
                "//*[contains(@text, 'Search Wikipedia')]",
                "element 'elementInitSearch' can not find",
                3);
       elementInitSearch.click();

        WebElement elementTypeInSearchField = driver.findElementById("search_src_text");
        elementTypeInSearchField.sendKeys("Java");

        waitElementPresentByXPath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
                "Can not find",
                15);
    }

    @Test
    public void Homework2(){
        WebElement elementInitSearch = waitElementPresentByXPath(
                "//*[contains(@text, 'Search Wikipedia')]",
                "element can not find",
                3);
        elementInitSearch.click();

        WebElement elementTypeInSearchField = driver.findElementById("search_src_text");
        elementTypeInSearchField.sendKeys("interface");

       waitElementPresentByXPath("//*[contains(@text, 'Interface (computing)')]", "Interface (computing)", 15);
    }






    @After
    public  void tearDown(){
        driver.quit();
    }
}
