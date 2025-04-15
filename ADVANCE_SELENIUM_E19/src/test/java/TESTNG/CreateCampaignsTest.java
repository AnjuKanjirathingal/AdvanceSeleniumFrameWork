package TESTNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtility.ExcelTestDataUtility;
import GenericUtility.File_Utility;
import GenericUtility.TestNgBaseClass;
import GenericUtility.WebDriverUtility;
import Object_Repository.CampaignMenu;
import Object_Repository.CreateCampaign;
import Object_Repository.CreateCampaignValidation;
import Object_Repository.HomePage;
import Object_Repository.LoginPage;
//@Listeners(GenericUtility.ListenerImplimentation.class)
@Listeners(GenericUtility.ExtentReportImplementation.class)
public class CreateCampaignsTest extends TestNgBaseClass
{
	@Test(groups = "smokeTest",retryAnalyzer =  GenericUtility.RetryListenerImplimentation.class)

	// @Test(groups = "smokeTest",retryAnalyzer =  GenericUtility.RetryListenerImplimentation.class).retry wont work with group execution
	public void createCampaignsTest() throws Throwable
	{
		WebDriverUtility driverUtility = new WebDriverUtility();
		ExcelTestDataUtility testData = new ExcelTestDataUtility();
		HomePage mainMenu = new HomePage(driver);
		CampaignMenu campMenu = new CampaignMenu(driver);
		CreateCampaign newCamp = new CreateCampaign(driver);
		CreateCampaignValidation validate = new CreateCampaignValidation(driver);
		mainMenu.clickOnCampaignMenu();
		campMenu.createCampaign();
		String cmpgName = testData.getExcelData("VtigerCampaigns", 1, 0);
		newCamp.campaignDetailsToPass(cmpgName);
		newCamp.saveCampaignDetails();
		validate.assertValidateCampaignName(driver, cmpgName);
	}
}
