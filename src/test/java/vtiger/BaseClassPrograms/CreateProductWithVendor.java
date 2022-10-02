package vtiger.BaseClassPrograms;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
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
import Vtiger.ObjectRepository.CreateNewVendor;
import Vtiger.ObjectRepository.CreatingNewProductPage;
import Vtiger.ObjectRepository.HomePage;
import Vtiger.ObjectRepository.LoginPage;
import Vtiger.ObjectRepository.ProductInfoPage;
import Vtiger.ObjectRepository.ProductsPage;
import Vtiger.ObjectRepository.VendorInfoPage;
import Vtiger.ObjectRepository.VendorPage;
import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(Vtiger.GenericUtility.ListenerImplementation.class)
public class CreateProductWithVendor extends BaseClass {
	@Test
	public void CreateProcuctWithMandatoryVendor() throws IOException

	{    
		
		String VENDORNAME = eUtil.readDataFromExcelSheet("Products", 1, 3)+jUtil.getRandomNumber();
		String PRODUCTNAME = eUtil.readDataFromExcelSheet("Contacts", 1, 2)+jUtil.getRandomNumber();
		
		
		
	
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
		Assert.assertTrue(VENDORHEADER.contains(VENDORNAME));
		Reporter.log("===Vendor created=====",true);
		
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
		
	
		Assert.assertTrue(PRODUCTHEADER.contains(PRODUCTNAME));
		Reporter.log("------product is created-----",true);
		
		
		
	}

}


