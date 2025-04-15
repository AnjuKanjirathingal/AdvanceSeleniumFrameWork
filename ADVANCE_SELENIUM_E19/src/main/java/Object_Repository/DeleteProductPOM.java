package Object_Repository;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteProductPOM {

	public DeleteProductPOM(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//table[@class='lvt small']")
	private WebElement tblProductList;
	
	@FindBy(xpath = "")
	private WebElement chkbxProductNameToDelete;
	
	@FindBy(xpath = "//input[@value=\"Delete\"]")
	private WebElement linkCommonDeleteButton;
	
	
	
	

	public WebElement getTblProductList() {
		return tblProductList;
	}
	public WebElement getChkbxProductNameToDelete() {
		return chkbxProductNameToDelete;
	}
	public WebElement getLinkCommonDeleteButton() {
		return linkCommonDeleteButton;
	}
	
	
	
	//Bussiness logic goes here
	
	public void deleteProductAction(WebDriver driver,String prdctName)
	{

		driver.findElement(By.xpath("//table[@class=\"lvt small\"]//a[text()='"+prdctName+"']/../preceding-sibling::td//input[@type=\"checkbox\"]")).click();
		 linkCommonDeleteButton.click();
		 
	}
	
	
	public void validateDeleteProductAction(WebDriver driver,String prdctName)
	{

		List<WebElement> allNames = driver.findElements(By.xpath("(//table[@class='lvt small']/tbody/tr//td[3])[position()>1]"));
		
		boolean flag=false;
		for (WebElement name : allNames)
		{
			String actPrd = name.getText();
			if(actPrd.equals(prdctName))
			{
			flag=true;
			break;
		}
			}
		if(flag)
		{
			System.out.println("product name is deleted");
		}
		else
		{
			System.out.println("Product name is not deleted");
		}
	}
	
}
	

			