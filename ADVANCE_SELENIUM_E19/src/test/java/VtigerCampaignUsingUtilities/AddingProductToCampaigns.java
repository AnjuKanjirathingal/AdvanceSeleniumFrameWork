package VtigerCampaignUsingUtilities;

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

import GenericUtility.ExcelTestDataUtility;
import GenericUtility.File_Utility;
import GenericUtility.Java_Utility;
import GenericUtility.WebDriverUtility;
import Object_Repository.CampaignMenu;
import Object_Repository.CreateCampaign;
import Object_Repository.CreateCampaignValidation;
import Object_Repository.HomePage;
import Object_Repository.LoginPage;
import Object_Repository.SwitchingWindowPageToselectPOM;

public class AddingProductToCampaigns {

	public static void main(String[] args) throws Throwable {
		WebDriver driver;

		WebDriverUtility driverUtility = new WebDriverUtility();
		ExcelTestDataUtility testData = new ExcelTestDataUtility();
		File_Utility commonPropertyFileValues = new File_Utility();
		Java_Utility randomUtility = new Java_Utility();

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
		
			driver = new ChromeDriver();
			driver.get(VtigerURL);
			driverUtility.maximizeWindow(driver);
			driverUtility.waitElementsToLoad(driver);

			LoginPage login = new LoginPage(driver);
			HomePage mainMenu = new HomePage(driver);
			CampaignMenu campMenu = new CampaignMenu(driver);
			CreateCampaign newCamp = new CreateCampaign(driver);
			CreateCampaignValidation validate = new CreateCampaignValidation(driver);
			SwitchingWindowPageToselectPOM switchingWIndow=new SwitchingWindowPageToselectPOM(driver);

			login.loginIntoVtiger(VtigerUsername, VtigerPassword);
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

			validate.validateCampaignName(driver, cmpgName);

			mainMenu.signOutVtiger();
			driver.quit();
	}

}
