import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomeWorks extends CoreTestCase {
    MainPageObject mainPageObject;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }

    @Test
    /*
Method, should check presence of text “Search…”
in the 'Search' field before typing text
*/
    public void testHW2IsSearchFieldHasCorrectText() {
        mainPageObject.waitForElementAndClick(
                By.id("fragment_onboarding_skip_button"),
                "can not find Element 'Skip'",
                5);
        mainPageObject.waitForElementAndClick(
                By.id("search_container"),
                "can not find Element with text 'Search Wikipedia'",
                5);
        String fieldText = mainPageObject.waitElementPresent(
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
    public void testHW3SearchByWordAndCancel() {
        //skip first page
//        waitForElementAndClick(
//                By.id("fragment_onboarding_skip_button"),
//                "can not find Element 'Skip'",
        //             5);
        //Type word to 'search'  field
        mainPageObject.waitForElementAndSendKeys(By.id("search_container"),
                "interface",
                "can not find Element with text 'Search Wikipedia'",
                5);

        Assert.assertEquals(true, mainPageObject.isElementPresent(By.id("page_list_item_description")));

        //cancel search by tapping on the 'x' button
        mainPageObject.tapOnElement(By.id("search_close_btn"));

        Assert.assertEquals(false, mainPageObject.isElementPresent(By.id("page_list_item_description")));

    }

    @Test
    /*test should:
1. search any word
2. To check, that all search results does contain this word
 */
    public void testHW4SearchByWordAndCheckThatAllResultsContainsThisWord() {
        //skip first page (on native device SG)
//        waitForElementAndClick(
//                By.id("fragment_onboarding_skip_button"),
//                "can not find Element 'Skip'",
//                5);
        //Type word to 'search'  field
        mainPageObject.waitForElementAndSendKeys(By.id("search_container"),
                "interface",
                "can not find Element with text 'Search Wikipedia'",
                5);

        Assert.assertEquals(true, mainPageObject.isElementPresent(By.id("page_list_item_description")));

        List<WebElement> results = driver.findElements(By.id("page_list_item_container"));
        for (WebElement result : results) {
            String title = result.findElement(By.id("page_list_item_title")).getText().toLowerCase();
            System.out.println(title);

            Assert.assertTrue("The title does not contain a word " + title.toString(), title.toString().contains("interface"));
        }
    }

    @Test
    public void testHW5AddTwoArticlesToTheListAndDeleteOne() throws InterruptedException {
        /*
        Test Should
        add two articles to the 'Reading list'
        delete by swipe one article
        verify, that in the list one article present
         */

        String listNamefieldLocatorByxpath = "//*[@text='Name of this list']";
        String listNamefieldLocatorById = "text_input";
        String folderName = "My Homework";

        //Skip  button
//        waitForElementAndClick(
//                By.id("fragment_onboarding_skip_button"),
//                "can not find Element 'Skip'",
//                5);
        //PART 1
        // Search article, select one and add to List
        mainPageObject.waitForElementAndClick(
                By.id("search_container"),
                "can not find Element 'Search bar'",
                5);
        mainPageObject.waitForElementAndSendKeys(By.id("search_container"),
                "interface",
                "can not find Element with text 'Search Wikipedia'",
                10);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='Interface (computing)']"),
                "Article 'Interface (computing)' not present ",
                15);
        Thread.sleep(2000);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@content-desc='More options']"), "", 15);
        Thread.sleep(2000);

        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "element menu 'Add to reading list' not present",
                5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/onboarding_container']//*[@text='GOT IT']"),
                "button 'GOT IT' not clickable",
                15);

        if (mainPageObject.isElementPresent(By.xpath("//*[@text='Create new']"))) {
            mainPageObject.waitForElementAndClick(By.xpath("//*[@text='Create new']"),
                    "not present link 'Create new'",
                    15);
        }
        //name list
        mainPageObject.waitForElementAndClick(By.id(listNamefieldLocatorById),
                "Not possible to click on the field 'listname'",
                15);
        mainPageObject.waitForElementAndClear(By.id(listNamefieldLocatorById),
                "Not possible to clear the field 'listname'");
        mainPageObject.waitForElementAndSendKeys(By.id(listNamefieldLocatorById),
                folderName,
                "Not possible to type to the field 'listname'",
                15);
        //ok
        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='OK']"),
                "button 'ok' not clicable",
                7);
        //x
        mainPageObject.waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"),
                "not possible to close page using 'x'",
                10);
        //PART 2
        //find and add to list 2-nd article

        mainPageObject.waitForElementAndClick(
                By.id("search_container"),
                "can not find Element 'Search bar'",
                5);

        mainPageObject.waitForElementAndSendKeys(By.id("search_container"),
                "java",
                "can not find Element with text 'Search Wikipedia'",
                10);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "element not found", 10);
        mainPageObject.waitElementPresent(By.xpath("//*[@content-desc='More options']"), "menu not present");

        mainPageObject.waitForElementAndClick(By.xpath("//*[@content-desc='More options']"),
                "Not possible to open menu",
                18);
        mainPageObject.waitElementPresent(By.xpath("//*[@text='Add to reading list']"), "element 'Add to reading list' not present");
        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "element menu 'Add to reading list' not present",
                18);
        Thread.sleep(2000);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='" + folderName + "']"),
                "the folder not present",
                15);

        //ckick x
        mainPageObject.waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"),
                "not possible to close page using 'x'",
                15);

        //PART 3 goTo saved lists and check, that articles are at list
        //goTo saved lists
        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "err",
                15);
        //click on my folder
        mainPageObject.waitElementPresent(By.xpath("//*[@text='" + folderName + "']"), "");
        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='" + folderName + "']"),
                "the folder not present",
                15);

        mainPageObject.waitElementPresent(By.id("page_list_item_container"), "");
        // check, that articles are at list and count
        int articlesCount = driver.findElements(By.id("page_list_item_container")).size();
//   System.out.println(articlesCount);
        Assert.assertEquals(2, articlesCount);


//        delete first article by swipe
        mainPageObject.swipeElementToLeft(By.xpath("//*[@text='Interface (computing)']"), "element not swiped");
//

        int articleCountAfterDeletion = driver.findElements(By.id("page_list_item_container")).size();
        Assert.assertEquals(1, articleCountAfterDeletion);
        String articleTytleInList = mainPageObject.waitElementPresent(By.id("page_list_item_title"),
                "",
                15).getText();

        mainPageObject.waitForElementAndClick(By.id("page_list_item_container"), "", 5);
        String articleTytle = mainPageObject.waitElementPresent(By.id("view_page_title_text"), "", 10).getText();

        //Assert title
        Assert.assertEquals("Oops not equal text", articleTytleInList, articleTytle);
    }

    @Test
    public void testHW6assertTitlePresent() {
        mainPageObject.waitForElementAndClick(By.id("view_featured_article_card_image"), "", 7);
        Assert.assertTrue(mainPageObject.isElementPresent(By.id("view_page_title_text")));

    }
}




