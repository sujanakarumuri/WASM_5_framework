package vtiger.POMTestScripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Vtiger.GenericUtility.ExcelFileUtility;
import Vtiger.GenericUtility.JavaUtility;
import Vtiger.GenericUtility.PropertyFileUtility;
import Vtiger.GenericUtility.WebDriverUtilities;
import Vtiger.ObjectRepository.ContactsInfoPage;
import Vtiger.ObjectRepository.ContactsPage;
import Vtiger.ObjectRepository.CreateNewContactsPage;
import Vtiger.ObjectRepository.CreateNewOrganizationPage;
import Vtiger.ObjectRepository.HomePage;
import Vtiger.ObjectRepository.LoginPage;
import Vtiger.ObjectRepository.OrganisationInfoPage;
import Vtiger.ObjectRepository.OrganisationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithMandatoryFieldsUsingPOM {

	public static void main(String[] args) throws IOException {
		
		WebDriver driver=null;
		
		//create objects for all utilities
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		JavaUtility jUtil=new JavaUtility();
		WebDriverUtilities wUtil=new WebDriverUtilities();
		
		//read all the required data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		String LASTNAME = eUtil.readDataFromExcelSheet("Contacts", 4, 2);
		String ORGNAME = eUtil.readDataFromExcelSheet("Contacts", 4, 3)+jUtil.getRandomNumber();
		
		
		//launch the browser
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
		    WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			System.out.println("----------Chromed launched successfully-----------");
		}
		else
			if(BROWSER.equalsIgnoreCase("firefox"))
		    {
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				System.out.println("-------firefox launched successfully----");
				
			}
			else
			{
				System.out.println("Invalid browser");
			}
		wUtil.maximizewindow(driver);
		wUtil.waitforElementsToLoadInDOM(driver);
		driver.get(URL);
	
		//login to app
		
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//navigate to organisation link
		HomePage hp=new HomePage(driver);
		hp.ClickOnOrgLink();
		
		//navigate to create Organisation
		OrganisationPage op=new OrganisationPage(driver);
		op.clickOnCreateNewOrgImg();
		
		//create new organisation
		CreateNewOrganizationPage cnp=new CreateNewOrganizationPage(driver);
		cnp.createNewOrg(ORGNAME);
		
		
		
		//validation
		OrganisationInfoPage Oip=new OrganisationInfoPage(driver);
		String ORGHEADER = Oip.getOrgHeader().toString();
		if(ORGHEADER.contains(ORGNAME))
		{
			System.out.println("----Organisation Created---");
		}
		else
		{
			System.out.println("----Organisation not Created---");
			
		}
		
		
		//Navigate to Contacts
		hp.ClickOnContactsLink();
		
		//navigate to create contact page
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateNewContactsImg();
       
		//navigate to create new contact page
		CreateNewContactsPage cnc=new CreateNewContactsPage(driver);
		cnc.createNewContact(LASTNAME, ORGNAME, driver);
		
		//validate
	      ContactsInfoPage cnf=new ContactsInfoPage(driver);
	       String ContactHeader = cnf.getContactInfo();
	       if(ContactHeader.contains(LASTNAME))
	       {
	    	   System.out.println("Contact created");
	       }
	       else
	       {
	    	   System.out.println("Contact not created");
	    	   
	       }
	       
	       hp.signOutofApp(driver);
	      
		
		
	}

}
