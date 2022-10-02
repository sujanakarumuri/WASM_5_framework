package vtiger.ContactTests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Vtiger.GenericUtility.ExcelFileUtility;
import Vtiger.GenericUtility.JavaUtility;
import Vtiger.GenericUtility.PropertyFileUtility;
import Vtiger.GenericUtility.WebDriverUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganisationTest {

	public static void main(String[] args) throws IOException {
		WebDriver driver=null;
		
		//step1: Create object for all utilities
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		JavaUtility jUtil=new JavaUtility();
		WebDriverUtilities wUtil=new WebDriverUtilities();
		
		//sep2:Read all the necessary data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		//read data from excel sheet
		String ORGNAME = eUtil.readDataFromExcelSheet("Contacts", 4, 3)+jUtil.getRandomNumber();
		String LASTNAME = eUtil.readDataFromExcelSheet("Contacts", 4, 2);
		
		//step3: launch the browser
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			System.out.println("launched chrome browser");
		}else
			if(BROWSER.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				System.out.println("launched firefox browser");
			}else
			{
				System.out.println("Invalid browser");
			}
		wUtil.maximizewindow(driver);
		wUtil.waitforElementsToLoadInDOM(driver);
		driver.get(URL);
		
		
		/*step4:login to app*/
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		/*step5: Navigate to Organization*/
		driver.findElement(By.linkText("Organizations")).click();
		
		/*step6: click on create organisation */
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);

		//step7: save the organisation
		driver.findElement(By.name("button")).click();
		String OrgHEADER = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		System.out.println(OrgHEADER);
		if(OrgHEADER.contains(ORGNAME))
		{
		
			System.out.println("pass");
			System.out.println("----organisation created----");
		}
		else
		{
			System.out.println("fail");
			System.out.println("----organisation not  created----");
		}
		
		//step8: create contact
		driver.findElement(By.linkText("Contacts")).click();
		  
		   driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		   driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		   driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@title='Select']")).click();
		  
		 //step9:switch the control to child window
		   wUtil.switchToWindow(driver, "Accounts");
		   driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
		   driver.findElement(By.name("search")).click();
		   driver.findElement(By.linkText(ORGNAME)).click();
		   
		   //step10: control back to parent
		   wUtil.switchToWindow(driver, "Contacts");
		   
		   //step11:save
		   driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		   
		  String CONTACTHEADER = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		  System.out.println(CONTACTHEADER);
		   if(CONTACTHEADER.contains(LASTNAME))
		   {
			   System.out.println("pass");
				System.out.println("----contact created----");
			}
			else
			{
				System.out.println("fail");
				System.out.println("----contact not  created----");
			}
			   
		   	   
		   //step12:logout
		   wUtil.mouseHover(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		   driver.findElement(By.linkText("Sign Out")).click();
	}

}
