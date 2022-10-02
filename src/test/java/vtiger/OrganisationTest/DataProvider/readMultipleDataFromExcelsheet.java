package vtiger.OrganisationTest.DataProvider;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Vtiger.GenericUtility.ExcelFileUtility;

public class readMultipleDataFromExcelsheet  {
	
	ExcelFileUtility eUtil=new ExcelFileUtility();
	@Test(dataProvider="Orgdata")
	public void mutipleData(String Orgname, String Industry)
	{
		System.out.println("Orgname----"+Orgname+"---Industry----"+Industry);
		
	}
	
	@DataProvider(name="Orgdata")
	public Object[][] data() throws EncryptedDocumentException, IOException
	{
		return eUtil.readMUltipleDataFromExcelSheet("MultipleOrg");
	}
	
	
	
	
	
	
	

}
