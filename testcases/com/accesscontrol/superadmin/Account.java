package com.accesscontrol.superadmin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.accesscontrol.common.LoginSuperAdmin;

import commons.BaseTest;
import pageObjects.accesscontrol.DashboardPageObject;
import pageObjects.accesscontrol.LoginPageObject;
import pageObjects.accesscontrol.PageGenerator;
import pageObjects.accesscontrol.account.AccountListPageObject;
import pageObjects.accesscontrol.account.AddAccountPageObject;
import pageObjects.accesscontrol.account.AssignProjectPageObject;
import pageObjects.accesscontrol.account.DetailAccountPageObject;
import pageObjects.accesscontrol.account.EditAccountPageObject;

public class Account extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	AccountListPageObject accountListPage;
	AddAccountPageObject addAccountPage;
	EditAccountPageObject editAccountPage;
	DetailAccountPageObject detailAccountPage;
	AssignProjectPageObject assignProjectPage;
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
		accountListPage = PageGenerator.getAccountListPage(driver);
		
		log.info("Account_01 - Step 02: Click 'Thêm Tài khoản' button");
		accountListPage.clickToButtonByIDName(driver, "Thêm Tài khoản");
		addAccountPage = PageGenerator.getAddAccountPage(driver);
		
		log.info("Account_01 - Step 03: Enter valid data to required fields");
		addAccountPage.enterToTextboxByIDName(driver, "name", accountName);
		addAccountPage.enterToTextboxByIDName(driver, "email", email);
		addAccountPage.enterToTextboxByIDName(driver, "password", password);
		
		log.info("Account_01 - Step 04: Click 'Thêm Tài khoản' button");
		addAccountPage.clickToButtonByIDName(driver, "Thêm Tài khoản");
		
		log.info("Account_01 - Step 05: Verify detail user");
		detailAccountPage = PageGenerator.getDetailAccountPage(driver);
		verifyTrue(detailAccountPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailAccountPage.getValueFieldByAttribute(driver, "name"), accountName);
		verifyEquals(detailAccountPage.getValueFieldByAttribute(driver, "email"), email);
		detailAccountPage.sleepInSecond(1);
	}

	@Test
	public void Account_02_Edit_Account() {
		log.info("Account_02 - Step 01: Click 'Sửa' icon");
		detailAccountPage.clickToEditIcon(driver);
		editAccountPage = PageGenerator.getEditAccountPage(driver);
		
		log.info("Account_02 - Step 02: Enter valid data to required fields");
		editAccountPage.enterToTextboxByIDName(driver, "name", accountNameUpdate);
		editAccountPage.enterToTextboxByIDName(driver, "email", emailUpdate);
		
		log.info("Account_02 - Step 03: Click 'Cập nhật Tài khoản' button");
		editAccountPage.clickToButtonByIDName(driver, "Cập nhật Tài khoản");
		
		log.info("Account_02 - Step 04: Verify detail user group");
		detailAccountPage = PageGenerator.getDetailAccountPage(driver);
		verifyTrue(detailAccountPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailAccountPage.getValueFieldByAttribute(driver, "name"), accountNameUpdate);
		verifyEquals(detailAccountPage.getValueFieldByAttribute(driver, "email"), emailUpdate);
	}

	@Test
	public void Account_03_Assign_Project() {
		log.info("Account_03 - Step 01: Click 'Gắn Dự án' button");
		detailAccountPage.clickToButtonByIDName(driver, "Gắn Dự án");
		assignProjectPage = PageGenerator.getAssignProjectPage(driver);
		
		log.info("Account_03 - Step 02: Select user");
		assignProjectPage.selectItemInCustomDropdownByAttribute(driver, "projects-search-input",  Project.projectNameUpdateCookie);
		
		log.info("Account_03 - Step 03: Click 'Gắn Dự án' button");
		assignProjectPage.clickToButtonByIDName(driver, "Gắn Dự án");
		detailAccountPage = PageGenerator.getDetailAccountPage(driver);
		
		log.info("Account_03 - Step 04: Verify select 'Dự án' success");
		verifyEquals(detailAccountPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "projects", "1", "2"), 
				Project.projectNameUpdateCookie);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}