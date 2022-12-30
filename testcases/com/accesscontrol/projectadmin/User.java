package com.accesscontrol.projectadmin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.accesscontrol.AddUserPageObject;
import pageObjects.accesscontrol.DashboardPageObject;
import pageObjects.accesscontrol.LoginPageObject;
import pageObjects.accesscontrol.PageGenerator;
import pageObjects.accesscontrol.UserListPageObject;

public class User extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	UserListPageObject userListPage;
	AddUserPageObject addUserPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-condition: Step 01 - Open browser '" + browserName + "'and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);

		log.info("Pre-condition: Step 02 - Login with Admin role");
		dashboardPage = loginPage.loginToSystem(driver, GlobalConstants.PROJECT_ADMIN_EMAIL, GlobalConstants.PROJECT_ADMIN_PASSWORD);
	}

	@Test
	public void User_01_Add_New_User() {
		log.info("User_01 - Step 01: Open 'Danh sách người dùng' menu");
		dashboardPage.openMenuPage(driver, "Danh sách người dùng ");
		userListPage = PageGenerator.getUserListPage(driver);
		
		log.info("User_01 - Step 02: Click 'Thêm người dùng'");
		userListPage.clickToButtonByIDName(driver, "Thêm người dùng");
		addUserPage = PageGenerator.getAddUserPage(driver);
		
		log.info("User_01 - Step 03: Enter valid data to required fields");
		addUserPage.enterToTextboxByIDName(driver, "name-them-nguoi-dung-text-field", "User 00001");
		addUserPage.enterToTextboxByIDName(driver, "phone-them-nguoi-dung-text-field", "0000000001");
		addUserPage.selectItemInDropdownByID(driver, "gender", "Nam");
		
		log.info("User_01 - Step 04: Click 'Thêm người dùng'");
		addUserPage.clickToButtonByIDName(driver, "Thêm người dùng");
	}

	@Test
	public void User_02_Edit_User() {
		
	}

	@Test
	public void User_03_Clone_User() {
		
	}

	
	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true) 
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
