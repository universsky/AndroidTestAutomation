/**
 * etao.test AMainRunner.java 2014年4月19日
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
 * @author 东海陈光剑 2014年4月19日 上午12:29:48
 */
public class RunnerA {

    /**
     * 测试主入口
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
     * 单线程执行测试用例
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
	System.out.println(Thread.currentThread().getName() + "开始");// 打印开始标记
	String packagePath = "etao.autotest.cases";

	/**
	 * private static List<Thread> runningThreads = new
	 * ArrayList<Thread>(5); 在第一次new LThread对象的时候,会在静态区创建runningThreads
	 * 而且仅仅创建这一次
	 */
	LThread lthread = new LThread();

	for (String deviceId : Utils.getDeviceList()) {
	    MyTestRunner testRunner = new MyTestRunner();
	    addTestClasses(testRunner, packagePath);
	    Runnable mr = new MultiRunner(deviceId, testRunner);
	    Thread t = new LThread(mr);
	    t.start();
	}

	while (true) {// 等待所有子线程执行完,这地方还有问题
	    if (!lthread.hasThreadRunning()) {
		break;
	    }
	}

	System.out.println(Thread.currentThread().getName() + "结束.");

	long end = System.currentTimeMillis();
	GenerateReport.report(start, end);
    }

    /**
     * 添加要测试类包
     * 
     * @param testRunner
     * @param packagePath
     *            要测试类包
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
     * 根据包名来获取此包下所有的类名
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
	    // 迭代此 Enumeration
	    while (dirs.hasMoreElements()) {
		URL url = dirs.nextElement();
		File file = new File(url.getFile());
		// 把此目录下的所有文件列出
		String[] classes = file.list();
		// 循环此数组，并把.class去掉 ".class".length() = 6
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
     * 根据包名来获取此包下所有的Test类名
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
	// 只保留*Test.java的测试类
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