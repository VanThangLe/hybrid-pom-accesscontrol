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
import pageObjects.accesscontrol.ac_reader.AddACReaderPageObject;
import pageObjects.accesscontrol.ac_reader.AddACReaderDoEventPageObject;
import pageObjects.accesscontrol.ac_reader.ACReaderListPageObject;
import pageObjects.accesscontrol.ac_reader.DetailACReaderPageObject;

public class AC_Reader extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	AddACReaderPageObject addACReaderPage;
	DetailACReaderPageObject detailACReaderPage;
	ACReaderListPageObject acReaderListPage;
	AddACReaderDoEventPageObject addACReaderDOEventPage;
	String acReaderName, position, sessionTimeoutSec, orderNumber;
	String acDoEventName, activeDIValue, doPosition, doValue, timeOut;
	public static String acReaderNameCookie;

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
		
		acReaderName = "Card Reader 1";
		position = "Trước lối vào";
		sessionTimeoutSec = "10";
		orderNumber = "0";
		
		acDoEventName = "DO 1";
		activeDIValue = "Quẹt thẻ không thành công";
		doPosition = "DO1";
		doValue = "0";
		timeOut = "5";
	}

	@Test
	public void AC_Reader_01_Add_New_AC_CardReader() {
		log.info("AC_Reader_01 - Step 01: Open 'Thiết bị đọc thẻ' menu");
		dashboardPage.openMenuPage(driver, "Thiết bị đọc thẻ");
		acReaderListPage = PageGenerator.getACReaderListPage(driver);
		
		log.info("AC_Reader_01 - Step 02: Click 'Thêm Thiết bị đọc thẻ' button");
		acReaderListPage.clickToButtonByIDName(driver, "Thêm Thiết bị đọc thẻ");
		addACReaderPage = PageGenerator.getAddACReaderPage(driver);
		
		log.info("AC_Reader_01 - Step 03: Enter valid data to required fields");
		addACReaderPage.enterToTextboxByIDName(driver, "name", acReaderName);
		addACReaderPage.selectItemInDropdownByID(driver, "type", position);
		addACReaderPage.enterToTextboxByIDName(driver, "session_timeout_sec", sessionTimeoutSec);
		addACReaderPage.selectItemInCustomDropdownByAttribute(driver, "ac-controllers-search-input", AC_Controller.acControllerNameCookie);
		addACReaderPage.enterToTextboxByIDName(driver, "order_num", orderNumber);
		
		log.info("AC_Reader_01 - Step 04: Click 'Thêm Thiết bị đọc thẻ' button");
		addACReaderPage.clickToButtonByIDName(driver, "Thêm Thiết bị đọc thẻ");
		
		log.info("AC_Reader_01 - Step 05: Verify detail ac card reader");
		detailACReaderPage = PageGenerator.getDetailACReaderPage(driver);
		verifyTrue(detailACReaderPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailACReaderPage.getValueFieldByAttribute(driver, "name"), acReaderName);
		verifyEquals(detailACReaderPage.getValueFieldByAttribute(driver, "type"), position);
		verifyEquals(detailACReaderPage.getValueFieldByAttribute(driver, "session_timeout_sec"), sessionTimeoutSec);
		verifyEquals(detailACReaderPage.getValueFieldByAttribute(driver, "controller"), AC_Controller.acControllerNameCookie);
		verifyEquals(detailACReaderPage.getValueFieldByAttribute(driver, "order_num"), orderNumber);
		acReaderNameCookie = detailACReaderPage.getValueFieldByAttribute(driver, "name");
	}

	@Test
	public void AC_Reader_02_Add_New_AC_Reader_DO_Event() {
		log.info("AC_Reader_02 - Step 01: Click 'Thêm Sự kiện chân DO' button");
		detailACReaderPage.clickToButtonByIDName(driver, "Thêm Sự kiện chân DO");
		addACReaderDOEventPage = PageGenerator.getACReaderDoEventPage(driver);
		
		log.info("AC_Reader_02- Step 02: Enter valid data to required fields");
		addACReaderDOEventPage.enterToTextboxByIDName(driver, "name", acDoEventName);
		addACReaderDOEventPage.selectItemInDropdownByID(driver, "active_state", activeDIValue);
		addACReaderDOEventPage.selectItemInDropdownByID(driver, "do_slot_num", doPosition);
		addACReaderDOEventPage.selectItemInDropdownByID(driver, "cmd_value", doValue);
		addACReaderDOEventPage.enterToTextboxByIDName(driver, "timeout", timeOut);
		
		log.info("AC_Reader_02 - Step 04: Click 'Thêm Sự kiện chân DO' button");
		addACReaderDOEventPage.clickToButtonByIDName(driver, "Thêm Sự kiện chân DO");
		
		log.info("AC_Reader_02 - Step 05: Verify detail DO event");
		detailACReaderPage = PageGenerator.getDetailACReaderPage(driver);
		verifyTrue(detailACReaderPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailACReaderPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "outputEvents", "1", "2"), acDoEventName);
		verifyEquals(detailACReaderPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "outputEvents", "1", "3"), activeDIValue);
		verifyEquals(detailACReaderPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "outputEvents", "1", "4"), doPosition);
		verifyEquals(detailACReaderPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "outputEvents", "1", "5"), doValue);
		verifyEquals(detailACReaderPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "outputEvents", "1", "6"), timeOut);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
