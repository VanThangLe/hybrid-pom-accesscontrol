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
import pageObjects.accesscontrol.role.AddRolePageObject;
import pageObjects.accesscontrol.role.AssignUserPageObject;
import pageObjects.accesscontrol.role.DetailRolePageObject;
import pageObjects.accesscontrol.role.RoleListPageObject;


public class Role extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	RoleListPageObject roleListPage;
	AddRolePageObject addRolePage;
	DetailRolePageObject detailRolePage;
	AssignUserPageObject assignUserPage;
	String roleName;

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
		
		roleName = "Role 1";
	}

	@Test
	public void Role_01_Add_New_Role() {
		log.info("Role_01 - Step 01: Open 'Nhóm quyền tài khoản' menu");
		dashboardPage.openMenuPage(driver, "Nhóm quyền tài khoản");
		roleListPage = PageGenerator.getRoleListPage(driver);
		
		log.info("Role_01 - Step 02: Click 'Thêm Nhóm quyền tài khoản' button");
		roleListPage.clickToButtonByIDName(driver, "Thêm Nhóm quyền tài khoản");
		addRolePage = PageGenerator.getAddRolePage(driver);
		
		log.info("Role_01 - Step 03: Enter valid data to required fields");
		addRolePage.enterToTextboxByIDName(driver, "name", roleName);
		for(int i = 0; i <= 71; i++) {
			addRolePage.clickToRadioByLabel(driver, "" + 1x);
		}
		
		log.info("Role_01 - Step 04: Click 'Thêm Nhóm quyền tài khoản' button");
		addRolePage.clickToButtonByIDName(driver, "Thêm Nhóm quyền tài khoản");
		
		log.info("Role_01 - Step 05: Verify detail role");
		detailRolePage = PageGenerator.getDetailRolePage(driver);
		verifyTrue(detailRolePage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailRolePage.getValueFieldByAttribute(driver, "name"), roleName);
	}
	
	@Test
	public void Role_01_Assign_User() {
		log.info("Role_01 - Step 01: Click 'Gắn Dự án' button");
		detailRolePage.clickToButtonByIDName(driver, "Gắn Dự án");
		assignUserPage = PageGenerator.getAssignUserPage(driver);
		
		log.info("Role_01 - Step 02: Select project");
		assignUserPage.selectItemInCustomDropdownByAttribute(driver, "projects-search-input",  AC_Project.acProjectNameCookie);
		
		log.info("Role_01 - Step 03: Click 'Gắn Dự án' button");
		assignUserPage.clickToButtonByIDName(driver, "Gắn Dự án");
		detailRolePage = PageGenerator.getDetailRolePage(driver);
		
		log.info("Role_01 - Step 04: Verify select 'Dự án' success");
		verifyEquals(detailRolePage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "projects", "1", "2"), AC_Project.acProjectNameCookie);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}