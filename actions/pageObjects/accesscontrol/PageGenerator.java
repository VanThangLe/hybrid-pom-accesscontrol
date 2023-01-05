package pageObjects.accesscontrol;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static AddUserPageObject getAddUserPage(WebDriver driver) {
		return new AddUserPageObject(driver);
	}
	
	public static DashboardPageObject getDashboardPage(WebDriver driver) {
		return new DashboardPageObject(driver);
	}
	
	public static UserListPageObject getUserListPage(WebDriver driver) {
		return new UserListPageObject(driver);
	}

	public static DetailUserPageObject getDetailUserPage(WebDriver driver) {
		return new DetailUserPageObject(driver);
	}
}
