package pageObjects.accesscontrol.ac_controller;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.accesscontrol.ac_controller.AddACDisPageUI;

public class AddACDisPageObject extends BasePage {
	private WebDriver driver;
	
	public AddACDisPageObject(WebDriver _driver) {
		this.driver = _driver;
	}
	
	public void clickToEditIcon() {
		waitForElementClickAble(driver, AddACDisPageUI.EDIT_ICON);
		clickToElement(driver, AddACDisPageUI.EDIT_ICON);
	}
}
