/**
 * LetsGo.java etao.autotest.util etao.test 
 * 上午10:20:14
 */
package etao.autotest.util;

import java.util.List;

import etao.autotest.util.Utils.MultiInstall;
import etao.autotest.util.Utils.MultiUninstall;

/**
 * @author 东海陈光剑 2014年4月11日 上午10:20:14
 */
public class LetsGo {
    /**
     * ETAO = 1 ; TAOBAO = 2; ALIPAY = 3
     */
    private final static int FLAG = 1; // 测试FLAG

    /**
     * @param args
     */

    public static void main(String[] args) {
	// genApks();
	LetsGo.letsGo();
    }

    public static void genApks() {
	if (FLAG == 1) {
	    Utils.generateApks(Const.ETAO.ApkPath);
	} else if (FLAG == 2) {
	    Utils.generateApks(Const.TAOBAO.apkFilePath);
	} else if (FLAG == 3) {
	    Utils.generateApks(Const.ALIPAY.apkFilePath);
	}

    }

    /**
     * 适配测试第一步：卸载安装
     */
    @SuppressWarnings("unused")
    public static void letsGo() {

	List<String> deviceList = Utils.getDeviceList();

	Utils.print("----------Device Uninstall Information----------- ");
	for (String e : deviceList) {
	    // 如果已经安装了旧的测试包，先卸载掉
	    if (FLAG == 1) {

		uninstall(e, Const.ETAO.packageEtao);
		uninstall(e, Const.ETAO.packageTest);

	    } else if (FLAG == 2) {

		uninstall(e, Const.TAOBAO.packageSigned);
		uninstall(e, Const.TAOBAO.packageTest);

	    } else if (FLAG == 3) {

		uninstall(e, Const.ALIPAY.packageSigned);
		uninstall(e, Const.ALIPAY.packageTest);

	    }

	}

	Utils.print("----------Device Install Information----------- ");
	for (String e : deviceList) {
	    // 安装本次测试包
	    if (FLAG == 1) {

		install(e, Const.ETAO.ApkPath);
		install(e, Const.ETAO.testApkPath);

	    } else if (FLAG == 2) {

		install(e, Const.TAOBAO.signedApkPath);
		install(e, Const.TAOBAO.testApkPath);

	    } else if (FLAG == 3) {

		install(e, Const.ALIPAY.signedApkPath);
		install(e, Const.ALIPAY.testApkPath);

	    }
	}

    }

    private static void install(String e, String apkpath) {
	Utils u = new Utils();
	MultiInstall t = u.new MultiInstall(e, apkpath);
	new Thread(t).start();

    }

    private static void uninstall(String e, String packageName) {
	Utils u = new Utils();
	MultiUninstall t = u.new MultiUninstall(e, packageName);
	new Thread(t).start();
    }

}
