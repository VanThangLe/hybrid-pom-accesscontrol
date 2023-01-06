package pageObjects.accesscontrol.cardreader;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.accesscontrol.DetailUserPageUI;

public class DetailCardReaderPageObject extends BasePage {
	private WebDriver driver;

	public DetailCardReaderPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToDetail() {
		waitForElementClickAble(driver, DetailUserPageUI.EDIT_ICON);
		clickToElement(driver, DetailUserPageUI.EDIT_ICON);
	}
}
