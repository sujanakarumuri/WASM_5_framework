package vtiger.Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Cal {
	
	@Test
	public void SelectAnyDate() throws InterruptedException
	{
		//Set the web driver manager to chrome
		WebDriverManager.chromedriver().setup();
		
		//Launch the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");
		
		Thread.sleep(1000);
		
		Actions act = new Actions(driver);
		act.moveByOffset(10, 10).click().perform();
		
		//driver.findElement(By.xpath("//a[@class='close']")).click();
		
		driver.findElement(By.xpath("//span[@class='langCardClose']")).click();
		
		//Navigate to From and To Elements
		WebElement src = driver.findElement(By.xpath("//input[@id='fromCity']"));
		WebElement dst = driver.findElement(By.xpath("//input[@id='toCity']"));
		
		
		src.sendKeys("mumbai");
		driver.findElement(By.xpath("//p[text()='Mumbai, India']")).click();
		
		Thread.sleep(2000);
		
		dst.sendKeys("chennai");
		driver.findElement(By.xpath("//p[text()='Chennai, India']")).click();
		
		Thread.sleep(2000);
		
		//navigate to departure
		driver.findElement(By.xpath("//label[@for='departure']")).click();
		
		//navigate to desired date in calender
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@aria-label='Sat Sep 24 2022']")).click();
		
		driver.close();
	}

}
