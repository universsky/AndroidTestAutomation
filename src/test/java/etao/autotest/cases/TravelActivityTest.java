/**
 * etao.test TravelActivityTest.java 2014年4月21日
 */
package etao.autotest.cases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.taobao.sword.android.elements.IAndroidActivity;
import com.taobao.sword.android.manager.AndroidRemoteDriver;
import com.taobao.sword.android.manager.IAndroidDriver;

import etao.autotest.util.Const;
import etao.autotest.util.Utils;

/**
 * @author 东海陈光剑 2014年4月21日 下午11:07:16
 */
@RunWith(Parameterized.class)
public class TravelActivityTest {
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
    public TravelActivityTest(String deviceId) {
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
	return col; // 字符串数组 [[Ljava.lang.String;@b4e29b,
	// [Ljava.lang.String;@18941f7, null, null, null]
    }

    @Test
    public void testStartMainActivity() throws InterruptedException {
	driver = AndroidRemoteDriver.start(Const.ETAO.packageTest, deviceId);
	// 遍历Activity
	for (String activity : Const.ETAO.Activities) {
	    driver.startActivity(activity);
	    Utils.record(driver, this.deviceId, imgCount++);
	    driver.finishAllActivity();
	}

    }

    @AfterClass
    public static void stopDriver() {
	AndroidRemoteDriver.stop();
    }
}
