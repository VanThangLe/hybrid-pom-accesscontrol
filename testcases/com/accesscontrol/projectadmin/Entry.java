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
import pageObjects.accesscontrol.entry.AddEntryPageObject;
import pageObjects.accesscontrol.entry.AssignReaderPageObject;
import pageObjects.accesscontrol.entry.AssignUserGroupPageObject;
import pageObjects.accesscontrol.entry.DetailEntryPageObject;
import pageObjects.accesscontrol.entry.EditEntryPageObject;
import pageObjects.accesscontrol.entry.EntryListPageObject;

public class Entry extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	AddEntryPageObject addEntryPage;
	EntryListPageObject entryListPage;
	DetailEntryPageObject detailEntryPage;
	EditEntryPageObject editEntryPage;
	AssignReaderPageObject assignReaderPage;
	AssignUserGroupPageObject assignUserGroupPage;
	String entryName, entryType, relayType, orderNumber, sessionTimeoutSec;
	String entryNameUpdate, entryTypeUpdate, relayTypeUpdate, orderNumberUpdate, sessionTimeoutSecUpdate;
	public static String entryNameUpdateCookie;

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
		
		entryName = "Entry 1";
		entryType = "Thang máy";
		relayType = "Cửa mở khi relay đóng";
		orderNumber = "0";
		sessionTimeoutSec = "5";
		entryNameUpdate = "Entry 1 Update";
		entryTypeUpdate = "Cửa";
		relayTypeUpdate = "Cửa mở khi relay mở";
		orderNumberUpdate = "1";
		sessionTimeoutSecUpdate = "10";
	}

	@Test
	public void Entry_01_Add_New_Entry() {
		log.info("Entry_01 - Step 01: Open 'Cửa và ra' menu");
		dashboardPage.openMenuPage(driver, "Cửa và ra");
		entryListPage = PageGenerator.getEntryListPage(driver);
		
		log.info("Entry_01 - Step 02: Click 'Thêm Cửa và ra' button");
		entryListPage.clickToButtonByIDName(driver, "Thêm Cửa và ra");
		addEntryPage = PageGenerator.getAddEntryPage(driver);
		
		log.info("Entry_01 - Step 03: Enter valid data to required fields");
		addEntryPage.enterToTextboxByIDName(driver, "name", entryName);
		addEntryPage.selectItemInDropdownByID(driver, "type", entryType);
		addEntryPage.selectItemInDropdownByID(driver, "relay_type", relayType);
		addEntryPage.enterToTextboxByIDName(driver, "order_num", orderNumber);
		addEntryPage.enterToTextboxByIDName(driver, "session_timeout_sec", sessionTimeoutSec);
		
		log.info("Entry_01 - Step 04: Click 'Thêm Cửa và ra' button");
		addEntryPage.clickToButtonByIDName(driver, "Thêm Cửa và ra");
		
		log.info("Entry_01 - Step 05: Verify detail entry");
		detailEntryPage = PageGenerator.getDetailEntryPage(driver);
		verifyTrue(detailEntryPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailEntryPage.getValueFieldByAttribute(driver, "name"), entryName);
		verifyEquals(detailEntryPage.getSelectedValueInDropdownByID(driver, "type"), entryType);
		verifyEquals(detailEntryPage.getSelectedValueInDropdownByID(driver, "relay_type"), relayType);
		verifyEquals(detailEntryPage.getValueFieldByAttribute(driver, "order_num"), orderNumber);
		verifyEquals(detailEntryPage.getValueFieldByAttribute(driver, "session_timeout_sec"), sessionTimeoutSec);
		detailEntryPage.sleepInSecond(1);
	}

	@Test
	public void Entry_02_Edit_Entry() {
		log.info("Entry_02 - Step 01: Click 'Sửa' icon");
		detailEntryPage.clickToEditIcon(driver);
		editEntryPage = PageGenerator.getEditEntryPage(driver);
		
		log.info("Entry_02 - Step 02: Enter valid data to required fields");
		editEntryPage.enterToTextboxByIDName(driver, "name", entryNameUpdate);
		editEntryPage.selectItemInDropdownByID(driver, "type", entryTypeUpdate);
		editEntryPage.selectItemInDropdownByID(driver, "relay_type", relayTypeUpdate);
		editEntryPage.enterToTextboxByIDName(driver, "order_num", orderNumberUpdate);
		editEntryPage.enterToTextboxByIDName(driver, "session_timeout_sec", sessionTimeoutSecUpdate);
		
		log.info("Entry_02 - Step 03: Click 'Cập nhật Cửa và ra' button");
		editEntryPage.clickToButtonByIDName(driver, "Cập nhật Cửa và ra");
		
		log.info("Entry_02 - Step 04: Verify detail entry");
		detailEntryPage = PageGenerator.getDetailEntryPage(driver);
		verifyTrue(detailEntryPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailEntryPage.getValueFieldByAttribute(driver, "name"), entryNameUpdate);
		verifyEquals(detailEntryPage.getSelectedValueInDropdownByID(driver, "type"), entryTypeUpdate);
		verifyEquals(detailEntryPage.getSelectedValueInDropdownByID(driver, "relay_type"), relayTypeUpdate);
		verifyEquals(detailEntryPage.getValueFieldByAttribute(driver, "order_num"), orderNumberUpdate);
		verifyEquals(detailEntryPage.getValueFieldByAttribute(driver, "session_timeout_sec"), sessionTimeoutSecUpdate);
		entryNameUpdateCookie = detailEntryPage.getValueFieldByAttribute(driver, "name");
	}

	@Test
	public void Entry_03_Assign_Reader() {
		log.info("Entry_03 - Step 01: Click 'Gắn Thiết bị đọc thẻ' button");
		detailEntryPage.clickToButtonByIDName(driver, "Gắn Thiết bị đọc thẻ");
		assignReaderPage = PageGenerator.getAssignReaderPage(driver);
		
		log.info("Entry_03 - Step 02: Select card reader");
		assignReaderPage.selectItemInCustomDropdownByAttribute(driver, "ac-readers-search-input", CardReader.cardReaderNameUpdateCookie);
		
		log.info("Entry_03 - Step 03: Click 'Gắn Thiết bị đọc thẻ' button");
		assignReaderPage.clickToButtonByIDName(driver, "Gắn Thiết bị đọc thẻ");
		detailEntryPage = PageGenerator.getDetailEntryPage(driver);
		
		log.info("Entry_03 - Step 04: Verify select 'Thiết bị đọc thẻ' success");
		verifyEquals(detailEntryPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "readers", "1", "2"), 
				CardReader.cardReaderNameUpdateCookie);
	}
	
	@Test
	public void Entry_04_Assign_UserGroup() {
		log.info("Entry_04 - Step 01: Click 'Gắn Nhóm người dùng' button");
		detailEntryPage.clickToButtonByIDName(driver, "Gắn Nhóm người dùng");
		assignUserGroupPage = PageGenerator.getAssignUserGroupPage(driver);
		
		log.info("Entry_04 - Step 02: Select user group");
		assignUserGroupPage.selectItemInCustomDropdownByAttribute(driver, "ac-roles-search-input", UserGroup.userGroupNameUpdateCookie);
		
		log.info("Entry_04 - Step 03: Click 'Gắn Nhóm người dùng' button");
		assignUserGroupPage.clickToButtonByIDName(driver, "Gắn Nhóm người dùng");
		detailEntryPage = PageGenerator.getDetailEntryPage(driver);
		
		log.info("Entry_04 - Step 04: Verify select 'Nhóm người dùng' success");
		verifyEquals(detailEntryPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "roles", "1", "2"), 
				UserGroup.userGroupNameUpdateCookie);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
