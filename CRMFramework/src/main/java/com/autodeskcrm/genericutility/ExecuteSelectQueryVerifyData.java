package com.autodeskcrm.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.testng.annotations.Test;
import com.mysql.cj.jdbc.Driver;

public class ExecuteSelectQueryVerifyData {
	
	@Test
	public void createConatct() throws Throwable {
		
		//step 1 : register the database-Driver  
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);
		
		 //step 2 : connect to database 
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "jeet");
		
		//step 3 : create statement
		Statement stat = con.createStatement();
		String query = "select * from country";
		
		//step 4 : execute Query 
		ResultSet result = stat.executeQuery(query);
		
		//display the resut
		while(result.next()) {
			System.out.println(result.getInt(1) + "\t" + result.getString(2) + "\t" + result.getString(3) +"\t" + result.getString(4) );
		}
		
		 //step 5 : close the connection 
		con.close();
	}
}
