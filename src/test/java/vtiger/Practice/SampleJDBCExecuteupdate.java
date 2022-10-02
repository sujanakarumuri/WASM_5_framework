package vtiger.Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExecuteupdate {
	
	public static void main(String[] args) throws SQLException
	{
		Driver driverRef=new Driver();
		Connection con=null;
		
		try {
		DriverManager.registerDriver(driverRef);		
		 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb","root","root");		
		Statement state=con.createStatement();
		
		String query="insert into customerinfo values(7,'sujana','pune')";
		int result = state.executeUpdate(query);
		if(result==1)
		{
			System.out.println("the data is inserted");
			
		}
		else
		{
			System.out.println("data is not inserted");
		}
		// for table display
		
		String query1="select * from customerinfo;";
		
		ResultSet result1 = state.executeQuery(query1);
		while(result1.next())
		{
			System.out.println(result1.getString(1)+" "+result1.getString(2)+" "+result1.getString(3));
		}
		}
		catch(Exception e)
		{
		
		}
		finally {
			con.close();
			System.out.println("database is closed");
		}
		
	}

}
