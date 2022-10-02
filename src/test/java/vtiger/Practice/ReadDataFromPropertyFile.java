package vtiger.Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertyFile {

	public static void main(String[] args) throws IOException {
		
		//Step1: Load the file into File input stream
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		
		//step2:create object of properties from java
		Properties pobj=new Properties();
		
		//step3: load the file to properties object
		pobj.load(fis);
		
		//step4:read data through the key
		String BROWSER = pobj.getProperty("browser");
		System.out.println(BROWSER);
		
		String URL = pobj.getProperty("url");
		System.out.println(URL);

	}

}
