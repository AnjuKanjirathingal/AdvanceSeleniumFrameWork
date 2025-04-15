package VtigerPRODUCT;

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

public class CreateProductAndDeletePrdByCommonDeleteButton 
{
	public static void main(String[] args) throws Throwable {
		File_Utility commonPropertyFileValues=new File_Utility();
		WebDriverUtility driverUtility=new WebDriverUtility();
		ExcelTestDataUtility testData= new ExcelTestDataUtility();
		
		
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
		driver.findElement(By.name("user_name")).sendKeys(VtigerUsername);
		driver.findElement(By.name("user_password")).sendKeys(VtigerPassword);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.cssSelector("[alt=\"Create Product...\"]")).click();

		Java_Utility jib = new Java_Utility();
		int ranNum = jib.getRandomNum();
		
		String prdName = testData.getExcelData("VtigerProducts", 0, 0)+ranNum;
		System.out.println(prdName);

		driver.findElement(By.name("productname")).sendKeys(prdName);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String actData = driver.findElement(By.xpath("//span[@id='dtlview_Product Name']")).getText();

		if (actData.contains(prdName)) {
			System.out.println("Product Name is created");
		} else {
			System.out.println("Product Name is not created");
		}

		driver.findElement(By.linkText("Products")).click();
		//table[@class="lvt small"]//a[text()='Product Name']/../preceding-sibling::td//input[@type="checkbox"]
		driver.findElement(By.xpath("//table[@class=\"lvt small\"]//a[text()='"+prdName+"']/../preceding-sibling::td//input[@type=\"checkbox\"]")).click();
		driver.findElement(By.xpath("//input[@value=\"Delete\"]")).click();
		
		driver.switchTo().alert().accept();
		
		List<WebElement> allNames = driver.findElements(By.xpath("(//table[@class='lvt small']/tbody/tr//td[3])[position()>1]"));
		
		boolean flag=false;
		for (WebElement name : allNames)
		{
			String actPrd = name.getText();
			if(actPrd.equals(prdName))
			{
			flag=true;
			break;
		}
			}
		if(flag)
		{
			System.out.println("product name is deleted");
		}
		else
		{
			System.out.println("Product name is not deleted");
		}
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}
