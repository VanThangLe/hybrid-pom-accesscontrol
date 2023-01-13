package pageUIs.accesscontrol;

public class BasePageUI {
	public static final String BUTTON_BY_ID_NAME = "//span[text()='%s']";
	public static final String TEXTBOX_BY_ID_NAME = "//input[@dusk='%s']";
	public static final String VALUE_FIELD_BY_ATTRIBUTE = "//div[@dusk='%s']/div/p";
	public static final String DROPDOWN_BY_ID = "//select[@id='%s']";
	public static final String CHECKBOX_BY_LABEL = "//label[text()='%s']/following-sibling::input";
	public static final String RADIO_BY_LABEL = "//label[text()='%s']/preceding-sibling::input";
	public static final String TABLE_HEADER_BY_NAME = "//tr/th[string()='%s']";
	public static final String TABLE_LIST_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX = "//tr['%s']/td['%s']";
	public static final String TABLE_ASSIGN_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX = "//div[@data-relationship='%s']//tr['%s']/td['%s']";
	public static final String MENU_BY_PAGE_NAME = "//div[@class='sidebar-menu pb-24 space-y-6']//span[contains(text(),'%s')]";
	public static final String ASSIGN_DROPDOWN = "//div[@dusk='%s']";
	
	public static final String EDIT_ICON = "//a[@dusk='edit-resource-button']";
	public static final String UPLOAD_FILE = "//input[@type='file']"; 
	public static final String SUCCESS_MESSAGE = "//div[@class='toasted nova success']";
	public static final String MULTI_DROPDOWN = "//div[@class='multiselect']";
}
