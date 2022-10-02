package vtiger.Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExecuteQuery {
	
	public static void main(String[] args) throws SQLException
	{
		Driver driverRef=new Driver();
		Connection con=null;
		
		try
		{
		//step1: Register to driver/database
		DriverManager.registerDriver(driverRef);
		
		//step2: Get the connection with the database--- use database name
		  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb","root","root");
		
		//step3: issue create statement
		Statement state=con.createStatement();
		
		//step4: execute a query----use table name
		ResultSet result=state.executeQuery("Select * from customerinfo;");
		
		while(result.next())
		{
			System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3));
		}
		}
		catch(Exception e)
		{
		}
		finally
		{
		con.close();
	}
	}
	

}
