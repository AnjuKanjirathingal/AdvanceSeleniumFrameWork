package GenericUtility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerImplimentation implements  ITestListener
{

	// Listener:-Listener is a feature available in TestNG which is used to capture
		// runTime events during the execution and perform appropriate action based on
		// eventType.

	public void onTestFailure(ITestResult result) 
	{

		TakesScreenshot takesSceenShot = (TakesScreenshot) TestNgBaseClass.screenshotListenerdriver;

		File src = takesSceenShot.getScreenshotAs(OutputType.FILE);
		File dst = new File("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Selenium Basic\\ECLIPSE WORKSPACE\\ADVANCE_SELENIUM_E19\\src\\test\\resources\\Screenshots\\Failed Scripts\\FailedScripts.png");
		try 
		{
			FileUtils.copyFile(src, dst);
		} catch (IOException e)
		{

			e.printStackTrace();
		}
	}
}
// this ListenerImplimentation is implimented in "AddingOrganizationTOContacts.java" class .
//where the scripts fails because of organization name assertion validation  after submitting the form.
//and this listener helps to take the screen shot of that page