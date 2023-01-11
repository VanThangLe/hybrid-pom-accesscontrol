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
import pageObjects.accesscontrol.controller.AddControllerPageObject;
import pageObjects.accesscontrol.controller.AddDIEventPageObject;
import pageObjects.accesscontrol.controller.ControllerListPageObject;
import pageObjects.accesscontrol.controller.DetailControllerPageObject;
import pageObjects.accesscontrol.controller.EditControllerPageObject;
import pageObjects.accesscontrol.controller.EditDIEventPageObject;

public class Controller extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ControllerListPageObject controllerListPage;
	AddControllerPageObject addControllerPage;
	EditControllerPageObject editControllerPage;
	DetailControllerPageObject detailControllerPage;
	AddDIEventPageObject addDIEventPage;
	EditDIEventPageObject editDIEventPage;
	String controllerName, controllerCode, macAddress, ipAddress, swipeCardMode, configVer, configSyncVer, configCardVer, configCardSyncVer, mqttUserName, mqttPassword;
	String controllerNameUpdate, controllerCodeUpdate, macAddressUpdate, ipAddressUpdate, swipeCardModeUpdate, configVerUpdate, configSyncVerUpdate, configCardVerUpdate, configCardSyncVerUpdate, mqttUserNameUpdate;
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
		
		controllerName = "";
		controllerCode = "";
		macAddress = "";
		ipAddress = "";
		swipeCardMode = "";
		configVer = "";
		configSyncVer = "";
		configCardVer = "";
		configCardSyncVer = "";
		mqttUserName = "";
		mqttPassword = "";
		
		controllerNameUpdate = "";
		controllerCodeUpdate = "";
		macAddressUpdate = "";
		ipAddressUpdate = "";
		swipeCardModeUpdate = "";
		configVerUpdate = "";
		configSyncVerUpdate = "";
		configCardVerUpdate = "";
		configCardSyncVerUpdate = "";
		mqttUserNameUpdate = "";
	}

	@Test
	public void Controller_01_Add_New_Controller() {
		
	}

	@Test
	public void Controller_02_Edit_Controller() {
		
	}
	
	@Test
	public void Controller_03_Add_New_DI_Event() {
		
	}
	
	@Test
	public void Controller_04_Edit_DI_Event() {
		
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
