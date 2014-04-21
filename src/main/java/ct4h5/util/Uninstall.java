/**
 * ct4h5 Install.java  ct4h5
 */
package ct4h5.util;

import ct4h5.util.Util;

/**
 * @author �����¹⽣ 2014��2��24�� ����4:37:47
 */
public class Uninstall implements Runnable {

    private String deviceId;

    /**
     * @param deviceId
     */
    public Uninstall(String deviceId) {
	super();
	this.deviceId = deviceId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
	String cmdUninstall = "adb -s " + deviceId
		+ " uninstall org.openqa.selenium.android.app";
	Util.excuCmd(cmdUninstall);

    }

}
