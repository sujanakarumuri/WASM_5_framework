package vtiger.Practice;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import Vtiger.GenericUtility.PropertyFileUtility;
import Vtiger.GenericUtility.WebDriverUtilities;
import Vtiger.ObjectRepository.HomePage;
import Vtiger.ObjectRepository.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Navigate to Vtiger application and click on contacts
 * Check the 5 checkbox and click on delete and accept 
 * @author sujana
 *
 */

public class WebTable_Scenario3 {
	
	WebDriver driver;
	
	@Test
	public void scenario3() throws IOException
	{
		PropertyFileUtility pUtil=new PropertyFileUtility();
		WebDriverUtilities wUtil=new WebDriverUtilities();
		
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		if(BROWSER.equalsIgnoreCase("Chrome"))
		{
			driver=new ChromeDriver();
			WebDriverManager.chromedriver().setup();
			System.out.println("ChromeDriver launched succesfully");
			
		}
		else
			if(BROWSER.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				System.out.println("Firefox launched successfully");
			}
			else
			{
				System.out.println("Default browser is chrome");
			}
		
		driver.get(URL);
		wUtil.maximizewindow(driver);
		wUtil.waitforElementsToLoadInDOM(driver);
		
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		HomePage hp=new HomePage(driver);
		hp.ClickOnContactsLink();
		
		List<WebElement> lst = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[1]"));
		
			driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+7+"]/td[1]")).click();
			
			driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[10]/a[text()='del']")).click();
			
			driver.switchTo().alert().accept();
	
		
		
	}

}
