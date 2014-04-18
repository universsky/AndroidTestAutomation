package taobao.autotest.cases;

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
	public static Collection deviceIds() {
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
		driver = AndroidRemoteDriver.start(Const.TAOBAO.packageTest, deviceId);

		// 打开被测的Activity
		IAndroidActivity activity = driver
				.startActivity(Const.TAOBAO.mainActivity);

		// 搜索框输入衣服
		activity.editText(By.id("searchedit")).setText("衣服");
		// 点击搜索按钮
		activity.imageView(By.id("searchbtn")).click();
		// 等待搜索结果的Activity加载完成
		IAndroidActivity searchListActivity = driver.waitForActivity(
				"com.taobao.tao.SearchListActivity", 10000);
		// 获取搜索结果list的总行数
		int searchResultCount = searchListActivity.listView(
				By.id("searchgoodsList")).getLineCount();
		// 断言总行数是否大于1
		assert (searchResultCount > 1);

	}

	@AfterClass
	public static void stopDriver() {
		AndroidRemoteDriver.stop();
	}

}
