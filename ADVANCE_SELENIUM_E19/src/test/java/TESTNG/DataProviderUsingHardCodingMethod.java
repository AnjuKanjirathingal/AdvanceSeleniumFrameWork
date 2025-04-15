package TESTNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(GenericUtility.ExtentReportImplementation.class)
public class DataProviderUsingHardCodingMethod 
{
	@Test(dataProvider = "getData",retryAnalyzer =  GenericUtility.RetryListenerImplimentation.class)
	public void bookTickets(String source,String Destination,int passengers)
	{
		System.out.println("TICKETS TO "+source +"To \t"+Destination+"\nNumber of passengers are \t"+passengers);
	}

	@DataProvider
	public Object[][] getData()
	{
		
		Object objLocationArray[][]=new Object[3][3];
		
		objLocationArray[0][0]="bangaloore";
		objLocationArray[0][1]="Mysore";
		objLocationArray[0][2]=15;
		
		objLocationArray[1][0]="Thrissur";
		objLocationArray[1][1]="Majestic";
		objLocationArray[1][2]=20;
		
		objLocationArray[2][0]="Hyd";
		objLocationArray[2][1]="Delhi";
		objLocationArray[2][2]=50;
		return objLocationArray;
		
	}
}
