package vtiger.Practice;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * Navigate to vtiger and click on contacts
 * and all the check boxes should be select
 * @author sujana
 *
 */

public class WebTable_Scenario1 {
	
	@Test
	public void scenario1Test()
	{
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
	
	    List<WebElement> lst = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[1]/input[@name='selected_id']"));
	        int x = lst.size();
	        System.out.println(x);
	        Iterator id=lst.iterator();
	        for(int i=3;i<x;i++)
	       
	        {
	        
	        	driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+i+"]/td[*]")).click();
	        }
	        driver.close();
	        System.out.println(" Clicked all the check boxes");
	        
	
	}
	

}
