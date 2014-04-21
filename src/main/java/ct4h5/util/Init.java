package ct4h5.util;

import ct4h5.util.Util;
import etao.autotest.util.Const;

/**
 * @author �����¹⽣ 2014��2��22�� ����2:27:35
 */
public class Init {

    /**
     * @param deviceId
     * 
     */

    public static void init(String deviceId) {
	String cmdUninstall = "adb -s " + deviceId
		+ " uninstall org.openqa.selenium.android.app";
	String cmdInstall = "adb -s " + deviceId + " install -r "
		+ Const.androidServerApkPath;

	/**
	 * ����WebDriver
	 */
	int port = 8080;
	int pcPort = 8080;
	String cmdStart = "adb -s "
		+ deviceId
		+ " shell am start -a android.intent.action.MAIN -n org.openqa.selenium.android.app/.MainActivity -e debug true";
	String cmdForward = "adb -s " + deviceId + " forward tcp:" + pcPort
		+ " tcp:" + port;

	Util.excuCmd(cmdUninstall);
	Util.excuCmd(cmdInstall);
	Util.excuCmd(cmdStart);
	Util.excuCmd(cmdForward);

    }

    /**
     * 
     * @param deviceId
     * @param pcPort
     *            void init
     */
    public static void init(String deviceId, int pcPort) {
	String cmdUninstall = "adb -s " + deviceId
		+ " uninstall org.openqa.selenium.android.app";
	String cmdInstall = "adb -s " + deviceId + " install -r "
		+ Const.androidServerApkPath;

	/**
	 * ����WebDriver
	 */
	int port = 8080;
	String cmdStart = "adb -s "
		+ deviceId
		+ " shell am start -a android.intent.action.MAIN -n org.openqa.selenium.android.app/.MainActivity -e debug true";
	String cmdForward = "adb -s " + deviceId + " forward tcp:" + pcPort
		+ " tcp:" + port;

	Util.excuCmd(cmdUninstall);
	Util.excuCmd(cmdInstall);
	Util.excuCmd(cmdStart);
	Util.excuCmd(cmdForward);

    }

    /**
     * @param deviceId
     * 
     */
    public static void forward(String deviceId) {

	int port = 8080;
	String cmdStart = "adb -s "
		+ deviceId
		+ " shell am start -a android.intent.action.MAIN -n org.openqa.selenium.android.app/.MainActivity -e debug true";
	String cmdForward = "adb -s " + deviceId + " forward tcp:" + port
		+ " tcp:" + port;
	Util.excuCmd(cmdStart);
	Util.excuCmd(cmdForward);

    }

    public static void destroy(String deviceId) {
	String cmdUninstall = "adb -s " + deviceId
		+ " uninstall org.openqa.selenium.android.app";

	Util.excuCmd(cmdUninstall);

    }

}
