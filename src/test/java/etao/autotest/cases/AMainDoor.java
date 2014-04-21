/**
 * etao.test MainRunner.java 2014��4��20��
 */
package etao.autotest.cases;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import etao.autotest.report.GenerateReport;

/**
 * @author �����¹⽣ 2014��4��20�� ����11:36:53
 */
public class AMainDoor {

    public static void main(String[] args) {
	long start = System.currentTimeMillis();
	/**
	 * ָ��Ҫ���еĲ�����
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
	 * ���ɲ��Ա���
	 * http://10.125.1.58:88/report.html?run_stamp=20140421122151&sec=
	 * 103&min=1
	 */
	GenerateReport.report(start, end);
    }

}
