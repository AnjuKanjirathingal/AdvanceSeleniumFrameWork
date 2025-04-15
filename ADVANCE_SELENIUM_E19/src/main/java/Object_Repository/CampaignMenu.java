package Object_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;import com.mysql.cj.jdbc.Driver;

public class CampaignMenu {

	
 // Constructor to initialize web elements using PageFactory	
	public CampaignMenu(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

 // Locate elements using @FindBy
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement imgCreateCampaign;
	
	
//Getter Method	
	public WebElement getImgCreateCampaign() {
		return imgCreateCampaign;
	}
	// Business methods (actions on elements)
	public void createCampaign()
	{
		imgCreateCampaign.click();
	}
}
