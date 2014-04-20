/**
 * etao.test MyTestRunner.java 2014年4月19日
 */
package etao.autotest.cases;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 东海陈光剑 2014年4月19日 上午1:12:33
 */

public class MyTestRunner {
    // 用于存放TestCase及其中的TestMethods
    private Map<Class<?>, List<Method>> mTestCases = new HashMap<Class<?>, List<Method>>();
    private static final String WARING_FMT = "警告: %s%n";
    private static final String ERROR_FMT = "错误: %s%n%s%n";

    // 增加一个测试用例类
    public MyTestRunner addTestCase(Class testcase) {
	List<Method> testMethods = findTestMethods(testcase);
	if (testMethods.isEmpty()) {
	    System.out.format(WARING_FMT, "TestCase '" + testcase.getName()
		    + "' 没有定义测试方法");
	} else {
	    mTestCases.put(testcase, testMethods);
	}
	return this;
    }

    // 返回某个测试用例中的测试方法
    // 测试方法必须满足：1.方法名为testXxx 2.无参
    private List<Method> findTestMethods(Class<?> testcase) {
	// 得到类中定义的所有方法，包括private
	Method[] methods = testcase.getDeclaredMethods();
	List<Method> result = new ArrayList<Method>();
	for (Method method : methods) {
	    if (method.getName().matches("test[A-Z\\d].*")
		    && method.getParameterTypes().length == 0) {
		result.add(method);
	    }
	}
	return result;
    }

    public void run(String deviceId) {
	if (mTestCases.isEmpty()) {
	    System.out.format(WARING_FMT, "没有可运行的测试用例。");
	    return;
	}

	int succeedTCNum = 0;
	System.out.format("开始执行。共有%d个测试用例%n", mTestCases.size());
	for (Class<?> testcase : mTestCases.keySet()) {
	    // 构造实例
	    Object instance = null;
	    try {
		Constructor constructor = testcase
			.getDeclaredConstructor(String.class);
		constructor.setAccessible(true);
		instance = constructor.newInstance(deviceId);
	    } catch (NoSuchMethodException ex) {
		System.err.format(ERROR_FMT,
			testcase.getName() + "没有对应参数的构造器！", ex.getCause()
				.getMessage());
	    } catch (InvocationTargetException ex) {
		System.err.format(ERROR_FMT, testcase.getName() + "构造发生异常！", ex
			.getCause().getMessage());
	    } catch (Exception ex) {
		System.err.format(ERROR_FMT, testcase.getName(),
			ex.getMessage());
	    }
	    if (instance == null) {
		continue;
	    }

	    System.out.format("%n==>TestCase:%s%n", testcase.getName());
	    // 执行每个测试方法
	    boolean isAllMethodSuccess = true;
	    List<Method> methods = mTestCases.get(testcase);
	    for (Method method : methods) {

		System.out.format("----方法%s%n", method.getName());
		try {
		    method.setAccessible(true);

		    method.invoke(instance);

		} catch (InvocationTargetException ex) {
		    isAllMethodSuccess = false;
		    System.err.format(ERROR_FMT, method.getName() + "方法发生异常！",
			    ex.getCause().getMessage());
		} catch (Exception ex) {
		    isAllMethodSuccess = false;
		    System.err.format(ERROR_FMT, method.getName(),
			    ex.getMessage());
		}
	    }
	    if (isAllMethodSuccess) {
		succeedTCNum++;
		System.out.format("<==完成！%n");
	    }

	}
	System.out.format("%n执行完毕。共有%d个测试用例通过测试!", succeedTCNum);
    }

    public void run() {
	if (mTestCases.isEmpty()) {
	    System.out.format(WARING_FMT, "没有可运行的测试用例。");
	    return;
	}

	int succeedTCNum = 0;
	System.out.format("开始执行。共有%d个测试用例%n", mTestCases.size());
	for (Class<?> testcase : mTestCases.keySet()) {
	    // 1.构造实例
	    Object instance = null;
	    try {
		Constructor constructor = testcase.getDeclaredConstructor();
		constructor.setAccessible(true);
		instance = constructor.newInstance();
	    } catch (NoSuchMethodException ex) {
		System.err.format(ERROR_FMT, testcase.getName() + "没有无参的构造器！",
			ex.getCause().getMessage());
	    } catch (InvocationTargetException ex) {
		System.err.format(ERROR_FMT, testcase.getName() + "构造发生异常！", ex
			.getCause().getMessage());
	    } catch (Exception ex) {
		System.err.format(ERROR_FMT, testcase.getName(),
			ex.getMessage());
	    }
	    if (instance == null) {
		continue;
	    }

	    System.out.format("%n==>TestCase:%s%n", testcase.getName());
	    // 2.执行每个测试方法
	    boolean isAllMethodSuccess = true;
	    List<Method> methods = mTestCases.get(testcase);
	    for (Method method : methods) {
		System.out.format("----方法%s%n", method.getName());
		try {
		    method.setAccessible(true);
		    method.invoke(instance);
		} catch (InvocationTargetException ex) {
		    isAllMethodSuccess = false;
		    System.err.format(ERROR_FMT, method.getName() + "方法发生异常！",
			    ex.getCause().getMessage());
		} catch (Exception ex) {
		    isAllMethodSuccess = false;
		    System.err.format(ERROR_FMT, method.getName(),
			    ex.getMessage());
		}
	    }
	    if (isAllMethodSuccess) {
		succeedTCNum++;
		System.out.format("<==完成！%n");
	    }
	}
	System.out.format("%n执行完毕。共有%d个测试用例通过测试!", succeedTCNum);
    }

}