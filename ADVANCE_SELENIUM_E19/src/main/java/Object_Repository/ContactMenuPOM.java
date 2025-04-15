package Object_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactMenuPOM {

	public ContactMenuPOM(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement imgCreateContact;
	
	
	public WebElement getImgCreateContact() {
		return imgCreateContact;
	}
	
	
	
	
	//Bussiness Logic goes here
	
	public void createContacts()
	{
		imgCreateContact.click();
	}
	

}
