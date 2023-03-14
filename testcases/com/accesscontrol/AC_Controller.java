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
import pageObjects.accesscontrol.ac_controller.AddACControllerPageObject;
import pageObjects.accesscontrol.ac_controller.AddACDisPageObject;
import pageObjects.accesscontrol.ac_controller.ACControllerListPageObject;
import pageObjects.accesscontrol.ac_controller.DetailACControllerPageObject;

public class AC_Controller extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ACControllerListPageObject acControllerListPage;
	AddACControllerPageObject addACControllerPage;
	DetailACControllerPageObject detailACControllerPage;
	AddACDisPageObject addACDisPage;
	String acControllerName, acControllerCode, macAddress, ipAddress, acControllerType, swipeCardMode, configVer, configSyncVer, configCardVer, configCardSyncVer, mqttACControllerUserName, mqttACControllerPassword;
	String acDisName, acDisNumber;
	public static String acControllerNameCookie;
	
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
		
		acControllerName = "Controller 1";
		acControllerCode = "C1";
		macAddress = "A1:B2:C3:D4:E5:F6";
		ipAddress = "1.1.1.1";
		acControllerType = "Bình thường";
		swipeCardMode = "Cấp thẻ";
		configVer = "0";
		configSyncVer = "0";
		configCardVer = "0";
		configCardSyncVer = "0";
		mqttACControllerUserName = "Controller_1";
		mqttACControllerPassword = "Controller_1";
		
		acDisName = "DI 1";
		acDisNumber = "DI1";
	}

	@Test
	public void Controller_01_Add_New_Controller() {
		log.info("Controller_01 - Step 01: Open 'Bộ điều khiển' menu");
		dashboardPage.openMenuPage(driver, "Bộ điều khiển");
		acControllerListPage = PageGenerator.getACControllerListPage(driver);
		
		log.info("Controller_01 - Step 02: Click 'Thêm Bộ điều khiển' button");
		acControllerListPage.clickToButtonByIDName(driver, "Thêm Bộ điều khiển");
		addACControllerPage = PageGenerator.getAddACControllerPage(driver);
		
		log.info("Controller_01 - Step 03: Enter valid data to required fields");
		addACControllerPage.enterToTextboxByIDName(driver, "name", acControllerName);
		addACControllerPage.enterToTextboxByIDName(driver, "code", acControllerCode);
		addACControllerPage.enterToTextboxByIDName(driver, "mac_address", macAddress);
		addACControllerPage.enterToTextboxByIDName(driver, "ip_address", ipAddress);
		addACControllerPage.selectItemInDropdownByID(driver, "mode", acControllerType);
		addACControllerPage.selectItemInDropdownByID(driver, "swipe_card_mode", swipeCardMode);
		addACControllerPage.enterToTextboxByIDName(driver, "config_version", configVer);
		addACControllerPage.enterToTextboxByIDName(driver, "synced_config_version", configSyncVer);
		addACControllerPage.enterToTextboxByIDName(driver, "card_version", configCardVer);
		addACControllerPage.enterToTextboxByIDName(driver, "synced_card_version", configCardSyncVer);
		addACControllerPage.enterToTextboxByIDName(driver, "mqtt_username", mqttACControllerUserName);
		addACControllerPage.enterToTextboxByIDName(driver, "mqtt_password", mqttACControllerPassword);
		
		log.info("Controller_01 - Step 04: Click 'Thêm Bộ điều khiển' button");
		addACControllerPage.clickToButtonByIDName(driver, "Thêm Bộ điều khiển");
		
		log.info("Controller_01 - Step 05: Verify detail controller");
		detailACControllerPage = PageGenerator.getDetailACControllerPage(driver);
		verifyTrue(detailACControllerPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailACControllerPage.getValueFieldByAttribute(driver, "name"), acControllerName);
		verifyEquals(detailACControllerPage.getValueFieldByAttribute(driver, "code"), acControllerCode);
		verifyEquals(detailACControllerPage.getValueFieldByAttribute(driver, "mac_address"), macAddress);
		verifyEquals(detailACControllerPage.getValueFieldByAttribute(driver, "ip_address"), ipAddress);
		verifyEquals(detailACControllerPage.getValueFieldByAttribute(driver, "mode"), acControllerType);
		verifyEquals(detailACControllerPage.getValueFieldByAttribute(driver, "swipe_card_mode"), swipeCardMode);
		verifyEquals(detailACControllerPage.getValueFieldByAttribute(driver, "config_version"), configVer);
		verifyEquals(detailACControllerPage.getValueFieldByAttribute(driver, "synced_config_version"), configSyncVer);
		verifyEquals(detailACControllerPage.getValueFieldByAttribute(driver, "card_version"), configCardVer);
		verifyEquals(detailACControllerPage.getValueFieldByAttribute(driver, "synced_card_version"), configCardSyncVer);
		verifyEquals(detailACControllerPage.getValueFieldByAttribute(driver, "mqtt_username"), mqttACControllerUserName);
		acControllerNameCookie = detailACControllerPage.getValueFieldByAttribute(driver, "name");
	}
	
	@Test
	public void AC_Controller_02_Add_New_AC_Dis() {
		log.info("AC_Controller_02 - Step 01: Click 'Thêm Chân DI' button");
		detailACControllerPage.clickToButtonByIDName(driver, "Thêm Chân DI");
		addACDisPage = PageGenerator.getAddACDisPage(driver);
		
		log.info("AC_Controller_02 - Step 02: Enter valid data to required fields");
		addACDisPage.enterToTextboxByIDName(driver, "name", acDisName);
		addACDisPage.selectItemInDropdownByID(driver, "slot_num", acDisNumber);
		
		log.info("AC_Controller_02 - Step 03: Click 'Thêm Chân DI' button");
		addACDisPage.clickToButtonByIDName(driver, "Thêm Chân DI");
		
		log.info("AC_Controller_02 - Step 04: Verify detail ac DI");
		detailACControllerPage = PageGenerator.getDetailACControllerPage(driver);
		verifyTrue(detailACControllerPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailACControllerPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "digitalInputs", "1", "2"), acDisName);
		verifyEquals(detailACControllerPage.getValueAtColumnIndexAndRowIndexTableAssign(driver, "digitalInputs", "1", "3"), acDisNumber);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
