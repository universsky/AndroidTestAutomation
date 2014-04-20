/**
 * etao.test MainRunner.java 2014年4月19日
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
 * @author 东海陈光剑 2014年4月19日 上午12:13:55
 */

/**
 * 此类的作用是整合测试也称 打包测试;可以把之前所有的写好的test class类进行集成; 如需测试多个类时，只需要把相关的测试类加入到"{}"即可;
 * 如果不是同一个包类的class记得加上package名称。
 * 
 * @author 东海陈光剑 2014年4月20日 下午10:20:24
 */

/**
 * 指定运行器@RunWith(Suite.class)
 */
@RunWith(Suite.class)
/**
 *  指定测试类@SuiteClasses
 * @author 东海陈光剑
 * 2014年4月21日 上午12:20:58
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
// // 以下三种方式均可以，具体情况可运行以下，看一下结果
// junit.textui.TestRunner.run(TestAll.class);//
// 如果没有制定特定的suite，则自动映射为执行用例类中所有的testXXX方法
// // junit.swingui.TestRunner.run(Test.class);
// // junit.awtui.TestRunner.run(Test.class);
// // junit.swingui.TestRunner.run(TestUnit.class);
// // junit.textui.TestRunner.run(suite()); // 运行测试用例类中添加到suite中的方法
// }
// }

// public class TestAll extends TestCase {
//
// public static TestSuite suite()// 固定格式
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