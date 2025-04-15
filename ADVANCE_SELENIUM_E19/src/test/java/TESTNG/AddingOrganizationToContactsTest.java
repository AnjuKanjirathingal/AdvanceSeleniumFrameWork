package TESTNG;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import GenericUtility.ExcelTestDataUtility;
import GenericUtility.Java_Utility;
import GenericUtility.TestNgBaseClass;
import GenericUtility.WebDriverUtility;
import Object_Repository.ContactMenuPOM;
import Object_Repository.CreateContactPOM;
import Object_Repository.CreateContactValidationPOM;
import Object_Repository.HomePage;
import Object_Repository.SwitchingWindowPageToselectPOM;

//@Listeners(GenericUtility.ListenerImplimentation.class)

@Listeners(GenericUtility.ExtentReportImplementation.class)

public class AddingOrganizationToContactsTest extends TestNgBaseClass {

	@Test(retryAnalyzer =  GenericUtility.RetryListenerImplimentation.class)
	public void addingOrganizationToContactsTest() throws Throwable
	{
		WebDriverUtility driverUtility= new WebDriverUtility();
		ExcelTestDataUtility testData= new ExcelTestDataUtility();
		Java_Utility randomUtilities = new Java_Utility();
	
		    HomePage mainMenu=new HomePage(driver);
			ContactMenuPOM contactMenu= new ContactMenuPOM(driver);
			CreateContactPOM newContact=new CreateContactPOM(driver);
			CreateContactValidationPOM validate=new CreateContactValidationPOM(driver);
			SwitchingWindowPageToselectPOM switchingWIndow=new SwitchingWindowPageToselectPOM(driver);
			
			/* login.loginIntoVtiger(VtigerUsername, VtigerPassword); */
			    mainMenu.clickOnContactsMenu();
			    contactMenu.createContacts();
			    
			    WebElement drpdwnFirstName= newContact.getDrpDwnFirstNamePrefix();
			    driverUtility.dropDown(drpdwnFirstName, "Mr.");
				int ranNum=randomUtilities.getRandomNum();
				 
			    String firstName=testData.getExcelData("VtigerContacts", 0, 0);
			    String lastName=testData.getExcelData("VtigerContacts", 0, 1)+ranNum;
			    
			    newContact.contactDetailsWithOrgToSubmit(firstName, lastName);
			    			    
			    String parentId = driver.getWindowHandle();
				System.out.println("parentId is :\t"+parentId);
		       
				driverUtility.windowSwitching(driver, "Products&action");

				String searchOrganizationName = testData.getExcelData("vtigerOrganizations", 0, 0);
				
				switchingWIndow.searchProductName(searchOrganizationName);
				switchingWIndow.dynamicXpathForOrganizationNameToContact(driver,searchOrganizationName);
				
		      	driver.switchTo().window(parentId);
			    
		      	 newContact.saveContactDetails();
		         validate.hardSoftAssertvalidateContactLastNameWithOrgName(driver, lastName,searchOrganizationName);
				// mainMenu.signOutVtiger();

	}

}
