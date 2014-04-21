/**
 * etao.test AMainRunner.java 2014��4��19��
 */
package etao.autotest.cases;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import etao.autotest.report.GenerateReport;
import etao.autotest.threadpool.LThread;
import etao.autotest.util.Utils;

/**
 * @author �����¹⽣ 2014��4��19�� ����12:29:48
 */
public class RunnerA {

    /**
     * ���������
     * 
     * @param args
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {
	test();
	// MultiThreadTest();
	// testAll();

    }

    /**
     * ���߳�ִ�в�������
     */
    private static void test() throws ClassNotFoundException {

	long start = System.currentTimeMillis();
	String packagePath = "etao.autotest.cases";

	for (String deviceId : Utils.getDeviceList()) {
	    MyTestRunner testRunner = new MyTestRunner();
	    addTestClasses(testRunner, packagePath);
	    testRunner.run(deviceId);
	}

	long end = System.currentTimeMillis();
	GenerateReport.report(start, end);

    }

    private static void testAll() {
	MyTestRunner testRunner = new MyTestRunner();
	testRunner.addTestCase(StartMainActivityTest.class)
		.addTestCase(FeedStreamTest.class)
		.addTestCase(SearchTest.class).run();
    }

    public static void MultiThreadTest() throws ClassNotFoundException {
	long start = System.currentTimeMillis();

	int threadNum = Utils.getDeviceList().size();
	System.out.println(Thread.currentThread().getName() + "��ʼ");// ��ӡ��ʼ���
	String packagePath = "etao.autotest.cases";

	/**
	 * private static List<Thread> runningThreads = new
	 * ArrayList<Thread>(5); �ڵ�һ��new LThread�����ʱ��,���ھ�̬������runningThreads
	 * ���ҽ���������һ��
	 */
	LThread lthread = new LThread();

	for (String deviceId : Utils.getDeviceList()) {
	    MyTestRunner testRunner = new MyTestRunner();
	    addTestClasses(testRunner, packagePath);
	    Runnable mr = new MultiRunner(deviceId, testRunner);
	    Thread t = new LThread(mr);
	    t.start();
	}

	while (true) {// �ȴ��������߳�ִ����,��ط���������
	    if (!lthread.hasThreadRunning()) {
		break;
	    }
	}

	System.out.println(Thread.currentThread().getName() + "����.");

	long end = System.currentTimeMillis();
	GenerateReport.report(start, end);
    }

    /**
     * ���Ҫ�������
     * 
     * @param testRunner
     * @param packagePath
     *            Ҫ�������
     * @throws ClassNotFoundException
     */
    private static void addTestClasses(MyTestRunner testRunner,
	    String packagePath) throws ClassNotFoundException {
	List<String> testClassList = getTestClassInPackage(packagePath);
	for (String e : testClassList) {
	    testRunner.addTestCase(Class.forName("etao.autotest.cases." + e));
	}

    }

    /**
     * ���ݰ�������ȡ�˰������е�����
     * 
     * @param packagePath
     * @return
     */
    public static List<String> getClassInPackage(String packagePath) {
	List<String> classList = new ArrayList<String>(10);
	String packageName = packagePath;
	String packageDirName = packageName.replace(".", "/");
	Enumeration<URL> dirs = null;
	try {
	    dirs = Thread.currentThread().getContextClassLoader()
		    .getResources(packageDirName);
	    // ������ Enumeration
	    while (dirs.hasMoreElements()) {
		URL url = dirs.nextElement();
		File file = new File(url.getFile());
		// �Ѵ�Ŀ¼�µ������ļ��г�
		String[] classes = file.list();
		// ѭ�������飬����.classȥ�� ".class".length() = 6
		for (String className : classes) {
		    className = className.substring(0, className.length() - 6);
		    classList.add(className);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return classList;

    }

    /**
     * ���ݰ�������ȡ�˰������е�Test����
     * 
     * @param packagePath
     * @return
     */
    public static List<String> getTestClassInPackage(String packagePath) {
	List<String> classList = getClassInPackage(packagePath);
	System.out.println(classList);// [AMainRunner, FeedStreamTest,
				      // StartMainActivityTest,
				      // SuiteClassesRunner$TestAll,
				      // SuiteClassesRunner]
	List<String> testClassList = new ArrayList<String>(10);
	// ֻ����*Test.java�Ĳ�����
	for (String e : classList) {
	    if (e.endsWith("Test")) {
		testClassList.add(e);
	    }
	}
	for (String e : testClassList) {
	    System.out.println(e);
	}
	return testClassList;
    }

}