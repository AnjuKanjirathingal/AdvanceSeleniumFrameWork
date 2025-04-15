import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadAllLinksOrMultipledataFromExcel 
{

	public static void main(String[] args) throws Throwable
	{
		// step1:- path connection
		FileInputStream fis = new FileInputStream("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Advanced selenium\\TestFolders/testData.xlsx");

		// step2:-keeps the workbook/excelfile in read mode
		Workbook book = WorkbookFactory.create(fis);

		// step3:- Navigating expected sheet
		Sheet sheet = book.getSheet("sheet2");

		int rowNum = sheet.getLastRowNum();

		for (int i = 0; i < rowNum; i++) {
			Row row = sheet.getRow(i);

			Cell cell = row.getCell(0);
			String links = cell.getStringCellValue();
		
			System.out.println(links);
		}
	}
		

}
