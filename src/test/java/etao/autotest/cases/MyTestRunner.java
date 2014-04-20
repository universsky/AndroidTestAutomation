/**
 * etao.test MyTestRunner.java 2014��4��19��
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
 * @author �����¹⽣ 2014��4��19�� ����1:12:33
 */

public class MyTestRunner {
    // ���ڴ��TestCase�����е�TestMethods
    private Map<Class<?>, List<Method>> mTestCases = new HashMap<Class<?>, List<Method>>();
    private static final String WARING_FMT = "����: %s%n";
    private static final String ERROR_FMT = "����: %s%n%s%n";

    // ����һ������������
    public MyTestRunner addTestCase(Class testcase) {
	List<Method> testMethods = findTestMethods(testcase);
	if (testMethods.isEmpty()) {
	    System.out.format(WARING_FMT, "TestCase '" + testcase.getName()
		    + "' û�ж�����Է���");
	} else {
	    mTestCases.put(testcase, testMethods);
	}
	return this;
    }

    // ����ĳ�����������еĲ��Է���
    // ���Է����������㣺1.������ΪtestXxx 2.�޲�
    private List<Method> findTestMethods(Class<?> testcase) {
	// �õ����ж�������з���������private
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
	    System.out.format(WARING_FMT, "û�п����еĲ���������");
	    return;
	}

	int succeedTCNum = 0;
	System.out.format("��ʼִ�С�����%d����������%n", mTestCases.size());
	for (Class<?> testcase : mTestCases.keySet()) {
	    // ����ʵ��
	    Object instance = null;
	    try {
		Constructor constructor = testcase
			.getDeclaredConstructor(String.class);
		constructor.setAccessible(true);
		instance = constructor.newInstance(deviceId);
	    } catch (NoSuchMethodException ex) {
		System.err.format(ERROR_FMT,
			testcase.getName() + "û�ж�Ӧ�����Ĺ�������", ex.getCause()
				.getMessage());
	    } catch (InvocationTargetException ex) {
		System.err.format(ERROR_FMT, testcase.getName() + "���췢���쳣��", ex
			.getCause().getMessage());
	    } catch (Exception ex) {
		System.err.format(ERROR_FMT, testcase.getName(),
			ex.getMessage());
	    }
	    if (instance == null) {
		continue;
	    }

	    System.out.format("%n==>TestCase:%s%n", testcase.getName());
	    // ִ��ÿ�����Է���
	    boolean isAllMethodSuccess = true;
	    List<Method> methods = mTestCases.get(testcase);
	    for (Method method : methods) {

		System.out.format("----����%s%n", method.getName());
		try {
		    method.setAccessible(true);

		    method.invoke(instance);

		} catch (InvocationTargetException ex) {
		    isAllMethodSuccess = false;
		    System.err.format(ERROR_FMT, method.getName() + "���������쳣��",
			    ex.getCause().getMessage());
		} catch (Exception ex) {
		    isAllMethodSuccess = false;
		    System.err.format(ERROR_FMT, method.getName(),
			    ex.getMessage());
		}
	    }
	    if (isAllMethodSuccess) {
		succeedTCNum++;
		System.out.format("<==��ɣ�%n");
	    }

	}
	System.out.format("%nִ����ϡ�����%d����������ͨ������!", succeedTCNum);
    }

    public void run() {
	if (mTestCases.isEmpty()) {
	    System.out.format(WARING_FMT, "û�п����еĲ���������");
	    return;
	}

	int succeedTCNum = 0;
	System.out.format("��ʼִ�С�����%d����������%n", mTestCases.size());
	for (Class<?> testcase : mTestCases.keySet()) {
	    // 1.����ʵ��
	    Object instance = null;
	    try {
		Constructor constructor = testcase.getDeclaredConstructor();
		constructor.setAccessible(true);
		instance = constructor.newInstance();
	    } catch (NoSuchMethodException ex) {
		System.err.format(ERROR_FMT, testcase.getName() + "û���޲εĹ�������",
			ex.getCause().getMessage());
	    } catch (InvocationTargetException ex) {
		System.err.format(ERROR_FMT, testcase.getName() + "���췢���쳣��", ex
			.getCause().getMessage());
	    } catch (Exception ex) {
		System.err.format(ERROR_FMT, testcase.getName(),
			ex.getMessage());
	    }
	    if (instance == null) {
		continue;
	    }

	    System.out.format("%n==>TestCase:%s%n", testcase.getName());
	    // 2.ִ��ÿ�����Է���
	    boolean isAllMethodSuccess = true;
	    List<Method> methods = mTestCases.get(testcase);
	    for (Method method : methods) {
		System.out.format("----����%s%n", method.getName());
		try {
		    method.setAccessible(true);
		    method.invoke(instance);
		} catch (InvocationTargetException ex) {
		    isAllMethodSuccess = false;
		    System.err.format(ERROR_FMT, method.getName() + "���������쳣��",
			    ex.getCause().getMessage());
		} catch (Exception ex) {
		    isAllMethodSuccess = false;
		    System.err.format(ERROR_FMT, method.getName(),
			    ex.getMessage());
		}
	    }
	    if (isAllMethodSuccess) {
		succeedTCNum++;
		System.out.format("<==��ɣ�%n");
	    }
	}
	System.out.format("%nִ����ϡ�����%d����������ͨ������!", succeedTCNum);
    }

}