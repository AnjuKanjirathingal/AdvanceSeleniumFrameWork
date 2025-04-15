package TESTNG;

import org.testng.annotations.Test;

import GenericUtility.TestNgBaseClass;

public class TrialTest extends TestNgBaseClass 
{
	@Test(priority = 2, invocationCount = 3)
	public void printMyStatement() {
		//Pulling back toEclipse 1
		  int i=1/0; // it is arithamatical exception which we are not going to handle it for now this will lead login() to get failed and as a result , the other two methods get skipped since it is dependant on login()
		 
		System.out.println("test my TESTNG");
	}

	@Test(priority = -1, dependsOnMethods = "printMyStatement")
	public static void logIn() {
		System.out.println("Log In ");
	}

	@Test(priority = 0, dependsOnMethods = "printMyStatement")

	public static void addToCart() {
		System.out.println("Item Added To cart ");
	}

	@Test(priority = 1, dependsOnMethods = "printMyStatement")
	public static void logOut() {
		System.out.println("Log Out ");
	}
}
