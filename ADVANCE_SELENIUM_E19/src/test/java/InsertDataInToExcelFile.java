import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class InsertDataInToExcelFile
{

	public static void main(String[] args)  throws Throwable
	{
		 // step1: path connection
	    FileInputStream fis = new FileInputStream("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Advanced selenium\\TestFolders/testData.xlsx");
	    // step2: keeps the workbook ready in read mode
	    Workbook book = WorkbookFactory.create(fis);
	    // step3: Navigating expected sheet
	 
		Sheet sheet = book.getSheet("Sheet1");
	
		//step4:- Navigating expected row--->row value starts from 0
		Row row = sheet.createRow(10);
		
		//step5:- Navigating expected cell--->cell value starts from 0
		Cell cell = row.createCell(1);
		
		cell.setCellValue("Inserting to Excel from the Script");

		FileOutputStream fos=new FileOutputStream("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Advanced selenium\\TestFolders/testData.xlsx");
		book.write(fos);
		book.close();
		
		
	}

}
