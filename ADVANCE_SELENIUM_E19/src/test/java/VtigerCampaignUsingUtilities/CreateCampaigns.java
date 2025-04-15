package VtigerCampaignUsingUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import GenericUtility.ExcelTestDataUtility;
import GenericUtility.File_Utility;
import GenericUtility.WebDriverUtility;
import Object_Repository.CampaignMenu;
import Object_Repository.CreateCampaign;
import Object_Repository.CreateCampaignValidation;
import Object_Repository.HomePage;
import Object_Repository.LoginPage;

public class CreateCampaigns {

	public static void main(String[] args) throws Throwable {
		WebDriver driver;
		WebDriverUtility driverUtility = new WebDriverUtility();
		ExcelTestDataUtility testData = new ExcelTestDataUtility();
		File_Utility commonPropertyFileValues = new File_Utility();

		String Browser = commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("browser");
		String VtigerURL = commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("url");
		String VtigerUsername = commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("username");
		String VtigerPassword = commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("password");

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
			driver.get(VtigerURL);
			driverUtility.maximizeWindow(driver);
			driverUtility.waitElementsToLoad(driver);

			LoginPage login = new LoginPage(driver);
			HomePage mainMenu = new HomePage(driver);
			CampaignMenu campMenu = new CampaignMenu(driver);
			CreateCampaign newCamp = new CreateCampaign(driver);
			CreateCampaignValidation validate = new CreateCampaignValidation(driver);

			login.loginIntoVtiger(VtigerUsername, VtigerPassword);
			mainMenu.clickOnCampaignMenu();

			campMenu.createCampaign();
			String cmpgName = testData.getExcelData("VtigerCampaigns", 1, 0);

			newCamp.campaignDetailsToPass(cmpgName);
			newCamp.saveCampaignDetails();
			validate.validateCampaignName(driver, cmpgName);

			mainMenu.signOutVtiger();
			driver.quit();
	}
}
