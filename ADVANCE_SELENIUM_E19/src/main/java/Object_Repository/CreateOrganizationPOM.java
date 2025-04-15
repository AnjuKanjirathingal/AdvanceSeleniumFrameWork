package Object_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationPOM {

	public CreateOrganizationPOM(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//input [@name='accountname']")
	private WebElement txtOrganizationName;
	
	@FindBy(xpath = "//input[@id='phone']")
	private WebElement txtPhoneNumber;
	
	@FindBy(xpath="//input[@id='email1']")
	private WebElement txtEmail;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement btnSave;


	public WebElement getTxtOrganizationName() {
		return txtOrganizationName;
	}


	public WebElement getTxtPhoneNumber() {
		return txtPhoneNumber;
	}


	public WebElement getTxtEmail() {
		return txtEmail;
	}


	public WebElement getBtnSave() {
		return btnSave;
	}
	
	
	//bussiness logic goes here
	
	
	
	public void organizationDetailsToSubmit(String orgName,String orgPhone,String orgEmail)
	{
		txtOrganizationName.sendKeys(orgName);
		txtPhoneNumber.sendKeys(orgPhone);
		txtEmail.sendKeys(orgEmail);
	}
	
	public void saveOrganizationDetails()
	{
		
		btnSave.click();
		
	}
	
	
	
}
