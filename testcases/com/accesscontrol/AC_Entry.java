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
import pageObjects.accesscontrol.ac_entry.AddACEntryPageObject;
import pageObjects.accesscontrol.ac_entry.AssignACReaderPageObject;
import pageObjects.accesscontrol.ac_entry.AssignACRolePageObject;
import pageObjects.accesscontrol.ac_entry.DetailACEntryPageObject;
import pageObjects.accesscontrol.ac_entry.ACEntryListPageObject;

public class AC_Entry extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	AddACEntryPageObject addEntryPage;
	ACEntryListPageObject entryListPage;
	DetailACEntryPageObject detailEntryPage;
	AssignACReaderPageObject assignReaderPage;
	AssignACRolePageObject assignUserGroupPage;
	String acEntryName, acEntryType, relayType, orderNumber, sessionTimeoutSec;
	public static String acEntryNameCookie;

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
		
		acEntryName = "Entry 1";
		acEntryType = "Thang máy";
		relayType = "Cửa mở khi relay đóng";
		orderNumber = "0";
		sessionTimeoutSec = "5";
	}

	@Test
	public void AC_Entry_01_Add_New_AC_Entry() {
		log.info("AC_Entry_01 - Step 01: Open 'Cửa và ra' menu");
		dashboardPage.openMenuPage(driver, "Cửa và ra");
		entryListPage = PageGenerator.getACEntryListPage(driver);
		
		log.info("AC_Entry_01 - Step 02: Click 'Thêm Cửa và ra' button");
		entryListPage.clickToButtonByIDName(driver, "Thêm Cửa và ra");
		addEntryPage = PageGenerator.getAddACEntryPage(driver);
		
		log.info("AC_Entry_01 - Step 03: Enter valid data to required fields");
		addEntryPage.enterToTextboxByIDName(driver, "name", acEntryName);
		addEntryPage.selectItemInDropdownByID(driver, "type", acEntryType);
		addEntryPage.selectItemInDropdownByID(driver, "relay_type", relayType);
		addEntryPage.enterToTextboxByIDName(driver, "order_num", orderNumber);
		addEntryPage.enterToTextboxByIDName(driver, "session_timeout_sec", sessionTimeoutSec);
		
		log.info("AC_Entry_01 - Step 04: Click 'Thêm Cửa và ra' button");
		addEntryPage.clickToButtonByIDName(driver, "Thêm Cửa và ra");
		
		log.info("AC_Entry_01 - Step 05: Verify detail ac entry");
		detailEntryPage = PageGenerator.getDetailACEntryPage(driver);
		verifyTrue(detailEntryPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailEntryPage.getValueFieldByAttribute(driver, "name"), acEntryName);
		verifyEquals(detailEntryPage.getValueFieldByAttribute(driver, "type"), acEntryType);
		verifyEquals(detailEntryPage.getValueFieldByAttribute(driver, "relay_type"), relayType);
		verifyEquals(detailEntryPage.getValueFieldByAttribute(driver, "order_num"), orderNumber);
		verifyEquals(detailEntryPage.getValueFieldByAttribute(driver, "session_timeout_sec"), sessionTimeoutSec);
		acEntryNameCookie = detailEntryPage.getValueFieldByAttribute(driver, "name");
	}

	@Test
	public void AC_Entry_02_Assign_Reader() {
		log.info("AC_Entry_02 - Step 01: Click 'Gắn Thiết bị đọc thẻ' button");
		detailEntryPage.clickToButtonByIDName(driver, "Gắn Thiết bị đọc thẻ");
		assignReaderPage = PageGenerator.getAssignACReaderPage(driver);
		
		log.info("AC_Entry_02 - Step 02: Select card reader");
		assignReaderPage.selectItemInCustomDropdownByAttribute(driver, "ac-readers-search-input", AC_Reader.acReaderNameCookie);
		
		log.info("AC_Entry_02 - Step 03: Click 'Gắn Thiết bị đọc thẻ' button");
		assignReaderPage.clickToButtonByIDName(driver, "Gắn Thiết bị đọc thẻ");
		detailEntryPage = PageGenerator.getDetailACEntryPage(driver);
		
		log.info("AC_Entry_02 - Step 04: Verify select 'Thiết bị đọc thẻ' success");
		verifyEquals(detailEntryPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "readers", "1", "2"), AC_Reader.acReaderNameCookie);
	}
	
	@Test
	public void AC_Entry_03_Assign_UserGroup() {
		log.info("AC_Entry_03 - Step 01: Click 'Gắn Nhóm người dùng' button");
		detailEntryPage.clickToButtonByIDName(driver, "Gắn Nhóm người dùng");
		assignUserGroupPage = PageGenerator.getAssignACRolePage(driver);
		
		log.info("AC_Entry_03 - Step 02: Select user group");
		assignUserGroupPage.selectItemInCustomDropdownByAttribute(driver, "ac-roles-search-input", AC_Role.acRoleNameCookie);
		
		log.info("AC_Entry_03 - Step 03: Click 'Gắn Nhóm người dùng' button");
		assignUserGroupPage.clickToButtonByIDName(driver, "Gắn Nhóm người dùng");
		detailEntryPage = PageGenerator.getDetailACEntryPage(driver);
		
		log.info("AC_Entry_03 - Step 04: Verify select 'Nhóm người dùng' success");
		verifyEquals(detailEntryPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "roles", "1", "2"), AC_Role.acRoleNameCookie);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
