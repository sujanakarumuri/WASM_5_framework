package vtiger.BaseClassPrograms;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Vtiger.GenericUtility.BaseClass;
import Vtiger.GenericUtility.ExcelFileUtility;
import Vtiger.GenericUtility.JavaUtility;
import Vtiger.ObjectRepository.ContactsPage;
import Vtiger.ObjectRepository.CreateNewContactsPage;
import Vtiger.ObjectRepository.HomePage;
@Listeners(Vtiger.GenericUtility.ListenerImplementation.class)
public class CreateNewContact extends BaseClass {
	
	@Test(groups="SmokeSuite")
	public void CreateContact() throws EncryptedDocumentException, IOException 
	{
	
	String LASTNAME = eUtil.readDataFromExcelSheet("Contacts", 1, 2)+jUtil.getRandomNumber();
	
	//navigate to contacts 
	HomePage hp=new HomePage(driver);
	hp.ClickOnContactsLink();
	Reporter.log("============Contact link clicked============", true);
	
	//navigate to create new contact img
	ContactsPage cp=new ContactsPage(driver);
	cp.clickOnCreateNewContactsImg();
	Reporter.log("======contact img clicked=====", true);
	
	//contacts page should display and enter all the details
	CreateNewContactsPage cnp=new CreateNewContactsPage(driver);
	cnp.createNewContact(LASTNAME);
	Reporter.log("===========contact created successfully====", true);

	
	 
	}
}
