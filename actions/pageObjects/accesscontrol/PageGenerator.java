package pageObjects.accesscontrol;

import org.openqa.selenium.WebDriver;

import pageObjects.accesscontrol.ac_card.ACCardListPageObject;
import pageObjects.accesscontrol.ac_card.AddACCardPageObject;
import pageObjects.accesscontrol.ac_card.DetailACCardPageObject;
import pageObjects.accesscontrol.ac_cardstandard.ACCardStandardListPageObject;
import pageObjects.accesscontrol.ac_cardstandard.AddACCardStandardPageObject;
import pageObjects.accesscontrol.ac_cardstandard.DetailACCardStandardPageObject;
import pageObjects.accesscontrol.ac_controller.ACControllerListPageObject;
import pageObjects.accesscontrol.ac_controller.AddACControllerPageObject;
import pageObjects.accesscontrol.ac_controller.AddACDisPageObject;
import pageObjects.accesscontrol.ac_controller.DetailACControllerPageObject;
import pageObjects.accesscontrol.ac_entry.ACEntryListPageObject;
import pageObjects.accesscontrol.ac_entry.AddACEntryPageObject;
import pageObjects.accesscontrol.ac_entry.AssignACReaderPageObject;
import pageObjects.accesscontrol.ac_entry.AssignACRolePageObject;
import pageObjects.accesscontrol.ac_entry.DetailACEntryPageObject;
import pageObjects.accesscontrol.ac_face_reader.ACFaceReaderListPageObject;
import pageObjects.accesscontrol.ac_face_reader.AddACFaceReaderPageObject;
import pageObjects.accesscontrol.ac_face_reader.DetailACFaceReaderPageObject;
import pageObjects.accesscontrol.ac_gateway.ACGatewayListPageObject;
import pageObjects.accesscontrol.ac_gateway.AddACGatewayPageObject;
import pageObjects.accesscontrol.ac_gateway.DetailACGatewayPageObject;
import pageObjects.accesscontrol.ac_project.ACProjectListPageObject;
import pageObjects.accesscontrol.ac_project.AddACProjectPageObject;
import pageObjects.accesscontrol.ac_project.DetailACProjectPageObject;
import pageObjects.accesscontrol.ac_reader.ACReaderListPageObject;
import pageObjects.accesscontrol.ac_reader.AddACReaderDoEventPageObject;
import pageObjects.accesscontrol.ac_reader.AddACReaderPageObject;
import pageObjects.accesscontrol.ac_reader.DetailACReaderPageObject;
import pageObjects.accesscontrol.ac_role.ACRoleListPageObject;
import pageObjects.accesscontrol.ac_role.AddACRolePageObject;
import pageObjects.accesscontrol.ac_role.AssignACUserPageObject;
import pageObjects.accesscontrol.ac_role.DetailACRolePageObject;
import pageObjects.accesscontrol.ac_role_entry_permission.ACRoleEntryPermissionListPageObject;
import pageObjects.accesscontrol.ac_role_entry_permission.AddACRoleEntryPermissionPageObject;
import pageObjects.accesscontrol.ac_role_entry_permission.DetailACRoleEntryPermissionPageObject;
import pageObjects.accesscontrol.ac_user.ACUserListPageObject;
import pageObjects.accesscontrol.ac_user.AddACUserPageObject;
import pageObjects.accesscontrol.ac_user.DetailACUserPageObject;
import pageObjects.accesscontrol.ac_user_approvement.ACUserApprovementListPageObject;
import pageObjects.accesscontrol.ac_user_approvement.AddACUserApprovementPageObject;
import pageObjects.accesscontrol.ac_user_approvement.DetailACUserApprovementPageObject;
import pageObjects.accesscontrol.ac_user_shift.ACUserShiftListPageObject;
import pageObjects.accesscontrol.ac_user_shift.AddACUserShiftPageObject;
import pageObjects.accesscontrol.ac_user_shift.DetailACUserShiftPageObject;
import pageObjects.accesscontrol.ac_user_type.ACUserTypeListPageObject;
import pageObjects.accesscontrol.ac_user_type.AddACUserTypePageObject;
import pageObjects.accesscontrol.ac_user_type.DetailACUserTypePageObject;
import pageObjects.accesscontrol.ac_user_work_schedule.ACUserWorkScheduleListPageObject;
import pageObjects.accesscontrol.ac_user_work_schedule.AddACUserWorkSchedulePageObject;
import pageObjects.accesscontrol.ac_user_work_schedule.DetailACUserWorkSchedulePageObject;
import pageObjects.accesscontrol.excel_importer.ExcelImporterPageObject;
import pageObjects.accesscontrol.role.AddRolePageObject;
import pageObjects.accesscontrol.role.AssignUserPageObject;
import pageObjects.accesscontrol.role.DetailRolePageObject;
import pageObjects.accesscontrol.role.RoleListPageObject;
import pageObjects.accesscontrol.user.AddUserPageObject;
import pageObjects.accesscontrol.user.AssignACProjectPageObject;
import pageObjects.accesscontrol.user.DetailUserPageObject;
import pageObjects.accesscontrol.user.UserListPageObject;

