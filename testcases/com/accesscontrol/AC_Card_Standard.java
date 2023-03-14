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
import pageObjects.accesscontrol.ac_cardstandard.AddACCardStandardPageObject;
import pageObjects.accesscontrol.ac_cardstandard.ACCardStandardListPageObject;
import pageObjects.accesscontrol.ac_cardstandard.DetailACCardStandardPageObject;

public class AC_Card_Standard extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ACCardStandardListPageObject acCardStandardListPage;
	AddACCardStandardPageObject addACCardStandardPage;
	DetailACCardStandardPageObject detailACCardStandardPage;
	String acCardStandardName, totalBits, bitMask;
	public static String acCardStandardNameCookie;

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
		
		acCardStandardName = "Card Standard 1";
		totalBits = "32";
		bitMask = "11111111111111111111111111111111";
	}

	@Test
	public void AC_CardStandard_01_Add_New_AC_CardStandard() {
		log.info("AC_CardStandard_01 - Step 01: Open 'Chuẩn thẻ' menu");
		dashboardPage.openMenuPage(driver, "Chuẩn thẻ");
		acCardStandardListPage = PageGenerator.getACCardStandardListPage(driver);
		
		log.info("AC_CardStandard_01 - Step 02: Click 'Thêm Chuẩn thẻ' button");
		acCardStandardListPage.clickToButtonByIDName(driver, "Thêm Chuẩn thẻ");
		addACCardStandardPage = PageGenerator.getAddACCardStandardPage(driver);
		
		log.info("AC_CardStandard_01 - Step 03: Enter valid data to required fields");
		addACCardStandardPage.enterToTextboxByIDName(driver, "name", acCardStandardName);
		addACCardStandardPage.enterToTextboxByIDName(driver, "total_bits", totalBits);
		addACCardStandardPage.enterToTextboxByIDName(driver, "bitmask", bitMask);
		
		log.info("AC_CardStandard_01 - Step 04: Click 'Thêm Chuẩn thẻ' button");
		addACCardStandardPage.clickToButtonByIDName(driver, "Thêm Chuẩn thẻ");
		
		log.info("AC_CardStandard_01 - Step 05: Verify detail ac card standard");
		detailACCardStandardPage = PageGenerator.getDetailACCardStandardPage(driver);
		verifyTrue(detailACCardStandardPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailACCardStandardPage.getValueFieldByAttribute(driver, "name"), acCardStandardName);
		verifyEquals(detailACCardStandardPage.getValueFieldByAttribute(driver, "total_bits"), totalBits);
		verifyEquals(detailACCardStandardPage.getValueFieldByAttribute(driver, "bitmask"), bitMask);
		acCardStandardNameCookie = detailACCardStandardPage.getValueFieldByAttribute(driver, "name");
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
