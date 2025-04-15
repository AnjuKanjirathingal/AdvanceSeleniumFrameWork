package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class MultipleDataInsertionToDBusingJdbc 
{

	public static void main(String[] args) throws Throwable 
	{
		Driver jdbcDriver =new Driver();
		
		DriverManager.registerDriver(jdbcDriver);
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advanceseleniumE19","root","root");
		Statement stmt=con.createStatement();
		String query="insert into signup values(107,'email@gmail.com','anju','12345',8546971205)";
		String query1="insert into signup values(202,'ail@gmail.com','aju','123b45',8546971205)";
		
		String query2="insert into signup values(113,'ahkil@gmail.com','ajjchu','jkjh',8546971205)";
		String query3="insert into signup values(804,'ghail@gmail.com','raju','123ggb45',8546971205)";
		
		int result=stmt.executeUpdate(query);
		int data1=stmt.executeUpdate(query1);
		int data2=stmt.executeUpdate(query3);
		int data3=stmt.executeUpdate(query2);
		if(result==1) {
			System.out.println("one row inserted");
		}
		if(data1==1)
		{
			System.out.println("2nd row inserted");	
		}
		if(data2==1)
		{
			System.out.println("3rd row inserted");	
		}
		if(data3==1)
		{
			System.out.println("4th row inserted");	
		}
		else
		{
			System.out.println("Data not inserted");
		}
	}

}