public class PageGenerator {
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static DashboardPageObject getDashboardPage(WebDriver driver) {
		return new DashboardPageObject(driver);
	}
	
	public static AddACUserPageObject getAddACUserPage(WebDriver driver) {
		return new AddACUserPageObject(driver);
	}
	
	public static ACUserListPageObject getACUserListPage(WebDriver driver) {
		return new ACUserListPageObject(driver);
	}

	public static DetailACUserPageObject getDetailACUserPage(WebDriver driver) {
		return new DetailACUserPageObject(driver);
	}
	
	public static AddACCardPageObject getAddACCardPage(WebDriver driver) {
		return new AddACCardPageObject(driver);
	}
	
	public static ACCardListPageObject getACCardListPage(WebDriver driver) {
		return new ACCardListPageObject(driver);
	}

	public static DetailACCardPageObject getDetailACCardPage(WebDriver driver) {
		return new DetailACCardPageObject(driver);
	}
	
	public static AddACReaderPageObject getAddACReaderPage(WebDriver driver) {
		return new AddACReaderPageObject(driver);
	}
	
	public static ACReaderListPageObject getACReaderListPage(WebDriver driver) {
		return new ACReaderListPageObject(driver);
	}

	public static DetailACReaderPageObject getDetailACReaderPage(WebDriver driver) {
		return new DetailACReaderPageObject(driver);
	}
	
	public static AddACCardStandardPageObject getAddACCardStandardPage(WebDriver driver) {
		return new AddACCardStandardPageObject(driver);
	}
	
	public static ACCardStandardListPageObject getACCardStandardListPage(WebDriver driver) {
		return new ACCardStandardListPageObject(driver);
	}

	public static DetailACCardStandardPageObject getDetailACCardStandardPage(WebDriver driver) {
		return new DetailACCardStandardPageObject(driver);
	}
	
	public static AddACControllerPageObject getAddACControllerPage(WebDriver driver) {
		return new AddACControllerPageObject(driver);
	}
	
	public static ACControllerListPageObject getACControllerListPage(WebDriver driver) {
		return new ACControllerListPageObject(driver);
	}

	public static DetailACControllerPageObject getDetailACControllerPage(WebDriver driver) {
		return new DetailACControllerPageObject(driver);
	}
	
	public static AddACEntryPageObject getAddACEntryPage(WebDriver driver) {
		return new AddACEntryPageObject(driver);
	}
	
	public static ACEntryListPageObject getACEntryListPage(WebDriver driver) {
		return new ACEntryListPageObject(driver);
	}

	public static DetailACEntryPageObject getDetailACEntryPage(WebDriver driver) {
		return new DetailACEntryPageObject(driver);
	}
	
	public static AddACFaceReaderPageObject getAddACFaceReaderPage(WebDriver driver) {
		return new AddACFaceReaderPageObject(driver);
	}
	
	public static ACFaceReaderListPageObject getACFaceReaderListPage(WebDriver driver) {
		return new ACFaceReaderListPageObject(driver);
	}

	public static DetailACFaceReaderPageObject getDetailACFaceReaderPage(WebDriver driver) {
		return new DetailACFaceReaderPageObject(driver);
	}
	
	public static AddACRoleEntryPermissionPageObject getAddACRoleEntryPermissionPage(WebDriver driver) {
		return new AddACRoleEntryPermissionPageObject(driver);
	}
	
	public static ACRoleEntryPermissionListPageObject getACRoleEntryPermissionListPage(WebDriver driver) {
		return new ACRoleEntryPermissionListPageObject(driver);
	}

