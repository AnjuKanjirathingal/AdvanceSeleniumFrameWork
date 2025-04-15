package VtigerPRODUCT;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateProduct
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
			driver.get(VtigerURL);
			
				driver.findElement(By.name("user_name")).sendKeys(VtigerUsername);
				driver.findElement(By.name("user_password")).sendKeys(VtigerPassword);
				driver.findElement(By.id(VtigerBtnLogin)).click();
				Thread.sleep(4000);
				driver.findElement(By.linkText("Products")).click();
				//driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[2]/table/tbody/tr/td[12]/a")).click();
				driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			
				  // step1: path connection
			    FileInputStream fis = new FileInputStream("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Advanced selenium\\TestFolders/testData.xlsx");
			    // step2: keeps the workbook ready in read mode
			    Workbook book = WorkbookFactory.create(fis);
			    // step3: Navigating expected sheet
			 
				Sheet sheet = book.getSheet("VtigerProducts");
			
				//step4:- Navigating expected row--->row value starts from 0
				Row row = sheet.getRow(0);
				
				//step5:- Navigating expected cell--->cell value starts from 0
				Cell cell = row.getCell(0);
				
				String prdctName = cell.getStringCellValue();
				
				driver.findElement(By.name("productname")).sendKeys(prdctName);
				//driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(prdctName);
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				String productNameAdded=driver.findElement(By.xpath("//span[@id='dtlview_Product Name']")).getText();
				
				if(productNameAdded.contains(prdctName))
				{
					System.out.println("Product Name is added");
				} else {
					System.out.println("Product Name is not added");
				}
				driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
				driver.findElement(By.linkText("Sign Out")).click();
				
			Thread.sleep(1500);
			driver.quit();
	    }
			
		
	}


}
