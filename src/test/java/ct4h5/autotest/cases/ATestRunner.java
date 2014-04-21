package ct4h5.autotest.cases;

import ct4h5.util.Install;
import ct4h5.util.Uninstall;
import ct4h5.util.Util;
import etao.autotest.report.GenerateReport;
import etao.autotest.threadpool.LThread;
import etao.autotest.util.Const;

/**
 * @author 东海陈光剑 2014年2月17日 下午8:58:02
 */
public class ATestRunner {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
	Go();
    }

    private static void Go() {
	long start = System.currentTimeMillis();
	multiThreadRunTraverseUrlsTest();
	long end = System.currentTimeMillis();
	report(start, end);

    }

    private static void multiThreadRunTraverseUrlsTest() {
	int threadNum = Util.getDeviceList().size();
	System.out.println(Thread.currentThread().getName() + "开始");

	for (int index = 0, pcPort = 8090; index < threadNum; index++, pcPort++) {
	    String device = Util.getDeviceList().get(index);
	    Runnable target = new TraverseUrlsTest(device, pcPort);
	    Thread thread = new LThread(target);
	    thread.start();
	}
	Thread.yield();
	while (true) {// 等待所有子线程执行完
	    if (!LThread.hasThreadRunning()) {
		break;
	    }
	}
	System.out.println(Thread.currentThread().getName() + "结束.");// 打印结束标记
    }

    private static void report(long start, long end) {
	System.out.println("Running Time: " + (end - start) / 1000 + " s"
		+ "( " + (end - start) / 1000 / 60 + " min )");
	System.out.println("Run timestamp:" + Const.timestamp);

	String runningSec = (end - start) / 1000 + "";
	String runningMin = (end - start) / 1000 / 60 + "";
	GenerateReport.genReport(Const.timestamp, runningSec, runningMin);

    }

    /**
     * 多线程执行安装android-server.apk
     */
    private static void install() {
	for (String e : Util.getDeviceList()) {
	    (new Thread(new Install(e))).start();
	}

    }

    /**
     * 多线程执行卸载android-server.apk
     */
    private static void uninstall() {
	for (String e : Util.getDeviceList()) {
	    (new Thread(new Uninstall(e))).start();
	}

    }

}