package GenericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import Object_Repository.HomePage;
import Object_Repository.LoginPage;

public class TestNgBaseClass 
{
	public WebDriver driver;
	public static WebDriver screenshotListenerdriver;
	
	@BeforeSuite(groups = {"smokeTest","regressionTest","sanityTest"})
	public void  beforeSuite()
	{
	
		System.out.println("DataBAseConnection..........");
	}

	@BeforeTest(groups = {"smokeTest","regressionTest","sanityTest"})
	public void  beforeTest()
	{
		System.out.println("pArallel execution|||||||||||");
	}
	
	@BeforeClass(groups = { "smokeTest", "regressionTest", "sanityTest" })
	public void beforeClass() throws Throwable {
		System.out.println("launching Browser*********");

		File_Utility commonPropertyFileValues = new File_Utility();

		String Browser = commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("browser");

		if (Browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (Browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		screenshotListenerdriver=driver;
	}
	 	
	/*
	 * //for cross browser testing we have to add parameter="Browser" in
	 * parallelExecutiontestng.xml file and we have to follow this changes in
	 * ' @beforeClass' in baseClass file
	 * 
	 * 
	 * @Parameters("Browser")
	 * 
	 * @BeforeClass(groups = {"smokeTest","regressionTest","sanityTest"}) public
	 * void beforeClass(String Browser) throws Throwable {
	 * System.out.println("launching Browser*********");
	 * 
	 * if(Browser.equalsIgnoreCase("chrome")) { driver = new ChromeDriver(); } else
	 * if (Browser.equalsIgnoreCase("firefox")) { driver = new FirefoxDriver(); }
	 * else if (Browser.equalsIgnoreCase("edge")) { driver = new EdgeDriver(); }
	 * else { driver = new ChromeDriver(); }
	 * 
	 * }
	 */

	
	@BeforeMethod(groups = { "smokeTest", "regressionTest", "sanityTest" })
	public void beforeMethod() throws Throwable {
		System.out.println("Login to Application..................");

		File_Utility commonPropertyFileValues = new File_Utility();
		WebDriverUtility driverUtility = new WebDriverUtility();
		String VtigerURL = commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("url");
		String VtigerUsername = commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("username");
		String VtigerPassword = commonPropertyFileValues.getCommonPropertiesFileKeyAndValues("password");

		driver.get(VtigerURL);
		driverUtility.maximizeWindow(driver);
		driverUtility.waitElementsToLoad(driver);

		LoginPage login = new LoginPage(driver);

		login.loginIntoVtiger(VtigerUsername, VtigerPassword);

	}

	/*
	 * //like cross browser testing we have addedd parameters as usernam and
	 * password in= in CrossBrowserparallelExecutiontestng.xml file and we have to
	 * follow this changes in ' @beforeMethod' in baseClass file
	 * 
	 * 
	 * 
	 * @Parameters({"URL","username","password"})
	 * 
	 * @BeforeMethod(groups = {"smokeTest","regressionTest","sanityTest"}) public
	 * void beforeMethod(String VtigerURL,String VtigerUsername,String
	 * VtigerPassword) throws Throwable {
	 * System.out.println("Login to Application..................");
	 * 
	 * WebDriverUtility driverUtility= new WebDriverUtility();
	 * 
	 * 
	 * driver.get(VtigerURL); driverUtility.maximizeWindow(driver);
	 * driverUtility.waitElementsToLoad(driver);
	 * 
	 * LoginPage login=new LoginPage(driver);
	 * 
	 * login.loginIntoVtiger(VtigerUsername, VtigerPassword);
	 * 
	 * }
	 * 
	 */	
	@AfterMethod(groups = {"smokeTest","regressionTest","sanityTest"})
	public void  afterMethod()
	{
		System.out.println("LOGOUT from application............");
		HomePage mainMenu=new HomePage(driver);
		 mainMenu.signOutVtiger();
	}
	
	@AfterClass(groups = {"smokeTest","regressionTest","sanityTest"})
	public void  afterClass()
	{
		System.out.println("closing browser******************");
		driver.quit();
	}
	
	@AfterTest(groups = {"smokeTest","regressionTest","sanityTest"})
	public void  afterTest()
	{
		System.out.println("parallel executon close||||||||||||||||||||");
	}
	
	@AfterSuite(groups = {"smokeTest","regressionTest","sanityTest"})
	public void  afterSuite()
	{
		System.out.println("close database connection.................");
	}

}
