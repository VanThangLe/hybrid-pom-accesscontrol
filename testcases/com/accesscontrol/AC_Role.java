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
import pageObjects.accesscontrol.ac_role.EditACRolePageObject;
import pageObjects.accesscontrol.ac_role.ACRoleListPageObject;

public class AC_Role extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ACRoleListPageObject userGroupListPage;
	AddACRolePageObject addUserGroupPage;
	DetailACRolePageObject detailUserGroupPage;
	EditACRolePageObject editUserGroupPage;
	AssignACUserPageObject assignUserPage;
	String userGroupName, userGroupCode;
	String userGroupNameUpdate, userGroupCodeUpdate;
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
		
		userGroupName = "Group 1";
		userGroupCode = "groupcode1";
		
		userGroupNameUpdate = "Group 1 Update";
		userGroupCodeUpdate = "groupcode1update";
	}

	@Test
	public void UserGroup_01_Add_New_UserGroup() {
		log.info("UserGroup_01 - Step 01: Open 'Nhóm người dùng' menu");
		dashboardPage.openMenuPage(driver, "Nhóm người dùng");
		userGroupListPage = PageGenerator.getACRoleListPage(driver);
		
		log.info("UserGroup_01 - Step 02: Click 'Thêm Nhóm người dùng' button");
		userGroupListPage.clickToButtonByIDName(driver, "Thêm Nhóm người dùng");
		addUserGroupPage = PageGenerator.getAddACRolePage(driver);
		
		log.info("UserGroup_01 - Step 03: Enter valid data to required fields");
		addUserGroupPage.enterToTextboxByIDName(driver, "role_name", userGroupName);
		addUserGroupPage.enterToTextboxByIDName(driver, "role_code", userGroupCode);
		
		log.info("UserGroup_01 - Step 04: Click 'Thêm Nhóm người dùng' button");
		addUserGroupPage.clickToButtonByIDName(driver, "Thêm Nhóm người dùng");
		
		log.info("UserGroup_01 - Step 05: Verify detail user group");
		detailUserGroupPage = PageGenerator.getDetailACRolePage(driver);
		verifyTrue(detailUserGroupPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailUserGroupPage.getValueFieldByAttribute(driver, "role_name"), userGroupName);
		verifyEquals(detailUserGroupPage.getValueFieldByAttribute(driver, "role_code"), userGroupCode);
		detailUserGroupPage.sleepInSecond(1);
	}

	@Test
	public void UserGroup_02_Edit_UserGroup() {
		log.info("UserGroup_02 - Step 01: Click 'Sửa' icon");
		detailUserGroupPage.clickToEditIcon(driver);
		editUserGroupPage = PageGenerator.getEditACRolePage(driver);
		
		log.info("UserGroup_02 - Step 02: Enter valid data to required fields");
		editUserGroupPage.enterToTextboxByIDName(driver, "role_name", userGroupNameUpdate);
		editUserGroupPage.enterToTextboxByIDName(driver, "role_code", userGroupCodeUpdate);
		
		log.info("UserGroup_02 - Step 03: Click 'Cập nhật Người dùng' button");
		editUserGroupPage.clickToButtonByIDName(driver, "Cập nhật Người dùng");
		
		log.info("UserGroup_02 - Step 04: Verify detail user group");
		detailUserGroupPage = PageGenerator.getDetailACRolePage(driver);
		verifyTrue(detailUserGroupPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailUserGroupPage.getValueFieldByAttribute(driver, "role_name"), userGroupNameUpdate);
		verifyEquals(detailUserGroupPage.getValueFieldByAttribute(driver, "role_code"), userGroupCodeUpdate);
		acRoleNameCookie = detailUserGroupPage.getValueFieldByAttribute(driver, "role_name");
	}

	@Test
	public void UserGroup_03_Assign_User() {
		log.info("UserGroup_03 - Step 01: Click 'Gắn Người dùng' button");
		detailUserGroupPage.clickToButtonByIDName(driver, "Gắn Người dùng");
		assignUserPage = PageGenerator.getAssignACUserPage(driver);
		
		log.info("UserGroup_03 - Step 02: Select user");
		assignUserPage.selectItemInCustomDropdownByAttribute(driver, "ac-users-search-input", AC_User.userNameUpdateCookie);
		
		log.info("UserGroup_03 - Step 03: Click 'Gắn Người dùng' button");
		assignUserPage.clickToButtonByIDName(driver, "Gắn Người dùng");
		detailUserGroupPage = PageGenerator.getDetailACRolePage(driver);
		
		log.info("UserGroup_03 - Step 04: Verify select 'Người dùng' success");
		verifyEquals(detailUserGroupPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "users", "1", "2"), 
				AC_User.userNameUpdateCookie);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
