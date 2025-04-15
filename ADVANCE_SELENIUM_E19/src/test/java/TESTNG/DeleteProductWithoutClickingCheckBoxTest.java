package TESTNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import GenericUtility.ExcelTestDataUtility;
import GenericUtility.Java_Utility;
import GenericUtility.TestNgBaseClass;
import GenericUtility.WebDriverUtility;
import Object_Repository.CreateProductPOM;
import Object_Repository.HomePage;
import Object_Repository.ProductMenuPOM;
import Object_Repository.ProductValidationPOM;
@Listeners(GenericUtility.ExtentReportImplementation.class)
@Test(retryAnalyzer =  GenericUtility.RetryListenerImplimentation.class)
public class DeleteProductWithoutClickingCheckBoxTest extends TestNgBaseClass {

	public void deleteProductWithoutClickingCheckBoxTest() throws Throwable
	{
		WebDriverUtility driverUtility=new WebDriverUtility();
		ExcelTestDataUtility testData= new ExcelTestDataUtility();
		Java_Utility randomUtility = new Java_Utility();
		
			HomePage mainMenu=new HomePage(driver);
			ProductMenuPOM prdctMenu=new ProductMenuPOM(driver);
			CreateProductPOM newPrdct=new CreateProductPOM(driver);
			ProductValidationPOM validate=new ProductValidationPOM(driver);
	
			Thread.sleep(4000);

			mainMenu.clickOnProductsMenu();
			prdctMenu.createProducts();
			int ranNUm=randomUtility.getRandomNum();
			String prdctName = testData.getExcelData("VtigerProducts", 0, 0)+ranNUm;
			newPrdct.productDetailsToBeSubmitted(prdctName);
			newPrdct.saveProductDetails();
			validate.productDetailsValidation(driver, prdctName);
			driver.findElement(By.linkText("Products")).click();

			WebElement del = driver.findElement(By.xpath("//table[@class=\"lvt small\"]//a[text()='"+ prdctName + "']/../following-sibling::td//a[text()='del']"));
			del.click();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			driver.findElement(By.linkText("Products")).click();

			validate.productDeletionValidation(driver, prdctName);
		}

}
