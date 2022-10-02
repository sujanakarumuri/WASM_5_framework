package vtiger.OrganisationTests;

import Vtiger.GenericUtility.PropertyFileUtility;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import Vtiger.GenericUtility.ExcelFileUtility;
import Vtiger.GenericUtility.JavaUtility;
import Vtiger.GenericUtility.WebDriverUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganisationTest {
@Test
	 public void CreateOrganisationtest() throws IOException
	 {

		WebDriver driver;
		/*step1: Create object to all utilities*/
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		JavaUtility jUtil=new JavaUtility();
		WebDriverUtilities wUtil=new WebDriverUtilities();	
		
		
		/*step2: Read the necessary data*/
		/* read data from property file*/
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		/* read data from excel sheet*/
		String LASTNAME = eUtil.readDataFromExcelSheet("Organisation", 1, 2);
		
		/*step3: Launch the browser*/
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			System.out.println("Chrome browser launched");
			
		}
		else
			if(BROWSER.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				System.out.println("firefox browser launched");
				
			}
			else
			{
				System.out.println("browser invalid, chrome browser launched");
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
				System.out.println("Chrome browser launched");
				
			}
		wUtil.maximizewindow(driver);
		wUtil.waitforElementsToLoadInDOM(driver);
		driver.get(URL);
		
		/* step4: Login to app*/
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		/* step5: Navigate to organization*/
		driver.findElement(By.linkText("Organizations")).click();
		
		
		/* step6: Click on create Organization*/
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		/* step7: create organization with mandatory information*/
		driver.findElement(By.name("accountname")).sendKeys(LASTNAME+jUtil.getRandomNumber());
		
		/* step8: save*/
		driver.findElement(By.name("button")).click();
		
		
		
		/* step9: logout*/
		WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHover(driver, element);
		driver.findElement(By.linkText("Sign Out")).click();

		
	}

}
