package pageObjects.accesscontrol;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.accesscontrol.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;
	
	public LoginPageObject(WebDriver _driver) {
		this.driver = _driver;
	}

	public DashboardPageObject loginToSystem(String email, String password) {
		waitForElementVisible(driver, LoginPageUI.TEXTBOX_BY_ID_NAME, "email");
		sendkeyToElement(driver, LoginPageUI.TEXTBOX_BY_ID_NAME, email, "email");
		sendkeyToElement(driver, LoginPageUI.TEXTBOX_BY_ID_NAME, password, "password");
		clickToElement(driver, LoginPageUI.BUTTON_BY_ID_NAME, "Đăng nhập");
		return PageGenerator.getDashboardPage(driver);
	}
}
