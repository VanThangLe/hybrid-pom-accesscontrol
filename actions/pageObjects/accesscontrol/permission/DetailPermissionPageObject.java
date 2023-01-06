package pageObjects.accesscontrol.permission;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.accesscontrol.DetailUserPageUI;

public class DetailPermissionPageObject extends BasePage {
	private WebDriver driver;

	public DetailPermissionPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToDetail() {
		waitForElementClickAble(driver, DetailUserPageUI.EDIT_ICON);
		clickToElement(driver, DetailUserPageUI.EDIT_ICON);
	}
}
