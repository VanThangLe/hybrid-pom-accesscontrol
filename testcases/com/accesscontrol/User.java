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
	public void Account_01_Add_New_Account() {
		log.info("Account_01 - Step 01: Open 'Tài khoản' menu");
		dashboardPage.openMenuPage(driver, "Tài khoản");
		accountListPage = PageGenerator.getACUserListPage(driver);
		
		log.info("Account_01 - Step 02: Click 'Thêm Tài khoản' button");
		accountListPage.clickToButtonByIDName(driver, "Thêm Tài khoản");
		addAccountPage = PageGenerator.getAddACUserPage(driver);
		
		log.info("Account_01 - Step 03: Enter valid data to required fields");
		addAccountPage.enterToTextboxByIDName(driver, "name", accountName);
		addAccountPage.enterToTextboxByIDName(driver, "email", email);
		addAccountPage.enterToTextboxByIDName(driver, "password", password);
		
		log.info("Account_01 - Step 04: Click 'Thêm Tài khoản' button");
		addAccountPage.clickToButtonByIDName(driver, "Thêm Tài khoản");
		
		log.info("Account_01 - Step 05: Verify detail account");
		detailAccountPage = PageGenerator.getDetailACUserPage(driver);
		verifyTrue(detailAccountPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailAccountPage.getValueFieldByAttribute(driver, "name"), accountName);
		verifyEquals(detailAccountPage.getValueFieldByAttribute(driver, "email"), email);
		detailAccountPage.sleepInSecond(1);
	}

	@Test
	public void Account_02_Edit_Account() {
		log.info("Account_02 - Step 01: Click 'Sửa' icon");
		detailAccountPage.clickToEditIcon(driver);
		editAccountPage = PageGenerator.getEditACUserPage(driver);
		
		log.info("Account_02 - Step 02: Enter valid data to required fields");
		editAccountPage.enterToTextboxByIDName(driver, "name", accountNameUpdate);
		editAccountPage.enterToTextboxByIDName(driver, "email", emailUpdate);
		
		log.info("Account_02 - Step 03: Click 'Cập nhật Tài khoản' button");
		editAccountPage.clickToButtonByIDName(driver, "Cập nhật Tài khoản");
		
		log.info("Account_02 - Step 04: Verify detail account");
		detailAccountPage = PageGenerator.getDetailACUserPage(driver);
		verifyTrue(detailAccountPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailAccountPage.getValueFieldByAttribute(driver, "name"), accountNameUpdate);
		verifyEquals(detailAccountPage.getValueFieldByAttribute(driver, "email"), emailUpdate);
	}

	@Test
	public void Account_03_Assign_Project() {
		log.info("Account_03 - Step 01: Click 'Gắn Dự án' button");
		detailAccountPage.clickToButtonByIDName(driver, "Gắn Dự án");
		assignProjectPage = PageGenerator.getAssignACProjectPage(driver);
		
		log.info("Account_03 - Step 02: Select project");
		assignProjectPage.selectItemInCustomDropdownByAttribute(driver, "projects-search-input",  AC_Project.projectNameUpdateCookie);
		
		log.info("Account_03 - Step 03: Click 'Gắn Dự án' button");
		assignProjectPage.clickToButtonByIDName(driver, "Gắn Dự án");
		detailAccountPage = PageGenerator.getDetailACUserPage(driver);
		
		log.info("Account_03 - Step 04: Verify select 'Dự án' success");
		verifyEquals(detailAccountPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "projects", "1", "2"), 
				AC_Project.projectNameUpdateCookie);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}