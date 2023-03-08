package pageObjects.accesscontrol;

import org.openqa.selenium.WebDriver;

import pageObjects.accesscontrol.ac_card.ACCardListPageObject;
import pageObjects.accesscontrol.ac_card.AddACCardPageObject;
import pageObjects.accesscontrol.ac_card.DetailACCardPageObject;
import pageObjects.accesscontrol.ac_card.EditACCardPageObject;
import pageObjects.accesscontrol.ac_cardstandard.ACCardStandardListPageObject;
import pageObjects.accesscontrol.ac_cardstandard.AddACCardStandardPageObject;
import pageObjects.accesscontrol.ac_cardstandard.DetailACCardStandardPageObject;
import pageObjects.accesscontrol.ac_cardstandard.EditACCardStandardPageObject;
import pageObjects.accesscontrol.ac_controller.ACControllerListPageObject;
import pageObjects.accesscontrol.ac_controller.AddACControllerPageObject;
import pageObjects.accesscontrol.ac_controller.AddACDisPageObject;
import pageObjects.accesscontrol.ac_controller.DetailACControllerPageObject;
import pageObjects.accesscontrol.ac_controller.EditACControllerPageObject;
import pageObjects.accesscontrol.ac_controller.EditACDisPageObject;
import pageObjects.accesscontrol.ac_entry.ACEntryListPageObject;
import pageObjects.accesscontrol.ac_entry.AddACEntryPageObject;
import pageObjects.accesscontrol.ac_entry.AssignACReaderPageObject;
import pageObjects.accesscontrol.ac_entry.AssignACRolePageObject;
import pageObjects.accesscontrol.ac_entry.DetailACEntryPageObject;
import pageObjects.accesscontrol.ac_entry.EditACEntryPageObject;
import pageObjects.accesscontrol.ac_face_reader.ACFaceReaderListPageObject;
import pageObjects.accesscontrol.ac_face_reader.AddACFaceReaderPageObject;
import pageObjects.accesscontrol.ac_face_reader.DetailACFaceReaderPageObject;
import pageObjects.accesscontrol.ac_face_reader.EditACFaceReaderPageObject;
import pageObjects.accesscontrol.ac_gateway.ACGatewayListPageObject;
import pageObjects.accesscontrol.ac_gateway.AddACGatewayPageObject;
import pageObjects.accesscontrol.ac_gateway.DetailACGatewayPageObject;
import pageObjects.accesscontrol.ac_gateway.EditACGatewayPageObject;
import pageObjects.accesscontrol.ac_project.ACProjectListPageObject;
import pageObjects.accesscontrol.ac_project.AddACProjectPageObject;
import pageObjects.accesscontrol.ac_project.DetailACProjectPageObject;
import pageObjects.accesscontrol.ac_project.EditACProjectPageObject;
import pageObjects.accesscontrol.ac_reader.ACReaderListPageObject;
import pageObjects.accesscontrol.ac_reader.AddACReaderDoEventPageObject;
import pageObjects.accesscontrol.ac_reader.AddACReaderPageObject;
import pageObjects.accesscontrol.ac_reader.DetailACReaderPageObject;
import pageObjects.accesscontrol.ac_reader.EditACReaderDoEventPageObject;
import pageObjects.accesscontrol.ac_reader.EditACReaderPageObject;
import pageObjects.accesscontrol.ac_role.ACRoleListPageObject;
import pageObjects.accesscontrol.ac_role.AddACRolePageObject;
import pageObjects.accesscontrol.ac_role.AssignACUserPageObject;
import pageObjects.accesscontrol.ac_role.DetailACRolePageObject;
import pageObjects.accesscontrol.ac_role.EditACRolePageObject;
import pageObjects.accesscontrol.ac_role_entry_permission.ACRoleEntryPermissionListPageObject;
import pageObjects.accesscontrol.ac_role_entry_permission.AddACRoleEntryPermissionPageObject;
import pageObjects.accesscontrol.ac_role_entry_permission.DetailACRoleEntryPermissionPageObject;
import pageObjects.accesscontrol.ac_role_entry_permission.EditACRoleEntryPermissionPageObject;
import pageObjects.accesscontrol.ac_user.ACUserListPageObject;
import pageObjects.accesscontrol.ac_user.AddACUserPageObject;
import pageObjects.accesscontrol.ac_user.DetailACUserPageObject;
import pageObjects.accesscontrol.ac_user.EditACUserPageObject;
import pageObjects.accesscontrol.ac_user_approvement.ACUserApprovementListPageObject;
import pageObjects.accesscontrol.ac_user_approvement.AddACUserApprovementPageObject;
import pageObjects.accesscontrol.ac_user_approvement.DetailACUserApprovementPageObject;
import pageObjects.accesscontrol.ac_user_approvement.EditACUserApprovementPageObject;
import pageObjects.accesscontrol.ac_user_shift.ACUserShiftListPageObject;
import pageObjects.accesscontrol.ac_user_shift.AddACUserShiftPageObject;
import pageObjects.accesscontrol.ac_user_shift.DetailACUserShiftPageObject;
import pageObjects.accesscontrol.ac_user_shift.EditACUserShiftPageObject;
import pageObjects.accesscontrol.ac_user_type.ACUserTypeListPageObject;
import pageObjects.accesscontrol.ac_user_type.AddACUserTypePageObject;
import pageObjects.accesscontrol.ac_user_type.DetailACUserTypePageObject;
import pageObjects.accesscontrol.ac_user_type.EditACUserTypePageObject;
import pageObjects.accesscontrol.ac_user_work_schedule.ACUserWorkScheduleListPageObject;
import pageObjects.accesscontrol.ac_user_work_schedule.AddACUserWorkSchedulePageObject;
import pageObjects.accesscontrol.ac_user_work_schedule.DetailACUserWorkSchedulePageObject;
import pageObjects.accesscontrol.ac_user_work_schedule.EditACUserWorkSchedulePageObject;
import pageObjects.accesscontrol.excel_importer.ExcelImporterPageObject;
import pageObjects.accesscontrol.user.AssignProjectPageObject;

