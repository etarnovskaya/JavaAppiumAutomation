package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUA;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.List;

public class HomeWorks extends CoreTestCase {
    SearchPageObject searchPageObject;
    ArticlePageObject articlePageObject;
    NavigationUA navigationUA;
    MyListsPageObject myListsPageObject;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        searchPageObject = new SearchPageObject(driver);
        articlePageObject = new ArticlePageObject(driver);
        navigationUA = new NavigationUA(driver);
        myListsPageObject = new MyListsPageObject(driver);
    }

    @Test
    /*
Method, should check presence of text “Search…”
in the 'Search' field before typing text
*/
    public void testHW2IsSearchFieldHasCorrectText() {
        searchPageObject.initSearchInput();
        String fieldText = searchPageObject.waitForElementAndGetText(By.id("search_src_text"));
        assertEquals(
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
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("interface");
        searchPageObject.waitForCancellButtonToAppear();
        searchPageObject.clickOnCancellSearchButton();
        searchPageObject.waitForCancellButtonToDisappear();

        assertEquals(false, articlePageObject.isArticlePresent());

    }

    @Test
    /*test should:
1. search any word
2. To check, that all search results does contain this word
 */
    public void testHW4SearchByWordAndCheckThatAllResultsContainsThisWord() {
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("interface");
        assertTrue(articlePageObject.isArticlePresent());
        List<String> titles = searchPageObject.getArticlesList();
        for (String title : titles) {
            assertTrue(title.contains("interface"));
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
        String folderName = "My Homework";

        //PART 1
        // Search article, select one and add to List
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("interface");
        searchPageObject.clickByArticleWithSubstring("Interface (computing)");
        articlePageObject.addArticleToMyList(folderName, true);
        navigationUA.closePage();

        //PART 2
        //find and add to list 2-nd article
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.addArticleToMyList(folderName, false);
        navigationUA.closePage();

        //PART 3 goTo saved lists and check, that articles are at list
        navigationUA.goToSavedLists();
        myListsPageObject.openFolderByName(folderName);

        // check, that articles are at list and count
        int articlesCount = myListsPageObject.getArticlesCountInList();
        assertEquals(2, articlesCount);

        // delete first article by swipe
        articlePageObject.deleteArticleBySwipe("Interface (computing)");
        int articleCountAfterDeletion = myListsPageObject.getArticlesCountInList();

        assertEquals(1, articleCountAfterDeletion);
        String articleTytleInList = articlePageObject.getArticleTitleInList();

        articlePageObject.clickOnTheArticleInList();
        String articleTitle = articlePageObject.getArticleTitleInToArticle();

        //Assert title
        assertEquals("Oops not equal text", articleTytleInList, articleTitle);
    }


    @Test
    public void testHW6assertTitlePresent() {
        articlePageObject.openArticleOnTheMainPage();
        assertTrue(articlePageObject.isElementPresent(By.id("view_page_title_text")));

    }
}




