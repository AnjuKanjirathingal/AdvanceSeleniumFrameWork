package Object_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPOM {

	public CreateContactPOM(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//select[@name='salutationtype']")
	private WebElement drpDwnFirstNamePrefix;

	@FindBy(xpath = "//input[@name='firstname']")
	private WebElement txtFirstName;
	
	
	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement txtLastName;
	
	@FindBy(xpath="//img[@src='themes/softed/images/select.gif']")
	private WebElement imgselectOrganization;
	
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement btnSave;


	public WebElement getDrpDwnFirstNamePrefix() {
		return drpDwnFirstNamePrefix;
	}


	public WebElement getTxtFirstName() {
		return txtFirstName;
	}


	public WebElement getTxtLastName() {
		return txtLastName;
	}


	
	
	public WebElement getImgselectOrganization() {
		return imgselectOrganization;
	}


	public WebElement getBtnSave() {
		return btnSave;
	}
	
	
	//BUssiness Logic goes here............
	
	
	public void contactDetailsToSubmit(String contactFname,String contactLname)
	{
		//drpDwnFirstNamePrefix.click();
		txtFirstName.sendKeys(contactFname);
		txtLastName.sendKeys(contactLname);
	}
	
	
	public void contactDetailsWithOrgToSubmit(String contactFname,String contactLname)
	{
		//drpDwnFirstNamePrefix.click();
		txtFirstName.sendKeys(contactFname);
		txtLastName.sendKeys(contactLname);
		imgselectOrganization.click();
	}
	
	public void saveContactDetails()
	{
		
		btnSave.click();
		
	}
	
	

}
