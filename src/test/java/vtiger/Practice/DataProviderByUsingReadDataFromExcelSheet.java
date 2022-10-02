package vtiger.Practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Vtiger.GenericUtility.ExcelFileUtility;

public class DataProviderByUsingReadDataFromExcelSheet {
	@Test(dataProvider="Org_Name")
	public void readDataFromExcelSheet(String orgname, String Industry)
	{
		System.out.println(orgname+"-"+Industry);
		
	}
	@DataProvider(name="Org_Name")
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		ExcelFileUtility eUtil=new ExcelFileUtility();
		Object[][] data = eUtil.readMUltipleDataFromExcelSheet("MultipleOrg");
		return data;
		
	}

}
