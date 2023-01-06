package pageObjects.accesscontrol.facereader;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.accesscontrol.DetailUserPageUI;

public class DetailFaceReaderPageObject extends BasePage {
	private WebDriver driver;

	public DetailFaceReaderPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToDetail() {
		waitForElementClickAble(driver, DetailUserPageUI.EDIT_ICON);
		clickToElement(driver, DetailUserPageUI.EDIT_ICON);
	}
}
