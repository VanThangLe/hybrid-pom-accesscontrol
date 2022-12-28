package commons;

import java.io.File;

public class GlobalConstants {
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_FOLDER_PATH = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_FOLDER_PATH = PROJECT_PATH + File.separator + "downloadFiles";
	
	public static final String PROJECT_ADMIN_EMAIL = "admin_elife@elifetech.vn";
	public static final String PROJECT_ADMIN_PASSWORD = "123456789";
	
	public static final String SUPER_ADMIN_EMAIL = "admin@elifetech.vn";
	public static final String SUPER_ADMIN_PASSWORD = "123456789";
	
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;
}
