import io.appium.java_client.TouchAction;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest extends TestBase {


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
        wait.withMessage("error message" + "\n");
        By by = By.xpath(xPath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @Test
    public void searchForJavaArticles() {
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
    public void Homework2() {
        WebElement elementInitSearch = waitElementPresentByXPath(
                "//*[contains(@text, 'Search Wikipedia')]",
                "element can not find",
                3);
        elementInitSearch.click();

        WebElement elementTypeInSearchField = driver.findElementById("search_src_text");
        elementTypeInSearchField.sendKeys("interface");

        waitElementPresentByXPath("//*[contains(@text, 'Interface (computing)')]", "Interface (computing)", 15);
    }

    @Test
    public void swipeTest() {
        WebElement elementInitSearch = waitElementPresentByXPath(
                "//*[contains(@text, 'Search Wikipedia')]",
                "element 'elementInitSearch' can not find",
                3);
        elementInitSearch.click();

        WebElement elementTypeInSearchField = driver.findElementById("search_src_text");
        elementTypeInSearchField.sendKeys("App");
        swipeToElement(By.id("page_external_link"), "element not present", 0);


//        waitElementPresentByXPath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
//                "Can not find",
//                15);
//        swipeUp(2000);
//        swipeUp(2000);
//        swipeUp(2000);
    }

  @Test
    public  void saveArticleToMyList(){

        String folderName = "Learning programming";

        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "element 'elementInitSearch' can not find", 3);
      waitForElementAndSendKeys(By.id("search_src_text"),
              "Java", "element 'elementInitSearch' can not find",3 );
      waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
              "element not found", 3 );
      //waitElementPresent(By.xpath("//*[@text='Object-oriented programming language']"),
            //  "notPresent", 3);
      waitForElementAndClick(By.xpath("//*[@content-desc='More options']"),
              "button menu not found", 3);
waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
        "err", 3);
waitForElementAndClick(By.id("onboarding_button"),
        "element button gotit not present", 3);
waitForElementAndClear(By.id("text_input"),
        "text_input not found");
waitForElementAndSendKeys(By.id("text_input"),folderName,
        "can not put text to input  field", 3);
waitForElementAndClick(By.xpath("//*[@text='OK']"),
        "button 'Ok' not found", 5);
waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"),
        "button 'X' not found", 5);
waitForElementAndClick(By.xpath("//*[@content-desc='My lists']"),
        "element 'My lists' not found", 5);
waitForElementAndClick(By.xpath("//*[@text='" + folderName+"']"),
        "folder 'Learning programming' not clicked", 5);
//waitElementPresent(By.xpath("//[@text='object-oriented programming language']"),
//        "article 'object-oriented programming language' not found",
//        3);
//object-oriented programming language
      swipeElementToLeft(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
              "article 'object-oriented programming language' not found");
      waitElementNotPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
              "article 'object-oriented programming language' not deleted", 5);
    }


}
