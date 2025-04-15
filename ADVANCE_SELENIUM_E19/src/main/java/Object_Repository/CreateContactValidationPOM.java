package Object_Repository;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class CreateContactValidationPOM {

	public CreateContactValidationPOM(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@id='dtlview_Last Name']")
	private WebElement lblContactLastName;

	
	@FindBy(id = "mouseArea_Organization Name")
	private WebElement lblSelectedOrgName;

	
	public WebElement getlblContactLastName() {
		return lblContactLastName;
	}
	
	
	public WebElement getLblSelectedOrgName() {
		return lblSelectedOrgName;
	}


	
	//bussiness logic .......
	
	



	public void validateContactLastName(WebDriver driver,String contactData)
	{
		String contactAdded = lblContactLastName.getText();

		if (contactAdded.contains(contactData))
		{

			System.out.println("Contact Created :\t" + contactAdded);

		} 
		else 
		{
			System.out.println("Contact Created");
		}
	}
	
	
	public void assertValidateContactLastName(WebDriver driver,String contactData)
	{
		String contactAdded = lblContactLastName.getText();

		Assert.assertEquals(contactAdded, contactData, "Assertion Failed ");

			System.out.println("Assertion Success....Contact Created :\t" + contactAdded);

	}
	
	
	public void validateContactLastNameWithOrgName(WebDriver driver,String contactData,String OrgName)
	{
		String contactAdded = lblContactLastName.getText();
		
		String orgAdded=lblSelectedOrgName.getText();
		if(	 contactAdded.contains(contactData) && (orgAdded.contains(OrgName)))
		{
			System.out.println("Contact Created along with Organization :\t"+contactAdded+"\n Organization selected :\t"+orgAdded);
		} 
		else
		{
			System.out.println("Contact Created");
		}
	}

	

	public void hardSoftAssertvalidateContactLastNameWithOrgName(WebDriver driver,String contactData,String OrgName)
	{
		String contactAdded = lblContactLastName.getText();
		
		String orgAdded=lblSelectedOrgName.getText();
		/*
		 * if( contactAdded.contains(contactData) && (orgAdded.contains(OrgName))) {
		 * System.out.println("Contact Created along with Organization :\t"
		 * +contactAdded+"\n Organization selected :\t"+orgAdded); } else {
		 * System.out.println("Contact Created"); }
		 */
		
		
		Assert.assertEquals(contactAdded,contactData, "Assert failed ...Contacts looks different");
		System.out.println("Assertion Success....Contact Created :" + contactAdded);
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(orgAdded,OrgName, "Asssertion failed ....Orgnization name actual and expected values are different");
		
		System.out.println("Assertion Success....Organization Created :" +orgAdded);
		sa.assertAll();
		
	}


}
