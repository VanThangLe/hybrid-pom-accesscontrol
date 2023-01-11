package com.accesscontrol.superadmin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.accesscontrol.common.LoginSuperAdmin;

import commons.BaseTest;
import pageObjects.accesscontrol.DashboardPageObject;
import pageObjects.accesscontrol.LoginPageObject;
import pageObjects.accesscontrol.PageGenerator;
import pageObjects.accesscontrol.project.AddProjectPageObject;
import pageObjects.accesscontrol.project.DetailProjectPageObject;
import pageObjects.accesscontrol.project.EditProjectPageObject;
import pageObjects.accesscontrol.project.ProjectListPageObject;

public class Project extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	AddProjectPageObject addProjectPage;
	ProjectListPageObject projectListPage;
	EditProjectPageObject editProjectPage;
	DetailProjectPageObject detailProjectPage;
	String projectName, projectCode;
	String projectNameUpdate, projectCodeUpdate;
	public static String projectNameUpdateCookie;

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
		
		projectName = "Project 1";
		projectCode = "P1";
		projectNameUpdate = "Project 1 Update";
		projectCodeUpdate = "P1Update";
	}

	@Test
	public void Project_01_Add_New_Project() {
		log.info("Project_01 - Step 01: Open 'Dự án' menu");
		dashboardPage.openMenuPage(driver, "Dự án");
		projectListPage = PageGenerator.getProjectListPage(driver);
		
		log.info("Project_01 - Step 02: Click 'Thêm Dự án' button");
		projectListPage.clickToButtonByIDName(driver, "Thêm Dự án");
		addProjectPage = PageGenerator.getAddProjectPage(driver);
		
		log.info("Project_01 - Step 03: Enter valid data to required fields");
		addProjectPage.enterToTextboxByIDName(driver, "name", projectName);
		addProjectPage.enterToTextboxByIDName(driver, "code", projectCode);
		
		log.info("Project_01 - Step 04: Click 'Thêm Dự án' button");
		addProjectPage.clickToButtonByIDName(driver, "Thêm Dự án");
		
		log.info("Project_01 - Step 05: Verify detail user");
		detailProjectPage = PageGenerator.getDetailProjectPage(driver);
		verifyTrue(detailProjectPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailProjectPage.getValueFieldByAttribute(driver, "name"), projectName);
		verifyEquals(detailProjectPage.getValueFieldByAttribute(driver, "code"), projectCode);
		detailProjectPage.sleepInSecond(1);
	}

	@Test
	public void Project_02_Edit_Project() {
		log.info("Project_02 - Step 01: Click 'Sửa' icon");
		detailProjectPage.clickToEditIcon(driver);
		editProjectPage = PageGenerator.getEditProjectPage(driver);
		
		log.info("Project_02 - Step 02: Enter valid data to required fields");
		editProjectPage.enterToTextboxByIDName(driver, "name", projectNameUpdate);
		editProjectPage.enterToTextboxByIDName(driver, "code", projectCodeUpdate);
		
		log.info("Project_02 - Step 03: Click 'Cập nhật Dự án' button");
		editProjectPage.clickToButtonByIDName(driver, "Cập nhật Dự án");
		
		log.info("Project_02 - Step 04: Verify detail user");
		detailProjectPage = PageGenerator.getDetailProjectPage(driver);
		verifyTrue(detailProjectPage.isSuccessMessageDisplayed(driver));
		verifyEquals(detailProjectPage.getValueFieldByAttribute(driver, "name"), projectNameUpdate);
		verifyEquals(detailProjectPage.getValueFieldByAttribute(driver, "code"), projectCodeUpdate);
		projectNameUpdateCookie = detailProjectPage.getValueFieldByAttribute(driver, "name");
	}

	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-condition: Close browser '" + browserName + "'");
		cleanDriverInstance();
	}
}
