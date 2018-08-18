package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    private static final String FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
    ARTICLE = "page_list_item_container";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
    public static String getFolderXpathByName(String folderName){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName );
    }

    public void openFolderByName(String folderName) {
        String folderNameXpath = getFolderXpathByName(folderName);
        waitElementPresent(By.xpath(folderNameXpath), "");
        waitForElementAndClick(By.xpath(folderNameXpath),
                "the folder not present",
                15);
        waitElementPresent(By.id(ARTICLE), "");
    }

    public int getArticlesCountInList() {
        return driver.findElements(By.id(ARTICLE)).size();
    }
}
