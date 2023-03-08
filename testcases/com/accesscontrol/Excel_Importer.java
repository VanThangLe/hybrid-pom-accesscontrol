package com.accesscontrol;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.accesscontrol.DashboardPageObject;
import pageObjects.accesscontrol.LoginPageObject;
import pageObjects.accesscontrol.PageGenerator;
import pageObjects.accesscontrol.user.AddUserPageObject;
import pageObjects.accesscontrol.user.AssignACProjectPageObject;
import pageObjects.accesscontrol.user.DetailUserPageObject;
import pageObjects.accesscontrol.user.EditUserPageObject;
import pageObjects.accesscontrol.user.UserListPageObject;

public class Excel_Importer extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	UserListPageObject accountListPage;
	AddUserPageObject addAccountPage;
	EditUserPageObject editAccountPage;
	DetailUserPageObject detailAccountPage;
	AssignACProjectPageObject assignProjectPage;
	String accountName, email, password;
	String accountNameUpdate, emailUpdate;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-condition: Step 01 - Open browser '" + browserName + "'and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);

		log.info("Pre-condition: Step 02 - Set login page cookie");
		loginPage.setAllCookies(driver, LoginSuperAdmin.loginPageCookie);
		loginPage.sleepInSecond(2);
		loginPage.refreshCurrentPage(driver);
		dashboardPage = PageGenerator.getDashboardPage(driver);
		
		accountName = "Account 1";
		email = "account1@elifetech.vn";
		password = "123456789";
		
		accountNameUpdate = "Account 1 Update";
		emailUpdate = "account1update@elifetech.vn";
	}

	@Test
	public void Account_01_Excel_Screen() {
		log.info("Account_01 - Step 01: Open 'Tài khoản' menu");
	}
	
	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}