public class PageGenerator {
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static DashboardPageObject getDashboardPage(WebDriver driver) {
		return new DashboardPageObject(driver);
	}
	
	public static AddACUserPageObject getAddUserPage(WebDriver driver) {
		return new AddACUserPageObject(driver);
	}
	
	public static ACUserListPageObject getAccountListPage(WebDriver driver) {
		return new ACUserListPageObject(driver);
	}

	public static DetailACUserPageObject getDetailAccountPage(WebDriver driver) {
		return new DetailACUserPageObject(driver);
	}

	public static EditACUserPageObject getEditAccountPage(WebDriver driver) {
		return new EditACUserPageObject(driver);
	}
	
	public static AddACCardPageObject getAddCardPage(WebDriver driver) {
		return new AddACCardPageObject(driver);
	}
	
	public static ACCardListPageObject getCardListPage(WebDriver driver) {
		return new ACCardListPageObject(driver);
	}

	public static DetailACCardPageObject getDetailCardPage(WebDriver driver) {
		return new DetailACCardPageObject(driver);
	}

	public static EditACCardPageObject getEditCardPage(WebDriver driver) {
		return new EditACCardPageObject(driver);
	}
	
	public static AddACReaderPageObject getAddCardReaderPage(WebDriver driver) {
		return new AddACReaderPageObject(driver);
	}
	
	public static ACReaderListPageObject getCardReaderListPage(WebDriver driver) {
		return new ACReaderListPageObject(driver);
	}

	public static DetailACReaderPageObject getDetailCardReaderPage(WebDriver driver) {
		return new DetailACReaderPageObject(driver);
	}

	public static EditACReaderPageObject getEditCardReaderPage(WebDriver driver) {
		return new EditACReaderPageObject(driver);
	}
	
	public static AddACCardStandardPageObject getAddCardStandardPage(WebDriver driver) {
		return new AddACCardStandardPageObject(driver);
	}
	
	public static ACCardStandardListPageObject getCardStandardListPage(WebDriver driver) {
		return new ACCardStandardListPageObject(driver);
	}

	public static DetailACCardStandardPageObject getDetailCardStandardPage(WebDriver driver) {
		return new DetailACCardStandardPageObject(driver);
	}

	public static EditACCardStandardPageObject getEditCardStandardPage(WebDriver driver) {
		return new EditACCardStandardPageObject(driver);
	}
	
