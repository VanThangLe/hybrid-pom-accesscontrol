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
import pageObjects.accesscontrol.ac_gateway.ACGatewayListPageObject;
import pageObjects.accesscontrol.ac_gateway.AddACGatewayPageObject;
import pageObjects.accesscontrol.ac_gateway.DetailACGatewayPageObject;

public class AC_Gateway extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ACGatewayListPageObject acGatewayListPage;
	DetailACGatewayPageObject detailGatewayPage;
	AddACGatewayPageObject addGatewayPage;
	String acGatewayName, acGatewayCode, mqttACGatewayUserName, mqttACGatewayPassword;

	@Parameters({ "browserName", "appUrl" })
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
		
		acGatewayName = "Gateway 0001";
		acGatewayCode = "GW0001";
		mqttACGatewayUserName = "Gateway_0001";
		mqttACGatewayPassword = "Gateway_0001";
	}

	@Test
	public void AC_Gateway_01_Add_New_AC_Gateway() {
		log.info("AC_Gateway_01 - Step 01: Open 'Gateway' menu");
		dashboardPage.openMenuPage(driver, "Gateway");
		acGatewayListPage = PageGenerator.getACGatewayListPage(driver);
		
		log.info("AC_Gateway_01 - Step 02: Click 'Thêm Gateway' button");
		acGatewayListPage.clickToButtonByIDName(driver, "Thêm Gateway");
		addGatewayPage = PageGenerator.getAddACGatewayPage(driver);
		
		log.info("AC_Gateway_01 - Step 03: Enter valid data to required fields");
		addGatewayPage.enterToTextboxByIDName(driver, "name", acGatewayName);
		addGatewayPage.enterToTextboxByIDName(driver, "code", acGatewayCode);
		addGatewayPage.enterToTextboxByIDName(driver, "mqtt_username", mqttACGatewayUserName);
		addGatewayPage.enterToTextboxByIDName(driver, "mqtt_password", mqttACGatewayPassword);
		
		log.info("AC_Gateway_01 - Step 04: Click 'Thêm Gateway' button");
		addGatewayPage.clickToButtonByIDName(driver, "Thêm Gateway");
		
		log.info("AC_Gateway_01 - Step 05: Verify detail ac gateway");
		detailGatewayPage = PageGenerator.getDetailACGatewayPage(driver);
		verifyTrue(detailGatewayPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailGatewayPage.getValueFieldByAttribute(driver, "name"), acGatewayName);
		verifyEquals(detailGatewayPage.getValueFieldByAttribute(driver, "code"), acGatewayCode);
		verifyEquals(detailGatewayPage.getValueFieldByAttribute(driver, "mqtt_username"), mqttACGatewayUserName);
		verifyEquals(detailGatewayPage.getValueFieldByAttribute(driver, "mqtt_password"), mqttACGatewayPassword);
	}

	@Parameters({ "browserName" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
