package Object_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
//element initialization
	public LoginPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		
	}
//Element declaration

	@FindBy(name="user_name")
	private WebElement txtUserName;
	
	@FindBy(name="user_password")
	private WebElement txtPassword;
	
	@FindBy(id="submitButton")
	private WebElement btnLogin;
//Getter Method
	public WebElement getTxtUserName() {
		return txtUserName;
	}
	public WebElement getTxtPassword() {
		return txtPassword;
	}
	public WebElement getBtnLogin() {
		return btnLogin;
	}
//BUssiness Logic
	public void loginIntoVtiger(String username,String Password)
	{
		txtUserName.sendKeys(username);
		txtPassword.sendKeys(Password);
		btnLogin.click();
	}
	
	

}
