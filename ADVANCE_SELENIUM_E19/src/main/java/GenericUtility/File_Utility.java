package GenericUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class File_Utility 
{
	/**
	 * This methpd is used to read common properties of the application from an external resources commonly knpwn as CommonPropertyFile
	 * @param key
	 * @return
	 * @throws Throwable
	 */
	public String getCommonPropertiesFileKeyAndValues(String key) throws Throwable
	{
		FileInputStream fis1 = new FileInputStream("./src/test/resources/propertyFileForVtiger.properties");
		Properties propertyFile = new Properties();
		propertyFile.load(fis1);
		
		//read the values using getProperty here
		
		String valueFetched= propertyFile.getProperty(key);
		
		return valueFetched;

		
	}

}
