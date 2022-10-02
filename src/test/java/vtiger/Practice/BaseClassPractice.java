package vtiger.Practice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BaseClassPractice {

	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("before suite");
	}
	@BeforeClass
	public void beforeclass()
	{
		System.out.println("before class");
	}
	@BeforeMethod
	public void beforeMethod()
	{
		System.out.println("before method");
	}
	@AfterMethod
	public void afterMethod()
	{
		System.out.println("after method");
	}
	@AfterClass
	public void afterClass()
	{
		System.out.println("after class");
	}
	@AfterSuite
	public void afterSuite()
	{
		System.out.println("after suite");
	}
	
	@Test
	public void test1()
	{
		System.out.println("test1 execute");
	}
	@Test
	public void test2()
	{
		System.out.println("test2 execute");
	}
}
