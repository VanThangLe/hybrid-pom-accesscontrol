package pageUIs.accesscontrol;

public class BasePageUI {
	public static final String BUTTON_BY_ID_NAME = "//span[text()='%s')]";//input[@id='%s']";
	public static final String TEXTBOX_BY_ID_NAME = "//input[@id='%s']";
	public static final String DROPDOWN_BY_ID = "//select[@id='%s']";
	public static final String CHECKBOX_BY_LABEL = "//label[text()='%s']/following-sibling::input";
	public static final String RADIO_BY_LABEL = "//label[text()='%s']/preceding-sibling::input";
	public static final String TABLE_HEADER_BY_ID_AND_NAME = "//table[@id='%s']//th[string()='%s']/preceding-sibling::th";
	public static final String TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX = "//table[@id='%s']/tbody/tr[%s]/td[%s]";
	public static final String MENU_BY_PAGE_NAME = "//div[@class='sidebar-menu pb-24 space-y-6']//div[@class='sidebar-section']"
			+ "//div[@class='mt-1 space-y-1']//div[@class='sidebar-item']//a[@class='sidebar-item-title relative']"
					+ "//span[@class='sidebar-item-label flex items-center'][contains(text(),'%s')]";
	public static final String SUCCESS_MESSAGE_VALUE = "//div[@class='inner']/div[contains(text(), '%s')]";
	public static final String ANY_FIELD_BY_ID = "//*[@id='%s']";
	
	public static final String LOGIN_BUTTON = "//button[@type='submit']";
	public static final String LOGOUT_LINK = "//div[@class='flex items-center hidden md:flex ml-2']";
	public static final String UPLOAD_FILE = "//input[@type='file']";
	
}
