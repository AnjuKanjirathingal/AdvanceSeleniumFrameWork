import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
public class FetchingDataFromJsonFile
{

	public static void main(String[] args) throws Throwable 
	{
		File file = new File("src/test/resources/jsonData.json");

		ObjectMapper jsonData = new ObjectMapper();
		//JsonNode data = jsonData.readTree(file);
	     JsonNode data=jsonData.readTree(file);
		String URL = data.get("url").asText();
		String USERNAME = data.get("userName").asText();
		String PASSWORD = data.get("password").asText();
		String VtigerBtnLogin="submitButton";
	
		WebDriver driver=new ChromeDriver();
		driver.get(URL);

		    driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id(VtigerBtnLogin)).click();
		 Thread.sleep(4000);
		 driver.quit();
		
	}

}
