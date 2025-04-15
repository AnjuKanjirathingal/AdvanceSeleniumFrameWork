package Object_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationMenuPOM {

	public OrganizationMenuPOM(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = " //img [@alt= 'Create Organization...']")
	private WebElement imgCreateOrganization;


	public WebElement getImgCreateOrganization() {
		return imgCreateOrganization;
	}
	
	
	
	//BUssiness logic goes here...............
	
	public void  createOrganizations()
	{
		imgCreateOrganization.click();
	}

}
