package Object_Repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class OrganizationValidationPOM {

	public OrganizationValidationPOM(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//span[@id='dtlview_Organization Name']")
	private WebElement lblOrgName;
	
	@FindBy(xpath = "//span[@id='dtlview_Phone']")
	private WebElement lblPhnNumber;
	
	@FindBy(xpath = "//td[@id='mouseArea_Email']")
	private WebElement lblEmailID;
	
	
	public WebElement getLblOrgName() {
		return lblOrgName;
	}




	public WebElement getLblPhnNumber() {
		return lblPhnNumber;
	}




	public WebElement getLblEmailID() {
		return lblEmailID;
	}




	public void organisationDetailsValidation(WebDriver driver,String orgName,String phnNumber,String emailId)
	{
		String orgNameAdded=lblOrgName. getText();
		String PhnAdded =lblPhnNumber.getText();
		String EmailAdded=lblEmailID.getText();
		if(	( (orgNameAdded.contains(orgName) ) &&  (EmailAdded.contains(emailId)) && (PhnAdded.contains(phnNumber)) ))
		{

					System.out.println("Details as below : \n Organization:\t"+orgNameAdded+"\n email :\t"+EmailAdded+"\n phoneAdded :\t"+PhnAdded);
		} 
		else
		{
			System.out.println("Organization details not added");
		}
		
	}
	
	
	public void organisationDetailsSoftandHardAssertValidation(WebDriver driver,String orgName,String phnNumber,String emailId)
	{
		String orgNameAdded=lblOrgName. getText();
		String PhnAdded =lblPhnNumber.getText();
		String EmailAdded=lblEmailID.getText();
		/*
		 * if( ( (orgNameAdded.contains(orgName) ) && (EmailAdded.contains(emailId)) &&
		 * (PhnAdded.contains(phnNumber)) )) {
		 * 
		 * System.out.println("Details as below : \n Organization:\t"
		 * +orgNameAdded+"\n email :\t"+EmailAdded+"\n phoneAdded :\t"+PhnAdded); } else
		 * { System.out.println("Organization details not added"); }
		 */
		
		Assert.assertEquals(orgNameAdded, orgName, "Assertion Failed..Both names are different");
		System.out.println("Assertion Success......Organization  Name is matching and its  added");
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(PhnAdded, phnNumber, "Assertion Failed.PHONE NUMBER does not match.\n");
		System.out.println("Assertion Success......PHONE NUMBER is matching and its  added");
		sa.assertEquals(EmailAdded, emailId, "Assertion Failed.Email id does not match.");
		System.out.println("Assertion Success......Organization EMAIL ID is matching and its  added");
		sa.assertAll();
	}


}
