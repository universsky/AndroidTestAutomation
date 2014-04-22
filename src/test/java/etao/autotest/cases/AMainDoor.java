/**
 * etao.test MainRunner.java 2014年4月20日
 */
package etao.autotest.cases;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import etao.autotest.report.GenerateReport;
import etao.autotest.threadpool.LThread;
import etao.autotest.util.LetsGo;
import etao.autotest.util.Utils;

/**
 * @author 东海陈光剑 2014年4月20日 下午11:36:53
 */
public class AMainDoor {

    public static void main(String[] args) {
	// LetsGo.genApks();
	// LetsGo.letsGo();
	AMainDoor m = new AMainDoor();
	m.go();
    }

    /**
     * 单线程
     */
    private void go() {
	long start = System.currentTimeMillis();
	/**
	 * 指定要运行的测试类
	 */
	// Result result = JUnitCore.runClasses(StartMainActivityTest.class,
	// FeedStreamTest.class, SearchTest.class);
	Result result = JUnitCore.runClasses(TestAll.class);

	for (Failure failure : result.getFailures()) {
	    System.out.println(failure.toString());
	}
	System.out.println(result.wasSuccessful());

	long end = System.currentTimeMillis();
	/**
	 * 生成测试报告
	 * http://10.125.1.58:88/report.html?run_stamp=20140421122151&sec=
	 * 103&min=1
	 */
	GenerateReport.report(start, end);

    }

    /**
     * 多线程
     */
    private void MGo() {

	for (String deviceId : Utils.getDeviceList()) {
	    Runnable mfeedStreamTest = new MFeedStreamTest(deviceId);
	    Thread thread = new LThread(mfeedStreamTest);
	    thread.start();
	}
	// Thread.yield();
	while (true) {// 等待所有子线程执行完
	    if (!LThread.hasThreadRunning()) {
		break;
	    }
	}
	System.out.println(Thread.currentThread().getName() + "结束.");
    }
}
