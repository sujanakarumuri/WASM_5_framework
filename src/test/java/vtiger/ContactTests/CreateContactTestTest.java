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

public class CreateContactTestTest {
	
	public static void main(String[] args) throws IOException
	{
		WebDriver driver;
		/*step1: Create object to all utilities*/
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		JavaUtility jUtil=new JavaUtility();
		WebDriverUtilities wUtils=new WebDriverUtilities();
		
		
		/*step2: Read all the necessary data*/
		//Read data from property file
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		//read data from excelsheet
		String LASTNAME = eUtil.readDataFromExcelSheet("Contacts", 4, 2);
		
		/* step3: launch the browser*/
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			System.out.println("Chrome Driver launched");
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
				System.out.println("browser invalid, hence launched chrome");
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
				System.out.println("Chrome Driver launched");
				
			}
		wUtils.maximizewindow(driver);
		wUtils.waitforElementsToLoadInDOM(driver);
		driver.get(URL);
		
		
		/* step4: login to app*/
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		/* step5: navigate to contacts*/
		driver.findElement(By.linkText("Contacts")).click();
		
		/*step6: click on create contact*/
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		/* step7: create contact with mandatory info*/
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME+jUtil.getRandomNumber());
		
		/* step8: save*/
		driver.findElement(By.name("button")).click();
		
		/* step9: logout*/
		WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtils.mouseHover(driver, element);
		driver.findElement(By.linkText("Sign Out")).click();
	}

}
