package vtiger.Practice;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrganisationTest {


	public static void main(String[] args) throws InterruptedException {
		
		//Step1: Launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("http://localhost:8888");
		
		
		//step2: Login to app
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//step3: Navigate to organisation link
		driver.findElement(By.linkText("Organizations")).click();
		
		//step4: click on create organisation look up image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//step5: enter the mandatory field
		driver.findElement(By.name("accountname")).sendKeys("Capgemini");
		
		//step6: save
		driver.findElement(By.name("button")).click();
		

		
		
		Thread.sleep(2000);
		//save and logout
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a =new Actions(driver);
		a.moveToElement(ele).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		//driver.close();
		System.out.println("organisation is created");
		
		
		

	}

}
