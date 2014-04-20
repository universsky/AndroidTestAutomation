/**
 * GenerateApks.java etao.test etao.test 
 * 下午10:00:11
 */
package etao.autotest.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.taobao.sword.android.apk.utils.ApkUtils;
import com.taobao.sword.android.elements.IAndroidActivity;
import com.taobao.sword.android.elements.ICaptureScreen;
import com.taobao.sword.android.manager.IAndroidDriver;

import etao.autotest.report.FileUpload;

/**
 * @author 东海陈光剑 2014年4月10日 下午10:00:11
 */
public class Utils {

    public Utils() {
    }

    /**
     * generateApks
     * 
     * @param apkFilePath
     */
    public static void generateApks(String apkFilePath) {
	// boolean IsResignApk = true; // 是否重新签名被测Apk
	// boolean IsCreateTestApk = true;// 是否生成测试Apk

	String keystorePath = "D:\\android_test_automation\\etao.test\\src\\main\\resources\\debug.keystore";
	String keystorePassword = "android";
	String keystoreAlias = "androiddebugkey";

	try {

	    /**
	     * 为了保证一淘客户端的安全黑匣子检查通过, sword签名采用跟一淘客户端的一致的签名
	     */
	    ApkUtils.createSwordTestApk(apkFilePath, keystorePath,
		    keystorePassword, keystoreAlias);

	    // 不用sword的签名证书keystore
	    // ApkUtils.createSwordTestApk(apkFilePath, IsResignApk,
	    // IsCreateTestApk);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * public static void createSwordTestApk(String apkFilePath, boolean
     * resignApk, boolean createTestApk) throws Exception {
     * createSwordTestApk(new File(apkFilePath), resignApk, createTestApk); }
     * 
     * public static void createSwordTestApk(String apkFilePath, String
     * keystorePath, String keystorePassword, String keystoreAlias) throws
     * Exception { System.out.println("正在生成Sword的测试包……"); String
     * testApkPackageName = createTestApk(new File(apkFilePath), new
     * File(keystorePath), keystorePassword, keystoreAlias);
     * System.out.println("生成Sword的测试包成功，package为" + testApkPackageName); }
     */
    public static void installApkToMobile(String devName, String apkAddress) {
	Utils.print(devName + " Installing " + apkAddress + "...");
	Process process = null;
	String buff = "";
	try {

	    /**
	     * adb -s HC34WW907981 install
	     * D:/Android/test/taobao_android.sword.signed.apk
	     */
	    process = Runtime.getRuntime().exec(
		    "adb -s " + devName + " install " + apkAddress);
	    BufferedReader br = new BufferedReader(new InputStreamReader(
		    process.getInputStream(), "utf-8"));
	    while ((buff = br.readLine()) != null) {
		Utils.print(buff);
		if (buff.contains("INSTALL_FAILED_ALREADY_EXISTS")) {
		    Utils.print(devName + "已安装");
		}
	    }
	    br.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * @param packageName
     */
    public static void uninstall(String deviceName, String packageName) {
	Utils.print(deviceName + " Uninstalling " + packageName + "...");
	Process process = null;
	String buff = "";
	try {
	    /**
	     * adb -s HC34WW907981 uninstall com.taobao.taobao.sword.test
	     */
	    process = Runtime.getRuntime().exec(
		    "adb -s " + deviceName + " uninstall " + packageName);
	    BufferedReader br = new BufferedReader(new InputStreamReader(
		    process.getInputStream(), "utf-8"));
	    while ((buff = br.readLine()) != null) {
		Utils.print(buff);
	    }
	    br.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static List<String> getDeviceList() {
	Process process = null;
	String strBuff = "";
	String t = "";
	String command = "adb devices";

	try {
	    process = Runtime.getRuntime().exec(command);
	    BufferedReader br = new BufferedReader(new InputStreamReader(
		    process.getInputStream(), "utf-8"));
	    while ((t = br.readLine()) != null) {
		strBuff += t + "\n";
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	/*
	 * List of devices attached
	 * \nCoolpad5890-a1a1314a\tdevice\nHC34WW907981\tdevice\n\n
	 */
	print(strBuff);

	List<String> deviceList = new ArrayList<String>(5);
	String regex = "[\\n](.*)[\\t]";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(strBuff);
	while (matcher.find()) {
	    String deviceId = strBuff.substring(matcher.start(), matcher.end());
	    deviceId = deviceId.replaceAll("\\s*", "");
	    deviceList.add(deviceId);
	}
	return deviceList;
    }

    public static void print(String buff) {
	System.out.println(buff);
    }

    /*********************
     * 根据包名来获取此包下所有的类名及其实例
     * 
     * @param packagePath
     * @return
     **********************/
    public static List<String> getClassInPackage(String packagePath) {
	List<String> classList = new ArrayList(10);
	String packageName = packagePath;
	String packageDirName = packageName.replace(".", "/");
	Enumeration<URL> dirs = null;
	try {
	    dirs = Thread.currentThread().getContextClassLoader()
		    .getResources(packageDirName);
	    // 迭代此 Enumeration
	    while (dirs.hasMoreElements()) {
		URL url = dirs.nextElement();
		File file = new File(url.getFile());
		// 把此目录下的所有文件列出
		String[] classes = file.list();
		// 循环此数组，并把.class去掉 ".class".length() = 6
		for (String className : classes) {
		    className = className.substring(0, className.length() - 6);
		    classList.add(className);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return classList;
    }

    /**
     * No enclosing instance of type E is accessible. Must qualify the
     * allocation with an enclosing instance of type E(e.g. x.new A() where x is
     * an instance of E). E指代我写的那个内部类。
     * 根据提示，没有可访问的内部类E的实例，必须分配一个合适的内部类E的实例（如x.new
     * A()，x必须是E的实例。）看着这句提示，我就纳闷了，我已经用new实例化了这个类，为什么还不行呢。
     * 
     * 于是百度谷歌了一下相关资料。原来我写的内部类是动态的，也就是开头以public class开头。而主程序是public static class
     * main。在Java中，类中的静态方法不能直接调用动态方法。只有将某个内部类修饰为静态类，然后才能够在静态类中调用该类的成员变量与成员方法。
     * 所以在不做其他变动的情况下，最简单的解决办法是将public class改为public static class.
     * 
     * 在这里记下这个问题，也方面遇到同样问题的朋友查阅。
     * 
     * @author 东海陈光剑 2014年4月11日 上午11:50:12
     */
    public class MultiInstall implements Runnable {
	String devName, apkAddress;

	/**
	 * 
	 * @param devName
	 * @param apkAddress
	 */
	public MultiInstall(String devName, String apkAddress) {
	    super();
	    this.devName = devName;
	    this.apkAddress = apkAddress;
	}

	public void run() {
	    Utils.print(devName + " Installing " + apkAddress + "...");
	    Process process = null;
	    String buff = "";
	    try {

		/**
		 * adb -s HC34WW907981 install
		 * D:/Android/test/taobao_android.sword.signed.apk
		 */
		process = Runtime.getRuntime().exec(
			"adb -s " + devName + " install " + apkAddress);
		BufferedReader br = new BufferedReader(new InputStreamReader(
			process.getInputStream(), "utf-8"));
		while ((buff = br.readLine()) != null) {
		    Utils.print(buff);
		    if (buff.contains("INSTALL_FAILED_ALREADY_EXISTS")) {
			Utils.print(devName + "已安装");
		    }
		}
		br.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

    }

    public class MultiUninstall implements Runnable {
	String deviceName, packageName;

	/**
	 * 
	 * @param deviceName
	 * @param packageName
	 */
	public MultiUninstall(String deviceName, String packageName) {
	    super();
	    this.deviceName = deviceName;
	    this.packageName = packageName;
	}

	public void run() {
	    Utils.print(deviceName + " Uninstalling " + packageName + "...");
	    Process process = null;
	    String buff = "";
	    try {
		/**
		 * adb -s HC34WW907981 uninstall com.taobao.taobao.sword.test
		 */
		process = Runtime.getRuntime().exec(
			"adb -s " + deviceName + " uninstall " + packageName);
		BufferedReader br = new BufferedReader(new InputStreamReader(
			process.getInputStream(), "utf-8"));
		while ((buff = br.readLine()) != null) {
		    Utils.print(buff);
		}
		br.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * 截图,记录操作过程
     * 
     * @param activity
     * @throws InterruptedException
     * 
     */
    public static void record(IAndroidDriver driver, String deviceId,
	    int imgCount) throws InterruptedException {

	Thread.sleep(1000);
	String imgTimeChamp = (new SimpleDateFormat("yyyyMMddhhmmss"))
		.format(new Date());
	IAndroidActivity activity = driver.getCurrentActivity();
	System.out.println(activity.getActivityName() + " | Device ID: "
		+ deviceId + "$" + imgCount);
	// System.out.println(activity.captureScreen());
	System.out.println("截图：" + deviceId + activity.getActivityName() + "$"
		+ imgCount);
	// 图片保存
	Utils.GenerateImage(activity, deviceId, imgCount, imgTimeChamp);
	// 入库
	String timestamp = Const.timestamp;
	String activityName = activity.getActivityName();
	String imgName = activity.getActivityName() + "$" + imgCount + ".jpeg";

	Utils.insert(timestamp, activityName, imgName, deviceId, imgTimeChamp);

    }

    /**
     * 测试记录信息入库
     * 
     * @param timestamp
     * @param activityName
     * @param imgName
     * @param deviceId
     * @param imgTimeChamp
     * @return
     */
    public static boolean insert(String timestamp, String activityName,
	    String imgName, String deviceId, String imgTimeChamp) {
	try {
	    Class.forName("com.mysql.jdbc.Driver");

	    System.out
		    .println("Success loading Mysql Driver  com.mysql.jdbc.Driver  !");
	} catch (Exception e) {
	    System.out
		    .print("Error loading Mysql Driver  com.mysql.jdbc.Driver  !");
	    e.printStackTrace();
	}
	try {
	    Connection connect = DriverManager.getConnection(
		    "jdbc:mysql://127.0.0.1:3306/test", "root", "isword");

	    System.out.println("Success connect Mysql server!");
	    Statement stmt = connect.createStatement();
	    String insertCmd = "INSERT INTO ct_pic(run_stamp,device_id,img_name,url,img_timestamp)"
		    + " VALUES ("
		    + "'"
		    + timestamp
		    + "',"
		    + "'"
		    + deviceId
		    + "',"
		    + "'"
		    + imgName
		    + "',"
		    + "'"
		    + activityName
		    + "','"
		    + imgTimeChamp + "')";
	    System.out.println(insertCmd);
	    boolean rs = stmt.execute(insertCmd);
	    return rs;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    /**
     * 将图片转换成Base64
     * 
     * @param imgFilePath
     * @return
     */
    public static String GetImageStr(String imgFilePath) {
	// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	byte[] data = null;
	// 读取图片字节数组
	try {
	    InputStream in = new FileInputStream(imgFilePath);
	    data = new byte[in.available()];
	    in.read(data);
	    in.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	// 对字节数组Base64编码
	sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    /**
     * 将Base64转换成图片
     * 
     * @param activity
     * @param deviceId
     * @return
     */
    public static boolean GenerateImage(IAndroidActivity activity,
	    String deviceId, int imgCount, String imgTimeStamp) {// 对字节数组字符串进行Base64解码并生成图片
	String imgStr = activity.captureScreen();
	// String imgPath = Const.photoSavePath + "/" + deviceId;
	String imgPath = "D:/Apache2.2/htdocs/img/";
	// 20140420070633$4d004077b4369049$20140420070650$com.etao.mobile.feedstream.FeedStreamActivity$5.jpeg
	String imgName = Const.timestamp + "$" + deviceId + "$" + imgTimeStamp
		+ "$" + activity.getActivityName() + "$" + imgCount + ".jpeg";
	if (imgStr == null)
	    // 图像数据为空
	    return false;
	sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	try {
	    // Base64解码
	    byte[] bytes = decoder.decodeBuffer(imgStr);
	    for (int i = 0; i < bytes.length; ++i) {
		if (bytes[i] < 0) {// 调整异常数据
		    bytes[i] += 256;
		}
	    }
	    // 生成jpeg图片
	    File f = new File(imgPath);
	    // 新建目录
	    if (!f.isDirectory())
		f.mkdirs();
	    if (!imgPath.endsWith("/"))
		imgPath += "/";
	    String imgFullPathName = imgPath + imgName;
	    // 把图片输出,保存到本地目录(后面做成,直接上传到图片服务器的模式)
	    OutputStream out = new FileOutputStream(imgFullPathName);
	    out.write(bytes);
	    out.flush();
	    out.close();

	    /**
	     * 上传图片到Tomcat服务器
	     */
	    FileUpload up = new FileUpload();
	    System.out.println(up.send(
		    "http://127.0.0.1:8888/UploadServlet/upload",
		    imgFullPathName));
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return false;
    }
}
