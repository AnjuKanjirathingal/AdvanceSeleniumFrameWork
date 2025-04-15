import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LaunchBrowser {

	public static void main(String[] args) throws InterruptedException, IOException
	{
		/* harcoding
		 * -------------
		 * ChromeDriver driver= new ChromeDriver();
		 * driver.get("https://www.google.com"); driver.manage().window().maximize();
		 * Thread.sleep(4000); driver.quit();
		 */
		
		WebDriver driver;
		
		FileInputStream fis=new FileInputStream("C:\\Users\\binoy\\Downloads\\propertyFileForMAven.txt");
		Properties propertyFile=new Properties();
		propertyFile.load(fis);
		String Browser=propertyFile.getProperty("browser");
		String VtigerURL=propertyFile.getProperty("url");
		String VtigerUsername=propertyFile.getProperty("username");
		String VtigerPassword=propertyFile.getProperty("password");
		String VtigerBtnLogin="submitButton";
		
		
		if(Browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.get(VtigerURL);
			
				driver.findElement(By.name("user_name")).sendKeys(VtigerUsername);
				driver.findElement(By.name("user_password")).sendKeys(VtigerPassword);
				driver.findElement(By.id(VtigerBtnLogin)).click();
			
			Thread.sleep(1500);
			
	    }
     	else if (Browser.equalsIgnoreCase("firefox")) 
     	{
		   driver = new FirefoxDriver();
		   driver.get(VtigerURL);
		   
			    driver.findElement(By.name("user_name")).sendKeys(VtigerUsername);
				driver.findElement(By.name("user_password")).sendKeys(VtigerPassword);
				driver.findElement(By.id(VtigerBtnLogin)).click();
		   
		   Thread.sleep(1500);
	    } 
     	else if (Browser.equalsIgnoreCase("edge"))
     	{
		   driver = new EdgeDriver();
		   driver.get(VtigerURL);
		   
			    driver.findElement(By.name("user_name")).sendKeys(VtigerUsername);
				driver.findElement(By.name("user_password")).sendKeys(VtigerPassword);
				driver.findElement(By.id(VtigerBtnLogin)).click();
		   Thread.sleep(1500);
	    } 
     	else 
     	{
		  driver = new ChromeDriver();
	    }
		driver.quit();
		
	}

}
