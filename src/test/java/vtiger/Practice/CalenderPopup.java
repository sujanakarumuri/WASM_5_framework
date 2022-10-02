package vtiger.Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CalenderPopup {

	@Test
	public void calender()
	{
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.makemytrip.com");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Mumbai");
		driver.findElement(By.linkText("Mumbai, India")).click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
	
		Actions act = new Actions(driver);
		act.moveByOffset(10, 10).click().perform();
		driver.findElement(By.xpath("//span[@class='langCardClose']")).click();
		
		//driver.findElement(By.xpath("//a[@class='close']")).click();
		
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("Chennai");
	driver.findElement(By.linkText("Chennai, India")).click();
	driver.findElement(By.xpath("//span[@class='font30 latoBlack']")).click();
	driver.findElement(By.linkText("28")).click();
	}
}
