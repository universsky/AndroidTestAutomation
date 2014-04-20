package etao.autotest.dao;

import java.sql.*;

public class InsertDB {

    public static void main(String[] args) {
	for (int i = 0; i < 10; i++)
	    insert("2", "2", "2", "2", "2");
    }

    public static boolean insert(String timestamp, String activityName,
	    String imgName, String deviceId, String imgTimeChamp) {
	try {
	    Class.forName("com.mysql.jdbc.Driver");

	    System.out
		    .println("Success loading Mysql Driver  com.mysql.jdbc.Driver  !");
	} catch (Exception e) {
	    System.out
		    .print("Error loading Mysql Driver  com.mysql.jdbc.Driver  !");
	    e.printStackTrace();
	}
	try {
	    Connection connect = DriverManager.getConnection(
		    "jdbc:mysql://10.125.1.58:3306/test", "root", "isword");

	    System.out.println("Success connect Mysql server!");
	    Statement stmt = connect.createStatement();
	    String insertCmd = "INSERT INTO ct_pic(run_stamp,device_id,img_name,url,img_timestamp)"
		    + " VALUES ("
		    + "'"
		    + timestamp
		    + "',"
		    + "'"
		    + deviceId
		    + "',"
		    + "'"
		    + imgName
		    + "',"
		    + "'"
		    + activityName
		    + "','"
		    + imgTimeChamp + "')";
	    System.out.println(insertCmd);
	    boolean rs = stmt.execute(insertCmd);
	    return rs;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }
}

// GRANT ALL ON *.* TO root@"%" IDENTIFIED BY 'isword';
// grant all privileges on *.* to root@'%' identified by '12345678' with grant
// option;