	public static DetailACRoleEntryPermissionPageObject getDetailACRoleEntryPermissionPage(WebDriver driver) {
		return new DetailACRoleEntryPermissionPageObject(driver);
	}
	
	public static AddACProjectPageObject getAddACProjectPage(WebDriver driver) {
		return new AddACProjectPageObject(driver);
	}
	
	public static ACProjectListPageObject getACProjectListPage(WebDriver driver) {
		return new ACProjectListPageObject(driver);
	}

	public static DetailACProjectPageObject getDetailACProjectPage(WebDriver driver) {
		return new DetailACProjectPageObject(driver);
	}
	
	public static AddUserPageObject getAddUserPage(WebDriver driver) {
		return new AddUserPageObject(driver);
	}
	
	public static UserListPageObject getUserListPage(WebDriver driver) {
		return new UserListPageObject(driver);
	}

	public static DetailUserPageObject getDetailUserPage(WebDriver driver) {
		return new DetailUserPageObject(driver);
	}
	
	public static AddACRolePageObject getAddACRolePage(WebDriver driver) {
		return new AddACRolePageObject(driver);
	}
	
	public static ACRoleListPageObject getACRoleListPage(WebDriver driver) {
		return new ACRoleListPageObject(driver);
	}

	public static DetailACRolePageObject getDetailACRolePage(WebDriver driver) {
		return new DetailACRolePageObject(driver);
	}
	
	public static AddACUserTypePageObject getAddACUserTypePage(WebDriver driver) {
		return new AddACUserTypePageObject(driver);
	}
	
	public static ACUserTypeListPageObject getACUserTypeListPage(WebDriver driver) {
		return new ACUserTypeListPageObject(driver);
	}

	public static DetailACUserTypePageObject getDetailACUserTypePage(WebDriver driver) {
		return new DetailACUserTypePageObject(driver);
	}

	public static AssignACUserPageObject getAssignACUserPage(WebDriver driver) {
		return new AssignACUserPageObject(driver);
	}

	public static AssignACProjectPageObject getAssignACProjectPage(WebDriver driver) {
		return new AssignACProjectPageObject(driver);
	}
	
	public static AddACDisPageObject getAddACDisPage(WebDriver driver) {
		return new AddACDisPageObject(driver);
	}
	
	public static AddACReaderDoEventPageObject getACReaderDoEventPage(WebDriver driver) {
		return new AddACReaderDoEventPageObject(driver);
	}

	public static AssignACReaderPageObject getAssignACReaderPage(WebDriver driver) {
		return new AssignACReaderPageObject(driver);
	}
	
	public static AssignACRolePageObject getAssignACRolePage(WebDriver driver) {
		return new AssignACRolePageObject(driver);
	}
	
	public static AssignUserPageObject getAssignUserPage(WebDriver driver) {
		return new AssignUserPageObject(driver);
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
	
	public static AddACUserShiftPageObject getAddACUserShiftPage(WebDriver driver) {
		return new AddACUserShiftPageObject(driver);
	}
	
	public static ACUserShiftListPageObject getACUserShiftListPage(WebDriver driver) {
		return new ACUserShiftListPageObject(driver);
	}

	public static DetailACUserShiftPageObject getDetailACUserShiftPage(WebDriver driver) {
		return new DetailACUserShiftPageObject(driver);
	}
	
	public static AddACUserWorkSchedulePageObject getAddACUserWorkSchedulePage(WebDriver driver) {
		return new AddACUserWorkSchedulePageObject(driver);
	}
	
	public static ACUserWorkScheduleListPageObject getACUserWorkScheduleListPage(WebDriver driver) {
		return new ACUserWorkScheduleListPageObject(driver);
	}

	public static DetailACUserWorkSchedulePageObject getDetailACUserWorkSchedulePage(WebDriver driver) {
		return new DetailACUserWorkSchedulePageObject(driver);
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
	
	public static AddRolePageObject getAddRolePage(WebDriver driver) {
		return new AddRolePageObject(driver);
	}
	
	public static RoleListPageObject getRoleListPage(WebDriver driver) {
		return new RoleListPageObject(driver);
	}

	public static DetailRolePageObject getDetailRolePage(WebDriver driver) {
		return new DetailRolePageObject(driver);
	}
}