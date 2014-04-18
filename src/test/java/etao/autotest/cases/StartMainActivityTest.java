/**
 * StartMainActivity.java etao.autotest.cases etao.test 
 * ����11:27:13
 */
package etao.autotest.cases;

/**
 * @author �����¹⽣ 2014��4��11�� ����11:27:13
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.taobao.sword.android.elements.IAndroidActivity;
import com.taobao.sword.android.manager.AndroidRemoteDriver;
import com.taobao.sword.android.manager.IAndroidDriver;
import com.taobao.sword.android.object.By;

import etao.autotest.util.Const;
import etao.autotest.util.Utils;

import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class StartMainActivityTest {
    private IAndroidDriver driver;
    private String deviceId;
    private int imgCount = 0;

    @BeforeClass
    public static void init() {
    }

    /**
     * @param driver
     * @param deviceId
     */
    public StartMainActivityTest(String deviceId) {
	this.deviceId = deviceId;
    }

    @Parameterized.Parameters
    public static Collection<String[]> deviceIds() {
	List<String> deviceList = Utils.getDeviceList();
	List<String[]> col = new ArrayList<String[]>();
	for (String e : deviceList) {
	    String[] el = new String[] { e };
	    col.add(el);
	}
	return col; // �ַ������� [[Ljava.lang.String;@b4e29b,
	// [Ljava.lang.String;@18941f7, null, null, null]
    }

    @Test
    public void testStartMainActivity() throws InterruptedException {
	driver = AndroidRemoteDriver.start(Const.ETAO.packageTest, deviceId);

	// ��MainActivity
	IAndroidActivity activity = driver
		.startActivity(Const.ETAO.mainActivity);
	Utils.record(driver, this.deviceId, imgCount++);

    }

    @AfterClass
    public static void stopDriver() {
	AndroidRemoteDriver.stop();
    }

}
