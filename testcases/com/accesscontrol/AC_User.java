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
import pageObjects.accesscontrol.ac_user.AddACUserPageObject;
import pageObjects.accesscontrol.ac_user.DetailACUserPageObject;
import pageObjects.accesscontrol.ac_user.EditACUserPageObject;
import pageObjects.accesscontrol.ac_user.ACUserListPageObject;

public class AC_User extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ACUserListPageObject userListPage;
	AddACUserPageObject addUserPage;
	DetailACUserPageObject detailUserPage;
	EditACUserPageObject editUserPage;
	String userName, userNameUpdate;
	public static String userNameUpdateCookie;

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
		
		userName = "User 1";
		
		userNameUpdate = "User 1 Update";
	}

	@Test
	public void User_01_Add_New_User() {
		log.info("User_01 - Step 01: Open 'Danh sách người dùng' menu");
		dashboardPage.openMenuPage(driver, "Danh sách người dùng");
		userListPage = PageGenerator.getUserListPage(driver);
		
		log.info("User_01 - Step 02: Click 'Thêm Người dùng' button");
		userListPage.clickToButtonByIDName(driver, "Thêm Người dùng");
		addUserPage = PageGenerator.getAddUserPage(driver);
		
		log.info("User_01 - Step 03: Enter valid data to required fields");
		addUserPage.enterToTextboxByIDName(driver, "name", userName);
		
		log.info("User_01 - Step 04: Click 'Thêm Người dùng' button");
		addUserPage.clickToButtonByIDName(driver, "Thêm Người dùng");
		
		log.info("User_01 - Step 05: Verify detail user");
		detailUserPage = PageGenerator.getDetailUserPage(driver);
		verifyTrue(detailUserPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailUserPage.getValueFieldByAttribute(driver, "name"), userName);
		detailUserPage.sleepInSecond(1);
	}

	@Test
	public void User_02_Edit_User() {
		log.info("User_02 - Step 01: Click 'Sửa' icon");
		detailUserPage.clickToEditIcon(driver);
		editUserPage = PageGenerator.getEditUserPage(driver);
		
		log.info("User_02 - Step 02: Enter valid data to required fields");
		editUserPage.enterToTextboxByIDName(driver, "name", userNameUpdate);
		
		log.info("User_02 - Step 03: Click 'Cập nhật Người dùng' button");
		editUserPage.clickToButtonByIDName(driver, "Cập nhật Người dùng");
		
		log.info("User_02 - Step 04: Verify detail user");
		detailUserPage = PageGenerator.getDetailUserPage(driver);
		verifyTrue(detailUserPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailUserPage.getValueFieldByAttribute(driver, "name"), userNameUpdate);
		userNameUpdateCookie = detailUserPage.getValueFieldByAttribute(driver, "name");
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true) 
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
