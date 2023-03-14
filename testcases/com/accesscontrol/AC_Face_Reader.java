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
import pageObjects.accesscontrol.ac_face_reader.AddACFaceReaderPageObject;
import pageObjects.accesscontrol.ac_face_reader.DetailACFaceReaderPageObject;
import pageObjects.accesscontrol.ac_face_reader.ACFaceReaderListPageObject;

public class AC_Face_Reader extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	AddACFaceReaderPageObject addACFaceReaderPage;
	DetailACFaceReaderPageObject detailACFaceReaderPage;
	ACFaceReaderListPageObject acFaceReaderListPage;
	String acFaceReaderName, deviceModel, dataVer, dataSyncVer, softwareVer, installVer;

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
		
		acFaceReaderName = "Face Reader 1";
		deviceModel = "S300";
		dataVer = "0";
		dataSyncVer = "0";
		softwareVer = "0";
		installVer = "0";
	}

	@Test
	public void AC_FaceReader_01_Add_New_AC_FaceReader() {
		log.info("AC_FaceReader_01 - Step 01: Open 'Thiết bị đọc FaceID' menu");
		dashboardPage.openMenuPage(driver, "Thiết bị đọc FaceID");
		acFaceReaderListPage = PageGenerator.getACFaceReaderListPage(driver);
		
		log.info("AC_FaceReader_01 - Step 02: Click 'Thêm Thiết bị đọc FaceID'");
		acFaceReaderListPage.clickToButtonByIDName(driver, "Thêm Thiết bị đọc FaceID");
		addACFaceReaderPage = PageGenerator.getAddACFaceReaderPage(driver);
		
		log.info("AC_FaceReader_01 - Step 03: Enter valid data to required fields");
		addACFaceReaderPage.enterToTextboxByIDName(driver, "name", acFaceReaderName);
		addACFaceReaderPage.enterToTextboxByIDName(driver, "model_name", deviceModel);
		addACFaceReaderPage.enterToTextboxByIDName(driver, "data_version", dataVer);
		addACFaceReaderPage.enterToTextboxByIDName(driver, "synced_data_version", dataSyncVer);
		addACFaceReaderPage.enterToTextboxByIDName(driver, "software_version", softwareVer);
		addACFaceReaderPage.enterToTextboxByIDName(driver, "installed_version", installVer);
		
		log.info("AC_FaceReader_01 - Step 04: Click 'Thêm Thiết bị đọc FaceID'");
		addACFaceReaderPage.clickToButtonByIDName(driver, "Thêm Thiết bị đọc FaceID");
		
		log.info("AC_FaceReader_01 - Step 05: Verify detail ac face reader");
		detailACFaceReaderPage = PageGenerator.getDetailACFaceReaderPage(driver);
		verifyTrue(detailACFaceReaderPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailACFaceReaderPage.getValueFieldByAttribute(driver, "name"), acFaceReaderName);
		verifyEquals(detailACFaceReaderPage.getValueFieldByAttribute(driver, "model_name"), deviceModel);
		verifyEquals(detailACFaceReaderPage.getValueFieldByAttribute(driver, "data_version"), dataVer);
		verifyEquals(detailACFaceReaderPage.getValueFieldByAttribute(driver, "synced_data_version"), dataSyncVer);
		verifyEquals(detailACFaceReaderPage.getValueFieldByAttribute(driver, "software_version"), softwareVer);
		verifyEquals(detailACFaceReaderPage.getValueFieldByAttribute(driver, "installed_version"), installVer);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
