package vtiger.OrganisationTests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import Vtiger.GenericUtility.ExcelFileUtility;
import Vtiger.GenericUtility.JavaUtility;
import Vtiger.GenericUtility.PropertyFileUtility;
import Vtiger.GenericUtility.WebDriverUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganisationWithHealthcareTest {
	@Test
	public void  CreateOrganisationWithHealthcaretest () throws IOException
	{
		WebDriver driver;
		/*step1: Create abject for all utilities*/
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		WebDriverUtilities wUtil=new WebDriverUtilities();
		JavaUtility jUtil=new JavaUtility();
		
		/*step2: Read the necessary data*/
		/*read data from property file*/
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		/*read data from excel sheet*/
		String ORGNAME = eUtil.readDataFromExcelSheet("Organisation", 4, 2);
		String IND = eUtil.readDataFromExcelSheet("Organisation", 4, 3);
		
		/*step3: launch the browser*/
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			System.out.println("Chrome driver launched");
		}
		else
			if(BROWSER.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				System.out.println("firefox driver launched");
			}
			else
			{
				System.out.println("Invalid browser, Chrome browser is launched");
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
				System.out.println("Chrome driver launched");
				
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
		
		/*step7: create organization with Industry dropdown*/
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME+jUtil.getRandomNumber());
		WebElement element = driver.findElement(By.name("industry"));
		wUtil.handleDropDown(IND, element);
		
		/*step8: save*/
		driver.findElement(By.name("button")).click();
		
		/*step9:logout*/
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHover(driver, ele);
		driver.findElement(By.linkText("Sign Out")).click();

	}


}
