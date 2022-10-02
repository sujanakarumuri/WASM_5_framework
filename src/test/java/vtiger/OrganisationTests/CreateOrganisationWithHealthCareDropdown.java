package vtiger.OrganisationTests;

import java.io.FileInputStream;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class CreateOrganisationWithHealthCareDropdown {
@Test
	public  void CreateOrganisationWithHealthCareDropDownTest() throws IOException, InterruptedException {
	
		WebDriver driver;
		Random r=new Random();
		int RANDOM = r.nextInt(100);
		
		//step1: Read the necessary data
		FileInputStream fisp=new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		Properties pobj=new Properties();
		pobj.load(fisp);
		String BROWSER = pobj.getProperty("browser");
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");
		
		//read data from excelsheet
		FileInputStream fise=new FileInputStream(".\\src\\test\\resources\\Testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		Sheet SHEET = wb.getSheet("Organisation");
		Row ROW = SHEET.getRow(4);
		Cell CELL = ROW.getCell(2);
		String ORGNAME = CELL.getStringCellValue();
		
		Row ROW1 = SHEET.getRow(4);
		Cell CELL1 = ROW1.getCell(3);
		String ORGNAME1 = CELL1.getStringCellValue();
		System.out.println(ORGNAME1);
		
		//step2:Launch the browser
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
			System.out.println("Chrome Browser launched successfully");
		}
		else
			if(BROWSER.equalsIgnoreCase("firefox"))
			{
				driver=new FirefoxDriver();
				System.out.println("Firefox driver launched succesfully");
			}
			else
			{
				System.out.println("Invalid browser");
				driver=new ChromeDriver();
				System.out.println("Default Chrome Browser launched successfully");
				
			}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000,TimeUnit.SECONDS);
		driver.get(URL);
		
		//Step 3: login to App
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();		
		
		//Step 4: navigate to organizations
		driver.findElement(By.linkText("Organizations")).click();

		//step5: Click on create organisation
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//step6:Enter the all mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME+RANDOM);
		
		//step7:Choose Healthcare in Industry dropdown
		WebElement ele =driver.findElement(By.name("industry"));
		Select s=new Select(ele);
		s.selectByValue(ORGNAME1);
		
		//step8: Save 
		driver.findElement(By.name("button")).click();
		
		//Step 9: logout
				WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				
				Actions a=new Actions(driver);
				a.moveToElement(element).perform();
				//Thread.sleep(3000);
				
				driver.findElement(By.linkText("Sign Out")).click();
				
	}

}
