/**
 * GenerateApks.java etao.test etao.test 
 * ����10:00:11
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
 * @author �����¹⽣ 2014��4��10�� ����10:00:11
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
	// boolean IsResignApk = true; // �Ƿ�����ǩ������Apk
	// boolean IsCreateTestApk = true;// �Ƿ����ɲ���Apk

	String keystorePath = "D:\\android_test_automation\\etao.test\\src\\main\\resources\\debug.keystore";
	String keystorePassword = "android";
	String keystoreAlias = "androiddebugkey";

	try {

	    /**
	     * Ϊ�˱�֤һ�Կͻ��˵İ�ȫ��ϻ�Ӽ��ͨ��, swordǩ�����ø�һ�Կͻ��˵�һ�µ�ǩ��
	     */
	    ApkUtils.createSwordTestApk(apkFilePath, keystorePath,
		    keystorePassword, keystoreAlias);

	    // ����sword��ǩ��֤��keystore
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
     * Exception { System.out.println("��������Sword�Ĳ��԰�����"); String
     * testApkPackageName = createTestApk(new File(apkFilePath), new
     * File(keystorePath), keystorePassword, keystoreAlias);
     * System.out.println("����Sword�Ĳ��԰��ɹ���packageΪ" + testApkPackageName); }
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
		    Utils.print(devName + "�Ѱ�װ");
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
     * ���ݰ�������ȡ�˰������е���������ʵ��
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
	    // ������ Enumeration
	    while (dirs.hasMoreElements()) {
		URL url = dirs.nextElement();
		File file = new File(url.getFile());
		// �Ѵ�Ŀ¼�µ������ļ��г�
		String[] classes = file.list();
		// ѭ�������飬����.classȥ�� ".class".length() = 6
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
     * an instance of E). Eָ����д���Ǹ��ڲ��ࡣ
     * ������ʾ��û�пɷ��ʵ��ڲ���E��ʵ�����������һ�����ʵ��ڲ���E��ʵ������x.new
     * A()��x������E��ʵ���������������ʾ���Ҿ������ˣ����Ѿ���newʵ����������࣬Ϊʲô�������ء�
     * 
     * ���ǰٶȹȸ���һ��������ϡ�ԭ����д���ڲ����Ƕ�̬�ģ�Ҳ���ǿ�ͷ��public class��ͷ������������public static class
     * main����Java�У����еľ�̬��������ֱ�ӵ��ö�̬������ֻ�н�ĳ���ڲ�������Ϊ��̬�࣬Ȼ����ܹ��ھ�̬���е��ø���ĳ�Ա�������Ա������
     * �����ڲ��������䶯������£���򵥵Ľ���취�ǽ�public class��Ϊpublic static class.
     * 
     * ���������������⣬Ҳ��������ͬ����������Ѳ��ġ�
     * 
     * @author �����¹⽣ 2014��4��11�� ����11:50:12
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
			Utils.print(devName + "�Ѱ�װ");
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
     * ��ͼ,��¼��������
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
	System.out.println("��ͼ��" + deviceId + activity.getActivityName() + "$"
		+ imgCount);
	// ͼƬ����
	Utils.GenerateImage(activity, deviceId, imgCount, imgTimeChamp);
	// ���
	String timestamp = Const.timestamp;
	String activityName = activity.getActivityName();
	String imgName = activity.getActivityName() + "$" + imgCount + ".jpeg";

	Utils.insert(timestamp, activityName, imgName, deviceId, imgTimeChamp);

    }

    /**
     * ���Լ�¼��Ϣ���
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
     * ��ͼƬת����Base64
     * 
     * @param imgFilePath
     * @return
     */
    public static String GetImageStr(String imgFilePath) {
	// ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
	byte[] data = null;
	// ��ȡͼƬ�ֽ�����
	try {
	    InputStream in = new FileInputStream(imgFilePath);
	    data = new byte[in.available()];
	    in.read(data);
	    in.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	// ���ֽ�����Base64����
	sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	return encoder.encode(data);// ����Base64��������ֽ������ַ���
    }

    /**
     * ��Base64ת����ͼƬ
     * 
     * @param activity
     * @param deviceId
     * @return
     */
    public static boolean GenerateImage(IAndroidActivity activity,
	    String deviceId, int imgCount, String imgTimeStamp) {// ���ֽ������ַ�������Base64���벢����ͼƬ
	String imgStr = activity.captureScreen();
	// String imgPath = Const.photoSavePath + "/" + deviceId;
	String imgPath = "D:/Apache2.2/htdocs/img/";
	// 20140420070633$4d004077b4369049$20140420070650$com.etao.mobile.feedstream.FeedStreamActivity$5.jpeg
	String imgName = Const.timestamp + "$" + deviceId + "$" + imgTimeStamp
		+ "$" + activity.getActivityName() + "$" + imgCount + ".jpeg";
	if (imgStr == null)
	    // ͼ������Ϊ��
	    return false;
	sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	try {
	    // Base64����
	    byte[] bytes = decoder.decodeBuffer(imgStr);
	    for (int i = 0; i < bytes.length; ++i) {
		if (bytes[i] < 0) {// �����쳣����
		    bytes[i] += 256;
		}
	    }
	    // ����jpegͼƬ
	    File f = new File(imgPath);
	    // �½�Ŀ¼
	    if (!f.isDirectory())
		f.mkdirs();
	    if (!imgPath.endsWith("/"))
		imgPath += "/";
	    String imgFullPathName = imgPath + imgName;
	    // ��ͼƬ���,���浽����Ŀ¼(��������,ֱ���ϴ���ͼƬ��������ģʽ)
	    OutputStream out = new FileOutputStream(imgFullPathName);
	    out.write(bytes);
	    out.flush();
	    out.close();

	    /**
	     * �ϴ�ͼƬ��Tomcat������
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
