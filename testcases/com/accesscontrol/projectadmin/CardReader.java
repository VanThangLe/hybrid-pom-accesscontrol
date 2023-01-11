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
import pageObjects.accesscontrol.cardreader.AddCardReaderPageObject;
import pageObjects.accesscontrol.cardreader.CardReaderListPageObject;
import pageObjects.accesscontrol.cardreader.DetailCardReaderPageObject;
import pageObjects.accesscontrol.cardreader.EditCardReaderPageObject;

public class CardReader extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	AddCardReaderPageObject addCardReaderPage;
	DetailCardReaderPageObject detailCardReaderPage;
	EditCardReaderPageObject editCardReaderPage;
	CardReaderListPageObject cardReaderListPage;
	String cardReaderName, position, sessionTimeoutSec, controllerName, orderNumber;
	String cardReaderNameUpdate, positionUpdate, sessionTimeoutSecUpdate, controllerNameUpdate, orderNumberUpdate;
	public static String cardReaderNameUpdateCookie;

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
		
		cardReaderName = "Card Reader 1";
		position = "Trước lối vào";
		sessionTimeoutSec = "10";
		controllerName = Controller.controllerNameUpdateCookie;
		orderNumber = "0";
		cardReaderNameUpdate = "Card Reader 1 Update";
		positionUpdate = "Sau lối vào";
		sessionTimeoutSecUpdate = "5";
		orderNumberUpdate = "1";
	}

	@Test
	public void CardReader_01_Add_New_CardReader() {
		log.info("User_01 - Step 01: Open 'Danh sách người dùng' menu");
		dashboardPage.openMenuPage(driver, "Danh sách người dùng");
		cardReaderListPage = PageGenerator.getCardReaderListPage(driver);
		
		log.info("User_01 - Step 02: Click 'Thêm đầu đọc thẻ'");
		cardReaderListPage.clickToButtonByIDName(driver, "Thêm Người dùng");
		addCardReaderPage = PageGenerator.getAddCardReaderPage(driver);
		
		log.info("User_01 - Step 03: Enter valid data to required fields");
		addCardReaderPage.enterToTextboxByIDName(driver, "name", "");
		
		log.info("User_01 - Step 04: Click 'Thêm Người dùng'");
		addCardReaderPage.clickToButtonByIDName(driver, "Thêm Người dùng");
		
		log.info("User_01 - Step 05: Verify detail user");
		detailCardReaderPage = PageGenerator.getDetailCardReaderPage(driver);
		verifyTrue(detailCardReaderPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailCardReaderPage.getValueFieldByAttribute(driver, "name"), "");
		detailCardReaderPage.sleepInSecond(1);
	}

	@Test
	public void CardReader_02_Edit_CardReader() {
		
	}

	@Test
	public void CardReader_03_Add_New_DO_Event() {
		
	}
	
	@Test
	public void CardReader_04_Edit_DO_Event() {
		
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
