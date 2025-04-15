package GenericUtility;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelTestDataUtility {
	/**
	 * 
	 * 
	 * this class is used to read test data from external Excel file
	 * @author Anju 
	 * 
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws Throwable
	 */

	public String getExcelData(String sheetName, int rowNum, int cellNum) throws Throwable
	{
		// step1: path connection
		FileInputStream fis = new FileInputStream("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Advanced selenium\\TestFolders/testData.xlsx");
		// step2: keeps the workbook ready in read mode
		Workbook book = WorkbookFactory.create(fis);
		// step3: Navigating expected sheet

		Sheet sheet = book.getSheet(sheetName);

		// step4:- Navigating expected row--->row value starts from 0
		Row row = sheet.getRow(rowNum);

		// step5:- Navigating expected cell--->cell value starts from 0
		Cell cell = row.getCell(cellNum);

		String excelDataFetching = cell.getStringCellValue();
		System.out.println("Excel data fetching..................."+excelDataFetching);
		return excelDataFetching;

	}
	
	public String getExcelDataUsingFormatter(String SheetName, int rowNum, int cellNum) throws Throwable {
		// step1:- path connection
		FileInputStream fis1 = new FileInputStream("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Advanced selenium\\TestFolders/testData.xlsx");
		// step2:-keeps the workbook/excelfile in read mode
		Workbook book = WorkbookFactory.create(fis1);
		// step3:- Navigating expected sheet
		Sheet sheet = book.getSheet(SheetName);
		// step4:- Navigating expected row--->row value starts from 0
		Row row = sheet.getRow(rowNum);
		// step5:- Navigating expected cell--->cell value starts from 0
		Cell cell = row.getCell(cellNum);

		DataFormatter format = new DataFormatter();
		String ExcelDataFetching = format.formatCellValue(cell);
		System.out.println("Excel data fetching..................."+ExcelDataFetching);
		return ExcelDataFetching ;
	}
	public Object[][] readDataUsingDataProvider(String SheetName) throws Throwable {
	
	    FileInputStream fis1 = new FileInputStream("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Advanced selenium\\TestFolders/testData.xlsx");
	    Workbook book = WorkbookFactory.create(fis1);
	    Sheet sheet = book.getSheet(SheetName);

	    int lastRow = sheet.getLastRowNum()+1;
	    System.out.println("LAstROw  count is............................\t"+lastRow);
	    int lastCell = sheet.getRow(0).getLastCellNum();
	    System.out.println("last cell  count is............................\t"+lastCell);
	    Object[][] obj = new Object[lastRow][lastCell];

	    for (int i = 0; i < lastRow; i++)
	    {
	        for (int j = 0; j < lastCell; j++)
	        {
	            obj[i][j] = sheet.getRow(i).getCell(j).toString();
	        }
	    }
	    return obj;
	}


	
}
