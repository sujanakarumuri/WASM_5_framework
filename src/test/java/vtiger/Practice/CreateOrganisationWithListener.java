package vtiger.Practice;

	import java.io.IOException;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.Assert;
	import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

	import Vtiger.GenericUtility.BaseClass;
	import Vtiger.GenericUtility.ExcelFileUtility;
	import Vtiger.GenericUtility.JavaUtility;
	import Vtiger.GenericUtility.PropertyFileUtility;
	import Vtiger.GenericUtility.WebDriverUtilities;
	import Vtiger.ObjectRepository.ContactsInfoPage;
	import Vtiger.ObjectRepository.ContactsPage;
	import Vtiger.ObjectRepository.CreateNewContactsPage;
	import Vtiger.ObjectRepository.CreateNewOrganizationPage;
	import Vtiger.ObjectRepository.HomePage;
	import Vtiger.ObjectRepository.OrganisationInfoPage;
	import Vtiger.ObjectRepository.OrganisationPage;
	import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(Vtiger.GenericUtility.ListenerImplementation.class)
	public class CreateOrganisationWithListener extends BaseClass{
		@Test
		public void CreateContactWithOrg() throws IOException
		{
			
			//read data from excel sheet
			String ORGNAME = eUtil.readDataFromExcelSheet("Contacts", 4, 3)+jUtil.getRandomNumber();
			String LASTNAME = eUtil.readDataFromExcelSheet("Contacts", 4, 2);
			
			/* Navigate to Organization*/
			HomePage hp=new HomePage(driver);
			hp.ClickOnOrgLink();
			
			
			/*click on create organisation */
			OrganisationPage op=new OrganisationPage(driver);
			op.clickOnCreateNewOrgImg();
			
			
			
			CreateNewOrganizationPage cnp=new CreateNewOrganizationPage(driver);
			cnp.createNewOrg(ORGNAME);
			
			//validate
			OrganisationInfoPage OrgInf=new OrganisationInfoPage(driver);
			String ORGHEADER = OrgInf.getOrgHeader();
			Assert.assertTrue(ORGHEADER.contains(ORGNAME));
			Reporter.log("===ORGANISATION CREATED===", true);
			
			Assert.fail();
			
			//click on contacts
			hp.ClickOnContactsLink();
			
			//navigate to contacts page
			ContactsPage cp=new ContactsPage(driver);
			cp.clickOnCreateNewContactsImg();
			
			//navigate to create new contact page
			CreateNewContactsPage cnc=new CreateNewContactsPage(driver); 
			cnc.createNewContact(LASTNAME, ORGNAME, driver);
			
			//validation
			ContactsInfoPage cip=new ContactsInfoPage(driver);
			String CONTACTHEADER = cip.getContactInfo();
			Assert.assertTrue(CONTACTHEADER.contains(LASTNAME));
			Reporter.log("========Contact created======", true);
			
	
		
	}

}