	public static AddACControllerPageObject getAddControllerPage(WebDriver driver) {
		return new AddACControllerPageObject(driver);
	}
	
	public static ACControllerListPageObject getControllerListPage(WebDriver driver) {
		return new ACControllerListPageObject(driver);
	}

	public static DetailACControllerPageObject getDetailControllerPage(WebDriver driver) {
		return new DetailACControllerPageObject(driver);
	}

	public static EditACControllerPageObject getEditControllerPage(WebDriver driver) {
		return new EditACControllerPageObject(driver);
	}
	
	public static AddACEntryPageObject getAddEntryPage(WebDriver driver) {
		return new AddACEntryPageObject(driver);
	}
	
	public static ACEntryListPageObject getEntryListPage(WebDriver driver) {
		return new ACEntryListPageObject(driver);
	}

	public static DetailACEntryPageObject getDetailEntryPage(WebDriver driver) {
		return new DetailACEntryPageObject(driver);
	}

	public static EditACEntryPageObject getEditEntryPage(WebDriver driver) {
		return new EditACEntryPageObject(driver);
	}
	
	public static AddACFaceReaderPageObject getAddFaceReaderPage(WebDriver driver) {
		return new AddACFaceReaderPageObject(driver);
	}
	
	public static ACFaceReaderListPageObject getFaceReaderListPage(WebDriver driver) {
		return new ACFaceReaderListPageObject(driver);
	}

	public static DetailACFaceReaderPageObject getDetailFaceReaderPage(WebDriver driver) {
		return new DetailACFaceReaderPageObject(driver);
	}

	public static EditACFaceReaderPageObject getEditFaceReaderPage(WebDriver driver) {
		return new EditACFaceReaderPageObject(driver);
	}
	
	public static AddACRoleEntryPermissionPageObject getAddPermissionPage(WebDriver driver) {
		return new AddACRoleEntryPermissionPageObject(driver);
	}
	
	public static EditACRoleEntryPermissionPageObject getEditPermissionPage(WebDriver driver) {
		return new EditACRoleEntryPermissionPageObject(driver);
	}
	
	public static ACRoleEntryPermissionListPageObject getPermissionListPage(WebDriver driver) {
		return new ACRoleEntryPermissionListPageObject(driver);
	}

	public static DetailACRoleEntryPermissionPageObject getDetailPermissionPage(WebDriver driver) {
		return new DetailACRoleEntryPermissionPageObject(driver);
	}
	
	public static AddACProjectPageObject getAddProjectPage(WebDriver driver) {
		return new AddACProjectPageObject(driver);
	}
	
	public static ACProjectListPageObject getProjectListPage(WebDriver driver) {
		return new ACProjectListPageObject(driver);
	}

	public static DetailACProjectPageObject getDetailProjectPage(WebDriver driver) {
		return new DetailACProjectPageObject(driver);
	}

	public static EditACProjectPageObject getEditProjectPage(WebDriver driver) {
		return new EditACProjectPageObject(driver);
	}
	
	public static AddACUserPageObject getAddUserPage(WebDriver driver) {
		return new AddACUserPageObject(driver);
	}
	
	public static ACUserListPageObject getUserListPage(WebDriver driver) {
		return new ACUserListPageObject(driver);
	}

	public static DetailACUserPageObject getDetailUserPage(WebDriver driver) {
		return new DetailACUserPageObject(driver);
	}

	public static EditACUserPageObject getEditUserPage(WebDriver driver) {
		return new EditACUserPageObject(driver);
	}
	
	public static AddACRolePageObject getAddUserGroupPage(WebDriver driver) {
		return new AddACRolePageObject(driver);
	}
	
	public static ACRoleListPageObject getUserGroupListPage(WebDriver driver) {
		return new ACRoleListPageObject(driver);
	}

	public static DetailACRolePageObject getDetailUserGroupPage(WebDriver driver) {
		return new DetailACRolePageObject(driver);
	}

	public static EditACRolePageObject getEditUserGroupPage(WebDriver driver) {
		return new EditACRolePageObject(driver);
	}
	
	public static AddACUserTypePageObject getAddUserTypePage(WebDriver driver) {
		return new AddACUserTypePageObject(driver);
	}
	
