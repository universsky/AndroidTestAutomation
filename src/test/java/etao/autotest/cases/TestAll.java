/**
 * etao.test MainRunner.java 2014��4��19��
 */
package etao.autotest.cases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.Suite;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import etao.autotest.report.GenerateReport;
import etao.autotest.util.Utils;

/**
 * @author �����¹⽣ 2014��4��19�� ����12:13:55
 */

/**
 * ��������������ϲ���Ҳ�� �������;���԰�֮ǰ���е�д�õ�test class����м���; ������Զ����ʱ��ֻ��Ҫ����صĲ�������뵽"{}"����;
 * �������ͬһ�������class�ǵü���package���ơ�
 * 
 * @author �����¹⽣ 2014��4��20�� ����10:20:24
 */

/**
 * ָ��������@RunWith(Suite.class)
 */
@RunWith(Suite.class)
/**
 *  ָ��������@SuiteClasses
 * @author �����¹⽣
 * 2014��4��21�� ����12:20:58
 */
@SuiteClasses({ StartMainActivityTest.class, FeedStreamTest.class,
	SearchTest.class })
public class TestAll extends TestCase {
}

// public class TestAll extends TestCase {
// public static Test suite() {
// TestSuite suite = new TestSuite();
// suite.addTestSuite(StartMainActivityTest.class);
// suite.addTestSuite(FeedStreamTest.class);
// suite.addTestSuite(SearchTest.class);
// return suite;
// }

// public static void main(String[] args) {
// // �������ַ�ʽ�����ԣ�����������������£���һ�½��
// junit.textui.TestRunner.run(TestAll.class);//
// ���û���ƶ��ض���suite�����Զ�ӳ��Ϊִ�������������е�testXXX����
// // junit.swingui.TestRunner.run(Test.class);
// // junit.awtui.TestRunner.run(Test.class);
// // junit.swingui.TestRunner.run(TestUnit.class);
// // junit.textui.TestRunner.run(suite()); // ���в�������������ӵ�suite�еķ���
// }
// }

// public class TestAll extends TestCase {
//
// public static TestSuite suite()// �̶���ʽ
// {
// TestSuite suite = new TestSuite();
//
// suite.addTestSuite(StartMainActivityTest.class);
// suite.addTestSuite(FeedStreamTest.class);
// suite.addTestSuite(SearchTest.class);
//
// return suite;
// }
// }