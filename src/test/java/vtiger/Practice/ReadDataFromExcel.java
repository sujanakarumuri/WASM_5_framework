package vtiger.Practice;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//step1: load the file into file input stream
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Testdata.xlsx");
		
		//step2:create workbook using WorkbookFactory
		Workbook wb = WorkbookFactory.create(fis);
		
		//step3:Navigate to sheet
		Sheet sheet = wb.getSheet("Contacts");
		
		//step4:Navigate to row
		Row row = sheet.getRow(4);
		
		//step5:Navigate to cell
		Cell cell = row.getCell(3);
		
		//step6:Read the response value
		String value = cell.getStringCellValue();
		System.out.println(value);
	}

}
