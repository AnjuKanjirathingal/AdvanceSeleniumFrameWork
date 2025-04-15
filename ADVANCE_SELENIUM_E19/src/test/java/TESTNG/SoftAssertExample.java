package TESTNG;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
@Listeners(GenericUtility.ExtentReportImplementation.class)
public class SoftAssertExample
{
	// Assertion
	// Types of Assertion ---->*HardAssert *SoftAssert
	// SoftAssert
	// *SoftAssert is non-Static in Nature
	// *In SoftAssert when ever @Test/Assert is failed,it not stops the execution in
	// same Line
	// *When ever SoftAssert Fails Throws AssertError Exception
	// *SoftAssert is used only for non Mandatory Fields.\
	// *AssertAll() is mandatory

	@Test
	public void m1()
	{
		System.out.println("Step1");
		System.out.println("Step2");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(true, false);
		System.out.println("Step3");
		System.out.println("Step4");
		soft.assertAll();
	}
	
	@Test
	public void m2()
	{
		System.out.println("Step1");
		System.out.println("Step2");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(true, true);
		System.out.println("Step3");
		System.out.println("Step4");
		soft.assertAll();
	}

}
