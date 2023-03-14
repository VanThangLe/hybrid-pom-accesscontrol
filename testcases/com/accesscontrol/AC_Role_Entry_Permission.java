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
import pageObjects.accesscontrol.ac_role_entry_permission.AddACRoleEntryPermissionPageObject;
import pageObjects.accesscontrol.ac_role_entry_permission.DetailACRoleEntryPermissionPageObject;
import pageObjects.accesscontrol.ac_role_entry_permission.ACRoleEntryPermissionListPageObject;

public class AC_Role_Entry_Permission extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ACRoleEntryPermissionListPageObject acRoleEntryPermissionListPage;
	AddACRoleEntryPermissionPageObject addACRoleEntryPermissionPage;
	DetailACRoleEntryPermissionPageObject detailACRoleEntryPermissionPage;

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
	}

	@Test
	public void AC_Role_Entry_Permission_01_Add_New_AC_Role_Entry_Permission() {
		log.info("AC_Role_Entry_Permission_01 - Step 01: Open 'Phân quyền' menu");
		dashboardPage.openMenuPage(driver, "Phân quyền");
		acRoleEntryPermissionListPage = PageGenerator.getACRoleEntryPermissionListPage(driver);
		
		log.info("AC_Role_Entry_Permission_01 - Step 02: Click 'Thêm Phân quyền' button");
		acRoleEntryPermissionListPage.clickToButtonByIDName(driver, "Thêm Phân quyền");
		addACRoleEntryPermissionPage = PageGenerator.getAddACRoleEntryPermissionPage(driver);
		
		log.info("AC_Role_Entry_Permission_01 - Step 03: Enter valid data to required fields");
		addACRoleEntryPermissionPage.selectItemInCustomDropdownByAttribute(driver, "ac-roles-search-input", AC_Role.acRoleNameCookie);
		addACRoleEntryPermissionPage.selectItemInMultiDropdown(driver, AC_Entry.acEntryNameCookie);
		
		log.info("AC_Role_Entry_Permission_01 - Step 04: Click 'Thêm Phân quyền' button");
		addACRoleEntryPermissionPage.clickToButtonByIDName(driver, "Thêm Phân quyền");
		
		log.info("AC_Role_Entry_Permission_01 - Step 05: Verify detail role entry permission");
		detailACRoleEntryPermissionPage = PageGenerator.getDetailACRoleEntryPermissionPage(driver);
		verifyTrue(detailACRoleEntryPermissionPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailACRoleEntryPermissionPage.getValueFieldByAttribute(driver, "role"), AC_Role.acRoleNameCookie);
		verifyEquals(detailACRoleEntryPermissionPage.getValueFieldByAttribute(driver, "entry"), AC_Entry.acEntryNameCookie);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
