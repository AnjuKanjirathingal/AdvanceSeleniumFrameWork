package TESTNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtility.ExcelTestDataUtility;
import GenericUtility.File_Utility;
import GenericUtility.Java_Utility;
import GenericUtility.TestNgBaseClass;
import GenericUtility.WebDriverUtility;
import Object_Repository.HomePage;
import Object_Repository.LoginPage;

@Listeners(GenericUtility.ExtentReportImplementation.class)
@Test(retryAnalyzer =  GenericUtility.RetryListenerImplimentation.class)
public class deleteOrganizationTest extends TestNgBaseClass {

	public void  deleteOrganizationTest() throws Throwable
	{
		WebDriverUtility driverUtility= new WebDriverUtility();
		ExcelTestDataUtility testData= new ExcelTestDataUtility();
		Java_Utility randomUtility = new Java_Utility();
		
			Thread.sleep(4000);
			HomePage mainMenu=new HomePage(driver);
			mainMenu.clickOnOrganizationMenu();
		    driver.findElement(By.xpath(" //img [@alt= 'Create Organization...']")).click();
		    
			int ranNum=randomUtility.getRandomNum();

			String orgnizationName = testData.getExcelDataUsingFormatter("vtigerOrganizations", 0, 0)+ranNum;
	
			String orgPhone=testData.getExcelDataUsingFormatter("vtigerOrganizations", 0, 1);
			String orgEmail=testData.getExcelDataUsingFormatter("vtigerOrganizations", 0, 2);

			driver.findElement(By.xpath("//input [@name='accountname']")).sendKeys(orgnizationName);
			driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(orgPhone);
			driver.findElement(By.xpath("//input[@id='email1']")).sendKeys(orgEmail);

			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

			Thread.sleep(2002);
			String orgNameAdded = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
			String PhnAdded = driver.findElement(By.xpath("//span[@id='dtlview_Phone']")).getText();
			String EmailAdded = driver.findElement(By.xpath("//td[@id='mouseArea_Email']")).getText();
			if (((orgNameAdded.contains(orgnizationName)) && (EmailAdded.contains(orgEmail))
					&& (PhnAdded.contains(orgPhone)))) {

				System.out.println("Details as below : \n Organization:\t" + orgNameAdded + "\n email :\t" + EmailAdded
						+ "\n phoneAdded :\t" + PhnAdded);

			} else {
				System.out.println("Organization details not added");
			}
			driver.findElement(By.linkText("Organizations")).click();
	 	//locate and delete the element in single click
			WebElement del = driver.findElement(By.xpath("//table[@class=\"lvt small\"]//a[text()='"+ orgNameAdded + "']/../following-sibling::td//a[text()='del']"));
			del.click();
			Thread.sleep(3000); 
			driverUtility.alertAccept(driver);
	}

}
