package vtiger.POMTestScripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Vtiger.GenericUtility.ExcelFileUtility;
import Vtiger.GenericUtility.JavaUtility;
import Vtiger.GenericUtility.PropertyFileUtility;
import Vtiger.GenericUtility.WebDriverUtilities;
import Vtiger.ObjectRepository.CreateNewVendor;
import Vtiger.ObjectRepository.CreatingNewProductPage;
import Vtiger.ObjectRepository.HomePage;
import Vtiger.ObjectRepository.LoginPage;
import Vtiger.ObjectRepository.ProductInfoPage;
import Vtiger.ObjectRepository.ProductsPage;
import Vtiger.ObjectRepository.VendorInfoPage;
import Vtiger.ObjectRepository.VendorPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewProductWithExistingVendor {
	
	public static void main(String[] args) throws IOException
	{    
		WebDriver driver=null;
		
		//create objects to all the utilities
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		JavaUtility jUtil=new JavaUtility();
		WebDriverUtilities wUtil=new WebDriverUtilities();
		
		
		//read all the necessary data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		String VENDORNAME = eUtil.readDataFromExcelSheet("Products", 1, 3)+jUtil.getRandomNumber();
		String PRODUCTNAME = eUtil.readDataFromExcelSheet("Contacts", 1, 2)+jUtil.getRandomNumber();
		
		
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
		
		//navigate to vendors
		HomePage hp=new HomePage(driver);
		hp.MouseHoverOnMore(driver);
		
		//click on create new vendor img
		VendorPage vp=new VendorPage(driver);
		vp.ClickOnNewVendorLinkImg();
		
		//enter all the mandatory fields
		CreateNewVendor cnv=new CreateNewVendor(driver);
		cnv.CreateNewVendorEdt(VENDORNAME);
		
		//validate
		VendorInfoPage vip=new VendorInfoPage(driver);
		String VENDORHEADER = vip.getVendorHeader().getText();
		if(VENDORHEADER.contains(VENDORNAME))
		{
			System.out.println("====vendor created successfully====");
		}
		else
		{
			System.out.println("====vendor not created successfully====");
			
		}
		
		//navigate to products
		hp.ClickOnProductLink();
		
		//navigate to products page
		ProductsPage pp=new ProductsPage(driver);
		pp.CreateNewProductImg();
		
		//navigate to creating new product page
		CreatingNewProductPage cnp=new CreatingNewProductPage(driver);
		cnp.ProdNameEdt(PRODUCTNAME, VENDORNAME, driver);
		
		//validate
		ProductInfoPage pip=new ProductInfoPage(driver);
		String PRODUCTHEADER = pip.ProductHeaderInfo().toString();
		
		System.out.println(PRODUCTHEADER);
		if(PRODUCTHEADER.contains(PRODUCTNAME))
		{
			System.out.println("----product created successfully with selecting vendor---");
		}
		else
		{
			System.out.println("Product not crerated");
		}
		
		//sign out
		hp.signOutofApp(driver);
		
	}

}
