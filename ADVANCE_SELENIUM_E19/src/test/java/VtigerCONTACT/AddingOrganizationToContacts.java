package VtigerCONTACT;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

public class AddingOrganizationToContacts 
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
				 * Java_Utility randomUtility=new Java_Utility(); int
				 * ranNum=randomUtility.getRandomNum();
				 */
			    String firstName=firstNamecell.getStringCellValue();
			    String lastName=lastNameCell.getStringCellValue()+ranNum;
			    
			    driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
			    driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
			    
			    driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
			    
			    String parentId = driver.getWindowHandle();
				System.out.println("parentId is :\t"+parentId);
		       	Set<String>  windowId = driver.getWindowHandles();
		       	
		       	for(String i : windowId)
				{
					Thread.sleep(3000);
					driver.switchTo().window(i);
					String title=driver.getTitle();
					System.out.println("Title of the current window :"+title);
					if(title.contains("Products&action"))
					{
						break;
					}
					Thread.sleep(2500);
				
				}
		       	
		       	Sheet sheet1 = book.getSheet("vtigerOrganizations");
				Row row1 = sheet1.getRow(0);
				Cell cell1 = row1.getCell(0);
				
				String searchContactName = cell1.getStringCellValue();
				
		    	driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(searchContactName);
		       	driver.findElement(By.xpath("//input[@name='search']")).click();
		        WebElement contactSelected=driver.findElement(By.xpath("//a[text()='" + searchContactName + "']"));
		        String selectedContact=contactSelected.getText();
	   		    contactSelected.click();
	 		      
		      	driver.switchTo().window(parentId);
			    
			    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				Thread.sleep(2002);
				String contactAdded=driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
				String orgAdded=driver.findElement(By.id("mouseArea_Organization Name")).getText();
				if(	 contactAdded.contains(lastName) && (orgAdded.contains(selectedContact)))
				{
					
	
					System.out.println("Contact Created :"+contactAdded+"\n Organization selected :"+orgAdded);
					
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
