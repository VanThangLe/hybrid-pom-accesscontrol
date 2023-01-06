package pageObjects.accesscontrol.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.accesscontrol.user.DetailUserPageUI;

public class DetailUserPageObject extends BasePage {
	private WebDriver driver;

	public DetailUserPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToEditIcon() {
		waitForElementClickAble(driver, DetailUserPageUI.EDIT_ICON);
		clickToElement(driver, DetailUserPageUI.EDIT_ICON);
	}
}