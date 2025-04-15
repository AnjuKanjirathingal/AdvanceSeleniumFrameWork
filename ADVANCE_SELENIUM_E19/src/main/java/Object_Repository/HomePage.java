package Object_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='More']")
	private WebElement linkMore;
	
	@FindBy(xpath = "//a[@href='index.php?module=Campaigns&action=index']")
	private WebElement linkCampaigns;
	
	@FindBy(xpath = "//a[@href='index.php?module=Contacts&action=index']")
	private WebElement linkContact;
	
	@FindBy(xpath = "//a[@href='index.php?module=Accounts&action=index']")
	private WebElement linkOrganizationst;
	
	@FindBy(linkText = "Products")
	private WebElement linkProduct;
	
	@FindBy(xpath = "//img[@src=\'themes/softed/images/user.PNG\']")
	private WebElement iconProfile;
	
	@FindBy(linkText = "Sign Out")
	private WebElement linkSignOut;
	
	
	
	
	
	public WebElement getLinkMore() {
		return linkMore;
	}

	public WebElement getLinkCampaigns() {
		return linkCampaigns;
	}

	public WebElement getLinkContact() {
		return linkContact;
	}

	public WebElement getLinkOrganizationst() {
		return linkOrganizationst;
	}

	public WebElement getLinkProduct() {
		return linkProduct;
	}

	public WebElement getIconProfile() {
		return iconProfile;
	}

	public WebElement getLinkSignOut() {
		return linkSignOut;
	}

//------------------------------------------------------------------------------------------//
	
	//Bussiness logic goes here
	
	public void clickOnCampaignMenu()
	{
		linkMore.click();
		linkCampaigns.click();
	}
	
	public void clickOnContactsMenu()
	{
		linkContact.click();
	}
	
	public void clickOnOrganizationMenu()
	{
		linkOrganizationst.click();
	}
	public void clickOnProductsMenu()
	{
		linkProduct.click();
	}
	
	public void signOutVtiger()
	{
		iconProfile.click();
		linkSignOut.click();
	}
	
}
