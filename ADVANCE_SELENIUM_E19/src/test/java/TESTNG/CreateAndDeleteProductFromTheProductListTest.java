package TESTNG;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import Object_Repository.HomePage;
import Object_Repository.LoginPage;
import Object_Repository.ProductMenuPOM;
import Object_Repository.ProductValidationPOM;
//@Listeners(GenericUtility.ListenerImplimentation.class)
@Listeners(GenericUtility.ExtentReportImplementation.class)
@Test(retryAnalyzer =  GenericUtility.RetryListenerImplimentation.class)
public class CreateAndDeleteProductFromTheProductListTest extends TestNgBaseClass {

	public void createAndDeleteProductFromTheProductListTest() throws Throwable
	{
		WebDriverUtility driverUtility = new WebDriverUtility();
		ExcelTestDataUtility testData = new ExcelTestDataUtility();
		Java_Utility randomUtility = new Java_Utility();
			HomePage mainMenu=new HomePage(driver);
			ProductMenuPOM prdctMenu=new ProductMenuPOM(driver);
			CreateProductPOM newPrdct=new CreateProductPOM(driver);
			ProductValidationPOM validate=new ProductValidationPOM(driver);
		
			mainMenu.clickOnProductsMenu();
			prdctMenu.createProducts();
			int ranNum=randomUtility.getRandomNum();
			String prdctName = testData.getExcelData("VtigerProducts", 0, 0)+ranNum;
			newPrdct.productDetailsToBeSubmitted(prdctName);
			newPrdct.saveProductDetails();
			validate.productDetailsValidation(driver, prdctName);

			mainMenu.clickOnProductsMenu();
			Thread.sleep(3500);
			
			  // Locating the product list table
			WebElement table =driver.findElement(By.xpath("//table[@class='lvt small']"));
			  
			  // Fetching all rows 
			  List<WebElement> rows = table.findElements(By.tagName("tr"));
			  
			  // Iterate through rows and extract data from the desired column
			  int  prdct_column_index = 2;
			  for (WebElement searchrow : rows)
			  { 
				  List<WebElement>  cells = searchrow.findElements(By.tagName("td")); 
				  if (cells.size() > prdct_column_index)
				  {
					  WebElement searchcell = cells.get(prdct_column_index);
					  String cellText = searchcell.getText();
					  System.out.println("cell selected text" + cellText); 
					  Thread.sleep(3500); 
					  if(cellText.equals(prdctName)) 
					  {
						  System.out.println("product found\t :" +cellText);
					  driver.findElement(By.xpath("//table[@class=\"lvt small\"]//a[text()='" +cellText+ "']/../preceding-sibling::td//input[@type=\"checkbox\"]")).click();
					  WebElement del = driver.findElement(By.xpath("//table[@class=\"lvt small\"]//a[text()='"+cellText + "']/../following-sibling::td//a[text()='del']")); del.click();
					  driver.switchTo().alert().accept();
					  driver.navigate().refresh();
					  }
				  
				  }
			  
			  }
			 
			driver.findElement(By.linkText("Products")).click();
     	   validate.productDeletionValidation(driver,prdctName);
			
	}

}
