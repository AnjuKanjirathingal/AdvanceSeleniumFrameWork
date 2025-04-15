package VtigerProductsUsingUtilities;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
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

public class DeleteProductWithoutClickingCheckBox {

	public static void main(String[] args) throws Throwable {

		WebDriver driver;
		File_Utility commonPropertyFileValues=new File_Utility();
		WebDriverUtility driverUtility=new WebDriverUtility();
		ExcelTestDataUtility testData= new ExcelTestDataUtility();
		Java_Utility randomUtility = new Java_Utility();
		
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
			mainMenu.signOutVtiger();
			driver.quit();
	}

}
