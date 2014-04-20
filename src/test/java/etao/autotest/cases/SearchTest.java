/**
 * etao.test SearchTest.java 2014��4��19��
 */
package etao.autotest.cases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.TestCase;

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
 * @author �����¹⽣ 2014��4��19�� ����6:04:05
 */
@RunWith(Parameterized.class)
public class SearchTest extends TestCase {

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
    public SearchTest(String deviceId) {
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
    public void testSearch() throws InterruptedException {
	driver = AndroidRemoteDriver.start(Const.ETAO.packageTest, deviceId);

	// ��MainActivity
	driver.startActivity(Const.ETAO.mainActivity);
	Utils.record(driver, this.deviceId, imgCount++);
	// ��SearchHomeActivity

    }

    @AfterClass
    public static void stopDriver() {
	AndroidRemoteDriver.stop();
    }

}
