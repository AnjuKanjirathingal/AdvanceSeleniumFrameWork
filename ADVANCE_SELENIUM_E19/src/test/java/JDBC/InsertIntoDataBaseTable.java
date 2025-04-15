package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.mysql.jdbc.Driver;



public class InsertIntoDataBaseTable {

	public static void main(String[] args) throws Throwable {
		
		
	Driver jdbcDriver=new Driver();
		
		DriverManager.registerDriver(jdbcDriver);
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_signup","root","root");
		
		Statement stmt=con.createStatement();
		String query="insert into signup values(101,'email@gmail.com','anju','12345',8546971205)";
		
		int result=stmt.executeUpdate(query);
	if(result==1) {
		System.out.println("inserted");
	}
	else {
		System.out.println("Data not inserted");
	}
	}

}
