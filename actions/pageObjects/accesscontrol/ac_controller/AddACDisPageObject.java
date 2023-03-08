package pageObjects.accesscontrol.ac_controller;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.accesscontrol.controller.AddDIPageUI;

public class AddACDisPageObject extends BasePage {
	private WebDriver driver;
	
	public AddACDisPageObject(WebDriver _driver) {
		this.driver = _driver;
	}
	
	public void clickToEditIcon() {
		waitForElementClickAble(driver, AddDIPageUI.EDIT_ICON);
		clickToElement(driver, AddDIPageUI.EDIT_ICON);
	}
}
