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
import com.taobao.sword.android.elements.IAndroidView;
import com.taobao.sword.android.manager.AndroidRemoteDriver;
import com.taobao.sword.android.manager.IAndroidDriver;
import com.taobao.sword.android.object.By;

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

	// // ��MainActivity
	// driver.startActivity(Const.ETAO.MainActivity);
	// Utils.record(driver, this.deviceId, imgCount++);
	// ��SearchHomeActivity
	driver.startActivity(Const.ETAO.SearchHomeActivity);
	Utils.record(driver, this.deviceId, imgCount++);

	driver.getCurrentActivity().view(By.id("search_header_center_text"))
		.click();

	driver.getCurrentActivity()
		.editText(By.id("search_header_center_edit"))
		.setText("ipad air");
	Utils.record(driver, this.deviceId, imgCount++);
	// ���"����"
	driver.getCurrentActivity().view(By.id("search_header_right_search"))
		.click();
	Utils.record(driver, this.deviceId, imgCount++);

	// <RelativeLayout
	// android:id="@+id/search_header_right"
	// android:layout_width="52dp"
	// android:layout_height="match_parent"
	// android:layout_alignParentRight="true"
	// android:gravity="center"
	// android:paddingLeft="10dp"
	// android:paddingRight="12dp" >
	//
	// <!-- ɨ�裬Ĭ����ʾ -->
	//
	// <ImageView
	// android:id="@+id/search_header_right_camera"
	// android:layout_width="wrap_content"
	// android:layout_height="wrap_content"
	// android:contentDescription="@string/home_icon_scanning"
	// android:src="@drawable/ic_scranning" />
	//
	// <!-- ɸѡ��Ĭ�ϲ���ʾ -->
	//
	// <TextView
	// android:id="@+id/search_header_right_filter"
	// android:layout_width="wrap_content"
	// android:layout_height="wrap_content"
	// android:text="@string/header_filter"
	// android:textColor="#ffffff"
	// android:textSize="14sp"
	// android:visibility="gone" />
	//
	// <!-- ������Ĭ�ϲ���ʾ -->
	//
	// <TextView
	// android:id="@+id/search_header_right_search"
	// android:layout_width="wrap_content"
	// android:layout_height="wrap_content"
	// android:layout_centerInParent="true"
	// android:gravity="center_vertical"
	// android:text="@string/header_search"
	// android:textColor="#ffffff"
	// android:textSize="14sp"
	// android:visibility="gone" />
	// </RelativeLayout>
	// driver.getCurrentActivity().view(By.id("search_header_right_filter"))
	// .click();

	// while (!driver.getCurrentActivity().searchView(
	// By.id("search_header_right_filter"))) {
	// driver.getCurrentActivity().waitForView(
	// By.id("search_header_right_filter"), 3000);
	// }

	// driver.getCurrentActivity().view(By.text("ɸѡ")).click();
	// driver.getCurrentActivity().topView(1).view(By.text("ɸѡ")).click();
	driver.waitForActivity("com.etao.mobile.search.SearchResultActivity",
		1000);
	// driver.getCurrentActivity().view(By.layout("views_search_header_fake"));

	// driver.getCurrentActivity().view(By.id("search_header_right_filter"))
	// .click();
	// driver.getCurrentActivity().view(By.layout("search_header_right"));

	// Utils.record(driver, this.deviceId, imgCount++);

	// // ���ü۸�����
	// driver.getCurrentActivity().editText(By.id("start_price"))
	// .setText("2000");
	// Utils.record(driver, this.deviceId, imgCount++);
	// driver.getCurrentActivity().editText(By.id("end_price"))
	// .setText("2500");
	// Utils.record(driver, this.deviceId, imgCount++);
	// // ���"ȷ��"
	// driver.getCurrentActivity().view(By.id("filter_ok"));
	// Utils.record(driver, this.deviceId, imgCount++);

	// ����,�۸�����
	// <TextView
	// android:id="@+id/search_tab2_text_1"
	// android:layout_width="wrap_content"
	// android:layout_height="wrap_content"
	// android:layout_centerHorizontal="true"
	// android:layout_centerVertical="true"
	// android:gravity="center"
	// android:text="����"
	// android:textColor="#999999"
	// android:textSize="14sp" />
	//
	driver.getCurrentActivity().textView(By.id("search_tab2_text_1"));
	Utils.record(driver, this.deviceId, imgCount++);
	// <TextView
	// android:id="@+id/search_tab3_text_1"
	// android:layout_width="wrap_content"
	// android:layout_height="wrap_content"
	// android:layout_centerHorizontal="true"
	// android:layout_centerInParent="true"
	// android:layout_centerVertical="true"
	// android:gravity="center"
	// android:text="�۸�"
	// android:textColor="#999999"
	// android:textSize="14sp" />

	// search_tab1_text "Ĭ��"
	// search_tab3_text "�۸�"
	// ����
	driver.getCurrentActivity().textView(By.id("search_tab3_text_1"))
		.click();
	Utils.record(driver, this.deviceId, imgCount++);
	// ����
	driver.getCurrentActivity().textView(By.id("search_tab3_text_1"))
		.click();
	Utils.record(driver, this.deviceId, imgCount++);

	// <TextView
	// android:id="@+id/search_tab1_text_1"
	// android:layout_width="wrap_content"
	// android:layout_height="wrap_content"
	// android:layout_centerHorizontal="true"
	// android:layout_centerVertical="true"
	// android:gravity="center"
	// android:text="Ĭ��"
	// android:textColor="#ffffff"
	// android:textSize="14sp" />

	// "Ĭ��"
	driver.getCurrentActivity().textView(By.id("search_tab1_text_1"))
		.click();
	Utils.record(driver, this.deviceId, imgCount++);

	// �л�Grid Listչʾ
	// <ImageView
	// android:id="@+id/mode_change_1"
	// android:layout_width="33dp"
	// android:layout_height="33dp"
	// android:layout_centerInParent="true"
	// android:paddingBottom="10dp"
	// android:paddingLeft="10dp"
	// android:paddingRight="10dp"
	// android:paddingTop="10dp"
	// android:src="@drawable/grid_show" />

	driver.getCurrentActivity().imageView(By.id("mode_change_1")).click();
	Utils.record(driver, this.deviceId, imgCount++);

	driver.getCurrentActivity().imageView(By.id("mode_change_1")).click();
	Utils.record(driver, this.deviceId, imgCount++);

	driver.getCurrentActivity().imageView(By.id("mode_change_1")).click();
	Utils.record(driver, this.deviceId, imgCount++);
	driver.finishAllActivity();
    }

    @AfterClass
    public static void stopDriver() {
	AndroidRemoteDriver.stop();
    }

}
