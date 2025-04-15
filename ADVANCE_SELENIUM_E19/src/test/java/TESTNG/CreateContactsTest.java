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


@Listeners(GenericUtility.ExtentReportImplementation.class)
@Test(groups = "regressionTest",retryAnalyzer =  GenericUtility.RetryListenerImplimentation.class)
public class CreateContactsTest  extends TestNgBaseClass{

	public void  createContactsTest() throws Throwable 
	{
		WebDriverUtility driverUtility= new WebDriverUtility();
		ExcelTestDataUtility testData= new ExcelTestDataUtility();
		Java_Utility randomUtilities = new Java_Utility();
			HomePage mainMenu=new HomePage(driver);
			ContactMenuPOM contactMenu= new ContactMenuPOM(driver);
			CreateContactPOM newContact=new CreateContactPOM(driver);
			CreateContactValidationPOM validate=new CreateContactValidationPOM(driver);
			
			Thread.sleep(4000);

			mainMenu.clickOnContactsMenu();
			contactMenu.createContacts();
			
			 WebElement drpdwnFirstName = newContact.getDrpDwnFirstNamePrefix();
			 driverUtility.dropDown(drpdwnFirstName, "Mr.");
			 int ranNum = randomUtilities.getRandomNum();

			String firstName =testData.getExcelData("VtigerContacts", 0, 0);
			String lastName = testData.getExcelData("VtigerContacts",0, 1) + ranNum;
            System.out.println("lastname generated "+lastName);
            
            newContact.contactDetailsToSubmit(firstName, lastName);
            newContact.saveContactDetails();
            validate.assertValidateContactLastName(driver, lastName);

	}

}
