/**
 * StartMainActivity.java etao.autotest.cases etao.test 
 * 上午11:27:13
 */
package alipay.autotest.cases;

/**
 * @author 东海陈光剑 2014年4月11日 上午11:27:13
 */

import static org.junit.Assert.*;

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

import etao.autotest.util.Const;
import etao.autotest.util.Utils;

import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class StartMainActivity {
	private IAndroidDriver driver;
	private String deviceId;

	@BeforeClass
	public static void init() {
	}

	/**
	 * @param driver
	 * @param deviceId
	 */
	public StartMainActivity(String deviceId) {
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
	public void testSearch() {
		driver = AndroidRemoteDriver.start(Const.ALIPAY.packageTest, deviceId);
		String loginActivityName = "com.alipay.mobile.security.authcenter.ui.login.LoginActivity_";
		// 打开MainActivity
		driver.startActivity(Const.ALIPAY.mainActivity);
		IAndroidActivity activity = driver.startActivity(loginActivityName);

		/**
		 * com.alipay.android.launcher.TabLauncher 启动转到登陆
		 */

		activity.sendKey("18888888888");
		String name = driver.getCurrentActivity().getActivityName();
		assertTrue(loginActivityName.equals(name));

	}

	@AfterClass
	public static void stopDriver() {
		AndroidRemoteDriver.stop();
	}

}
