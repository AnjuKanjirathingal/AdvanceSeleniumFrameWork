package Object_Repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProductValidationPOM {

	public ProductValidationPOM(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//span[@id='dtlview_Product Name']")
	private WebElement lblPrdctName;


	@FindBy(xpath = "//input[@class='txtBox']")
	private WebElement txtsearchedProduct;
	
	@FindBy(xpath = "//input[@name='submit']")
	private WebElement btnProductListSearchBox;
	
	@FindBy(xpath = "//span[@class='genHeaderSmall']")
	private WebElement lblListedProductForDeletionVAlidation;
	
	
	
	public WebElement getLblListedProductForDeletionVAlidation() {
		return lblListedProductForDeletionVAlidation;
	}


	public WebElement getTxtsearchedProduct() {
		return txtsearchedProduct;
	}


	public WebElement getLblPrdctName() {
		return lblPrdctName;
	}
	
	public WebElement getBtnProductListSearchBox() {
		return btnProductListSearchBox;
	}


	
	//Bussiness logic goes here
	

	public void productDetailsValidation(WebDriver driver, String prdctName) 
	{

		String productNameAdded = lblPrdctName.getText();
		if (productNameAdded.contains(prdctName)) 
		{
			System.out.println("Product Name is added");
		} 
		else 
		{
			System.out.println("Product Name is not added");
		}
	}
	
	public void productDetailsAssertValidation(WebDriver driver, String prdctName) 
	{

		String productNameAdded = lblPrdctName.getText();
		
		Assert.assertEquals(productNameAdded, prdctName, "Assertion Failed");
		System.out.println("Assertion Success......product  Name is added");
		
	}
	
	public void productDeletionValidation(WebDriver driver,String prdctName) 
	{
		
		txtsearchedProduct.sendKeys(prdctName);
		System.out.println(" delete Validation Search..\t"+prdctName);
		btnProductListSearchBox.click();
		 String listedProduct=lblListedProductForDeletionVAlidation.getText();
		 System.out.println(listedProduct);
  	    if(listedProduct.equals("No Product Found !"))
  	    {
  	    	System.out.println("product is deleted");
  	    }
  	    else
  	    {
  	    	System.out.println("product is not yet deleted");
  	    }

	}
	
}
