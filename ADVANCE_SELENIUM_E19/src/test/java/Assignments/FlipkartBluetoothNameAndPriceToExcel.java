package Assignments;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlipkartBluetoothNameAndPriceToExcel {

	public static void main(String[] args) throws Throwable {
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

		Sheet sheet = book.getSheet("Sheet3");

		String Browser = propertyFile.getProperty("browser");
		String URL = propertyFile.getProperty("url");
		if (Browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.get(URL);
			driver.manage().window().maximize();

			driver.findElement(By.name("q")).sendKeys("Bluetooth");
			driver.findElement(By.className("_2iLD__")).click();

			List<WebElement> allLinksPresent = driver.findElements(By.xpath("//div[@class=\'wjcEIp\']"));
			List<WebElement> bthPrices = driver.findElements(
					By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[5]/div/div[2]/div/a[3]/div/div[1]"));
			for (int i = 0; i < allLinksPresent.size(); i++) {
				String bthEarPhn=allLinksPresent.get(i).getText();
				// step4:- Navigating expected row--->row value starts from 0
				Row row = sheet.createRow(i);
				// step5:- Navigating expected cell--->cell value starts from 0
				Cell cell = row.createCell(0);

				
				String price = (bthPrices.get(i).getText());
				System.out.println(price);
				// step4:- Navigating expected row--->row value starts from 0

				// step5:- Navigating expected cell--->cell value starts from 0
				Cell cell1 = row.createCell(1);
				cell.setCellValue(bthEarPhn);
				cell1.setCellValue(price);

			}

			FileOutputStream fos = new FileOutputStream(
									"C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Advanced selenium\\TestFolders/testData.xlsx");
			book.write(fos);
			book.close();
			driver.quit();

		}

	}

}
