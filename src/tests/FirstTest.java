package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUA;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class FirstTest extends CoreTestCase {
    SearchPageObject searchPageObject;
    ArticlePageObject articlePageObject;
    NavigationUA navigationUA;
    MyListsPageObject myListsPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        searchPageObject = new SearchPageObject(driver);
        articlePageObject = new ArticlePageObject(driver);
        navigationUA = new NavigationUA(driver);
        myListsPageObject = new MyListsPageObject(driver);
    }

    @Test
    public void testFirst() {
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Appium");
    }


    @Test
    public void testSearchForJavaArticles() {
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testHomework2() {
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("interface");
        searchPageObject.waitForSearchResult("Interface (computing)");
    }

    @Test
    public void testSwipeTest() {
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("App");
        searchPageObject.swipeToTheItem();

//        waitElementPresentByXPath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
//                "Can not find",
//                15);
//        swipeUp(2000);
//        swipeUp(2000);
//        swipeUp(2000);
    }

    @Test
    public void testSaveArticleToMyList() throws InterruptedException {
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        String folderName = "Learning programming";

        articlePageObject.addArticleToMyList(folderName, true);
        navigationUA.closePage();
        navigationUA.goToSavedLists();
     myListsPageObject.openFolderByName(folderName);
        articlePageObject.deleteArticleBySwipe("Object-oriented programming language");
     //  articlePageObject.isArticleNotPresentByName("Object-oriented programming language");
        articlePageObject.waitElementNotPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "article 'object-oriented programming language' not deleted", 5);
    }


}
