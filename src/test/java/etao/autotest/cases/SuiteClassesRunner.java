/**
 * etao.test MainRunner.java 2014��4��19��
 */
package etao.autotest.cases;

import org.junit.runner.RunWith;

import etao.autotest.report.GenerateReport;

/**
 * @author �����¹⽣ 2014��4��19�� ����12:13:55
 */
@RunWith(org.junit.runners.Suite.class)
public class SuiteClassesRunner {

    @org.junit.runners.Suite.SuiteClasses({ StartMainActivityTest.class,
	    FeedStreamTest.class })
    // ָ��������
    public class TestAll { // ����һ�����裬ʵ��û��
    }
}
