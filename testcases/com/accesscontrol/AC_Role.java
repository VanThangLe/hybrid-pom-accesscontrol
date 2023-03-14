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
import pageObjects.accesscontrol.ac_role.AddACRolePageObject;
import pageObjects.accesscontrol.ac_role.AssignACUserPageObject;
import pageObjects.accesscontrol.ac_role.DetailACRolePageObject;
import pageObjects.accesscontrol.ac_role.ACRoleListPageObject;

public class AC_Role extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ACRoleListPageObject acRoleListPage;
	AddACRolePageObject addACRolePage;
	DetailACRolePageObject detailACRolePage;
	AssignACUserPageObject assignACUserPage;
	String acRoleName, acRoleCode;
	public static String acRoleNameCookie;

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
		
		acRoleName = "Role 1";
		acRoleCode = "role1";
	}

	@Test
	public void AC_Role_01_Add_New_AC_Role() {
		log.info("AC_Role_01 - Step 01: Open 'Nhóm người dùng' menu");
		dashboardPage.openMenuPage(driver, "Nhóm người dùng");
		acRoleListPage = PageGenerator.getACRoleListPage(driver);
		
		log.info("AC_Role_01 - Step 02: Click 'Thêm Nhóm người dùng' button");
		acRoleListPage.clickToButtonByIDName(driver, "Thêm Nhóm người dùng");
		addACRolePage = PageGenerator.getAddACRolePage(driver);
		
		log.info("AC_Role_01 - Step 03: Enter valid data to required fields");
		addACRolePage.enterToTextboxByIDName(driver, "role_name", acRoleName);
		addACRolePage.enterToTextboxByIDName(driver, "role_code", acRoleCode);
		
		log.info("AC_Role_01 - Step 04: Click 'Thêm Nhóm người dùng' button");
		addACRolePage.clickToButtonByIDName(driver, "Thêm Nhóm người dùng");
		
		log.info("AC_Role_01 - Step 05: Verify detail role");
		detailACRolePage = PageGenerator.getDetailACRolePage(driver);
		verifyTrue(detailACRolePage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailACRolePage.getValueFieldByAttribute(driver, "role_name"), acRoleName);
		verifyEquals(detailACRolePage.getValueFieldByAttribute(driver, "role_code"), acRoleCode);
	}

	@Test
	public void AC_Role_02_Assign_AC_User() {
		log.info("AC_Role_02 - Step 01: Click 'Gắn Người dùng' button");
		detailACRolePage.clickToButtonByIDName(driver, "Gắn Người dùng");
		assignACUserPage = PageGenerator.getAssignACUserPage(driver);
		
		log.info("AC_Role_02 - Step 02: Select user");
		assignACUserPage.selectItemInCustomDropdownByAttribute(driver, "ac-users-search-input", AC_User.acUserNameCookie);
		
		log.info("AC_Role_02 - Step 03: Click 'Gắn Người dùng' button");
		assignACUserPage.clickToButtonByIDName(driver, "Gắn Người dùng");
		detailACRolePage = PageGenerator.getDetailACRolePage(driver);
		
		log.info("AC_Role_02 - Step 04: Verify select 'Người dùng' success");
		verifyEquals(detailACRolePage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "users", "1", "2"), AC_User.acUserNameCookie);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
