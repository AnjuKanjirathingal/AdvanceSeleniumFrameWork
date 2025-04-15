package Object_Repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwitchingWindowPageToselectPOM {

	public SwitchingWindowPageToselectPOM(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath ="//input[@id='search_txt']")
	private WebElement txtSearch;
	
	@FindBy(xpath = "//input[@name='search']")
	private WebElement btnSearch;
	
	public void searchProductName(String name)
	{
		txtSearch.sendKeys(name);
		btnSearch.click();
	}
	
	public void dynamicXpathForProductNameToCampaign(WebDriver driver,String productName)
	{
		driver.findElement(By.xpath("//a[text()='" +productName+ "']")).click();

	}
	
	
	public void dynamicXpathForOrganizationNameToContact(WebDriver driver,String orgName)
	{
		driver.findElement(By.xpath("//a[text()='" +orgName+ "']")).click();

	}
}
