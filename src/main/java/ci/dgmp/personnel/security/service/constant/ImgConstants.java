package ci.dgmp.personnel.security.service.constant;

import java.util.Arrays;
import java.util.List;

public class ImgConstants {
    public static String userDir = System.getProperty("user.dir");
    public static String userHome = System.getProperty("user.home");
    public static String staticDirectory = userDir + "\\src\\main\\resources\\static";
    //public static String uploadDir = staticDirectory + "\\user_profile_img";
    public static String uploadDir = userHome + "\\sigrh\\user_profile_img";
    public static String defaultUserImg = userHome + "\\sigrh\\user_profile_img\\default_user.png";

    public static final List<String> DOCUMENT_AUTHORIZED_TYPE = Arrays.asList(".png", ".jpg", ".jpeg", ".pdf", ".doc", ".docx");
    public static final long UPLOAD_MAX_SIZE = 1000000;
}
