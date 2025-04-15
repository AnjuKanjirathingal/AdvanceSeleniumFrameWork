package Object_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductPOM {

	public CreateProductPOM(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(name ="productname")
	private WebElement txtPrdctName;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement btnSave;

	public WebElement getTxtPrdctName() {
		return txtPrdctName;
	}

	public WebElement getBtnSave() {
		return btnSave;
	}

	
	//Business logic goes here
	
	public void productDetailsToBeSubmitted(String prdctName)
	{
		txtPrdctName.sendKeys(prdctName);
	}
	
	public void saveProductDetails()
	{	
		btnSave.click();
	}
	
	
}
