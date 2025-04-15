package VtigerCONTACT;

import java.io.FileInputStream;
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
import org.openqa.selenium.support.ui.Select;

public class CreateContacts 
{
	public static void main(String[] args) throws Throwable
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
			    
			    FileInputStream fis = new FileInputStream("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Advanced selenium\\TestFolders/testData.xlsx");			 
			    Workbook book = WorkbookFactory.create(fis);
				Sheet sheet = book.getSheet("VtigerContacts");
				Row row = sheet.getRow(0);
				Cell firstNamecell = row.getCell(0);
				Cell lastNameCell=row.getCell(1);
							
				
				  Random ran = new Random(); int ranNum = ran.nextInt(1000);
				 
				
					/*
					 * Java_Utility randomUtilities=new Java_Utility(); int ranNum=
					 * randomUtilities.getRandomNum();
					 */
			    String firstName=firstNamecell.getStringCellValue();
			    String lastName=lastNameCell.getStringCellValue()+ranNum;
			    
			    driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
			    driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
			    
			    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				Thread.sleep(2002);
				String contactAdded=driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
				
				if(	 contactAdded.contains(lastName)) 
				{
					
	
							System.out.println("Contact Created :\t"+contactAdded);
					
				} 
				else
				{
					System.out.println("Contact Created");
				}
	
				driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
				driver.findElement(By.linkText("Sign Out")).click();
				
			Thread.sleep(1500);
			
			driver.quit();
	}

}
