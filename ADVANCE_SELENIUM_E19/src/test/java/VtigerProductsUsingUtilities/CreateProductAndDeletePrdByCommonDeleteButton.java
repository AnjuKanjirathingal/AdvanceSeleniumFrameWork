package VtigerProductsUsingUtilities;

import java.util.List;

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
import Object_Repository.CreateProductPOM;
import Object_Repository.DeleteProductPOM;
import Object_Repository.HomePage;
import Object_Repository.LoginPage;
import Object_Repository.ProductMenuPOM;
import Object_Repository.ProductValidationPOM;

public class CreateProductAndDeletePrdByCommonDeleteButton {

	public static void main(String[] args) throws Throwable {
		File_Utility commonPropertyFileValues=new File_Utility();
		WebDriverUtility driverUtility=new WebDriverUtility();
		ExcelTestDataUtility testData= new ExcelTestDataUtility();
		Java_Utility randomUtility=new Java_Utility();
		
		String Browser=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("browser");
		String VtigerURL=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("url");
		String VtigerUsername=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("username");
		String VtigerPassword=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("password");

		WebDriver driver;

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

		driverUtility.maximizeWindow(driver);
		driverUtility.waitElementsToLoad(driver);
		driver.get(VtigerURL);
		LoginPage login=new LoginPage(driver);
		HomePage mainMenu=new HomePage(driver);
		ProductMenuPOM prdctMenu=new ProductMenuPOM(driver);
		CreateProductPOM newPrdct=new CreateProductPOM(driver);
		ProductValidationPOM validate=new ProductValidationPOM(driver);
		DeleteProductPOM  delete=new DeleteProductPOM(driver);
		login.loginIntoVtiger(VtigerUsername, VtigerPassword);
			
		mainMenu.clickOnProductsMenu();
		prdctMenu.createProducts();
		int ranNum=randomUtility.getRandomNum();
		String prdctName = testData.getExcelData("VtigerProducts", 0, 0)+ranNum;
		System.out.println("Product name added..............."+prdctName);
		newPrdct.productDetailsToBeSubmitted(prdctName);
		newPrdct.saveProductDetails();
		validate.productDetailsValidation(driver, prdctName);
		mainMenu.clickOnProductsMenu();
	
		delete.deleteProductAction(driver, prdctName);
		driverUtility.alertAccept(driver);
		delete.validateDeleteProductAction(driver, prdctName);
		
		mainMenu.signOutVtiger();
		driver.quit();
	}



}
