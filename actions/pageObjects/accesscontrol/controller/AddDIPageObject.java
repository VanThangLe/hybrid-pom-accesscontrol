package pageObjects.accesscontrol.controller;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.accesscontrol.controller.AddDIPageUI;

public class AddDIPageObject extends BasePage {
	private WebDriver driver;
	
	public AddDIPageObject(WebDriver _driver) {
		this.driver = _driver;
	}
	
	public void clickToEditIcon() {
		waitForElementClickAble(driver, AddDIPageUI.EDIT_ICON);
		clickToElement(driver, AddDIPageUI.EDIT_ICON);
	}
}
