package com.accesscontrol.projectadmin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.accesscontrol.DashboardPageObject;
import pageObjects.accesscontrol.LoginPageObject;
import pageObjects.accesscontrol.PageGenerator;
import pageObjects.accesscontrol.user.AddUserPageObject;
import pageObjects.accesscontrol.user.DetailUserPageObject;
import pageObjects.accesscontrol.user.EditUserPageObject;
import pageObjects.accesscontrol.user.UserListPageObject;

public class User extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	UserListPageObject userListPage;
	AddUserPageObject addUserPage;
	DetailUserPageObject detailUserPage;
	EditUserPageObject editUserPage;
	String userName, userNameUpdate;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-condition: Step 01 - Open browser '" + browserName + "'and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);

		log.info("Pre-condition: Step 02 - Login with Admin role");
		dashboardPage = loginPage.loginToSystem(driver, GlobalConstants.PROJECT_ADMIN_EMAIL, GlobalConstants.PROJECT_ADMIN_PASSWORD);
		
		userName = "User 00001";
		userNameUpdate = "User 00001 Update";
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
		
		log.info("User_01 - Step 04: Click 'Thêm Người dùng'");
		addUserPage.clickToButtonByIDName(driver, "Thêm Người dùng");
		
		log.info("User_01 - Step 05: Verify detail User");
		detailUserPage = PageGenerator.getDetailUserPage(driver);
		verifyTrue(detailUserPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailUserPage.getValueFieldByAttribute(driver, "name"), userName);
	}

	@Test
	public void User_02_Edit_User() {
		log.info("User_02 - Step 01: Click 'Sửa người dùng' icon");
		detailUserPage.clickToDetail();
		editUserPage = PageGenerator.getEditUserPage(driver);
		
		log.info("User_02 - Step 02: Enter valid data to required fields");
		editUserPage.enterToTextboxByIDName(driver, "name-them-nguoi-dung-text-field", userNameUpdate);
		
		log.info("User_02 - Step 03: Click 'Cập nhật Người dùng'");
		editUserPage.clickToButtonByIDName(driver, "Cập nhật Người dùng");
		
		log.info("User_02 - Step 04: Verify detail User");
		detailUserPage = PageGenerator.getDetailUserPage(driver);
		verifyTrue(detailUserPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailUserPage.getValueFieldByAttribute(driver, "name"), userNameUpdate);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true) 
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
