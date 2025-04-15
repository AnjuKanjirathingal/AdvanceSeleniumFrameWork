import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class InsertDataIntoPropertyFile 
{

	public static void main(String[] args) throws IOException
	{
		Properties propertyFile=new Properties();
		propertyFile.setProperty("browser", "chrome");
		propertyFile.setProperty("url", "http://localhost:8888/index.php?action=Login&module=Users");
		
		FileOutputStream fos=new FileOutputStream("./src/test/resources/commonDataProperties.properties");
		propertyFile.store(fos, "second data stored");

	}

}
