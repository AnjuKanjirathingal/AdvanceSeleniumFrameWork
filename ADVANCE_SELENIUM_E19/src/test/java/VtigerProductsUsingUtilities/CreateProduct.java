package VtigerProductsUsingUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import GenericUtility.ExcelTestDataUtility;
import GenericUtility.File_Utility;
import GenericUtility.WebDriverUtility;
import Object_Repository.CreateProductPOM;
import Object_Repository.HomePage;
import Object_Repository.LoginPage;
import Object_Repository.ProductMenuPOM;
import Object_Repository.ProductValidationPOM;

public class CreateProduct {

	public static void main(String[] args) throws Throwable
	{
		WebDriver driver;
		
		File_Utility commonPropertyFileValues=new File_Utility();
		WebDriverUtility driverUtility=new WebDriverUtility();
		ExcelTestDataUtility testData= new ExcelTestDataUtility();
		
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

			driver.get(VtigerURL);
			driverUtility.maximizeWindow(driver);
			driverUtility.waitElementsToLoad(driver);
			
			LoginPage login=new LoginPage(driver);
			HomePage mainMenu=new HomePage(driver);
			ProductMenuPOM prdctMenu=new ProductMenuPOM(driver);
			CreateProductPOM newPrdct=new CreateProductPOM(driver);
			ProductValidationPOM validate=new ProductValidationPOM(driver);
		
			login.loginIntoVtiger(VtigerUsername, VtigerPassword);
				
			mainMenu.clickOnProductsMenu();
			prdctMenu.createProducts();
			
			String prdctName = testData.getExcelData("VtigerProducts", 0, 0);
			newPrdct.productDetailsToBeSubmitted(prdctName);
			newPrdct.saveProductDetails();
			validate.productDetailsValidation(driver, prdctName);

			mainMenu.signOutVtiger();
			driver.quit();
	}

}
