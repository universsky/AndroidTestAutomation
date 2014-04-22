/**
 * etao.test FeedStreamTest.java 2014年4月18日
 */
package etao.autotest.cases;

import com.taobao.sword.android.enums.AndroidKeys;
import com.taobao.sword.android.manager.AndroidRemoteDriver;
import com.taobao.sword.android.manager.IAndroidDriver;
import com.taobao.sword.android.object.By;

import etao.autotest.util.Const;
import etao.autotest.util.Utils;

/**
 * @author 东海陈光剑 2014年4月18日 下午11:01:28
 */
public class MFeedStreamTest implements Runnable {
    private int imgCount = 0;
    private String deviceId;

    public MFeedStreamTest(String deviceId) {
	this.deviceId = deviceId;
    }

    public void testFeedStream(String deviceId) throws InterruptedException {
	IAndroidDriver driver = AndroidRemoteDriver.start(
		Const.ETAO.packageTest, deviceId);
	// 打开MainActivity
	driver.startActivity(Const.ETAO.MainActivity);
	Utils.record(driver, deviceId, imgCount++);
	/**
	 * List Scroll
	 */
	// solo.getCurrentActivity().view(By.id("feed_stream_listview"))
	// .scroll(ScrollSide.UP);
	driver.getCurrentActivity().view(By.id("feed_stream_listview")).click();
	Utils.record(driver, deviceId, imgCount++);
	// solo.getCurrentActivity().view(By.id("feed_stream_item_pic")).click();
	// Utils.record(solo, this.deviceId, imgCount++);
	driver.getCurrentActivity().goBack();
	Utils.record(driver, deviceId, imgCount++);
	/**
	 * head 搜索框
	 */
	driver.getCurrentActivity().view(By.id("search_header_center_text"))
		.click();
	Utils.record(driver, deviceId, imgCount++);
	// solo.getCurrentActivity().sendKey("ipad air");
	driver.getCurrentActivity()
		.editText(By.id("search_header_center_edit"))
		.setText("ipad air");
	Utils.record(driver, deviceId, imgCount++);

	driver.getCurrentActivity().sendKeyEvent(
		AndroidKeys.ENTER.getAndroidKeyCode());
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
	driver.getCurrentActivity().goBack();
	Utils.record(driver, deviceId, imgCount++);
	// <ImageView
	// android:id="@+id/search_header_right_camera"
	// android:layout_width="wrap_content"
	// android:layout_height="wrap_content"
	// android:contentDescription="@string/home_icon_scanning"
	// android:src="@drawable/ic_scranning" />
	// driver.getCurrentActivity()
	// .imageView(By.id("search_header_right_camera")).click();
	// Utils.record(driver, this.deviceId, imgCount++);
	// driver.getCurrentActivity().sendKeyEvent(
	// AndroidKeys.BACK.getAndroidKeyCode());
	// // driver.getCurrentActivity().button(By.id("back_btn")).click();
	// Utils.record(driver, this.deviceId, imgCount++);
	driver.finishAllActivity();
    }

    public void run() {
	try {
	    testFeedStream(this.deviceId);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	AndroidRemoteDriver.stop();
    }
}
