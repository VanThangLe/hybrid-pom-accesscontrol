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
	ACRoleEntryPermissionListPageObject permissionListPage;
	AddACRoleEntryPermissionPageObject addPermissionPage;
	DetailACRoleEntryPermissionPageObject detailPermissionPage;

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
	public void Permission_01_Add_New_Permission() {
		log.info("Permission_01 - Step 01: Open 'Phân quyền' menu");
		dashboardPage.openMenuPage(driver, "Phân quyền");
		permissionListPage = PageGenerator.getACRoleEntryPermissionListPage(driver);
		
		log.info("Permission_01 - Step 02: Click 'Thêm Phân quyền' button");
		permissionListPage.clickToButtonByIDName(driver, "Thêm Phân quyền");
		addPermissionPage = PageGenerator.getAddACRoleEntryPermissionPage(driver);
		
		log.info("Permission_01 - Step 03: Enter valid data to required fields");
		addPermissionPage.selectItemInCustomDropdownByAttribute(driver, "ac-roles-search-input", AC_Role.acRoleNameCookie);
		addPermissionPage.selectItemInMultiDropdown(driver, AC_Entry.entryNameUpdateCookie);
		
		log.info("Permission_01 - Step 04: Click 'Thêm Phân quyền' button");
		addPermissionPage.clickToButtonByIDName(driver, "Thêm Phân quyền");
		
		log.info("Permission_01 - Step 05: Verify detail user group");
		detailPermissionPage = PageGenerator.getDetailACRoleEntryPermissionPage(driver);
		verifyTrue(detailPermissionPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailPermissionPage.getValueFieldByAttribute(driver, "role"), AC_Role.acRoleNameCookie);
		verifyEquals(detailPermissionPage.getValueFieldByAttribute(driver, "entry"), AC_Entry.entryNameUpdateCookie);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
