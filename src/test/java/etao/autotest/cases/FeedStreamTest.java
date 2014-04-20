/**
 * etao.test FeedStreamTest.java 2014年4月18日
 */
package etao.autotest.cases;

import java.awt.event.KeyEvent;
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
import com.taobao.sword.android.enums.ScrollSide;
import com.taobao.sword.android.manager.AndroidRemoteDriver;
import com.taobao.sword.android.manager.IAndroidDriver;
import com.taobao.sword.android.object.By;

import etao.autotest.util.Const;
import etao.autotest.util.Utils;

/**
 * @author 东海陈光剑 2014年4月18日 下午11:01:28
 */
@RunWith(Parameterized.class)
public class FeedStreamTest extends TestCase {
    private IAndroidDriver solo;
    private String deviceId;
    private int imgCount = 0;

    @BeforeClass
    public static void init() {
    }

    /**
     * @param solo
     * @param deviceId
     */
    public FeedStreamTest(String deviceId) {
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
    public void testFeedStream() throws InterruptedException {
	solo = AndroidRemoteDriver.start(Const.ETAO.packageTest, deviceId);
	// 打开MainActivity
	solo.startActivity(Const.ETAO.mainActivity);
	Utils.record(solo, this.deviceId, imgCount++);
	/**
	 * List Scroll
	 */
	// solo.getCurrentActivity().view(By.id("feed_stream_listview"))
	// .scroll(ScrollSide.UP);
	solo.getCurrentActivity().view(By.id("feed_stream_listview")).click();
	Utils.record(solo, this.deviceId, imgCount++);
	// solo.getCurrentActivity().view(By.id("feed_stream_item_pic")).click();
	// Utils.record(solo, this.deviceId, imgCount++);
	solo.getCurrentActivity().goBack();
	Utils.record(solo, this.deviceId, imgCount++);
	/**
	 * head 搜索框
	 */
	solo.getCurrentActivity().view(By.id("search_header_center_text"))
		.click();
	Utils.record(solo, this.deviceId, imgCount++);
	// solo.getCurrentActivity().sendKey("ipad air");
	solo.getCurrentActivity().editText(By.id("search_header_center_edit"))
		.setText("ipad air");
	Utils.record(solo, this.deviceId, imgCount++);

	// solo.getCurrentActivity().button(By.id("searchbtn")).click();
	// public static final int VK_ENTER = '\n';
	// public static final int VK_BACK_SPACE = '\b';
	// public static final int VK_TAB = '\t';
	// public static final int VK_CANCEL = 0x03;
	// public static final int VK_CLEAR = 0x0C;
	// public static final int VK_SHIFT = 0x10;
	// public static final int VK_CONTROL = 0x11;
	// public static final int VK_ALT = 0x12;
	// public static final int VK_PAUSE = 0x13;
	// public static final int VK_CAPS_LOCK = 0x14;
	// public static final int VK_ESCAPE = 0x1B;
	// public static final int VK_SPACE = 0x20;
	// public static final int VK_PAGE_UP = 0x21;
	// public static final int VK_PAGE_DOWN = 0x22;
	// public static final int VK_END = 0x23;
	// public static final int VK_HOME = 0x24;

	// solo.getCurrentActivity().sendKeyEvent(KeyEvent.VK_ENTER);
	// Utils.record(solo, this.deviceId, imgCount++);
	solo.getCurrentActivity().goBack();
	Utils.record(solo, this.deviceId, imgCount++);
    }

    @AfterClass
    public static void stopDriver() {
	AndroidRemoteDriver.stop();
    }
}
