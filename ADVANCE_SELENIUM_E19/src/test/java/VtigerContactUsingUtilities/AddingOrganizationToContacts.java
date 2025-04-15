package VtigerContactUsingUtilities;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import GenericUtility.ExcelTestDataUtility;
import GenericUtility.File_Utility;
import GenericUtility.Java_Utility;
import GenericUtility.WebDriverUtility;
import Object_Repository.ContactMenuPOM;
import Object_Repository.CreateContactPOM;
import Object_Repository.CreateContactValidationPOM;
import Object_Repository.HomePage;
import Object_Repository.LoginPage;
import Object_Repository.SwitchingWindowPageToselectPOM;

public class AddingOrganizationToContacts 
{

	public static void main(String[] args) throws Throwable
	{
		WebDriver driver;

		WebDriverUtility driverUtility= new WebDriverUtility();
		ExcelTestDataUtility testData= new ExcelTestDataUtility();
		File_Utility commonPropertyFileValues=new File_Utility();
		Java_Utility randomUtilities = new Java_Utility();
		
		
		String Browser=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("browser");
		String VtigerURL=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("url");
		String VtigerUsername=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("username");
		String VtigerPassword=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("password");
		
		if(Browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		} else if (Browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (Browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		
			driver = new ChromeDriver();
			driver.get(VtigerURL);
			driverUtility.maximizeWindow(driver);
			driverUtility.waitElementsToLoad(driver);
			
			LoginPage login=new LoginPage(driver);
			HomePage mainMenu=new HomePage(driver);
			ContactMenuPOM contactMenu= new ContactMenuPOM(driver);
			CreateContactPOM newContact=new CreateContactPOM(driver);
			CreateContactValidationPOM validate=new CreateContactValidationPOM(driver);
			SwitchingWindowPageToselectPOM switchingWIndow=new SwitchingWindowPageToselectPOM(driver);
			
			login.loginIntoVtiger(VtigerUsername, VtigerPassword);
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
				 mainMenu.signOutVtiger();
			driver.quit();
	
	}
	
}