	public static ACUserTypeListPageObject getUserTypeListPage(WebDriver driver) {
		return new ACUserTypeListPageObject(driver);
	}

	public static DetailACUserTypePageObject getDetailUserTypePage(WebDriver driver) {
		return new DetailACUserTypePageObject(driver);
	}

	public static EditACUserTypePageObject getEditUserTypePage(WebDriver driver) {
		return new EditACUserTypePageObject(driver);
	}

	public static AssignACUserPageObject getAssignUserPage(WebDriver driver) {
		return new AssignACUserPageObject(driver);
	}

	public static AssignProjectPageObject getAssignProjectPage(WebDriver driver) {
		return new AssignProjectPageObject(driver);
	}
	
	public static AddACDisPageObject getAddDIPage(WebDriver driver) {
		return new AddACDisPageObject(driver);
	}
	
	public static EditACDisPageObject getEditDIPage(WebDriver driver) {
		return new EditACDisPageObject(driver);
	}
	
	public static AddACReaderDoEventPageObject getAddDOEventPage(WebDriver driver) {
		return new AddACReaderDoEventPageObject(driver);
	}
	
	public static EditACReaderDoEventPageObject getEditDOEventPage(WebDriver driver) {
		return new EditACReaderDoEventPageObject(driver);
	}

	public static AssignACReaderPageObject getAssignReaderPage(WebDriver driver) {
		return new AssignACReaderPageObject(driver);
	}
	
	public static AssignACRolePageObject getAssignUserGroupPage(WebDriver driver) {
		return new AssignACRolePageObject(driver);
	}
	
	public static AddACUserApprovementPageObject getAddACUserApprovementPage(WebDriver driver) {
		return new AddACUserApprovementPageObject(driver);
	}
	
	public static ACUserApprovementListPageObject getACUserApprovementListPage(WebDriver driver) {
		return new ACUserApprovementListPageObject(driver);
	}

	public static DetailACUserApprovementPageObject getDetailACUserApprovementPage(WebDriver driver) {
		return new DetailACUserApprovementPageObject(driver);
	}

	public static EditACUserApprovementPageObject getEditACUserApprovementPage(WebDriver driver) {
		return new EditACUserApprovementPageObject(driver);
	}
	
	public static AddACUserShiftPageObject getAddUserShiftPage(WebDriver driver) {
		return new AddACUserShiftPageObject(driver);
	}
	
	public static ACUserShiftListPageObject getUserShiftListPage(WebDriver driver) {
		return new ACUserShiftListPageObject(driver);
	}

	public static DetailACUserShiftPageObject getDetailUserShiftPage(WebDriver driver) {
		return new DetailACUserShiftPageObject(driver);
	}

	public static EditACUserShiftPageObject getEditUserShiftPage(WebDriver driver) {
		return new EditACUserShiftPageObject(driver);
	}
	
	public static AddACUserWorkSchedulePageObject getAddUserWorkSchedulePage(WebDriver driver) {
		return new AddACUserWorkSchedulePageObject(driver);
	}
	
	public static ACUserWorkScheduleListPageObject getUserWorkScheduleListPage(WebDriver driver) {
		return new ACUserWorkScheduleListPageObject(driver);
	}

	public static DetailACUserWorkSchedulePageObject getDetailUserWorkSchedulePage(WebDriver driver) {
		return new DetailACUserWorkSchedulePageObject(driver);
	}

	public static EditACUserWorkSchedulePageObject getEditUserWorkSchedulePage(WebDriver driver) {
		return new EditACUserWorkSchedulePageObject(driver);
	}
	
	public static ExcelImporterPageObject getExcelImporterPage(WebDriver driver) {
		return new ExcelImporterPageObject(driver);
	}
	
	public static AddACGatewayPageObject getAddACGatewayPage(WebDriver driver) {
		return new AddACGatewayPageObject(driver);
	}
	
	public static ACGatewayListPageObject getACGatewayListPage(WebDriver driver) {
		return new ACGatewayListPageObject(driver);
	}

	public static DetailACGatewayPageObject getDetailACGatewayPage(WebDriver driver) {
		return new DetailACGatewayPageObject(driver);
	}

	public static EditACGatewayPageObject getEditACGatewayPage(WebDriver driver) {
		return new EditACGatewayPageObject(driver);
	}
}