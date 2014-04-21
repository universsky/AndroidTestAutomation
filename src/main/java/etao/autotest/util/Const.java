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
	String[] Activities = {
		"com.etao.mobile.start.Welcome",
		"com.etao.mobile.search.SearchHomeActivity",
		// "com.etao.mobile.search.SearchFilterActivity",
		// "com.etao.mobile.search.SearchTipsActivity",
		"com.etao.mobile.jiukuaijiu.JiukuaijiuActivity",
		"com.etao.mobile.zhidemai.ZhiDeMaiListActivity",
		// "com.etao.mobile.login.LoginActivity",
		// "com.etao.mobile.collection.CollectedAuctionsActivity",
		"com.etao.mobile.favorite.MyFavoriteActivity",
		"com.etao.mobile.wanke.WankeShaidanListActivity",
		"com.etao.mobile.wanke.WankeShaidanPublishActivity",
		"com.etao.mobile.wanke.WankeCommentEditActivity",

		// "com.etao.mobile.msgcenter.MsgCenterActivity",
		// "com.etao.mobile.msgcenter.MsgDetailListActivity",
		// "com.etao.mobile.msgcenter.MsgOrderActivity",
		// "com.etao.mobile.msgcenter.MsgSettingActivity",
		"com.etao.mobile.jfb.JFBActivity",
		"com.etao.mobile.jfbtaskcenter.TaskCenterActivity",
		"com.etao.mobile.wode.AboutActivity",
		"com.etao.mobile.wode.FeedbackActivity",
		"com.etao.mobile.wode.NewMyInfoActivity",
		"com.etao.mobile.wode.MySettingActivity"
	// "com.etao.mobile.saomiao.HuoyanActivity"
	};
	String MainActivity = "com.etao.mobile.start.Welcome";
	String SearchHomeActivity = "com.etao.mobile.search.SearchHomeActivity";
	String SearchFilterActivity = "com.etao.mobile.search.SearchFilterActivity";
	String SearchTipsActivity = "com.etao.mobile.search.SearchTipsActivity";
	String JiukuaijiuActivity = "com.etao.mobile.jiukuaijiu.JiukuaijiuActivity";
	String ZhiDeMaiListActivity = "com.etao.mobile.zhidemai.ZhiDeMaiListActivity";
	String LoginActivity = "com.etao.mobile.login.LoginActivity";
	String CollectedAuctionsActivity = "com.etao.mobile.collection.CollectedAuctionsActivity";
	String MyFavoriteActivity = "com.etao.mobile.favorite.MyFavoriteActivity";
	String WankeShaidanListActivity = "com.etao.mobile.wanke.WankeShaidanListActivity";
	String WankeShaidanPublishActivity = "com.etao.mobile.wanke.WankeShaidanPublishActivity";
	String WankeCommentEditActivity = "com.etao.mobile.wanke.WankeCommentEditActivity";
	String HuoyanActivity = "com.etao.mobile.saomiao.HuoyanActivity";

	String MsgCenterActivity = "com.etao.mobile.msgcenter.MsgCenterActivity";
	String MsgDetailListActivity = "com.etao.mobile.msgcenter.MsgDetailListActivity";
	String MsgOrderActivity = "com.etao.mobile.msgcenter.MsgOrderActivity";
	String MsgSettingActivity = "com.etao.mobile.msgcenter.MsgSettingActivity";

	String JFBActivity = "com.etao.mobile.jfb.JFBActivity";
	String TaskCenterActivity = "com.etao.mobile.jfbtaskcenter.TaskCenterActivity";

	String AboutActivity = "com.etao.mobile.wode.AboutActivity";
	String FeedbackActivity = "com.etao.mobile.wode.FeedbackActivity";
	String NewMyInfoActivity = "com.etao.mobile.wode.NewMyInfoActivity";
	String MySettingActivity = "com.etao.mobile.wode.MySettingActivity";
	// com.etao.mobile.search.SearchFilterActivity
	// com.etao.mobile.search.SearchTipsActivity
	// com.etao.mobile.jiukuaijiu.JiukuaijiuActivity
	// com.etao.mobile.zhidemai.ZhiDeMaiListActivity
	// com.etao.mobile.login.LoginActivity
	// com.etao.mobile.collection.CollectedAuctionsActivity
	// com.etao.mobile.favorite.MyFavoriteActivity
	// com.etao.mobile.wanke.WankeShaidanListActivity
	// com.etao.mobile.wanke.WankeShaidanPublishActivity
	// com.etao.mobile.wanke.WankeCommentEditActivity
	// com.etao.mobile.saomiao.HuoyanActivity
	// com.etao.mobile.msgcenter.MsgCenterActivity
	// com.etao.mobile.msgcenter.MsgDetailListActivity
	// com.etao.mobile.msgcenter.MsgOrderActivity
	// com.etao.mobile.msgcenter.MsgSettingActivity
	// com.etao.mobile.jfb.JFBActivity
	// com.etao.mobile.jfbtaskcenter.TaskCenterActivity
	// com.etao.mobile.wode.AboutActivity
	// com.etao.mobile.wode.FeedbackActivity
	// com.etao.mobile.wode.NewMyInfoActivity
	// com.etao.mobile.wode.MySettingActivity

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
     * H5自动化测试
     */
    public static final String androidServerApkPath = "D:\\android_test_automation\\etao.test\\src\\main\\resources\\android-server.apk";

    public static final String imgSavePath = "D:/Apache2.2/htdocs/img/";

    public static final String[] etaoWebUrlsPre = {
	    "http://wapa.etao.com?hidepop=true",
	    "http://wapa.etao.com/?hidepop=true#!/youhui/sign.php?type=only",
	    "http://wapa.etao.com/?hidepop=true#!/free99/index.php",
	    "http://wapa.etao.com/?hidepop=true#!/myetao/index.php",
	    "http://wapa.etao.com/?hidepop=true#!/myetao/myjfb.php",
	    "http://wapa.etao.com/search/index.php?spm=0.0.0.0",
	    "http://wapa.etao.com/search/cat.php?spm=0.0.0.0&name=%E5%86%85%E8%A1%A3",
	    "http://wapa.etao.com/search/search.php?spm=0.0.0.0&q=%E5%B0%8F%E6%9D%AF%E6%96%87%E8%83%B8&cat=52402010",
	    "http://wapa.etao.com/search/filter.php?spm=0.0.0.0&q=%E5%8C%85%E8%87%80%E8%A3%99&yhsrc=h5",
	    "http://h5.m.taobao.com/trip/flight/search/index.html?spm=0.0.0.0&ttid=703402@etao_h5_4.0",
	    "http://ju.taobao.com/m/jusp/zhengdian/mtp.htm?spm=0.0.0.0",
	    "http://h5.m.taobao.com/try/list.htm?spm=0.0.0.0" };

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
