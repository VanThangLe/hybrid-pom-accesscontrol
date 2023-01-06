package pageObjects.accesscontrol.entry;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.accesscontrol.DetailUserPageUI;

public class DetailEntryPageObject extends BasePage {
	private WebDriver driver;

	public DetailEntryPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToDetail() {
		waitForElementClickAble(driver, DetailUserPageUI.EDIT_ICON);
		clickToElement(driver, DetailUserPageUI.EDIT_ICON);
	}
}
