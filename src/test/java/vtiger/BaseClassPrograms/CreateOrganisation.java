package vtiger.BaseClassPrograms;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Vtiger.GenericUtility.BaseClass;
import Vtiger.GenericUtility.ExcelFileUtility;
import Vtiger.ObjectRepository.CreateNewOrganizationPage;
import Vtiger.ObjectRepository.HomePage;
import Vtiger.ObjectRepository.OrganisationPage;
@Listeners(Vtiger.GenericUtility.ListenerImplementation.class)
public class CreateOrganisation extends BaseClass{

	@Test
	public void CreateNewOrg() throws EncryptedDocumentException, IOException
	{
	   // ExcelFileUtility eUtil=new ExcelFileUtility();
		//read all the necessary details
	    String ORGNAME = eUtil.readDataFromExcelSheet("Organisation", 1,2)+jUtil.getRandomNumber();
	  
		//navigate to org link
		                     
		HomePage hp=new HomePage(driver);
		hp.ClickOnOrgLink();
		
		//navigate to create new org img
		OrganisationPage op=new OrganisationPage(driver);
		op.clickOnCreateNewOrgImg();
	
		
		//
		CreateNewOrganizationPage cnp=new CreateNewOrganizationPage(driver);
		cnp.createNewOrg(ORGNAME);
		
		
		
	}
	

}
