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
import pageObjects.accesscontrol.DetailUserPageObject;
import pageObjects.accesscontrol.LoginPageObject;
import pageObjects.accesscontrol.PageGenerator;
import pageObjects.accesscontrol.UserListPageObject;

public class User extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	UserListPageObject userListPage;
	AddUserPageObject addUserPage;
	DetailUserPageObject detailUserPage;
	String userName, phone;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-condition: Step 01 - Open browser '" + browserName + "'and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);

		log.info("Pre-condition: Step 02 - Login with Admin role");
		dashboardPage = loginPage.loginToSystem(driver, GlobalConstants.PROJECT_ADMIN_EMAIL, GlobalConstants.PROJECT_ADMIN_PASSWORD);
		
		userName = "User 00001";
		phone = "0000000001";
	}

	@Test
	public void User_01_Add_New_User() {
		log.info("User_01 - Step 01: Open 'Danh sách người dùng' menu");
		dashboardPage.openMenuPage(driver, "Danh sách người dùng ");
		userListPage = PageGenerator.getUserListPage(driver);
		
		log.info("User_01 - Step 02: Click 'Thêm Người dùng'");
		dashboardPage.clickToButtonByIDName(driver, "Thêm Người dùng");
		addUserPage = PageGenerator.getAddUserPage(driver);
		
		log.info("User_01 - Step 03: Enter valid data to required fields");
		addUserPage.enterToTextboxByIDName(driver, "name-them-nguoi-dung-text-field", userName);
		addUserPage.enterToTextboxByIDName(driver, "phone-them-nguoi-dung-text-field", phone);
		addUserPage.selectItemInDropdownByID(driver, "gender", "Nam");
		
		log.info("User_01 - Step 04: Click 'Thêm Người dùng'");
		addUserPage.clickToButtonByIDName(driver, "Thêm Người dùng");
		
		log.info("User_01 - Step 05: Verify detail User");
		verifyTrue(addUserPage.isSuccessMessageDisplayed(driver));
		detailUserPage = PageGenerator.getDetailUserPage(driver);
		verifyEquals(detailUserPage.getValueFieldByAttribute(driver, "name"), userName);
		verifyEquals(detailUserPage.getValueFieldByAttribute(driver, "phone"), phone);
	}

	@Test
	public void User_02_Edit_User() {
		detailUserPage.clickToDetail();
		
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
