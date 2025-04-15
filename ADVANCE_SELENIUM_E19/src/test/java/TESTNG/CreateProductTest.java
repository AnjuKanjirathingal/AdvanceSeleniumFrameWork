package TESTNG;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtility.ExcelTestDataUtility;
import GenericUtility.TestNgBaseClass;
import GenericUtility.WebDriverUtility;
import Object_Repository.CreateProductPOM;
import Object_Repository.HomePage;
import Object_Repository.ProductMenuPOM;
import Object_Repository.ProductValidationPOM;


@Listeners(GenericUtility.ExtentReportImplementation.class)



@Test(groups = "sanityTest",retryAnalyzer =  GenericUtility.RetryListenerImplimentation.class)
public class CreateProductTest extends TestNgBaseClass {

	public void createProductTest() throws Throwable
	{	
		WebDriverUtility driverUtility=new WebDriverUtility();
		ExcelTestDataUtility testData= new ExcelTestDataUtility();
		
			HomePage mainMenu=new HomePage(driver);
			ProductMenuPOM prdctMenu=new ProductMenuPOM(driver);
			CreateProductPOM newPrdct=new CreateProductPOM(driver);
			ProductValidationPOM validate=new ProductValidationPOM(driver);
		
			mainMenu.clickOnProductsMenu();
			prdctMenu.createProducts();
			
			String prdctName = testData.getExcelData("VtigerProducts", 0, 0);
			newPrdct.productDetailsToBeSubmitted(prdctName);
			newPrdct.saveProductDetails();
			validate.productDetailsAssertValidation(driver, prdctName);

	}

}
