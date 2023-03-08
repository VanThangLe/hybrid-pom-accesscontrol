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
import pageObjects.accesscontrol.ac_face_reader.AddACFaceReaderPageObject;
import pageObjects.accesscontrol.ac_face_reader.DetailACFaceReaderPageObject;
import pageObjects.accesscontrol.ac_face_reader.EditACFaceReaderPageObject;
import pageObjects.accesscontrol.ac_face_reader.ACFaceReaderListPageObject;

public class AC_Face_Reader extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	AddACFaceReaderPageObject addFaceReaderPage;
	DetailACFaceReaderPageObject detailFaceReaderPage;
	ACFaceReaderListPageObject faceReaderListPage;
	EditACFaceReaderPageObject editFaceReaderPage;
	String faceReaderName, deviceModel, dataVer, dataSyncVer, softwareVer, installVer;
	String faceReaderNameUpdate, deviceModelUpdate, dataVerUpdate, dataSyncVerUpdate, softwareVerUpdate, installVerUpdate;

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
		
		faceReaderName = "Face Reader 1";
		deviceModel = "S300";
		dataVer = "0";
		dataSyncVer = "0";
		softwareVer = "0";
		installVer = "0";
		
		faceReaderNameUpdate = "Face Reader 1 Update";
		deviceModelUpdate = "S301";
		dataVerUpdate = "1";
		dataSyncVerUpdate = "1";
		softwareVerUpdate = "1";
		installVerUpdate = "1";
	}

	@Test
	public void FaceReader_01_Add_New_FaceReader() {
		log.info("FaceReader_01 - Step 01: Open 'Thiết bị đọc FaceID' menu");
		dashboardPage.openMenuPage(driver, "Thiết bị đọc FaceID");
		faceReaderListPage = PageGenerator.getFaceReaderListPage(driver);
		
		log.info("FaceReader_01 - Step 02: Click 'Thêm Thiết bị đọc FaceID'");
		faceReaderListPage.clickToButtonByIDName(driver, "Thêm Thiết bị đọc FaceID");
		addFaceReaderPage = PageGenerator.getAddFaceReaderPage(driver);
		
		log.info("FaceReader_01 - Step 03: Enter valid data to required fields");
		addFaceReaderPage.enterToTextboxByIDName(driver, "name", faceReaderName);
		addFaceReaderPage.enterToTextboxByIDName(driver, "model_name", deviceModel);
		addFaceReaderPage.enterToTextboxByIDName(driver, "data_version", dataVer);
		addFaceReaderPage.enterToTextboxByIDName(driver, "synced_data_version", dataSyncVer);
		addFaceReaderPage.enterToTextboxByIDName(driver, "software_version", softwareVer);
		addFaceReaderPage.enterToTextboxByIDName(driver, "installed_version", installVer);
		
		log.info("FaceReader_01 - Step 04: Click 'Thêm Thiết bị đọc FaceID'");
		addFaceReaderPage.clickToButtonByIDName(driver, "Thêm Thiết bị đọc FaceID");
		
		log.info("FaceReader_01 - Step 05: Verify detail face reader");
		detailFaceReaderPage = PageGenerator.getDetailFaceReaderPage(driver);
		verifyTrue(detailFaceReaderPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailFaceReaderPage.getValueFieldByAttribute(driver, "name"), faceReaderName);
		verifyEquals(detailFaceReaderPage.getValueFieldByAttribute(driver, "model_name"), deviceModel);
		verifyEquals(detailFaceReaderPage.getValueFieldByAttribute(driver, "data_version"), dataVer);
		verifyEquals(detailFaceReaderPage.getValueFieldByAttribute(driver, "synced_data_version"), dataSyncVer);
		verifyEquals(detailFaceReaderPage.getValueFieldByAttribute(driver, "software_version"), softwareVer);
		verifyEquals(detailFaceReaderPage.getValueFieldByAttribute(driver, "installed_version"), installVer);
		detailFaceReaderPage.sleepInSecond(1);
	}

	@Test
	public void FaceReader_02_Edit_FaceReader() {
		log.info("FaceReader_02 - Step 01: Click 'Sửa' icon");
		detailFaceReaderPage.clickToEditIcon(driver);
		editFaceReaderPage = PageGenerator.getEditFaceReaderPage(driver);
		
		log.info("FaceReader_02 - Step 02: Enter valid data to required fields");
		editFaceReaderPage.enterToTextboxByIDName(driver, "name", faceReaderNameUpdate);
		editFaceReaderPage.enterToTextboxByIDName(driver, "model_name", deviceModelUpdate);
		editFaceReaderPage.enterToTextboxByIDName(driver, "data_version", dataVerUpdate);
		editFaceReaderPage.enterToTextboxByIDName(driver, "synced_data_version", dataSyncVerUpdate);
		editFaceReaderPage.enterToTextboxByIDName(driver, "software_version", softwareVerUpdate);
		editFaceReaderPage.enterToTextboxByIDName(driver, "installed_version", installVerUpdate);
		
		log.info("FaceReader_02 - Step 03: Click 'Cập nhật Thiết bị đọc FaceID' button");
		editFaceReaderPage.clickToButtonByIDName(driver, "Cập nhật Thiết bị đọc FaceID");
		
		log.info("FaceReader_02 - Step 04: Verify detail face reader");
		detailFaceReaderPage = PageGenerator.getDetailFaceReaderPage(driver);
		verifyTrue(detailFaceReaderPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailFaceReaderPage.getValueFieldByAttribute(driver, "name"), faceReaderNameUpdate);
		verifyEquals(detailFaceReaderPage.getValueFieldByAttribute(driver, "model_name"), deviceModelUpdate);
		verifyEquals(detailFaceReaderPage.getValueFieldByAttribute(driver, "data_version"), dataVerUpdate);
		verifyEquals(detailFaceReaderPage.getValueFieldByAttribute(driver, "synced_data_version"), dataSyncVerUpdate);
		verifyEquals(detailFaceReaderPage.getValueFieldByAttribute(driver, "software_version"), softwareVerUpdate);
		verifyEquals(detailFaceReaderPage.getValueFieldByAttribute(driver, "installed_version"), installVerUpdate);
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
