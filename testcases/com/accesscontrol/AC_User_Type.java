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
import pageObjects.accesscontrol.ac_user.AddUserPageObject;
import pageObjects.accesscontrol.ac_user_type.AddACUserTypePageObject;
import pageObjects.accesscontrol.ac_user_type.DetailACUserTypePageObject;
import pageObjects.accesscontrol.ac_user_type.EditACUserTypePageObject;
import pageObjects.accesscontrol.ac_user_type.ACUserTypeListPageObject;

public class AC_User_Type extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ACUserTypeListPageObject userTypeListPage;
	AddACUserTypePageObject addUserTypePage;
	EditACUserTypePageObject editUserTypePage;
	DetailACUserTypePageObject detailUserTypePage;
	AddUserPageObject addUserPage;
	String userTypeName, userTypeNameUpdate;

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
		
		userTypeName = "Type 1";
		
		userTypeNameUpdate = "Type 1 Update";
	}

	@Test
	public void UserType_01_Add_New_UserType() {
		log.info("UserType_01 - Step 01: Open 'Loại người dùng' menu");
		dashboardPage.openMenuPage(driver, "Loại người dùng");
		userTypeListPage = PageGenerator.getACUserTypeListPage(driver);
		
		log.info("UserType_01 - Step 02: Click 'Thêm Loại người dùng' button");
		userTypeListPage.clickToButtonByIDName(driver, "Thêm Loại người dùng");
		addUserTypePage = PageGenerator.getAddACUserTypePage(driver);
		
		log.info("UserType_01 - Step 03: Enter valid data to required fields");
		addUserTypePage.enterToTextboxByIDName(driver, "name", userTypeName);
		
		log.info("UserType_01 - Step 04: Click 'Thêm Loại người dùng' button");
		addUserTypePage.clickToButtonByIDName(driver, "Thêm Loại người dùng");
		
		log.info("UserType_01 - Step 05: Verify detail user type");
		detailUserTypePage = PageGenerator.getDetailACUserTypePage(driver);
		verifyTrue(detailUserTypePage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailUserTypePage.getValueFieldByAttribute(driver, "name"), userTypeNameUpdate);
		detailUserTypePage.sleepInSecond(1);
	}

	@Test
	public void UserType_02_Edit_UserType() {
		log.info("UserType_02 - Step 01: Click 'Sửa' icon");
		detailUserTypePage.clickToEditIcon(driver);
		editUserTypePage = PageGenerator.getEditACUserTypePage(driver);
		
		log.info("UserType_02 - Step 02: Enter valid data to required fields");
		editUserTypePage.enterToTextboxByIDName(driver, "name", userTypeNameUpdate);
		
		log.info("UserType_02 - Step 03: Click 'Cập nhật Loại người dùng' button");
		editUserTypePage.clickToButtonByIDName(driver, "Cập nhật Loại người dùng");
		
		log.info("UserType_02 - Step 04: Verify detail user type");
		detailUserTypePage = PageGenerator.getDetailACUserTypePage(driver);
		verifyTrue(detailUserTypePage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailUserTypePage.getValueFieldByAttribute(driver, "name"), userTypeNameUpdate);
	}

	@Test
	public void UserType_03_Open_Add_User_Screen() {
		log.info("UserType_03 - Step 01: Click 'Thêm Người dùng' button");
		detailUserTypePage.clickToButtonByIDName(driver, "Thêm Người dùng");
		addUserPage = PageGenerator.getAddACUserPage(driver);
		
		log.info("UserType_03 - Step 02: Verify enabled fields");
		verifyTrue(addUserPage.isFieldEnabledByID(driver, "name"));
		verifyTrue(addUserPage.isFieldEnabledByID(driver, "email"));
		verifyTrue(addUserPage.isFieldEnabledByID(driver, "phone"));
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
