package vtiger.Practice;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.mysql.cj.jdbc.Driver;

public class CreateContactWithExistingOrganisation {

	public static void main(String[] args) {
	
		//step1: Launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("http://localhost:8888");
		
		//step2:Login to Vtiger
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//step3:Navigate to contact
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//step4:Enter all the mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("karumuri");
		
		//step5:Choose any existing organisation in organisation list
		driver.findElement(By.xpath("//img[@alt='Select']")).click();
		String parent = driver.getWindowHandle();
		Set<String> allw = driver.getWindowHandles();
		
		for(String id:allw)
		{
			driver.switchTo().window(id);
		}
		
		
	
		driver.findElement(By.linkText("HP")).click();
		driver.switchTo().window(parent);
		
		WebElement img = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(driver);
		a.moveToElement(img).perform();
		
		
		driver.findElement(By.linkText("Sign Out")).click();
		
		
		

	}

}
