package TESTNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtility.ExcelTestDataUtility;
import GenericUtility.File_Utility;
import GenericUtility.Java_Utility;
import GenericUtility.TestNgBaseClass;
import GenericUtility.WebDriverUtility;
import Object_Repository.CampaignMenu;
import Object_Repository.CreateCampaign;
import Object_Repository.CreateCampaignValidation;
import Object_Repository.HomePage;
import Object_Repository.LoginPage;
import Object_Repository.SwitchingWindowPageToselectPOM;

//@Listeners(GenericUtility.ListenerImplimentation.class)
@Listeners(GenericUtility.ExtentReportImplementation.class)

@Test(retryAnalyzer =  GenericUtility.RetryListenerImplimentation.class)
public class AddingProductToCampaignsTest extends TestNgBaseClass {

	public void addingProductToCampaignsTest() throws Throwable 
	{
		WebDriverUtility driverUtility = new WebDriverUtility();
		ExcelTestDataUtility testData = new ExcelTestDataUtility();
		Java_Utility randomUtility = new Java_Utility();
			HomePage mainMenu = new HomePage(driver);
			CampaignMenu campMenu = new CampaignMenu(driver);
			CreateCampaign newCamp = new CreateCampaign(driver);
			CreateCampaignValidation validate = new CreateCampaignValidation(driver);
			SwitchingWindowPageToselectPOM switchingWIndow=new SwitchingWindowPageToselectPOM(driver);

			mainMenu.clickOnCampaignMenu();
			
			campMenu.createCampaign();
			String cmpgName = testData.getExcelData("VtigerCampaigns", 3, 0);

			int ranNum = randomUtility.getRandomNum();

			// Data is fetching from excelUility class
			String prdctName = testData.getExcelData("VtigerProducts", 0, 0);
			System.out.println("prdct name is :\t" + prdctName);

			newCamp.campaignDetailsWithProductToPass(cmpgName);
			
			String parentId = driver.getWindowHandle();
			System.out.println("parentId is :\t" + parentId);

			driverUtility.windowSwitching(driver, "Products&action");

			Thread.sleep(2000);

			switchingWIndow.searchProductName(prdctName);
			switchingWIndow.dynamicXpathForProductNameToCampaign(driver, prdctName);
		
			driver.switchTo().window(parentId);

			newCamp.saveCampaignDetails();

			validate.assertValidateCampaignName(driver, cmpgName);

	}

}
