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
import pageObjects.accesscontrol.ac_reader.EditACReaderPageObject;
import pageObjects.accesscontrol.ac_reader.EditACReaderDoEventPageObject;

public class AC_Reader extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	AddACReaderPageObject addCardReaderPage;
	DetailACReaderPageObject detailCardReaderPage;
	EditACReaderPageObject editCardReaderPage;
	ACReaderListPageObject cardReaderListPage;
	AddACReaderDoEventPageObject addDOEventPage;
	EditACReaderDoEventPageObject editDOEventPage;
	String cardReaderName, position, sessionTimeoutSec, orderNumber;
	String cardReaderNameUpdate, positionUpdate, sessionTimeoutSecUpdate, controllerNameUpdate, orderNumberUpdate;
	String eventName, activeDIValue, doPosition, doValue, timeOut;
	String eventNameUpdate, activeDIValueUpdate, doPositionUpdate, doValueUpdate, timeOutUpdate;
	public static String cardReaderNameUpdateCookie;

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
		
		cardReaderName = "Card Reader 1";
		position = "Trước lối vào";
		sessionTimeoutSec = "10";
		orderNumber = "0";
		
		cardReaderNameUpdate = "Card Reader 1 Update";
		positionUpdate = "Sau lối vào";
		sessionTimeoutSecUpdate = "5";
		orderNumberUpdate = "1";
		
		eventName = "DO 1";
		activeDIValue = "Quẹt thẻ không thành công";
		doPosition = "DO1";
		doValue = "0";
		timeOut = "5";
		
		eventNameUpdate = "DO 1 Update";
		activeDIValueUpdate = "Quẹt thẻ thành công";
		doPositionUpdate = "DO2";
		doValueUpdate = "1";
		timeOutUpdate = "10";
	}

	@Test
	public void CardReader_01_Add_New_CardReader() {
		log.info("CardReader_01 - Step 01: Open 'Thiết bị đọc thẻ' menu");
		dashboardPage.openMenuPage(driver, "Thiết bị đọc thẻ");
		cardReaderListPage = PageGenerator.getCardReaderListPage(driver);
		
		log.info("CardReader_01 - Step 02: Click 'Thêm Thiết bị đọc thẻ' button");
		cardReaderListPage.clickToButtonByIDName(driver, "Thêm Thiết bị đọc thẻ");
		addCardReaderPage = PageGenerator.getAddCardReaderPage(driver);
		
		log.info("CardReader_01 - Step 03: Enter valid data to required fields");
		addCardReaderPage.enterToTextboxByIDName(driver, "name", cardReaderName);
		addCardReaderPage.selectItemInDropdownByID(driver, "type", position);
		addCardReaderPage.enterToTextboxByIDName(driver, "session_timeout_sec", sessionTimeoutSec);
		addCardReaderPage.selectItemInCustomDropdownByAttribute(driver, "ac-controllers-search-input", AC_Controller.controllerNameUpdateCookie);
		addCardReaderPage.enterToTextboxByIDName(driver, "order_num", orderNumber);
		
		log.info("CardReader_01 - Step 04: Click 'Thêm Thiết bị đọc thẻ' button");
		addCardReaderPage.clickToButtonByIDName(driver, "Thêm Thiết bị đọc thẻ");
		
		log.info("CardReader_01 - Step 05: Verify detail card reader");
		detailCardReaderPage = PageGenerator.getDetailCardReaderPage(driver);
		verifyTrue(detailCardReaderPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailCardReaderPage.getValueFieldByAttribute(driver, "name"), cardReaderName);
		verifyEquals(detailCardReaderPage.getValueFieldByAttribute(driver, "type"), position);
		verifyEquals(detailCardReaderPage.getValueFieldByAttribute(driver, "session_timeout_sec"), sessionTimeoutSec);
		verifyEquals(detailCardReaderPage.getValueFieldByAttribute(driver, "controller"), AC_Controller.controllerNameUpdateCookie);
		verifyEquals(detailCardReaderPage.getValueFieldByAttribute(driver, "order_num"), orderNumber);
		detailCardReaderPage.sleepInSecond(1);
	}

	@Test
	public void CardReader_02_Edit_CardReader() {
		log.info("CardReader_02 - Step 01: Click 'Sửa' icon");
		detailCardReaderPage.clickToEditIcon(driver);
		editCardReaderPage = PageGenerator.getEditCardReaderPage(driver);
		
		log.info("CardReader_02 - Step 02: Enter valid data to required fields");
		editCardReaderPage.enterToTextboxByIDName(driver, "name", cardReaderNameUpdate);
		editCardReaderPage.selectItemInDropdownByID(driver, "type", positionUpdate);
		editCardReaderPage.enterToTextboxByIDName(driver, "session_timeout_sec", sessionTimeoutSecUpdate);
		editCardReaderPage.enterToTextboxByIDName(driver, "order_num", orderNumberUpdate);
		
		log.info("CardReader_02 - Step 03: Click 'Cập nhật Thiết bị đọc thẻ' button");
		editCardReaderPage.clickToButtonByIDName(driver, "Cập nhật Thiết bị đọc thẻ");
		
		log.info("CardReader_02 - Step 04: Verify detail card reader");
		detailCardReaderPage = PageGenerator.getDetailCardReaderPage(driver);
		verifyTrue(detailCardReaderPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailCardReaderPage.getValueFieldByAttribute(driver, "name"), cardReaderNameUpdate);
		verifyEquals(detailCardReaderPage.getValueFieldByAttribute(driver, "type"), positionUpdate);
		verifyEquals(detailCardReaderPage.getValueFieldByAttribute(driver, "session_timeout_sec"), sessionTimeoutSecUpdate);
		verifyEquals(detailCardReaderPage.getValueFieldByAttribute(driver, "order_num"), orderNumberUpdate);
		cardReaderNameUpdateCookie = detailCardReaderPage.getValueFieldByAttribute(driver, "name");
	}

	@Test
	public void CardReader_03_Add_New_DO_Event() {
		log.info("CardReader_03 - Step 01: Click 'Thêm Sự kiện chân DO' button");
		detailCardReaderPage.clickToButtonByIDName(driver, "Thêm Sự kiện chân DO");
		addDOEventPage = PageGenerator.getAddDOEventPage(driver);
		
		log.info("CardReader_03 - Step 02: Enter valid data to required fields");
		addDOEventPage.enterToTextboxByIDName(driver, "name", eventName);
		addDOEventPage.selectItemInDropdownByID(driver, "active_state", activeDIValue);
		addDOEventPage.selectItemInDropdownByID(driver, "do_slot_num", doPosition);
		addDOEventPage.selectItemInDropdownByID(driver, "cmd_value", doValue);
		addDOEventPage.enterToTextboxByIDName(driver, "timeout", timeOut);
		
		log.info("CardReader_03 - Step 04: Click 'Thêm Sự kiện chân DO' button");
		addDOEventPage.clickToButtonByIDName(driver, "Thêm Sự kiện chân DO");
		
		log.info("CardReader_03 - Step 05: Verify detail DO event");
		detailCardReaderPage = PageGenerator.getDetailCardReaderPage(driver);
		verifyTrue(detailCardReaderPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailCardReaderPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "outputEvents", "1", "2"), eventName);
		verifyEquals(detailCardReaderPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "outputEvents", "1", "3"), activeDIValue);
		verifyEquals(detailCardReaderPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "outputEvents", "1", "4"), doPosition);
		verifyEquals(detailCardReaderPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "outputEvents", "1", "5"), doValue);
		verifyEquals(detailCardReaderPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "outputEvents", "1", "6"), timeOut);
		detailCardReaderPage.sleepInSecond(1);
	}
	
	@Test
	public void CardReader_04_Edit_DO_Event() {
		log.info("CardReader_04 - Step 01: Click 'Sửa' icon");
		detailCardReaderPage.clickToEditIcon(driver);
		editCardReaderPage = PageGenerator.getEditCardReaderPage(driver);
		
		log.info("CardReader_04 - Step 02: Enter valid data to required fields");
		editDOEventPage.enterToTextboxByIDName(driver, "name", eventNameUpdate);
		editDOEventPage.selectItemInDropdownByID(driver, "active_state", activeDIValueUpdate);
		editDOEventPage.selectItemInDropdownByID(driver, "do_slot_num", doPositionUpdate);
		editDOEventPage.selectItemInDropdownByID(driver, "cmd_value", doValueUpdate);
		editDOEventPage.enterToTextboxByIDName(driver, "timeout", timeOutUpdate);
		
		log.info("CardReader_04 - Step 03: Click 'Cập nhật Sự kiện chân DO' button");
		editDOEventPage.clickToButtonByIDName(driver, "Cập nhật Sự kiện chân DO");
		
		log.info("CardReader_04 - Step 04: Verify detail DO event");
		detailCardReaderPage = PageGenerator.getDetailCardReaderPage(driver);
		verifyTrue(detailCardReaderPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailCardReaderPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "outputEvents", "1", "2"), eventNameUpdate);
		verifyEquals(detailCardReaderPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "outputEvents", "1", "3"), activeDIValueUpdate);
		verifyEquals(detailCardReaderPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "outputEvents", "1", "4"), doPositionUpdate);
		verifyEquals(detailCardReaderPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "outputEvents", "1", "5"), doValueUpdate);
		verifyEquals(detailCardReaderPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "outputEvents", "1", "6"), timeOutUpdate);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
