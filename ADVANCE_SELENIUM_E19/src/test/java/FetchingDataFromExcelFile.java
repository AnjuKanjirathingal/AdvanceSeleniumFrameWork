import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchingDataFromExcelFile {

	public static void main(String[] args) throws Throwable
	{
		// TODO Auto-generated method stub

		 
		    // step1: path connection
		    FileInputStream fis = new FileInputStream("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Advanced selenium\\TestFolders/testData.xlsx");
		    // step2: keeps the workbook ready in read mode
		    Workbook book = WorkbookFactory.create(fis);
		    // step3: Navigating expected sheet
		 
			Sheet sheet = book.getSheet("Sheet1");
		
			//step4:- Navigating expected row--->row value starts from 0
			Row row = sheet.getRow(0);
			
			//step5:- Navigating expected cell--->cell value starts from 0
			Cell cell = row.getCell(0);
			
			String excelData = cell.getStringCellValue();
			System.out.println(excelData);
			
			row=sheet.getRow(3);
			cell=row.getCell(1);
		    long	excelNumericData=(long) cell.getNumericCellValue();
		    System.out.println(excelNumericData);
		    
		    
		    row=sheet.getRow(4);
			cell=row.getCell(1);
		    String	excelSpecialData= cell.getStringCellValue();
		    System.out.println(excelSpecialData);
		    
		   
		    
	}

}
