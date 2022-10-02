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
 * Navigate to vtiger and click on contacts
 * select the  last check box
 * @author sujana
 *
 */

public class WebTable_scenario2 {
	

	WebDriver driver;
	@Test
	public void scenario2() throws IOException, InterruptedException
	{
		
		//WebDriver driver=new ChromeDriver();;
		PropertyFileUtility pUtil=new PropertyFileUtility();
		WebDriverUtilities wUtil=new WebDriverUtilities();
		
		// driver=new ChromeDriver();
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL=pUtil.readDataFromPropertyFile("url");
		String USERNAME= pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");	
		
		
		if(BROWSER.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			System.out.println("ChromeBrowser launched successfully");
			
		}
		else
			if(BROWSER.equalsIgnoreCase("firefox"))
			{
				
				WebDriverManager.chromedriver();
				driver=new FirefoxDriver();
				System.out.println("firefox launched successfully");
				}
			else
			{
				System.out.println("Invalid browser");
			}
		
		wUtil.maximizewindow(driver);
		wUtil.waitforElementsToLoadInDOM(driver);
		driver.get(URL);
		
		LoginPage lp=new LoginPage(driver);
		
		lp.loginToApp(USERNAME, PASSWORD);
		
		HomePage hp=new HomePage(driver);
		hp.ClickOnContactsLink();
		
		List<WebElement> lst = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[1]"));
		
		
		driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+ lst.size()+"]/td[1]")).click();
			
		
		
	}

}
