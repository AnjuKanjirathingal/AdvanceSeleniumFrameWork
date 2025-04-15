package TESTNG;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtility.ExcelTestDataUtility;

@Listeners(GenericUtility.ExtentReportImplementation.class)
public class DataProviderUsingExcelTestData 
{
	@Test(dataProvider="getExcelData",retryAnalyzer =  GenericUtility.RetryListenerImplimentation.class)
	public void createVtigerContactFromExcelTestDataProviderMethod(String firstName,String lastName) throws Throwable
	{
		WebDriver driver;
		FileInputStream fis1 = new FileInputStream("./src/test/resources/propertyFileForVtiger.properties");
		Properties propertyFile = new Properties();
		propertyFile.load(fis1);
		
		String Browser=propertyFile.getProperty("browser");
		String VtigerURL=propertyFile.getProperty("url");
		String VtigerUsername=propertyFile.getProperty("username");
		String VtigerPassword=propertyFile.getProperty("password");
		String VtigerBtnLogin="submitButton";
		
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
			driver.manage().window().maximize();
			
				driver.findElement(By.name("user_name")).sendKeys(VtigerUsername);
				driver.findElement(By.name("user_password")).sendKeys(VtigerPassword);
				driver.findElement(By.id(VtigerBtnLogin)).click();
				
				Thread.sleep(4000);
				
			    driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();
			    driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			    
			    WebElement drpdwnFirstName=driver.findElement(By.xpath("//select[@name='salutationtype']"));
			    Select dropdown = new Select(drpdwnFirstName);
			    dropdown.selectByValue("Mrs.");
			  
			    driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
			    driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
			    
			    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				Thread.sleep(2002);
	
				driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
				driver.findElement(By.linkText("Sign Out")).click();
				
			Thread.sleep(1500);
			
			driver.quit();
	}
	
	@DataProvider
	public Object[][] getExcelData() throws Throwable
	{
	
		ExcelTestDataUtility testdatProvider = new ExcelTestDataUtility();
		Object[][] data = testdatProvider.readDataUsingDataProvider("DataProviderContactCreation");
		return data;
	}
	
}
