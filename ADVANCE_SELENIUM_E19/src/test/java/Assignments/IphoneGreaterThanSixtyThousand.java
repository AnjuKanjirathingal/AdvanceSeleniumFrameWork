package Assignments;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class IphoneGreaterThanSixtyThousand {

	public static void main(String[] args) throws Throwable 
	{
		WebDriver driver;
		// step1: path connection
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Advanced selenium\\TestFolders/testData.xlsx");

		FileInputStream fis1 = new FileInputStream("./src/test/resources/flipkartCommonProperties.properties");
		Properties propertyFile = new Properties();
		propertyFile.load(fis1);
		// step2: keeps the workbook ready in read mode
		Workbook book = WorkbookFactory.create(fis);
		// step3: Navigating expected sheet

		Sheet sheet = book.getSheet("Sheet4");
		
		 Row headerRow = sheet.createRow(0);
         headerRow.createCell(0).setCellValue("iPhone Name");
         headerRow.createCell(1).setCellValue("Price");

		String Browser = propertyFile.getProperty("browser");
		String URL = propertyFile.getProperty("url");
		if (Browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.get(URL);
			driver.manage().window().maximize();

			driver.findElement(By.name("q")).sendKeys("iphone");
			driver.findElement(By.className("_2iLD__")).click();

			List<WebElement> alliPhonePresent = driver.findElements(By.xpath("//div[@class=\'KzDlHZ\']"));
			List<WebElement> iphnPrices = driver.findElements(By.xpath("//div[@class='Nx9bqj _4b5DiR']"));
			for (int i = 0; i < alliPhonePresent.size(); i++)
			{
				String iphoneName = alliPhonePresent.get(i).getText();
				String cost = iphnPrices.get(i).getText();
				cost = cost.replace("₹", "");
				cost = cost.replace(",", "");
				// System.out.println(cost);
				Long iphnprice = Long.parseLong(cost);
				// System.out.println("Parsed"+iphnprice);
				Row row = sheet.createRow(i);
				// step5:- Navigating expected cell--->cell value starts from 0
				if (iphnprice > 60000) {
					System.out.println(iphoneName);
					System.out.println(iphnprice);
					
					
					row.createCell(0).setCellValue(iphoneName);
				    row.createCell(1).setCellValue(iphnprice);
					
				}

			}
			
			  FileOutputStream fos=new FileOutputStream("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Advanced selenium\\TestFolders/testData.xlsx" );
			  book.write(fos);
			  book.close(); 
			  driver.quit();

		}

	}

}
