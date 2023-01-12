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
import pageObjects.accesscontrol.cardstandard.AddCardStandardPageObject;
import pageObjects.accesscontrol.cardstandard.CardStandardListPageObject;
import pageObjects.accesscontrol.cardstandard.DetailCardStandardPageObject;
import pageObjects.accesscontrol.cardstandard.EditCardStandardPageObject;

public class CardStandard extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	CardStandardListPageObject cardStandardListPage;
	AddCardStandardPageObject addCardStandardPage;
	EditCardStandardPageObject editCardStandardPage;
	DetailCardStandardPageObject detailCardStandardPage;
	String cardStandardName, totalBits, bitMask;
	String cardStandardNameUpdate, totalBitsUpdate, bitMaskUpdate;
	public static String cardStandardNameUpdateCookie;

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
		
		cardStandardName = "Card Standard 1";
		totalBits = "32";
		bitMask = "11111111111111111111111111111111";
		cardStandardNameUpdate = "Card Standard 1 Update";
		totalBits = "34";
		bitMask = "0111111111111111111111111111111110";
	}

	@Test
	public void CardStandard_01_Add_New_CardStandard() {
		log.info("CardStandard_01 - Step 01: Open 'Chuẩn thẻ' menu");
		dashboardPage.openMenuPage(driver, "Chuẩn thẻ");
		cardStandardListPage = PageGenerator.getCardStandardListPage(driver);
		
		log.info("CardStandard_01 - Step 02: Click 'Thêm Chuẩn thẻ' button");
		cardStandardListPage.clickToButtonByIDName(driver, "Thêm Chuẩn thẻ");
		addCardStandardPage = PageGenerator.getAddCardStandardPage(driver);
		
		log.info("CardStandard_01 - Step 03: Enter valid data to required fields");
		addCardStandardPage.enterToTextboxByIDName(driver, "name", cardStandardName);
		addCardStandardPage.enterToTextboxByIDName(driver, "total_bits", totalBits);
		addCardStandardPage.enterToTextboxByIDName(driver, "bitmask", bitMask);
		
		log.info("CardStandard_01 - Step 04: Click 'Thêm Chuẩn thẻ' button");
		addCardStandardPage.clickToButtonByIDName(driver, "Thêm Chuẩn thẻ");
		
		log.info("CardStandard_01 - Step 05: Verify detail card standard");
		detailCardStandardPage = PageGenerator.getDetailCardStandardPage(driver);
		verifyTrue(detailCardStandardPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailCardStandardPage.getValueFieldByAttribute(driver, "name"), cardStandardName);
		verifyEquals(detailCardStandardPage.getValueFieldByAttribute(driver, "total_bits"), totalBits);
		verifyEquals(detailCardStandardPage.getValueFieldByAttribute(driver, "bitmask"), bitMask);
		detailCardStandardPage.sleepInSecond(1);
	}

	@Test
	public void CardStandard_02_Edit_CardStandard() {
		log.info("CardStandard_02 - Step 01: Click 'Sửa' icon");
		detailCardStandardPage.clickToEditIcon(driver);
		editCardStandardPage = PageGenerator.getEditCardStandardPage(driver);
		
		log.info("CardStandard_02 - Step 02: Enter valid data to required fields");
		editCardStandardPage.enterToTextboxByIDName(driver, "name", cardStandardNameUpdate);
		editCardStandardPage.enterToTextboxByIDName(driver, "total_bits", totalBitsUpdate);
		editCardStandardPage.enterToTextboxByIDName(driver, "bitmask", bitMaskUpdate);
		
		log.info("CardStandard_02 - Step 03: Click 'Cập nhật Chuẩn thẻ' button");
		editCardStandardPage.clickToButtonByIDName(driver, "Cập nhật Chuẩn thẻ");
		
		log.info("CardStandard_02 - Step 04: Verify detail card standard");
		detailCardStandardPage = PageGenerator.getDetailCardStandardPage(driver);
		verifyTrue(detailCardStandardPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailCardStandardPage.getValueFieldByAttribute(driver, "name"), cardStandardNameUpdate);
		verifyEquals(detailCardStandardPage.getValueFieldByAttribute(driver, "total_bits"), totalBitsUpdate);
		verifyEquals(detailCardStandardPage.getValueFieldByAttribute(driver, "bitmask"), bitMaskUpdate);
		cardStandardNameUpdateCookie = detailCardStandardPage.getValueFieldByAttribute(driver, "name");
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
