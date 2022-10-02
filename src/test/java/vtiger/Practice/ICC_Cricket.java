package vtiger.Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ICC_Cricket {
	
	@Test
	public void icc_Cricket()
	{
		String name="New Zealand";
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.icc-cricket.com/rankings/mens/team-rankings/test");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement ele = driver.findElement(By.xpath("//span[text()='"+ name+"']/ancestor::td[@class='table-body__cell rankings-table__team']/following-sibling::td[@class='table-body__cell u-text-right rating']"));
		
	  String value = ele.getText();
	  System.out.println(value);
	}

}
