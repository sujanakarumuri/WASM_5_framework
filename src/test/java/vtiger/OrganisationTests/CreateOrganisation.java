package vtiger.OrganisationTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreateOrganisation {

	@Test
	
		public void CreateOrganisationTest() throws IOException {
		
		WebDriver driver;
		
		Random r=new Random();
		int RANDOM = r.nextInt(1000);
		
		//step1:Read the data
		FileInputStream fisp=new FileInputStream(".\\src\\test\\resources\\commonData.properties");
          Properties pobj1=new Properties();
		pobj1.load(fisp);
		String BROWSER = pobj1.getProperty("browser");
		String URL = pobj1.getProperty("url");
		String USERNAME = pobj1.getProperty("username");
		String PASSWORD = pobj1.getProperty("password");
		
		//read data from excelsheet
		
		FileInputStream fise=new FileInputStream(".\\src\\test\\resources\\Testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		Sheet SHEET = wb.getSheet("Organisation");
		Row row = SHEET.getRow(1);
		Cell CELL = row.getCell(2);
		String ORGNAME = CELL.getStringCellValue();
		
		
		//step2:Launch the browser
		if(BROWSER.equalsIgnoreCase("Chrome"))
		{
			driver=new ChromeDriver();
			System.out.println("ChromeDriver launched succesfully");
		}else
			if(BROWSER.equalsIgnoreCase("Firefox"))
			{
				driver=new FirefoxDriver();
				System.out.println("FirefoxDriver launched succesfully");
				
			}
			else
			{
				System.out.println("Default browser is Chrome");
				driver=new ChromeDriver();
				System.out.println("ChromeDriver launched succesfully");
			}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(URL);
		
		//Step 3: login to App
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();		
				
				//Step 4: navigate to organizations
				driver.findElement(By.linkText("Organizations")).click();
				
				//Step 5: navigate create Organizations
				driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
				
				//Step 6: create Organization with mandatory fields
				driver.findElement(By.name("accountname")).sendKeys(ORGNAME+RANDOM);
				
				//Step 7: save
				driver.findElement(By.name("button")).click();
				
				//Step 8: logout
				WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				
				Actions a=new Actions(driver);
				a.moveToElement(element).perform();
			
				
				driver.findElement(By.linkText("Sign Out")).click();
				



		
		
		
	}

}
