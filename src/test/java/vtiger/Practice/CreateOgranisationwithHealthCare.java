package vtiger.Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateOgranisationwithHealthCare {

	public static void main(String[] args) {
		
		//step1: launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("http://localhost:8888");
		
		//step2:Enter login credential and click login
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//step3://step3: Navigate to organisation link
		driver.findElement(By.linkText("Organizations")).click();
		
		//step4: click on create organisation look up image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//step5:Enter organisation name
		driver.findElement(By.name("accountname")).sendKeys("Qspider3");
		
		//step6:Choose Healthcare in the Industry dropdown
		driver.findElement(By.name("industry")).click();
		driver.findElement(By.xpath("//option[@value='Electronics']")).click();
		
		//step7:Choose Investor in Type dropdown
		driver.findElement(By.name("accounttype")).click();
		driver.findElement(By.xpath("//option[@value='Investor']")).click();
		
		//step8:Click on save button
		driver.findElement(By.name("button")).click();
		
		
		
		driver.close();
		
		System.out.println("Organisation created with Electronics selected in industry drop down and Investor in Type dropdown");
	}

}
