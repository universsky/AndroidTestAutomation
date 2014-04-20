/**
 * etao.test MultiRunner.java 2014��4��19��
 */
package etao.autotest.cases;

/**
 * @author �����¹⽣ 2014��4��19�� ����1:55:23
 */

class MultiThread implements Runnable {
    String deviceId;
    MyTestRunner myTestRunner;

    public MultiThread(String deviceId, MyTestRunner myTestRunner) {
	this.deviceId = deviceId;
	this.myTestRunner = myTestRunner;
    }

    public void run() {
	myTestRunner.run(deviceId);
    }

}
