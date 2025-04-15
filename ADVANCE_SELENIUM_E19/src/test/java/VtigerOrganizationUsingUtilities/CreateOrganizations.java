package VtigerOrganizationUsingUtilities;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import GenericUtility.ExcelTestDataUtility;
import GenericUtility.File_Utility;
import GenericUtility.Java_Utility;
import GenericUtility.WebDriverUtility;
import Object_Repository.CreateOrganizationPOM;
import Object_Repository.HomePage;
import Object_Repository.LoginPage;
import Object_Repository.OrganizationMenuPOM;
import Object_Repository.OrganizationValidationPOM;

public class CreateOrganizations 
{

	public static void main(String[] args) throws Throwable
	{
		WebDriver driver;
		
		File_Utility commonPropertyFileValues=new File_Utility();
		WebDriverUtility driverUtility=new WebDriverUtility();
		ExcelTestDataUtility testdata = new ExcelTestDataUtility();
		Java_Utility randomUtility=new Java_Utility();
		
		String Browser=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("browser");
		String VtigerURL=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("url");
		String VtigerUsername=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("username");
		String VtigerPassword=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("password");

		if(Browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.get(VtigerURL);
			driverUtility.maximizeWindow(driver);
			driverUtility.waitElementsToLoad(driver);
			
			LoginPage login=new LoginPage(driver);
			HomePage mainMenu=new HomePage(driver);
			OrganizationMenuPOM orgMenu=new OrganizationMenuPOM(driver);
			CreateOrganizationPOM newOrg=new CreateOrganizationPOM(driver);
			OrganizationValidationPOM validate=new OrganizationValidationPOM(driver);
			
			login.loginIntoVtiger(VtigerUsername, VtigerPassword);
			mainMenu.clickOnOrganizationMenu();
			orgMenu.createOrganizations();
			
				int ranNum=randomUtility.getRandomNum();
				String orgnizationName = testdata.getExcelDataUsingFormatter("vtigerOrganizations", 0, 0)+ranNum;
				String orgPhone=testdata.getExcelDataUsingFormatter("vtigerOrganizations", 0, 1);
				String orgEmail=testdata.getExcelDataUsingFormatter("vtigerOrganizations", 0, 2);
				
				newOrg.organizationDetailsToSubmit(orgnizationName, orgPhone, orgEmail);
				newOrg.saveOrganizationDetails();
				
				validate.organisationDetailsValidation(driver, orgnizationName, orgPhone, orgEmail);
				
				mainMenu.signOutVtiger();
			driver.quit();
			
	    }
	}
}
