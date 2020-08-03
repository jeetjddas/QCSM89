package com.autodeskcrm.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.testng.annotations.Test;
import com.mysql.cj.jdbc.Driver;

public class ExecuteNonSelectQuery {
	
	@Test
	public void createContact() throws Throwable {
		
		//step 1 : register the mysql database-Driver  
		
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);
		
		 //step 2 : connect to database 
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "jeet");
		
		//step 3 : create statement
		Statement stat = con.createStatement();
		String query = "insert into country values(110, 'Jeet')";
		
		//step 4 : execute Query 
		int result = stat.executeUpdate(query);
		
	     System.out.println(result);       
		
		 //step 5 : close the connection 
		con.close();
	}
}
