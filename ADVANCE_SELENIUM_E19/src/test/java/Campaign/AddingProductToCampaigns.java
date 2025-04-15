package Campaign;

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

import GenericUtility.Java_Utility;

public class AddingProductToCampaigns 
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
			driver.manage().window().maximize();
			
				driver.findElement(By.name("user_name")).sendKeys(VtigerUsername);
				driver.findElement(By.name("user_password")).sendKeys(VtigerPassword);
				driver.findElement(By.id(VtigerBtnLogin)).click();
				
				Thread.sleep(4000);
				
			    driver.findElement(By.xpath("//a[text()='More']")).click();
				//driver.findElement(By.xpath("//a[@href='index.php?module=Campaigns&action=index']")).click();
				
				//driver.findElement(By.linkText("Campaigns")).click();
				driver.findElement(By.xpath("//a[@href='index.php?module=Campaigns&action=index']")).click();
				driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			
				  // step1: path connection
			    FileInputStream fis = new FileInputStream("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Advanced selenium\\TestFolders/testData.xlsx");
			    // step2: keeps the workbook ready in read mode
			    Workbook book = WorkbookFactory.create(fis);
			    // step3: Navigating expected sheet
			 
				Sheet sheet = book.getSheet("VtigerCampaigns");
			
				//step4:- Navigating expected row--->row value starts from 0
				Row row = sheet.getRow(0);
				
				//step5:- Navigating expected cell--->cell value starts from 0
				Cell cell = row.getCell(0);
				
				String cmpgName = cell.getStringCellValue();
				
				//for handling duplicate product here we use random class to generate unique productname 
				
				
				  Random ran = new Random(); int ranNum = ran.nextInt(1000);
				 
				
				/*
				 * Java_Utility randomUtility=new Java_Utility(); int
				 * ranNum=randomUtility.getRandomNum();
				 */
				
				Sheet sheet1 = book.getSheet("VtigerProducts");
				Row row1 = sheet1.getRow(0);
				Cell cell1 = row1.getCell(0);
				
				//we can here add the ranNum to the  cell1.getStringCellValue() as "  cell1.getStringCellValue()+ranNum; " if needed
				String prdctName = cell1.getStringCellValue();
				
				driver.findElement(By.name("campaignname")).sendKeys(cmpgName);
				//driver.findElement(By.xpath("//input[@name='campaignname']")).sendKeys(prdctName);
				
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
		       	
		       	driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(prdctName);
		       	driver.findElement(By.xpath("//input[@name='search']")).click();
		        WebElement productSelected=driver.findElement(By.xpath("//a[text()='" + prdctName + "']"));
		        String selectedProduct=productSelected.getText();
	   		    productSelected.click();
	 		      
		      //  driver.close();
				
		       	driver.switchTo().window(parentId);
		       	
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				String cmpgNameAdded=driver.findElement(By.xpath("//span[@id='dtlview_Campaign Name']")).getText();
				
				if(cmpgName.contains(cmpgName))
				{
					System.out.println("CAmpaign  Name is added");
				} else {
					System.out.println("CAmpaign Name is not added");
				}
				
				
				  String addedProduct=driver.findElement(By.xpath(
				  "//a[@href='index.php?module=Products&action=DetailView&record=5']")).getText
				  ();
				  
				  
				  if(addedProduct.contains(selectedProduct)) {
				  System.out.println("product  Name is added"); } else {
				  System.out.println("product Name is not added"); }
				 
				
				
				driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
				driver.findElement(By.linkText("Sign Out")).click();
				
			Thread.sleep(1500);
			
			driver.quit();
			
	    }
			



	}

}
