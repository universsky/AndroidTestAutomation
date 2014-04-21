/**
 * ct4h5 Install.java  ct4h5
 */
package ct4h5.util;

import ct4h5.util.Util;
import etao.autotest.util.Const;

/**
 * @author 东海陈光剑 2014年2月24日 下午4:37:47
 */
public class Install implements Runnable {

    private String deviceId;

    /**
     * @param deviceId
     */
    public Install(String deviceId) {
	super();
	this.deviceId = deviceId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
	String cmdInstall = "adb -s " + deviceId + " install -r "
		+ Const.androidServerApkPath;
	Util.excuCmd(cmdInstall);

    }

}
