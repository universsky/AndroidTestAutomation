/**
 * Const.java etao.autotest.util etao.test 
 * 下午10:20:54
 */
package etao.autotest.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 东海陈光剑 2014年4月10日 下午10:20:54
 */
public class Const {
    public interface ETAO {
	String ApkPath = "D:\\android_test_automation\\etao_android\\bin\\etao_android.apk";
	String testApkPath = "D:\\android_test_automation\\etao_android\\bin\\etao_android.sword.test.apk";

	String mainActivity = "com.etao.mobile.start.Welcome";
	String packageEtao = "com.taobao.etao";
	String packageTest = "com.taobao.etao.sword.test";
    }

    public interface TAOBAO {
	String signedApkPath = "D:/ae/taobao_android/taobao_android.sword.signed.apk";
	String testApkPath = "D:/ae/taobao_android/taobao_android.sword.test.apk";

	String packageSigned = "com.taobao.taobao";
	String packageTest = "com.taobao.taobao.sword.test";

	String apkFilePath = "D:/ae/taobao_android/taobao_android.apk";
	String mainActivity = "com.taobao.tao.homepage.MainActivity3";
    }

    public interface ALIPAY {

	String mainActivity = "com.alipay.android.launcher.TabLauncher";
	String apkFilePath = "D:/ae/apks/alipay_wap_main.apk";
	String packageSigned = "com.eg.android.AlipayGphone";
	String packageTest = "com.eg.android.AlipayGphone.sword.test";
	String signedApkPath = "D:/ae/apks/alipay_wap_main.sword.signed.apk";
	String testApkPath = "D:/ae/apks/alipay_wap_main.sword.test.apk";

    }

    // public static final String reportPath =
    // "http://127.0.0.1:88/report.html?run_stamp=";
    public static final String reportPath = "http://10.125.1.58:88/report.html?run_stamp=";

    /**
     * 数据库配置 "jdbc:mysql://10.125.1.58:3306/test", "root", "isword"
     */
    public static final String mysqlUrl = "jdbc:mysql://10.125.1.58:3306/test";
    public static final String mysqlUser = "root";
    public static final String mysqlPassword = "isword";
    /**
     * UploadServlet
     */
    public static final String uploadServerUrl = "http://10.125.1.58:8888/UploadServlet/upload";

    /**
     * 截图保存的路径
     */
    public static String timestamp = (new SimpleDateFormat("yyyyMMddhhmmss"))
	    .format(new Date());

    // public static String photoSavePath = new File("src/test/resources/"
    // + timestamp + "/").getAbsolutePath();

    public static String photoSavePath = "D:/Apache2.2/htdocs/resources/"
	    + timestamp + "/";
    public static String photoSrcPath = "http://127.0.0.1:88/resources/"
	    + timestamp + "/";
}
