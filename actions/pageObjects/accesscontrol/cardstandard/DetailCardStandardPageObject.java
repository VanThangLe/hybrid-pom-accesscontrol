package pageObjects.accesscontrol.cardstandard;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.accesscontrol.DetailUserPageUI;

public class DetailCardStandardPageObject extends BasePage {
	private WebDriver driver;

	public DetailCardStandardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToDetail() {
		waitForElementClickAble(driver, DetailUserPageUI.EDIT_ICON);
		clickToElement(driver, DetailUserPageUI.EDIT_ICON);
	}
}
