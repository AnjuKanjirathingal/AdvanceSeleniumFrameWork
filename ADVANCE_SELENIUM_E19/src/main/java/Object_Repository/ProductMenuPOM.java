package Object_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductMenuPOM {

	public ProductMenuPOM(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement imgcreateProduct;


	public WebElement getImgcreateProduct() {
		return imgcreateProduct;
	}
	
	
	//bussiness logic goes here
	
	public void  createProducts()
	{
		imgcreateProduct.click();
	}

	
	
}
