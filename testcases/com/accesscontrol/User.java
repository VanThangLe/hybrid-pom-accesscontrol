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

public class User extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	UserListPageObject userListPage;
	AddUserPageObject addUserPage;
	EditUserPageObject editUserPage;
	DetailUserPageObject detailUserPage;
	AssignACProjectPageObject assignACProjectPage;
	String userName, email, password;
	public static String userNameCookie;

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
		email = "user1@elifetech.vn";
		password = "123456789";
	}

	@Test
	public void User_01_Add_New_User() {
		log.info("User_01 - Step 01: Open 'Tài khoản' menu");
		dashboardPage.openMenuPage(driver, "Tài khoản");
		userListPage = PageGenerator.getUserListPage(driver);
		
		log.info("User_01 - Step 02: Click 'Thêm Tài khoản' button");
		userListPage.clickToButtonByIDName(driver, "Thêm Tài khoản");
		addUserPage = PageGenerator.getAddUserPage(driver);
		
		log.info("User_01 - Step 03: Enter valid data to required fields");
		addUserPage.enterToTextboxByIDName(driver, "name", userName);
		addUserPage.enterToTextboxByIDName(driver, "email", email);
		addUserPage.enterToTextboxByIDName(driver, "password", password);
		
		log.info("User_01 - Step 04: Click 'Thêm Tài khoản' button");
		addUserPage.clickToButtonByIDName(driver, "Thêm Tài khoản");
		
		log.info("User_01 - Step 05: Verify detail account");
		detailUserPage = PageGenerator.getDetailUserPage(driver);
		verifyTrue(detailUserPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailUserPage.getValueFieldByAttribute(driver, "name"), userName);
		verifyEquals(detailUserPage.getValueFieldByAttribute(driver, "email"), email);
		userNameCookie = detailUserPage.getValueFieldByAttribute(driver, "name");
		detailUserPage.sleepInSecond(1);
	}

	@Test
	public void User_02_Assign_AC_Project() {
		log.info("User_02 - Step 01: Click 'Gắn Dự án' button");
		detailUserPage.clickToButtonByIDName(driver, "Gắn Dự án");
		assignACProjectPage = PageGenerator.getAssignACProjectPage(driver);
		
		log.info("User_02 - Step 02: Select project");
		assignACProjectPage.selectItemInCustomDropdownByAttribute(driver, "projects-search-input",  AC_Project.acProjectNameCookie);
		
		log.info("User_02 - Step 03: Click 'Gắn Dự án' button");
		assignACProjectPage.clickToButtonByIDName(driver, "Gắn Dự án");
		detailUserPage = PageGenerator.getDetailUserPage(driver);
		
		log.info("User_02 - Step 04: Verify select 'Dự án' success");
		verifyEquals(detailUserPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "projects", "1", "2"), AC_Project.acProjectNameCookie);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}