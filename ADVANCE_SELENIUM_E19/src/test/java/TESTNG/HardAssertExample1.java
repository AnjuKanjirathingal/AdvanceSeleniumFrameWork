package TESTNG;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(GenericUtility.ExtentReportImplementation.class)
public class HardAssertExample1
{
	// Assertion
		// Types of Assertion ---->*HardAssert *SoftAssert
		// HardAssert
		// *HardAssert is Static in Nature
		// *In HardAssert when ever @Test/Assert is failed,it stops the execution in
		// same Line
		// *When ever HardAssert Fails Throws AssertError Exception
		// *HardAssert is used only for Mandatory Fields..
	//AssertAll() is not mandatory
	//hardassert is used for mandatory fields  in a form and soft assert will be  ussed for non mandatory fields
	
   @Test
	public void method1AssertEquals()
	{
		System.out.println("akdfhiygc");
		System.out.println("ajsgyigf");
		Assert.assertEquals("AB", "AB");
		System.out.println("adytu");
		System.out.println("ajfu");
	}
   
   
   @Test
   public void method2AssertEquals()
   {
	   int Actual=10;
	   int expected=10;
	   Assert.assertEquals(Actual, expected);
   }
   
   @Test
   public void method3AssertEqualsWithFailureMessagesPassed()
	{
		System.out.println("akdfhiygc");
		System.out.println("ajsgyigf");
		Assert.assertEquals("aA", "B", "asserts are not equal");
		System.out.println("adytu");
		System.out.println("ajfu");
	}
   
   //Assert.assertEquals(Actual, expected);    Assert.assertEquals( expected,Actual); can we write both way  ????
   
   
   
   @Test
   public void method4AssertNotEqualsWithFailureMessagesPassed()
   {
	   String Actual="ANJU";
	   String expected="ANJU";
	   Assert.assertNotEquals(Actual, expected,"Assertions are equal");
	   System.out.println("Assertions are not equal.test passed");
   }
   
   
   @Test
   public void method5AssertNotEqualsWithFailureMessagesPassed()
   {
	   String Actual="ANJU";
	   String expected="Anju";
	   Assert.assertNotEquals(Actual, expected,"Assertions are equal");
	   System.out.println("Assertions are not equal.test passed");
   }
   
   @Test
   public void method6()
   {
	   String Actual="ANJU";
	   String expected="Anju";
	   Assert.assertTrue(Actual.equalsIgnoreCase(expected),"Assertions Failed");
	   System.out.println("Assertions are  equal.test passed");
   }
   
   
   @Test
   public void method7()
   {
	   String Actual="ANJU";
	   String expected="AnjuS";
	   Assert.assertTrue(Actual.equalsIgnoreCase(expected),"Assertions Failed");
	   System.out.println("Assertions are  equal.test passed");
   }
   
   @Test
   public void method7AssertFAlseWIthMessage()
   {
	   String Actual="zqnhsh";
	   String expected="AnjuS";
	   Assert.assertFalse(Actual.equalsIgnoreCase(expected),"Assertions Equal");
	   System.out.println("Assertions are Not  equal.test passed");
   }
   
   
   @Test
   public void method8AssertFAlseWIthMessage()
   {
	   String Actual="ANJUS";
	   String expected="AnjuS";
	   Assert.assertFalse(Actual.equalsIgnoreCase(expected),"Assertions Equal");
	   System.out.println("Assertions are Not  equal.test passed");
   }
   
}
