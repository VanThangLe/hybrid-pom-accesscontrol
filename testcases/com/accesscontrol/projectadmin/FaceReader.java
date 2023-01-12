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
import pageObjects.accesscontrol.facereader.AddFaceReaderPageObject;
import pageObjects.accesscontrol.facereader.DetailFaceReaderPageObject;
import pageObjects.accesscontrol.facereader.EditFaceReaderPageObject;
import pageObjects.accesscontrol.facereader.FaceReaderListPageObject;

public class FaceReader extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	AddFaceReaderPageObject addFaceReaderPage;
	DetailFaceReaderPageObject detailFaceReaderPage;
	FaceReaderListPageObject faceReaderListPage;
	EditFaceReaderPageObject editFaceReaderPage;
	String faceReaderName, deviceModel, dataVer, dataSyncVer, softVer, softSyncVer;
	String faceReaderNameUpdate, deviceModelUpdate, dataVerUpdate, dataSyncVerUpdate, softVerUpdate, softSyncVerUpdate;

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
		
		faceReaderName = "";
		deviceModel = "";
		dataVer = "";
		dataSyncVer = "";
		softVer = "";
		softSyncVer = "";
		faceReaderNameUpdate = "";
		deviceModelUpdate = "";
		dataVerUpdate = "";
		dataSyncVerUpdate = "";
		softVerUpdate = "";
		softSyncVerUpdate = "";
	}

	@Test
	public void FaceReader_01_Add_New_FaceReader() {
		
	}

	@Test
	public void FaceReader_02_Edit_FaceReader() {
		
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
