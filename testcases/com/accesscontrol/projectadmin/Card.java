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
import pageObjects.accesscontrol.card.AddCardPageObject;
import pageObjects.accesscontrol.card.CardListPageObject;
import pageObjects.accesscontrol.card.DetailCardPageObject;
import pageObjects.accesscontrol.card.EditCardPageObject;

public class Card extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	CardListPageObject cardListPage;
	EditCardPageObject editCardPage;
	DetailCardPageObject detailCardPage;
	AddCardPageObject addCardPage;
	String cardCode, activateDate, expireDate;
	String activateDateUpdate, expireDateUpdate;

	@Parameters({ "browserName", "appUrl" })
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
		
		cardCode = "12345678";
		activateDate = "01/01/2023 00:00:00";
		expireDate = "12/31/2023 00:00:00";
		activateDateUpdate = "02/01/2023 00:00:00";
		expireDateUpdate = "12/31/2024 00:00:00";
	}

	@Test
	public void Card_01_Add_New_Card() {
		log.info("Card_01 - Step 01: Open 'Thẻ' menu");
		dashboardPage.openMenuPage(driver, "Thẻ");
		cardListPage = PageGenerator.getCardListPage(driver);
		
		log.info("Card_01 - Step 02: Click 'Thêm Thẻ' button");
		cardListPage.clickToButtonByIDName(driver, "Thêm Thẻ");
		addCardPage = PageGenerator.getAddCardPage(driver);
		
		log.info("Card_01 - Step 03: Enter valid data to required fields");
		addCardPage.selectItemInCustomDropdownByAttribute(driver, "ac-card-standards-search-input", CardStandard.cardStandardNameUpdateCookie);
		addCardPage.enterToTextboxByIDName(driver, "code", cardCode);
		addCardPage.enterToTextboxByIDName(driver, "valid_from", activateDate);
		addCardPage.enterToTextboxByIDName(driver, "valid_to", expireDate);
		
		log.info("Card_01 - Step 04: Click 'Thêm Thẻ' button");
		addCardPage.clickToButtonByIDName(driver, "Thêm Thẻ");
		
		log.info("Card_01 - Step 05: Verify detail card");
		detailCardPage = PageGenerator.getDetailCardPage(driver);
		verifyTrue(detailCardPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailCardPage.getValueFieldByAttribute(driver, "cardStandard"), CardStandard.cardStandardNameUpdateCookie);
		verifyEquals(detailCardPage.getValueFieldByAttribute(driver, "code"), cardCode);
		verifyEquals(detailCardPage.getValueFieldByAttribute(driver, "valid_from"), activateDate);
		verifyEquals(detailCardPage.getValueFieldByAttribute(driver, "valid_to"), expireDate);
		detailCardPage.sleepInSecond(1);
	}

	@Test
	public void Card_02_Edit_Card() {
		log.info("Card_02 - Step 01: Click 'Sửa' icon");
		detailCardPage.clickToEditIcon(driver);
		editCardPage = PageGenerator.getEditCardPage(driver);
		
		log.info("Card_02 - Step 02: Enter valid data to required fields");
		editCardPage.enterToTextboxByIDName(driver, "valid_from", activateDateUpdate);
		editCardPage.enterToTextboxByIDName(driver, "valid_to", expireDateUpdate);
		
		log.info("Card_02 - Step 03: Click 'Cập nhật Thẻ' button");
		editCardPage.clickToButtonByIDName(driver, "Cập nhật Thẻ");
		
		log.info("Card_02 - Step 04: Verify detail card");
		detailCardPage = PageGenerator.getDetailCardPage(driver);
		verifyTrue(detailCardPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailCardPage.getValueFieldByAttribute(driver, "cardStandard"), CardStandard.cardStandardNameUpdateCookie);
		verifyEquals(detailCardPage.getValueFieldByAttribute(driver, "code"), cardCode);
		verifyEquals(detailCardPage.getValueFieldByAttribute(driver, "valid_from"), activateDateUpdate);
		verifyEquals(detailCardPage.getValueFieldByAttribute(driver, "valid_to"), expireDateUpdate);
	}

	@Parameters({ "browserName" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
