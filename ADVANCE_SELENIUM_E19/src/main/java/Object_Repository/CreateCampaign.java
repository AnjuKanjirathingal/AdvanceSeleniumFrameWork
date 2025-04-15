package Object_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaign {

	public CreateCampaign(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "campaignname")
	private WebElement txtCampaignName;

	@FindBy(xpath = "//img[@src='themes/softed/images/select.gif']")
	private WebElement imgSelectProduct;

	
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement btnSave;

	public WebElement getTxtCampaignName() {
		return txtCampaignName;
	}

	public WebElement getBtnSave() {
		return btnSave;
	}
	
	public WebElement getImgSelectProduct() {
		return imgSelectProduct;
	}

	
	//Bussiness logic goes here
	
	
	
	public void campaignDetailsToPass(String campaignNameFetchedFromExcelTestData)
	{
		txtCampaignName.sendKeys(campaignNameFetchedFromExcelTestData);
	
	}
	
	public void campaignDetailsWithProductToPass(String campaignNameFetchedFromExcelTestData)
	{
		txtCampaignName.sendKeys(campaignNameFetchedFromExcelTestData);
		imgSelectProduct.click();
	
	}
	
	
	public void saveCampaignDetails()
	{
		
		btnSave.click();
		
	}
	
}
