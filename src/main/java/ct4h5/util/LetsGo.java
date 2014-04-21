package ct4h5.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.concurrent.locks.Lock;

import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

import etao.autotest.util.Const;

/**
 * @author �����¹⽣ 2014��2��19�� ����10:29:27
 */
public class LetsGo implements Runnable {
    public static void main(String[] args) {
	List<String> deviceList = Util.getDeviceList();
	for (String e : deviceList) {
	    (new Thread(new LetsGo(e))).start();
	}
    }

    public LetsGo(String deviceId) {
	this.deviceId = deviceId;
    }

    /**
     * @param deviceId
     * @param lock
     */
    public LetsGo(String deviceId, Lock lock) {
	super();
	this.deviceId = deviceId;
	this.lock = lock;
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {

	long startTime = System.currentTimeMillis();

	try {

	    install(this.deviceId, Const.androidServerApkPath);

	} catch (Exception e) {
	}
	long endTime = System.currentTimeMillis();
	long runTime = endTime - startTime;

	System.out.println(Thread.currentThread() + " | ж�ذ�װ | "
		+ this.deviceId + " | Running time : " + runTime / 1000
		+ " seconds (" + runTime / 1000 / 60 + " minutes.)");
    }

    /**
     * ��װandroid-server.apk
     * 
     * @param devName
     * @param apkAddress
     */
    private static void install(String devName, String apkAddress) {
	Util.print(devName + " Installing " + apkAddress + "...");
	String cmd = "adb -s " + devName + " install -r " + apkAddress;
	Util.excuCmd(cmd);
    }

    /**
     * @param packageName
     */
    private static void uninstall(String deviceName, String packageName) {
	// TODO Auto-generated method stub
	Util.print(deviceName + " Uninstalling " + packageName + "...");
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
		Util.print(buff);
	    }
	    br.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    String deviceId;
    Lock lock;

}
