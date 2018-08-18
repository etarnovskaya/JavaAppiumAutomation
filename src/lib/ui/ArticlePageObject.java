package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE_XPATH_BY_SUBSTRING = "//*[@text='{SUBSTRING}']",
            LIST_NAME_INPUT_FIELD_ID = "text_input",
            OPTION_BUTTON = "//*[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
            GOT_IT_OVERLAY = "//*[@resource-id='org.wikipedia:id/onboarding_container']//*[@text='GOT IT']",
            MY_LIST_CREATE_NEW_LIST_BUTTON = "//*[@text='Create new']",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            ARTICLE_ON_THE_MAIN_PAGE = "view_featured_article_card_image",
    ARTICLE_TITLE_IN_LIST = "page_list_item_title",
    ARTICLE_IN_LIST = "page_list_item_container",
    ARTICLE_TITLE_INTO_ARTICLE = "view_page_title_text";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
//public WebElement waitForTitleElement(){
//      //  return waitElementPresent(By.id())
//}


    public List<String> getArticlesList() {
        List<String> titles = new ArrayList();
        List<WebElement> articles = driver.findElements(By.id("page_list_item_container"));
        for (WebElement article : articles) {
            String title = article.findElement(By.id("page_list_item_title")).getText().toString().toLowerCase();
            titles.add(title);
        }
        return titles;
    }

    public void deleteArticleBySwipe(String substring) {

        swipeElementToLeft(By.xpath(TITLE_XPATH_BY_SUBSTRING.replace("{SUBSTRING}", substring)), "element not swiped");
    }

    public void addArticleToMyList(String folderName, boolean creation) throws InterruptedException {
        waitForElementAndClick(By.xpath(OPTION_BUTTON), "not possible to click on 'Options' button", 15);
        Thread.sleep(2000);

        waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "element menu 'Add to reading list' not present",
                5);
        Thread.sleep(2000);
        if (isElementPresent(By.xpath(GOT_IT_OVERLAY))) {
            waitForElementAndClick(By.xpath(GOT_IT_OVERLAY),
                    "button 'GOT IT' not clickable",
                    15);
        }

        if (creation) {
        if (isElementPresent(By.xpath(MY_LIST_CREATE_NEW_LIST_BUTTON))) {
            waitForElementAndClick(By.xpath(MY_LIST_CREATE_NEW_LIST_BUTTON),
                    "not present link 'Create new'",
                    15);
        }
            waitForElementAndClick(By.id(LIST_NAME_INPUT_FIELD_ID),
                    "Not possible to click on the field 'listname'",
                    15);
            waitForElementAndClear(By.id(LIST_NAME_INPUT_FIELD_ID),
                    "Not possible to clear the field 'listname'");
            waitForElementAndSendKeys(By.id(LIST_NAME_INPUT_FIELD_ID),
                    folderName,
                    "Not possible to type to the field 'listname'",
                    15);
            waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON),
                    "button 'ok' not clicable",
                    7);
        } else {
            waitForElementAndClick(By.xpath("//*[@text='" + folderName + "']"),
                    "the folder not present",
                    15);
            Thread.sleep(2000);
        }
    }

    public String getArticleTitleInToArticle() {
        return waitElementPresent(By.id(ARTICLE_TITLE_INTO_ARTICLE ), "There is not title of article", 10).getText();
    }

    public void clickOnTheArticleInList() {
        waitForElementAndClick(By.id(ARTICLE_IN_LIST), "", 5);
    }

    public String getArticleTitleInList() {
       return waitElementPresent(By.id(ARTICLE_TITLE_IN_LIST),
                "",
                15).getText();
    }

    public void openArticleOnTheMainPage() {
        waitForElementAndClick(By.id(ARTICLE_ON_THE_MAIN_PAGE), "There is not articles", 7);

    }

//    public boolean isArticleNotPresentByName(String articleTitle) {
//      return !isArticlePresent(By.xpath(TITLE_XPATH_BY_SUBSTRING.replace("{SUBSTRING}", articleTitle)));
//    }
}
