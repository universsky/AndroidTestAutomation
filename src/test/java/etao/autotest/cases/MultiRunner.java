/**
 * etao.test MultiRunner.java 2014年4月19日
 */
package etao.autotest.cases;

/**
 * @author 东海陈光剑 2014年4月19日 上午1:55:23
 */

class MultiRunner implements Runnable {
    String deviceId;
    MyTestRunner myTestRunner;

    public MultiRunner(String deviceId, MyTestRunner myTestRunner) {
	this.deviceId = deviceId;
	this.myTestRunner = myTestRunner;
    }

    public void run() {
	myTestRunner.run(deviceId);
    }

}
