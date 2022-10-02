package vtiger.ContactTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
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

public class CreateContact {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		
		WebDriver driver;
		
		Random r=new Random();
		int RANDOM = r.nextInt(100);
       
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
		Sheet SHEET = wb.getSheet("Contacts");
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
				
			}else
			{System.out.println("Invalid browser");
			driver=new ChromeDriver();
			System.out.println("Chromedriver is default browser");
			}

	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(2000,TimeUnit.SECONDS);
	   driver.get(URL);
	   
	   //Step3: Login to app
	   driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	   driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	   driver.findElement(By.id("submitButton")).click();
	   
	   //Step4:Create contact
	   driver.findElement(By.linkText("Contacts")).click();
	   driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	   driver.findElement(By.name("lastname")).sendKeys(ORGNAME+RANDOM);
	   
		//Step 7: save
		driver.findElement(By.name("button")).click();
		
		//Step 8: logout
		WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		Actions a=new Actions(driver);
		a.moveToElement(element).perform();
		Thread.sleep(3000);
		
		driver.findElement(By.linkText("Sign Out")).click();
		
	   
	}

}
