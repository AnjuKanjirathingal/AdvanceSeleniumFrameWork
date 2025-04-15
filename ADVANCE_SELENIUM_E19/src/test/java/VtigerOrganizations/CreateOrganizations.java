package VtigerOrganizations;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import GenericUtility.Java_Utility;

public class CreateOrganizations 
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
			driver = new ChromeDriver();
			driver.get(VtigerURL);
			driver.manage().window().maximize();
			
				driver.findElement(By.name("user_name")).sendKeys(VtigerUsername);
				driver.findElement(By.name("user_password")).sendKeys(VtigerPassword);
				driver.findElement(By.id(VtigerBtnLogin)).click();
				
				Thread.sleep(4000);
				
			    driver.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index']")).click();
				//driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			    driver.findElement(By.xpath(" //img [@alt= 'Create Organization...']")).click();
				 
			    FileInputStream fis = new FileInputStream("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Advanced selenium\\TestFolders/testData.xlsx");			 
			    Workbook book = WorkbookFactory.create(fis);
				Sheet sheet = book.getSheet("vtigerOrganizations");
				Row row = sheet.getRow(0);
				Cell namecell = row.getCell(0);
				Cell phnCell=row.getCell(1);
				Cell emailCell=row.getCell(2);
				
				
				  Random ran = new Random(); int ranNum = ran.nextInt(1000);
				 
				
				/*
				 * Java_Utility randomUtility=new Java_Utility(); int
				 * ranNum=randomUtility.getRandomNum();
				 */
				
				String orgnizationName = namecell.getStringCellValue()+ranNum;
				
				DataFormatter dataFormat=new DataFormatter();
				
				//String orgPhone=phnCell.getStringCellValue();
				String orgPhone=dataFormat.formatCellValue(phnCell);
				String orgEmail=emailCell.getStringCellValue();
				driver.findElement(By.xpath("//input [@name='accountname']")).sendKeys(orgnizationName);
				driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(orgPhone);
				driver.findElement(By.xpath("//input[@id='email1']")).sendKeys(orgEmail);
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				Thread.sleep(2002);
				String orgNameAdded=driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
				String PhnAdded =driver.findElement(By.xpath("//span[@id='dtlview_Phone']")).getText();
				String EmailAdded=driver.findElement(By.xpath("//td[@id='mouseArea_Email']")).getText();
				if(	( (orgNameAdded.contains(orgnizationName) ) &&  (EmailAdded.contains(orgEmail)) && (PhnAdded.contains(orgPhone)) ))
				{
					
	
							System.out.println("Details as below : \n Organization:\t"+orgNameAdded+"\n email :\t"+EmailAdded+"\n phoneAdded :\t"+PhnAdded);
					
				} 
				else
				{
					System.out.println("Organization details not added");
				}
				
				
				driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
				driver.findElement(By.linkText("Sign Out")).click();
				
			Thread.sleep(1500);
			
			driver.quit();
	}
}
