/**
 * 
 */
package ct4h5.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.remote.ScreenshotException;

import sun.misc.BASE64Decoder;

import com.taobao.sword.android.elements.IAndroidActivity;

import etao.autotest.report.FileUpload;
import etao.autotest.util.Const;
import etao.autotest.util.Utils;

;

/**
 * @author �����¹⽣ 2014��2��22�� ����12:16:10
 */
public class Util {

    public static List<String> getDeviceList() {
	// TODO Auto-generated method stub
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
	    // TODO Auto-generated catch block
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
	// TODO Auto-generated method stub
	System.out.println(buff);
    }

    /***********
     * ���ݰ�������ȡ�˰������е���������ʵ��
     * 
     * @param packagePath
     * @return
     */
    public static List<String> getClassInPackage(String packagePath) {
	List<String> classList = new ArrayList(5);
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

    public static void record(AndroidDriver driver, String deviceId)
	    throws InterruptedException {
	/**
	 * ���ǵ�ҳ����ص�ʱ�䣬�߳���ʱ2s
	 */

	printTitle(driver, deviceId);
	sleep();
	screenShot(driver, deviceId);
    }

    public static void sleep() throws InterruptedException {
	Thread.sleep(2000);
    }

    /**
     * @param driver
     * @param deviceId
     */
    public static void printTitle(AndroidDriver driver, String deviceId) {
	System.out.println(deviceId + " | " + driver.getTitle());
	System.out.println(driver.getCurrentUrl());
    }

    public static void printTitle(AndroidDriver driver) {
	System.out.println(driver.getTitle());
	System.out.println(driver.getCurrentUrl());
    }

    /**
     * @param driver
     * @param xPath
     * @throws InterruptedException
     */
    public static void click(AndroidDriver driver, String xPath)
	    throws InterruptedException {

	driver.findElement(By.xpath(xPath)).click();
	// driver.wait();// ����java.lang.IllegalMonitorStateException
	sleep();
    }

    /**
     * ��ͼ
     * 
     * @param driver
     * @param imgName
     */
    public static void takeScreenShot(WebDriver driver, String imgName) {
	File scrFile = ((TakesScreenshot) driver)
		.getScreenshotAs(OutputType.FILE);
	try {
	    FileUtils.copyFile(scrFile, new File(imgName));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * �����쳣��ͼ
     * 
     * @param e
     * @return
     */
    public String extractScreenShot(WebDriverException e) {
	Throwable cause = e.getCause();
	if (cause instanceof ScreenshotException) {
	    return ((ScreenshotException) cause).getBase64EncodedScreenshot();
	}
	return null;
    }

    /**
     * ��Base64ת����ͼƬ
     * 
     * @param activity
     * @param url
     * @return
     */

    /**
     * >adb -s <deviceID> shell /system/bin/screencap -p /sdcard/screenshot.png
     * >adb -s <deviceID> pull /sdcard/screenshot.png d:/screenshot.png 2990
     * KB/s (382785 bytes in 0.125s)
     * 
     * @param driver
     * @param deviceId
     * @return
     */
    public static boolean GenerateImage(AndroidDriver driver, String deviceId) {// ���ֽ������ַ�������Base64���벢����ͼƬ
	String url = driver.getCurrentUrl();
	System.out.println("AndroidDriver ��ͼ | " + url);
	String imgStr = driver.getScreenshotAs(OutputType.BASE64);
	/**
	 * ����ǵü���"/"Ŀ¼��ʾ��
	 */
	File fp = new File(Const.photoSavePath);
	if (!fp.isDirectory())
	    fp.mkdirs();

	String imgPath = Const.photoSavePath + "/" + deviceId;
	File f = new File(imgPath);
	if (!f.isDirectory())
	    f.mkdirs();
	if (!imgPath.endsWith("/"))
	    imgPath += "/";

	String timeChamp = (new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss"))
		.format(new Date());
	/**
	 * url(ȥ���Ƿ��ַ�)+ʱ��� ����ͼƬ
	 */
	String imgName = url.replaceAll("[\\/:*?\"<>|!#]", "") + "_"
		+ timeChamp + "_driver.jpeg";
	if (imgStr == null)
	    // ͼ������Ϊ��
	    return false;
	BASE64Decoder decoder = new BASE64Decoder();
	try {
	    // Base64����
	    byte[] bytes = decoder.decodeBuffer(imgStr);
	    for (int i = 0; i < bytes.length; ++i) {
		if (bytes[i] < 0) {// �����쳣����
		    bytes[i] += 256;
		}
	    }
	    // ����jpegͼƬ
	    f = new File(imgPath);
	    if (!f.isDirectory())
		f.mkdirs();
	    if (!imgPath.endsWith("/"))
		imgPath += "/";
	    String imgFullPathName = imgPath + imgName;

	    OutputStream out = new FileOutputStream(imgFullPathName);
	    out.write(bytes);
	    out.flush();
	    out.close();
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    // @SuppressWarnings("deprecation")
    // public static boolean GenerateImage(AndroidDriver driver) {//
    // ���ֽ������ַ�������Base64���벢����ͼƬ
    //
    // String url = driver.getCurrentUrl();
    // System.out.println("��ͼ | " + url);
    // String imgStr = driver.getScreenshotAs(OutputType.BASE64);
    // String imgPath = Const.photoSavePath + "/";
    // String timeChamp = (new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss"))
    // .format(new Date());
    // /**
    // * url(ȥ���Ƿ��ַ�)+ʱ��� ����ͼƬ
    // */
    // String imgName = url.replaceAll("[\\/:*?\"<>|]", "") + "-" + timeChamp
    // + ".jpeg";
    // if (imgStr == null)
    // // ͼ������Ϊ��
    // return false;
    // BASE64Decoder decoder = new BASE64Decoder();
    // try {
    // // Base64����
    // byte[] bytes = decoder.decodeBuffer(imgStr);
    // for (int i = 0; i < bytes.length; ++i) {
    // if (bytes[i] < 0) {// �����쳣����
    // bytes[i] += 256;
    // }
    // }
    // // ����jpegͼƬ
    // File f = new File(imgPath);
    // if (!f.isDirectory())
    // f.mkdir();
    // if (!imgPath.endsWith("/"))
    // imgPath += "/";
    // String imgFullPathName = imgPath + imgName;
    // OutputStream out = new FileOutputStream(imgFullPathName);
    // out.write(bytes);
    // out.flush();
    // out.close();
    // return true;
    // } catch (Exception e) {
    // return false;
    // }
    // }

    @SuppressWarnings({ "restriction", "unused" })
    public static boolean screenShot(AndroidDriver driver, String deviceId) {// ���ֽ������ַ�������Base64���벢����ͼƬ

	/**
	 * ����ǵü���"/"Ŀ¼��ʾ��
	 */
	File fp = new File(Const.photoSavePath);
	if (!fp.isDirectory())
	    fp.mkdirs();

	String url = driver.getCurrentUrl();
	System.out.println("adb screenShot ��ͼ | " + url);
	// String imgStr = driver.getScreenshotAs(OutputType.BASE64);
	// String imgPath = Const.photoSavePath + "/" + deviceId;
	String imgPath = Const.imgSavePath;
	File f = new File(imgPath);
	if (!f.isDirectory())
	    f.mkdirs();
	if (!imgPath.endsWith("/"))
	    imgPath += "/";

	String timeChamp = (new SimpleDateFormat("yyyyMMddhhmmss"))
		.format(new Date());
	/**
	 * url(ȥ���Ƿ��ַ�)+ʱ��� ����ͼƬ
	 */
	String imgName = url.replaceAll("[\\/%:*?\"<>|!#]", "") + "_"
		+ timeChamp + ".jpeg";

	// String imgSrcPath = Const.photoSrcPath + deviceId + "/" + imgName;
	String imgSrcPath = Const.photoSavePath;
	Utils.insert(Const.timestamp, url, imgName, deviceId, timeChamp);

	/**
	 * >adb -s <deviceID> shell /system/bin/screencap -p
	 * /sdcard/screenshot.png >adb -s <deviceID> pull /sdcard/screenshot.png
	 * d:/screenshot.png
	 */

	// ͼƬ�����ڷ������ϵ�����
	String imgSaveName = Const.timestamp + "$" + deviceId + "$" + timeChamp
		+ "$" + imgName;

	String imgFullPathName = imgPath + imgSaveName;
	/**
	 * ����Driver��ͼ
	 */
	Util.takeScreenShot(driver, imgFullPathName);
	/**
	 * ����Android shell /system/bin/screencap��ͼ
	 */
	// String screencapCmd = "adb -s " + deviceId
	// + " shell /system/bin/screencap -p  /sdcard/screenshot.png";
	// String pullScreenShotCmd = "adb -s " + deviceId
	// + " pull /sdcard/screenshot.png " + imgFullPathName;
	// String rmScreenShotCmd = "rm -f /sdcard/screenshot.png";
	// excuCmd(screencapCmd);
	// excuCmd(pullScreenShotCmd);
	// excuCmd(rmScreenShotCmd);
	FileUpload fu = new FileUpload();
	try {
	    fu.send(Const.uploadServerUrl, imgFullPathName);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return true;
	// Process process = null;
	// try {
	// process = Runtime.getRuntime().exec(screencapCmd);
	// BufferedReader br = new BufferedReader(new InputStreamReader(
	// process.getInputStream(), "utf-8"));
	// String buff = "";
	// String imgStr = "";
	// while ((buff = br.readLine()) != null) {
	// Util.print(buff);
	// imgStr += buff;
	// }
	//
	// if (imgStr == null) // ͼ������Ϊ��
	// return false;
	// BASE64Decoder decoder = new BASE64Decoder();
	//
	// // Base64����
	// byte[] bytes = decoder.decodeBuffer(imgStr);
	// for (int i = 0; i < bytes.length; ++i) {
	// if (bytes[i] < 0) {// �����쳣����
	// bytes[i] += 256;
	// }
	// }
	// // ����jpegͼƬ
	// File f1 = new File(imgPath);
	// if (!f1.isDirectory())
	// f1.mkdir();
	// if (!imgPath.endsWith("/"))
	// imgPath += "/";
	// OutputStream out = new FileOutputStream(imgFullPathName);
	// out.write(bytes);
	// out.flush();
	// out.close();
	// return true;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return false;
	// }

    }

    public static void excuCmd(String cmd) {
	System.out.println("$" + cmd);
	Process process = null;
	String buff = "";
	try {

	    /**
	     * adb -s HC34WW907981 install
	     * D:/Android/test/taobao_android.sword.signed.apk
	     */
	    process = Runtime.getRuntime().exec(cmd);
	    BufferedReader br = new BufferedReader(new InputStreamReader(
		    process.getInputStream(), "utf-8"));
	    while ((buff = br.readLine()) != null) {
		Util.print(buff);
		// if (buff.contains("INSTALL_FAILED_ALREADY_EXISTS")) {
		// Util.print(devName + "�Ѱ�װ");
		// }
	    }
	    br.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * @param e
     */
    public static void install(String deviceId) {
	String cmdInstall = "adb -s " + deviceId + " install -r "
		+ Const.androidServerApkPath;
	Util.excuCmd(cmdInstall);

    }
}
