package com.accesscontrol;

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
import pageObjects.accesscontrol.ac_controller.AddACControllerPageObject;
import pageObjects.accesscontrol.ac_controller.AddACDisPageObject;
import pageObjects.accesscontrol.ac_controller.ACControllerListPageObject;
import pageObjects.accesscontrol.ac_controller.DetailACControllerPageObject;
import pageObjects.accesscontrol.ac_controller.EditACControllerPageObject;
import pageObjects.accesscontrol.ac_controller.EditACDisPageObject;

public class AC_Controller extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ACControllerListPageObject controllerListPage;
	AddACControllerPageObject addControllerPage;
	EditACControllerPageObject editControllerPage;
	DetailACControllerPageObject detailControllerPage;
	AddACDisPageObject addDIPage;
	EditACDisPageObject editDIPage;
	String controllerName, controllerCode, macAddress, ipAddress, controllerType, swipeCardMode, configVer, configSyncVer, configCardVer, configCardSyncVer, mqttUserName, mqttPassword;
	String controllerNameUpdate, controllerCodeUpdate, controllerTypeUpdate, swipeCardModeUpdate, configVerUpdate, configSyncVerUpdate, configCardVerUpdate, configCardSyncVerUpdate;
	String diName, diNumber;
	String diNameUpdate, diNumberUpdate;
	public static String controllerNameUpdateCookie;
	
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
		
		controllerName = "Controller 1";
		controllerCode = "C1";
		macAddress = "A1:B2:C3:D4:E5:F6";
		ipAddress = "1.1.1.1";
		controllerType = "Bình thường";
		swipeCardMode = "Cấp thẻ";
		configVer = "0";
		configSyncVer = "0";
		configCardVer = "0";
		configCardSyncVer = "0";
		mqttUserName = "MQTTElife";
		mqttPassword = "123456";
		
		controllerNameUpdate = "Controller 1 Update";
		controllerCodeUpdate = "C1Update";
		controllerTypeUpdate = "Báo cháy";
		swipeCardModeUpdate = "Đọc thẻ";
		configVerUpdate = "1";
		configSyncVerUpdate = "1";
		configCardVerUpdate = "1";
		configCardSyncVerUpdate = "1";
		
		diName = "DI 1";
		diNumber = "DI1";
		
		diNameUpdate = "DI 1 Update";
		diNumberUpdate = "DI2";
	}

	@Test
	public void Controller_01_Add_New_Controller() {
		log.info("Controller_01 - Step 01: Open 'Bộ điều khiển' menu");
		dashboardPage.openMenuPage(driver, "Bộ điều khiển");
		controllerListPage = PageGenerator.getControllerListPage(driver);
		
		log.info("Controller_01 - Step 02: Click 'Thêm Bộ điều khiển' button");
		controllerListPage.clickToButtonByIDName(driver, "Thêm Bộ điều khiển");
		addControllerPage = PageGenerator.getAddControllerPage(driver);
		
		log.info("Controller_01 - Step 03: Enter valid data to required fields");
		addControllerPage.enterToTextboxByIDName(driver, "name", controllerName);
		addControllerPage.enterToTextboxByIDName(driver, "code", controllerCode);
		addControllerPage.enterToTextboxByIDName(driver, "mac_address", macAddress);
		addControllerPage.enterToTextboxByIDName(driver, "ip_address", ipAddress);
		addControllerPage.selectItemInDropdownByID(driver, "mode", controllerType);
		addControllerPage.selectItemInDropdownByID(driver, "swipe_card_mode", swipeCardMode);
		addControllerPage.enterToTextboxByIDName(driver, "config_version", configVer);
		addControllerPage.enterToTextboxByIDName(driver, "synced_config_version", configSyncVer);
		addControllerPage.enterToTextboxByIDName(driver, "card_version", configCardVer);
		addControllerPage.enterToTextboxByIDName(driver, "synced_card_version", configCardSyncVer);
		addControllerPage.enterToTextboxByIDName(driver, "mqtt_username", mqttUserName);
		addControllerPage.enterToTextboxByIDName(driver, "mqtt_password", mqttPassword);
		
		log.info("Controller_01 - Step 04: Click 'Thêm Bộ điều khiển' button");
		addControllerPage.clickToButtonByIDName(driver, "Thêm Bộ điều khiển");
		
		log.info("Controller_01 - Step 05: Verify detail controller");
		detailControllerPage = PageGenerator.getDetailControllerPage(driver);
		verifyTrue(detailControllerPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "name"), controllerName);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "code"), controllerCode);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "mac_address"), macAddress);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "ip_address"), ipAddress);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "mode"), controllerType);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "swipe_card_mode"), swipeCardMode);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "config_version"), configVer);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "synced_config_version"), configSyncVer);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "card_version"), configCardVer);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "synced_card_version"), configCardSyncVer);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "mqtt_username"), mqttUserName);
		detailControllerPage.sleepInSecond(1);
	}

	@Test
	public void Controller_02_Edit_Controller() {
		log.info("Controller_02 - Step 01: Click 'Sửa' icon");
		detailControllerPage.clickToEditIcon(driver);
		editControllerPage = PageGenerator.getEditControllerPage(driver);
		
		log.info("Controller_02 - Step 02: Enter valid data to required fields");
		editControllerPage.enterToTextboxByIDName(driver, "name", controllerNameUpdate);
		editControllerPage.selectItemInDropdownByID(driver, "code", controllerCodeUpdate);
		editControllerPage.enterToTextboxByIDName(driver, "mode", controllerTypeUpdate);
		editControllerPage.enterToTextboxByIDName(driver, "swipe_card_mode", swipeCardModeUpdate);
		editControllerPage.enterToTextboxByIDName(driver, "config_version", configVerUpdate);
		editControllerPage.enterToTextboxByIDName(driver, "synced_config_version", configSyncVerUpdate);
		editControllerPage.enterToTextboxByIDName(driver, "card_version", configCardVerUpdate);
		editControllerPage.enterToTextboxByIDName(driver, "synced_card_version", configCardSyncVerUpdate);
		
		log.info("Controller_02 - Step 03: Click 'Cập nhật Bộ điều khiển' button");
		editControllerPage.clickToButtonByIDName(driver, "Cập nhật Bộ điều khiển");
		
		log.info("Controller_02 - Step 04: Verify detail card reader");
		detailControllerPage = PageGenerator.getDetailControllerPage(driver);
		verifyTrue(detailControllerPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "name"), controllerNameUpdate);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "code"), controllerCodeUpdate);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "mode"), controllerTypeUpdate);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "swipe_card_mode"), swipeCardModeUpdate);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "config_version"), configVerUpdate);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "synced_config_version"), configSyncVerUpdate);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "card_version"), configCardVerUpdate);
		verifyEquals(detailControllerPage.getValueFieldByAttribute(driver, "synced_card_version"), configCardSyncVerUpdate);
		controllerNameUpdateCookie = detailControllerPage.getValueFieldByAttribute(driver, "name");
	}
	
	@Test
	public void Controller_03_Add_New_DI() {
		log.info("Controller_03 - Step 01: Click 'Thêm Chân DI' button");
		detailControllerPage.clickToButtonByIDName(driver, "Thêm Chân DI");
		addDIPage = PageGenerator.getAddDIPage(driver);
		
		log.info("Controller_03 - Step 02: Enter valid data to required fields");
		addDIPage.enterToTextboxByIDName(driver, "name", diName);
		addDIPage.selectItemInDropdownByID(driver, "slot_num", diNumber);
		
		log.info("Controller_03 - Step 04: Click 'Thêm Chân DI' button");
		addDIPage.clickToButtonByIDName(driver, "Thêm Chân DI");
		
		log.info("Controller_03 - Step 05: Verify detail DI");
		detailControllerPage = PageGenerator.getDetailControllerPage(driver);
		verifyTrue(detailControllerPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailControllerPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "digitalInputs", "1", "2"), diName);
		verifyEquals(detailControllerPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "digitalInputs", "1", "3"), diNumber);
		detailControllerPage.sleepInSecond(1);
	}
	
	@Test
	public void Controller_04_Edit_DI() {
		log.info("Controller_04 - Step 01: Click 'Sửa' icon");
		detailControllerPage.clickToEditIcon(driver);
		editDIPage = PageGenerator.getEditDIPage(driver);
		
		log.info("Controller_04 - Step 02: Enter valid data to required fields");
		editDIPage.enterToTextboxByIDName(driver, "name", diNameUpdate);
		editDIPage.selectItemInDropdownByID(driver, "slot_num", diNumberUpdate);
		
		log.info("Controller_04 - Step 03: Click 'Cập nhật Chân DI' button");
		editDIPage.clickToButtonByIDName(driver, "Cập nhật Chân DI");
		
		log.info("Controller_04 - Step 04: Verify detail DI");
		detailControllerPage = PageGenerator.getDetailControllerPage(driver);
		verifyTrue(detailControllerPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailControllerPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "digitalInputs", "1", "2"), diNameUpdate);
		verifyEquals(detailControllerPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "digitalInputs", "1", "3"), diNumberUpdate);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
