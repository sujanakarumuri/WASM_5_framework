package vtiger.Practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import Vtiger.GenericUtility.ExcelFileUtility;
import Vtiger.GenericUtility.JavaUtility;

public class GenericUtilityPractice {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		JavaUtility jlib=new JavaUtility();
		int r=jlib.getRandomNumber();
		System.out.println(r);
		
		String da = jlib.getSystemDate();
		System.out.println(da);
		
		String format = jlib.getSystemdateFormat();
		System.out.println(format);
		
	ExcelFileUtility flib=new ExcelFileUtility();
	flib.writeDataintoExcelSheet("Organisation", 7, 6, "sujana");
	System.out.println("data added");
	
	}
	
	

}

