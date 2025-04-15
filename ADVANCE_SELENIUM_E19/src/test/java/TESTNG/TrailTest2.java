package TESTNG;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
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

public class TrailTest2  extends TestNgBaseClass
{
	@Test
	public void printMyStatement() {
		/*
		 * int i=1/0; it is arithamatical exception which we are not going to handle it
		 * for now this will lead login() to get failed and as a result , the other two
		 * methods get skipped since it is dependant on login()
		 */
		System.out.println("test my TESTNG");

	}
	@Test(priority = -1)
	public static void logIn() 
	{
		int i=1/0; /*it is arithamatical exception which we are not going to handle it for now
				   this will lead login() to get failed and as a result ,
				   the other two methods get skipped since it is dependant on login()*/ 
		Reporter.log("Log In ",true);
		
	}
	
	@Test(priority = 0,dependsOnMethods = { "logIn","printMyStatement"})

	public static void addToCart() 
	{
		Reporter.log("Item Added To cart ",true);
	}

	@Test(priority = 1,dependsOnMethods = { "logIn","printMyStatement"})
	public static void logOut() 
	{
		Reporter.log("Log Out ",true);
	}

	@Test
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
				switchingWIndow.dynamicXpathForOrganizationNameToContact(driver, searchOrganizationName);
				
		      	driver.switchTo().window(parentId);
			    
		      	 newContact.saveContactDetails();
		         validate.validateContactLastNameWithOrgName(driver, lastName, searchOrganizationName);
				// mainMenu.signOutVtiger();

	}


}
