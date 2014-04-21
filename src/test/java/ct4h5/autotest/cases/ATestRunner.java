package ct4h5.autotest.cases;

import ct4h5.util.Install;
import ct4h5.util.Uninstall;
import ct4h5.util.Util;
import etao.autotest.report.GenerateReport;
import etao.autotest.threadpool.LThread;
import etao.autotest.util.Const;

/**
 * @author �����¹⽣ 2014��2��17�� ����8:58:02
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
	System.out.println(Thread.currentThread().getName() + "��ʼ");

	for (int index = 0, pcPort = 8090; index < threadNum; index++, pcPort++) {
	    String device = Util.getDeviceList().get(index);
	    Runnable target = new TraverseUrlsTest(device, pcPort);
	    Thread thread = new LThread(target);
	    thread.start();
	}
	Thread.yield();
	while (true) {// �ȴ��������߳�ִ����
	    if (!LThread.hasThreadRunning()) {
		break;
	    }
	}
	System.out.println(Thread.currentThread().getName() + "����.");// ��ӡ�������
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
     * ���߳�ִ�а�װandroid-server.apk
     */
    private static void install() {
	for (String e : Util.getDeviceList()) {
	    (new Thread(new Install(e))).start();
	}

    }

    /**
     * ���߳�ִ��ж��android-server.apk
     */
    private static void uninstall() {
	for (String e : Util.getDeviceList()) {
	    (new Thread(new Uninstall(e))).start();
	}

    }

}