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
import pageObjects.accesscontrol.ac_project.AddACProjectPageObject;
import pageObjects.accesscontrol.ac_project.DetailACProjectPageObject;
import pageObjects.accesscontrol.ac_project.ACProjectListPageObject;

public class AC_Project extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	AddACProjectPageObject addACProjectPage;
	ACProjectListPageObject acProjectListPage;
	DetailACProjectPageObject detailACProjectPage;
	String acProjectName, acProjectCode;
	public static String acProjectNameCookie;

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
		
		acProjectName = "Project 1";
		acProjectCode = "P1";
	}

	@Test
	public void AC_Project_01_Add_New_AC_Project() {
		log.info("AC_Project_01 - Step 01: Open 'Dự án' menu");
		dashboardPage.openMenuPage(driver, "Dự án");
		acProjectListPage = PageGenerator.getACProjectListPage(driver);
		
		log.info("AC_Project_01 - Step 02: Click 'Thêm Dự án' button");
		acProjectListPage.clickToButtonByIDName(driver, "Thêm Dự án");
		addACProjectPage = PageGenerator.getAddACProjectPage(driver);
		
		log.info("AC_Project_01 - Step 03: Enter valid data to required fields");
		addACProjectPage.enterToTextboxByIDName(driver, "name", acProjectName);
		addACProjectPage.enterToTextboxByIDName(driver, "code", acProjectCode);
		addACProjectPage.clickToCheckboxByID(driver, "has_approvement-tinh-nang-du-an-boolean-field");
		addACProjectPage.clickToCheckboxByID(driver, "has_work_schedule-tinh-nang-du-an-boolean-field");
		
		log.info("AC_Project_01 - Step 04: Click 'Thêm Dự án' button");
		addACProjectPage.clickToButtonByIDName(driver, "Thêm Dự án");
		
		log.info("AC_Project_01 - Step 05: Verify detail project");
		detailACProjectPage = PageGenerator.getDetailACProjectPage(driver);
		verifyTrue(detailACProjectPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailACProjectPage.getValueFieldByAttribute(driver, "name"), acProjectName);
		verifyEquals(detailACProjectPage.getValueFieldByAttribute(driver, "code"), acProjectCode);
		verifyTrue(detailACProjectPage.isCheckboxButtonSelectedByID(driver, "has_approvement-tinh-nang-du-an-boolean-field"));
		verifyTrue(detailACProjectPage.isCheckboxButtonSelectedByID(driver, "has_work_schedule-tinh-nang-du-an-boolean-field"));
		acProjectNameCookie = detailACProjectPage.getValueFieldByAttribute(driver, "name");
		
		log.info("AC_Project_01 - Step 06: Click 'Chọn dự án'");
		detailACProjectPage.clickToActionButtonByDusk(driver, "6-control-selector");
		
		log.info("AC_Project_01 - Step 07: Click confirm action");
		detailACProjectPage.clickToActionButtonByDusk(driver, "confirm-action-button");
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
