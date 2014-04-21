package ct4h5.autotest.cases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.android.AndroidDriver;

import ct4h5.util.*;

/**
 * @author 东海陈光剑 2014年3月13日 下午2:07:00
 */
@SuppressWarnings("deprecation")
public class TraverseUrlsTest implements Runnable {

    String deviceId;
    AndroidDriver driver;
    int pcPort;

    public TraverseUrlsTest(String deviceId, int pcPort) {
	this.deviceId = deviceId;
	this.pcPort = pcPort;
    }

    public void init() {
	/**
	 * forward tcp
	 */
	Init.init(deviceId, pcPort);
	URL driverUrl;
	try {
	    driverUrl = new URL("http://localhost:" + pcPort + "/wd/hub/");
	    driver = new AndroidDriver(driverUrl);
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	}
    }

    public void testUrls() throws Exception {

	String[] urlsPre = {
		"http://wapa.etao.com?hidepop=true",
		"http://wapa.etao.com/?hidepop=true#!/youhui/sign.php?type=only",
		"http://wapa.etao.com/?hidepop=true#!/free99/index.php",
		"http://wapa.etao.com/?hidepop=true#!/myetao/index.php",
		"http://wapa.etao.com/?hidepop=true#!/myetao/myjfb.php",
		"http://wapa.etao.com/search/index.php?spm=0.0.0.0",
		"http://wapa.etao.com/search/cat.php?spm=0.0.0.0&name=%E5%86%85%E8%A1%A3",
		"http://wapa.etao.com/search/search.php?spm=0.0.0.0&q=%E5%B0%8F%E6%9D%AF%E6%96%87%E8%83%B8&cat=52402010",
		"http://wapa.etao.com/search/filter.php?spm=0.0.0.0&q=%E5%8C%85%E8%87%80%E8%A3%99&yhsrc=h5",
		"http://h5.m.taobao.com/trip/flight/search/index.html?spm=0.0.0.0&ttid=703402@etao_h5_4.0",
		"http://ju.taobao.com/m/jusp/zhengdian/mtp.htm?spm=0.0.0.0",
		"http://h5.m.taobao.com/try/list.htm?spm=0.0.0.0" };

	/**
	 * 预发测试
	 */
	for (String url : urlsPre) {
	    init();
	    driver.get(url);
	    Util.record(driver, deviceId);
	    destroy();
	}
    }

    public void destroy() {
	Init.destroy(deviceId);
    }

    public synchronized void run() {
	try {

	    testUrls();

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }
}