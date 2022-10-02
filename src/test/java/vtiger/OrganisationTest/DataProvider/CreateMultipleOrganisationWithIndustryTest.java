package vtiger.OrganisationTest.DataProvider;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Vtiger.GenericUtility.BaseClass;
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

public class CreateMultipleOrganisationWithIndustryTest extends BaseClass {
	
	
	//create object for all required utilities
	JavaUtility jUtil=new JavaUtility();
	ExcelFileUtility eUtil=new ExcelFileUtility();
	WebDriverUtilities wUtil=new WebDriverUtilities();
	PropertyFileUtility pUtil=new PropertyFileUtility();
	
	WebDriver driver;
	
	@Test(dataProvider="orgData")
	public void createMutipleOrgTest(String Orgname, String IndustryType) throws IOException
	{
		//read all the necessary data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
	
		String Org=Orgname+jUtil.getRandomNumber();
		
		//launch the browser
		if(BROWSER.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			System.out.println("ChromeDriver launched succesfully");
		}else
			if(BROWSER.equalsIgnoreCase("Firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				System.out.println("FirefoxDriver launched succesfully");
				
			}
			else
			{
				System.out.println("Default browser is Chrome");
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();			
				System.out.println("ChromeDriver launched succesfully");
			}
		
		wUtil.maximizewindow(driver);
		wUtil.waitforElementsToLoadInDOM(driver);
		driver.get(URL);
		
		//login to page
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//navigate to homepage
		HomePage hp=new HomePage(driver);
		hp.ClickOnOrgLink();
		
		//Navigate to create org look up img
		OrganisationPage op=new OrganisationPage(driver);
		op.clickOnCreateNewOrgImg();
		
		//create new organization with industry type
		CreateNewOrganizationPage cnp=new CreateNewOrganizationPage(driver);
		cnp.createNewOrg(Org, IndustryType);
		
		//validate
		OrganisationInfoPage oip=new OrganisationInfoPage(driver);
		String ORGHEADER = oip.getOrgHeader();
		if(ORGHEADER.contains(Org))
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
		
	}
@DataProvider(name="orgData")
public Object[][] getData() throws EncryptedDocumentException, IOException
{
	return eUtil.readMUltipleDataFromExcelSheet("MultipleOrg");
}
}
