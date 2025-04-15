package TESTNG;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(GenericUtility.ExtentReportImplementation.class)
public class HardAssertEx2 {
	
	// Assertion
	// Types of Assertion ---->*HardAssert *SoftAssert
	// HardAssert
	// *HardAssert is Static in Nature
	// *In HardAssert when ever @Test/Assert is failed,it stops the execution in
	// same Line
	// *When ever HardAssert Fails Throws AssertError Exception
	// *HardAssert is used only for Mandatory Fields..
//AssertAll() is not mandatory
	
	@Test
	public void method9AssertNullWIthMessage()
	{
		String Actual=null;
		   
		   Assert.assertNull(Actual, "Assertion is not Null");
		   System.out.println("Assertions are Null.test passed");
	}
	
	
	@Test
	public void method10AssertNullWIthMessage()
	{
		String Actual="not Null Value here";
		   
		   Assert.assertNull(Actual, "Assertion is not Null");
		   System.out.println("Assertions are Null.test passed");
	}
	
	@Test
	public void method11AssertNotNullWIthMessage()
	{
		String Actual=null;
		   
		   Assert.assertNotNull(Actual, "Assertion is Null");
		   System.out.println("Assertions are Not Null.test passed");
	}
	
	@Test
	public void method12AssertNotNullWIthMessage()
	{
		String Actual="ANju";
		   
		   Assert.assertNotNull(Actual, "Assertion is Null");
		   System.out.println("Assertions are Not Null.test passed");
	}
	
	
	@Test
	public void method13AssertSamelWIthMessage()
	{
		 String Actual="ABCD";
		 String expected="ABCD";
		   Assert.assertSame(Actual, expected,"Asserts are not same");
		   System.out.println("Assertions are SAme.test passed");
	}
	
	@Test
	public void method14AssertSamelWIthMessage()
	{
		 String Actual="ABD";
		 String expected="ABCD";
		   Assert.assertSame(Actual, expected,"Asserts are not same.Test Failed");
		   System.out.println("Assertions are SAme.test passed");
	}
	
	
	@Test
	public void method15AssertNotSamelWIthMessage()
	{
		 String Actual="ABD";
		 String expected="ABCD";
		   Assert.assertNotSame(Actual, expected,"Asserts are same.Test Failed \t"+Actual);
		   System.out.println("Assertions are SAme.test passed");
	}
	
	@Test
	public void method16AssertNotSamelWIthMessage()
	{
		 String Actual="ABCD";
		 String expected="ABCD";
		   Assert.assertNotSame(Actual, expected,"Asserts are same.Test Failed \t");
		   System.out.println("Assertions are SAme.test passed");
	}
	
	
	//this method used to intentionally fail the script even if there is no failure chance.we can use this to check whether screenshot is taken after the failure of the scriot
	@Test
	public void method17AsserFail()
	{
		  Assert.fail("Im intentionally failing this Script");
	}
}
