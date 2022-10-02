package vtiger.POMTestScripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Vtiger.GenericUtility.ExcelFileUtility;
import Vtiger.GenericUtility.JavaUtility;
import Vtiger.GenericUtility.PropertyFileUtility;
import Vtiger.GenericUtility.WebDriverUtilities;
import Vtiger.ObjectRepository.OrganisationPage;
import Vtiger.ObjectRepository.CreateNewOrganizationPage;
import Vtiger.ObjectRepository.HomePage;
import Vtiger.ObjectRepository.LoginPage;
import Vtiger.ObjectRepository.OrganisationInfoPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganisation {

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
		
		String ORGNAME = eUtil.readDataFromExcelSheet("Organisation", 1, 2)+jUtil.getRandomNumber();
		
		
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
	
		//navigate to Organization
		HomePage hp=new HomePage(driver);
		hp.ClickOnOrgLink();
		
		//navigate to Organization page
		OrganisationPage op=new OrganisationPage(driver);
		op.clickOnCreateNewOrgImg();
		
		//click on create Organization
         CreateNewOrganizationPage cnp=new CreateNewOrganizationPage(driver);
         cnp.createNewOrg(ORGNAME);
         
        
         
         OrganisationInfoPage inf=new OrganisationInfoPage(driver);
         String ORGHEADER = inf.getOrgHeader().toString();
         if(ORGHEADER.contains(ORGNAME))
         {
        	 System.out.println("Orgnasiation created");
         }
         else
         {
        	 System.out.println("Orgnasiation not created");
        	 
         }
         //signout
         hp.signOutofApp(driver);
         
         
		
		
	}


}
