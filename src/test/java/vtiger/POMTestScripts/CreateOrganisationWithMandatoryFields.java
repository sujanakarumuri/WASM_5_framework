package vtiger.POMTestScripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Vtiger.GenericUtility.ExcelFileUtility;
import Vtiger.GenericUtility.JavaUtility;
import Vtiger.GenericUtility.PropertyFileUtility;
import Vtiger.GenericUtility.WebDriverUtilities;
import Vtiger.ObjectRepository.CreateNewOrganizationPage;
import Vtiger.ObjectRepository.HomePage;
import Vtiger.ObjectRepository.LoginPage;
import Vtiger.ObjectRepository.OrganisationInfoPage;
import Vtiger.ObjectRepository.OrganisationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganisationWithMandatoryFields {

	public static void main(String[] args) throws IOException {
		
		WebDriver driver=null;
		
		//create objects to all utilities
		WebDriverUtilities wUtil=new WebDriverUtilities();
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		JavaUtility jUtil=new JavaUtility();
		
		//read all the necessary data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		//read data from excel sheet
		String ORGNAME = eUtil.readDataFromExcelSheet("Organisation", 7, 2)+jUtil.getRandomNumber();
		String INDUSTRYDROPDOWN = eUtil.readDataFromExcelSheet("Organisation", 7, 3);
		String TYPEDROPDOWN = eUtil.readDataFromExcelSheet("Organisation", 7, 4);
		
		//Launch the browser
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
              WebDriverManager.chromedriver().setup();
              driver=new ChromeDriver();
              System.out.println("-----ChromeDriver successfully launched----");
		}
		else
		if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new FirefoxDriver();
			System.out.println("-----Firefox Driver successfully launched----");
			
		}
		else
		{
			System.out.println("-----Browser Invalid---");
		}
	
		wUtil.maximizewindow(driver);
		wUtil.waitforElementsToLoadInDOM(driver);
		driver.get(URL);
		
		//login to app
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//navigateto organization
		HomePage hp=new HomePage(driver);
		hp.ClickOnOrgLink();
		
		//navigate to Organization img
		OrganisationPage op=new OrganisationPage(driver);
		op.clickOnCreateNewOrgImg();
		
		//create new Organization page with mandatory fields
		CreateNewOrganizationPage cnp=new CreateNewOrganizationPage(driver);
		cnp.createNewOrg(ORGNAME, INDUSTRYDROPDOWN, TYPEDROPDOWN);
		
		//validate
		OrganisationInfoPage inf=new OrganisationInfoPage(driver);
		String ORGHEADER = inf.getOrgHeader().toString();
		if(ORGHEADER.contains(ORGNAME))
		{
			System.out.println("Organisation created successfully with Industry dropdown and Type dropdown ");
		}
		else
		{
			System.out.println("Organisation not created");
		}
		
		//signout
		hp.signOutofApp(driver);
	}

}
