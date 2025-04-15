package TESTNG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

@Listeners(GenericUtility.ExtentReportImplementation.class)
public class DataProviderUsingHardCodingMethodEx2
{
	
	@Test(dataProvider = "getData",retryAnalyzer =  GenericUtility.RetryListenerImplimentation.class)
	public void createVtigerContact(String firstName,String lastName) throws Throwable
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
			
			driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();
			driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

			WebElement drpdwnFirstName = driver.findElement(By.xpath("//select[@name='salutationtype']"));
			Select dropdown = new Select(drpdwnFirstName);
			dropdown.selectByValue("Mrs.");
			
			driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
			driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);

			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
			driver.findElement(By.linkText("Sign Out")).click();
			
			driver.quit();
		
	}

	@DataProvider
	public Object[][] getData()
	{
		
		Object objLocationArray[][]=new Object[4][2];
		
		objLocationArray[0][0]="Anju";
		objLocationArray[0][1]="Binoy";
		
		
		objLocationArray[1][0]="Anu";
		objLocationArray[1][1]="Steve";
		
		
		objLocationArray[2][0]="Shalet";
		objLocationArray[2][1]="savio";
		
		objLocationArray[3][0]="neetal";
		objLocationArray[3][1]="paul";
		
		return objLocationArray;
		
	}

}
