package vtiger.ContactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Vtiger.GenericUtility.BaseClass;
import Vtiger.ObjectRepository.ContactsInfoPage;
import Vtiger.ObjectRepository.ContactsPage;
import Vtiger.ObjectRepository.CreateNewContactsPage;
import Vtiger.ObjectRepository.CreateNewOrganizationPage;
import Vtiger.ObjectRepository.HomePage;
import Vtiger.ObjectRepository.LoginPage;
import Vtiger.ObjectRepository.OrganisationInfoPage;
import Vtiger.ObjectRepository.OrganisationPage;

@Listeners(Vtiger.GenericUtility.ListenerImplementation.class)
public class CreateContactWithOrgUsingListenersTest extends BaseClass{
	
	@Test(groups="SmokeSuite")
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException
	{
		//read all the required data
		String ORGNAME = eUtil.readDataFromExcelSheet("Contacts", 4, 3)+jUtil.getRandomNumber();
		String LASTNAME = eUtil.readDataFromExcelSheet("Contacts", 4, 2);
		
		//Homepage
		HomePage hp=new HomePage(driver);
		hp.ClickOnOrgLink();
		Reporter.log("Organisation created", true);
		
		//Navigate to org img
		OrganisationPage op=new OrganisationPage(driver);
		op.clickOnCreateNewOrgImg();
		Reporter.log("org img link is clicked", true);
		
		//create org with mandatory fields
		CreateNewOrganizationPage cnp=new CreateNewOrganizationPage(driver);
		cnp.createNewOrg(ORGNAME);
		Reporter.log(" Organisation created",true);
		

		//validate
		OrganisationInfoPage OrgInf=new OrganisationInfoPage(driver);
		String ORGHEADER = OrgInf.getOrgHeader();
		Assert.assertTrue(ORGHEADER.contains(ORGNAME));
		Reporter.log("===ORGANISATION CREATED===", true);
		
		
		
		//click on contacts
		hp.ClickOnContactsLink();
		Reporter.log("Contact link clicked",true);
		
		
		//navigate to contacts page
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateNewContactsImg();
		Reporter.log("new Contacts img link clicked",true);
		
		//navigate to create new contact page
		CreateNewContactsPage cnc=new CreateNewContactsPage(driver); 
		cnc.createNewContact(LASTNAME, ORGNAME, driver);
		Reporter.log("Contact created with mandatory fields ",true);
		
		//validation
		ContactsInfoPage cip=new ContactsInfoPage(driver);
		String CONTACTHEADER = cip.getContactInfo();
		Assert.assertTrue(CONTACTHEADER.contains(LASTNAME));
		Reporter.log("========Contact created======", true);
		
		
		
		
		
		
		
		
	}

}
