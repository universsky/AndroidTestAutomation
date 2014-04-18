/**
 * etao.test MainRunner.java 2014年4月19日
 */
package etao.autotest.cases;

import org.junit.runner.RunWith;

import etao.autotest.report.GenerateReport;

/**
 * @author 东海陈光剑 2014年4月19日 上午12:13:55
 */
@RunWith(org.junit.runners.Suite.class)
public class SuiteClassesRunner {

    @org.junit.runners.Suite.SuiteClasses({ StartMainActivityTest.class,
	    FeedStreamTest.class })
    // 指定测试类
    public class TestAll { // 仅仅一个摆设，实际没用
    }
}
