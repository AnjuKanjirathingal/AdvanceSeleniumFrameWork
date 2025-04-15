package SIKULI;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class SikuliExWebApplications
{
public static void main(String[] args) throws Throwable {
		
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.flipkart.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		Screen scr = new Screen();
		
		Pattern search = new Pattern("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Selenium Basic\\ECLIPSE WORKSPACE\\ADVANCE_SELENIUM_E19\\src\\test\\resources\\Screenshots\\FlipkarSearchBar.png");
		scr.type(search, "Puma");
		Thread.sleep(1000);
		
		driver.quit();
		
	}

}
