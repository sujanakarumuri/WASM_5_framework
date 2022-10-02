package vtiger.OrganisationTests;

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

public class CreateOrganisationWithIndustryAndTypeDropDownTest {

	public static void main(String[] args) throws IOException
	{
		WebDriver driver;
		/*step1: create object for all utiltities*/
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		JavaUtility jUtil=new JavaUtility();
		WebDriverUtilities wUtil=new WebDriverUtilities();
		
		/*step2: read all the necessary data*/
		/*read data from property file*/
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		/*read data from excel sheet*/
		String ORGNAME = eUtil.readDataFromExcelSheet("Organisation", 7, 2);
		String INDUSDD= eUtil.readDataFromExcelSheet("Organisation", 7, 3);
		String TYPEDD = eUtil.readDataFromExcelSheet("Organisation", 7, 4);
		
		/* step3:launch the browser*/
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			System.out.println("Chrome browser launched");
			
		}else
			if(BROWSER.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.chromedriver().setup();
				driver=new FirefoxDriver();
				System.out.println("firefox browser launched");
			}
			else
			
			{ System.out.println("Invalid browser, chrome browser launched");
					WebDriverManager.chromedriver().setup();
					driver=new ChromeDriver();
					System.out.println("Chrome browser launched");
					
			}
		wUtil.maximizewindow(driver);
		wUtil.waitforElementsToLoadInDOM(driver);
		driver.get(URL);
		
		/*step 4 login to app*/
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		/*step5: Navigate to organization*/
		driver.findElement(By.linkText("Organizations")).click();
		
		/*step6: click on create organization*/
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		/*step7: enter all the mandatory fields*/
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME+jUtil.getRandomNumber());
		
		/*Industry drop down*/
		WebElement element = driver.findElement(By.name("industry"));
		wUtil.handleDropDown(INDUSDD, element);
		
		/*Type drop down*/
		WebElement type=driver.findElement(By.name("accounttype"));
		wUtil.handleDropDown(TYPEDD, type);
		
		/*step8: save*/
		driver.findElement(By.name("button")).click();
		
		/*step9:logout*/
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHover(driver, ele);
		driver.findElement(By.linkText("Sign Out")).click();

	}
		
		
	
}
