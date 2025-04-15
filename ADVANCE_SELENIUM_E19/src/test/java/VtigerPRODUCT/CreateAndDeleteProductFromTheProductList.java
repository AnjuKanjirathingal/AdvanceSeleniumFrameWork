package VtigerPRODUCT;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import GenericUtility.ExcelTestDataUtility;
import GenericUtility.File_Utility;
import GenericUtility.Java_Utility;
import GenericUtility.WebDriverUtility;
import Object_Repository.HomePage;

public class CreateAndDeleteProductFromTheProductList {
	public static void main(String[] args) throws Throwable {
		WebDriver driver;
		
		File_Utility commonPropertyFileValues=new File_Utility();
		WebDriverUtility driverUtility=new WebDriverUtility();
		ExcelTestDataUtility testData= new ExcelTestDataUtility();
	    Java_Utility randomUtility=new Java_Utility();
		
		String Browser=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("browser");
		String VtigerURL=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("url");
		String VtigerUsername=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("username");
		String VtigerPassword=commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("password");
		String VtigerBtnLogin="submitButton";
		
		if (Browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.get(VtigerURL);
			driverUtility.maximizeWindow(driver);
			driverUtility.waitElementsToLoad(driver);
			
			driver.manage().window().maximize();
			driver.findElement(By.name("user_name")).sendKeys(VtigerUsername);
			driver.findElement(By.name("user_password")).sendKeys(VtigerPassword);
			driver.findElement(By.id(VtigerBtnLogin)).click();

			Thread.sleep(4000);
			HomePage mainMenu=new HomePage(driver);
			mainMenu.clickOnProductsMenu();
			driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		    int ranNum=randomUtility.getRandomNum();
		    String prdctName = testData.getExcelData("VtigerProducts", 0, 0) + ranNum;

			driver.findElement(By.name("productname")).sendKeys(prdctName);
			// driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(prdctName);

			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

			String productNameAdded = driver.findElement(By.xpath("//span[@id='dtlview_Product Name']")).getText();

			if (productNameAdded.contains(prdctName)) {
				System.out.println(
						"Product Name is added \tprdctName: " + prdctName + "\t productNameAdded :" + productNameAdded);
			} else {
				System.out.println("Product Name is not added");
			}

			driver.findElement(By.linkText("Products")).click();

			Thread.sleep(3500);
			// Locating the product list table
			WebElement table = driver.findElement(By.xpath("//table[@class='lvt small']"));

			// Fetching  all rows
			List<WebElement> rows = table.findElements(By.tagName("tr"));

			// Iterate through rows and extract data from the desired column
			int prdct_column_index = 2;
			for (WebElement searchrow : rows) 
			{
				List<WebElement> cells = searchrow.findElements(By.tagName("td"));
				if (cells.size() > prdct_column_index) {
					WebElement searchcell = cells.get(prdct_column_index);
					String cellText = searchcell.getText();
					System.out.println("cell selected text" + cellText);
					Thread.sleep(3500);
					if (cellText.equals(productNameAdded)) {
						System.out.println("product found\t :" + cellText);
						driver.findElement(By.xpath("//table[@class=\"lvt small\"]//a[text()='" + cellText+ "']/../preceding-sibling::td//input[@type=\"checkbox\"]")).click();
						WebElement del = driver.findElement(By.xpath("//table[@class=\"lvt small\"]//a[text()='"+ cellText + "']/../following-sibling::td//a[text()='del']"));
						del.click();
						driver.switchTo().alert().accept();
						//Thread.sleep(2500);
						driver.navigate().refresh();

					}

				}
			}

			driver.findElement(By.linkText("Products")).click();
			driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(productNameAdded);
			driver.findElement(By.xpath("//input[@name='submit']")).click();
			Thread.sleep(3500);
     	    String searchedProduct=driver.findElement(By.xpath("//span[@class='genHeaderSmall']")). getText();
     	    if(searchedProduct.equals("No Product Found !"))
     	    {
     	    	System.out.println("product is deleted");
     	    }
     	    else
     	    {
     	    	System.out.println("product is not yet deleted");
     	    }

     		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
			driver.findElement(By.linkText("Sign Out")).click();
			driver.quit();
		}

	}



}
