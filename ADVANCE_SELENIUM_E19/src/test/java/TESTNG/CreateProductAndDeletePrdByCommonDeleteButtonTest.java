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
import Object_Repository.CreateProductPOM;
import Object_Repository.DeleteProductPOM;
import Object_Repository.HomePage;
import Object_Repository.LoginPage;
import Object_Repository.ProductMenuPOM;
import Object_Repository.ProductValidationPOM;

@Listeners(GenericUtility.ExtentReportImplementation.class)

@Test(retryAnalyzer =  GenericUtility.RetryListenerImplimentation.class)

public class CreateProductAndDeletePrdByCommonDeleteButtonTest extends TestNgBaseClass {

	public void createProductAndDeletePrdByCommonDeleteButtonTest() throws Throwable 
	{
		WebDriverUtility driverUtility=new WebDriverUtility();
		ExcelTestDataUtility testData= new ExcelTestDataUtility();
		Java_Utility randomUtility=new Java_Utility();
		
		HomePage mainMenu=new HomePage(driver);
		ProductMenuPOM prdctMenu=new ProductMenuPOM(driver);
		CreateProductPOM newPrdct=new CreateProductPOM(driver);
		ProductValidationPOM validate=new ProductValidationPOM(driver);
		DeleteProductPOM  delete=new DeleteProductPOM(driver);
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
		
	}

}
