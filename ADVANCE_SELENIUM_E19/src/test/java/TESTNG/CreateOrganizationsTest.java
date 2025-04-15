package TESTNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtility.ExcelTestDataUtility;
import GenericUtility.File_Utility;
import GenericUtility.Java_Utility;
import GenericUtility.TestNgBaseClass;
import GenericUtility.WebDriverUtility;
import Object_Repository.CreateOrganizationPOM;
import Object_Repository.HomePage;
import Object_Repository.LoginPage;
import Object_Repository.OrganizationMenuPOM;
import Object_Repository.OrganizationValidationPOM;


@Listeners(GenericUtility.ExtentReportImplementation.class)
@Test(groups = "sanityTest",retryAnalyzer =  GenericUtility.RetryListenerImplimentation.class)
public class CreateOrganizationsTest extends TestNgBaseClass {
	
	public void createOrganizationsTest() throws Throwable 
	{
		WebDriverUtility driverUtility=new WebDriverUtility();
		ExcelTestDataUtility testdata = new ExcelTestDataUtility();
		Java_Utility randomUtility=new Java_Utility();
		
			HomePage mainMenu=new HomePage(driver);
			OrganizationMenuPOM orgMenu=new OrganizationMenuPOM(driver);
			CreateOrganizationPOM newOrg=new CreateOrganizationPOM(driver);
			OrganizationValidationPOM validate=new OrganizationValidationPOM(driver);
			
			mainMenu.clickOnOrganizationMenu();
			orgMenu.createOrganizations();
			
				int ranNum=randomUtility.getRandomNum();
				String orgnizationName = testdata.getExcelDataUsingFormatter("vtigerOrganizations", 0, 0)+ranNum;
				String orgPhone=testdata.getExcelDataUsingFormatter("vtigerOrganizations", 0, 1);
				String orgEmail=testdata.getExcelDataUsingFormatter("vtigerOrganizations", 0, 2);
				
				newOrg.organizationDetailsToSubmit(orgnizationName, orgPhone, orgEmail);
				newOrg.saveOrganizationDetails();
				
				validate.organisationDetailsSoftandHardAssertValidation(driver, orgnizationName, orgPhone, orgEmail);
	}

}
