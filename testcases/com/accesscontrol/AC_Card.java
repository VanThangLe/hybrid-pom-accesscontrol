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
import pageObjects.accesscontrol.ac_card.AddACCardPageObject;
import pageObjects.accesscontrol.ac_card.ACCardListPageObject;
import pageObjects.accesscontrol.ac_card.DetailACCardPageObject;

public class AC_Card extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ACCardListPageObject acCardListPage;
	DetailACCardPageObject detailACCardPage;
	AddACCardPageObject addACCardPage;
	String cardCode, activateDate, expireDate;

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
		
		cardCode = "12345678";
		activateDate = "01/01/2023 00:00:00";
		expireDate = "12/31/2023 00:00:00";
	}

	@Test
	public void AC_Card_01_Add_New_AC_Card() {
		log.info("AC_Card_01 - Step 01: Open 'Thẻ' menu");
		dashboardPage.openMenuPage(driver, "Thẻ");
		acCardListPage = PageGenerator.getACCardListPage(driver);
		
		log.info("AC_Card_01 - Step 02: Click 'Thêm Thẻ' button");
		acCardListPage.clickToButtonByIDName(driver, "Thêm Thẻ");
		addACCardPage = PageGenerator.getAddACCardPage(driver);
		
		log.info("AC_Card_01 - Step 03: Enter valid data to required fields");
		addACCardPage.selectItemInCustomDropdownByAttribute(driver, "ac-card-standards-search-input", AC_Card_Standard.acCardStandardNameCookie);
		addACCardPage.enterToTextboxByIDName(driver, "code", cardCode);
		addACCardPage.enterToTextboxByIDName(driver, "valid_from", activateDate);
		addACCardPage.enterToTextboxByIDName(driver, "valid_to", expireDate);
		
		log.info("AC_Card_01 - Step 04: Click 'Thêm Thẻ' button");
		addACCardPage.clickToButtonByIDName(driver, "Thêm Thẻ");
		
		log.info("AC_Card_01 - Step 05: Verify detail ac card");
		detailACCardPage = PageGenerator.getDetailACCardPage(driver);
		verifyTrue(detailACCardPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailACCardPage.getValueFieldByAttribute(driver, "cardStandard"), AC_Card_Standard.acCardStandardNameCookie);
		verifyEquals(detailACCardPage.getValueFieldByAttribute(driver, "code"), cardCode);
		verifyEquals(detailACCardPage.getValueFieldByAttribute(driver, "valid_from"), activateDate);
		verifyEquals(detailACCardPage.getValueFieldByAttribute(driver, "valid_to"), expireDate);
	}

	@Parameters({ "browserName" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
