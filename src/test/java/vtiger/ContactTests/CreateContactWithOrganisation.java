package vtiger.ContactTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
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

public class CreateContactWithOrganisation {
	
	private static final String TimeUnits = null;

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		 WebDriver driver;
		 Random r=new Random(100);
		 int RANDOM = r.nextInt();
		 
		 //step1:Load the file into input stream
		 FileInputStream fise=new FileInputStream(".\\src\\test\\resources\\Testdata.xlsx");
          Workbook wb = WorkbookFactory.create(fise);
          Sheet sh = wb.getSheet("Contacts");
          Row row = sh.getRow(4);
         Cell c = row.getCell(2);
        String ORGNAME = c.getStringCellValue();
        System.out.println(ORGNAME);
        
        FileInputStream fisp=new FileInputStream(".\\src\\test\\resources\\commonData.properties");
        Properties pobj=new Properties();
        pobj.load(fisp);
        String BROWSER = pobj.getProperty("browser");
       String URL = pobj.getProperty("url");
       String USERNAME = pobj.getProperty("username");
       String PASSWORD = pobj.getProperty("password");
       
       //step2: Launch the browser
       if(BROWSER.equalsIgnoreCase("Chrome"))
       {
    	   driver=new ChromeDriver();
    	   System.out.println("Chromebrowser launched successfully");
       }else
    	   if(BROWSER.equalsIgnoreCase("firefox"))
    	   {
    		   driver=new FirefoxDriver();
    		   System.out.println("Firefox driver launched successfully");
    		   
    	   }else
    	   {
    		   System.out.println("Invalid browser");
    		   driver=new ChromeDriver();
        	   System.out.println("Chromebrowser launched successfully");
    		   
    	   }
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
       driver.get(URL);
       
       //step3: Login to app
       driver.findElement(By.name("user_name")).sendKeys(USERNAME);
       driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
       driver.findElement(By.id("submitButton")).click();
       
		//step4:Navigate to Contacts
       driver.findElement(By.linkText("Contacts")).click();
       driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
       driver.findElement(By.name("lastname")).sendKeys(ORGNAME+RANDOM);
       
       //step5:select an existing organisation name
       driver.findElement(By.xpath("//img[@alt='Select']")).click();
       
       String parent = driver.getWindowHandle();
       Set<String> wind = driver.getWindowHandles();
       for(String id:wind)
       {
    	   driver.switchTo().window(id);
       }
      	
		driver.findElement(By.id("15")).click();
		driver.switchTo().window(parent);
		
		//step 6:save
		driver.findElement(By.name("button")).click();
		
		//step7:logout
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(driver);
		a.moveToElement(element).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
	}
	

}
