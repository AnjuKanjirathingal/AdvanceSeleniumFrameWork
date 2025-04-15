package VtigerProductsUsingUtilities;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;

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
import Object_Repository.CreateProductPOM;
import Object_Repository.HomePage;
import Object_Repository.LoginPage;
import Object_Repository.ProductMenuPOM;
import Object_Repository.ProductValidationPOM;

public class CreateAndDeleteProductFromTheProductList {

	public static void main(String[] args) throws Throwable {
		WebDriver driver;

		File_Utility commonPropertyFileValues = new File_Utility();
		WebDriverUtility driverUtility = new WebDriverUtility();
		ExcelTestDataUtility testData = new ExcelTestDataUtility();
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
			mainMenu.signOutVtiger();
			driver.quit();
	}

}
