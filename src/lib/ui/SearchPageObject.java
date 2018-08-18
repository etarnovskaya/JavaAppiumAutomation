package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPageObject extends MainPageObject {
    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "search_src_text",
            SEARCH_CANCELL_BUTTON_BY_ID = "search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
    ;


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }


    /*Templates*/
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*Templates*/


    public void initSearchInput() {
        waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Can not find and click search init element ", 5);
        waitElementPresent(By.id(SEARCH_INPUT), "Can not find search input after clicking search init element", 5);
    }

    public void typeInSearchLine(String searchLine) {
        waitForElementAndSendKeys(By.id(SEARCH_INPUT), searchLine, "Can not find and type into the search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        waitElementPresent
                (By.xpath(searchResultXpath),
                        "Can not find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) throws InterruptedException {
        String searchResultXPath = getResultSearchElement(substring);
        waitForElementAndClick(By.xpath(searchResultXPath), "Can not find and click search result with substring " + substring, 10);
        Thread.sleep(2000);
    }

    public void waitForCancellButtonToAppear() {
        waitElementPresent(By.id(SEARCH_CANCELL_BUTTON_BY_ID), "Can not find and click 'search cancell button'", 5);
    }

    public void waitForCancellButtonToDisappear() {
        waitElementNotPresent(By.id(SEARCH_CANCELL_BUTTON_BY_ID), "'search cancell button' is still present", 5);
    }

    public void clickOnCancellSearchButton() {
        waitForElementAndClick(By.id(SEARCH_CANCELL_BUTTON_BY_ID), "Can not find 'search cancell button'", 5);
    }


    public String waitForElementAndGetText(By locator) {
        return waitElementPresent(
                locator,
                "can not get text from Element ",
                5).getText();
    }

    public List<String> getArticlesList() {
        List<String> titles = new ArrayList();
        List<WebElement> results = driver.findElements(By.id("page_list_item_container"));
        for (WebElement result : results) {
            String title = result.findElement(By.id("page_list_item_title")).getText().toString().toLowerCase();
            titles.add(title);
        }
        return titles;
    }

    public void swipeToTheItem() {
       swipeToElement(By.id("page_external_link"), "element not present", 0);
    }
}
