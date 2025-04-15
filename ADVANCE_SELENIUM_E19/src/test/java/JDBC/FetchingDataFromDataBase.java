package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class FetchingDataFromDataBase {

	public static void main(String[] args) throws Throwable {
		
		//step1:- Register driver/load mysql database
		Driver driverRef = new Driver();

		DriverManager.registerDriver(driverRef);
		
		//step2:- get connect to database
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advanceseleniumE19","root","root");
		
		//step3:- create SQL statement
		Statement state = con.createStatement();
		String query = "select * from signup";
		
		//step4:-excute statement/query
		ResultSet result = state.executeQuery(query);
		
		while(result.next())
		{
			System.out.println(result.getInt(1)+"\t"+result.getString(2)+"\t"+result.getString(3)+"\t"+result.getString(4)+"\t"+result.getLong(5));
		}
		
		//step5:- close the database connection
		con.close();
	}

}
 
