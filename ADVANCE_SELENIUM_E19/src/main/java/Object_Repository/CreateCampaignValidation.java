package Object_Repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CreateCampaignValidation {

	public CreateCampaignValidation(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//span[@id='dtlview_Campaign Name']")
	private WebElement lblCampaignName;


	
	public WebElement getLblCampaignName() {
		return lblCampaignName;
	}
	public void setLblCampaignName(WebElement lblCampaignName) {
		this.lblCampaignName = lblCampaignName;
	}
	
	
	//bussiness logic goes here
	
	


	public void validateCampaignName(WebDriver driver,String campdata)
	{
		String cmpgNameAdded=lblCampaignName.getText();
		
		if(cmpgNameAdded.contains(campdata))
		{
			System.out.println("CAmpaign  Name is added");
		} else {
			System.out.println("CAmpaign Name is not added");
		}
		
		/*
		 * String addedProduct=driver.findElement(By.xpath(
		 * "//a[@href='index.php?module=Products&action=DetailView&record=5']")).getText
		 * ();
		 * 
		 * if(addedProduct.contains(selectedProduct)) {
		 * System.out.println("product  Name is added"); } else {
		 * System.out.println("product Name is not added"); }
		 */
	
	}
	
	
	public void assertValidateCampaignName(WebDriver driver,String campdata)
	{
		String cmpgNameAdded=lblCampaignName.getText();
		
		
		Assert.assertEquals(cmpgNameAdded, campdata, "Assertion Failed");
		System.out.println("Assertion Success......CAmpaign  Name is added");
		
		
	
	}
}
