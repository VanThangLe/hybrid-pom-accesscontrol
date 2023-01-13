package com.accesscontrol.projectadmin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.accesscontrol.common.LoginProjectAdmin;

import commons.BaseTest;
import pageObjects.accesscontrol.DashboardPageObject;
import pageObjects.accesscontrol.LoginPageObject;
import pageObjects.accesscontrol.PageGenerator;
import pageObjects.accesscontrol.permission.AddPermissionPageObject;
import pageObjects.accesscontrol.permission.DetailPermissionPageObject;
import pageObjects.accesscontrol.permission.PermissionListPageObject;

public class Permission extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	PermissionListPageObject permissionListPage;
	AddPermissionPageObject addPermissionPage;
	DetailPermissionPageObject detailPermissionPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-condition: Step 01 - Open browser '" + browserName + "'and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);

		log.info("Pre-condition: Step 02 - Set login page cookie");
		loginPage.setAllCookies(driver, LoginProjectAdmin.loginPageCookie);
		loginPage.sleepInSecond(2);
		loginPage.refreshCurrentPage(driver);
		dashboardPage = PageGenerator.getDashboardPage(driver);
	}

	@Test
	public void Permission_01_Add_New_Permission() {
		log.info("Permission_01 - Step 01: Open 'Phân quyền' menu");
		dashboardPage.openMenuPage(driver, "Phân quyền");
		permissionListPage = PageGenerator.getPermissionListPage(driver);
		
		log.info("Permission_01 - Step 02: Click 'Thêm Phân quyền' button");
		permissionListPage.clickToButtonByIDName(driver, "Thêm Phân quyền");
		addPermissionPage = PageGenerator.getAddPermissionPage(driver);
		
		log.info("Permission_01 - Step 03: Enter valid data to required fields");
		addPermissionPage.selectItemInCustomDropdownByAttribute(driver, "ac-roles-search-input", UserGroup.userGroupNameUpdateCookie);
		addPermissionPage.selectItemInMultiDropdown(driver, Entry.entryNameUpdateCookie);
		
		log.info("Permission_01 - Step 04: Click 'Thêm Phân quyền' button");
		addPermissionPage.clickToButtonByIDName(driver, "Thêm Phân quyền");
		
		log.info("Permission_01 - Step 05: Verify detail user group");
		detailPermissionPage = PageGenerator.getDetailPermissionPage(driver);
		verifyTrue(detailPermissionPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailPermissionPage.getValueFieldByAttribute(driver, "role"), UserGroup.userGroupNameUpdateCookie);
		verifyEquals(detailPermissionPage.getValueFieldByAttribute(driver, "entry"), Entry.entryNameUpdateCookie);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
