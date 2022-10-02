package vtiger.Practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertPractice {
	@Test
	public void assertPracticeTest1()
	{
		System.out.println("==Test 1 step1==");
		System.out.println("==Test 1 step2==");
		System.out.println("==Test 1 step3==");
		Assert.assertEquals("A","B"); //Hard Assert
		System.out.println("==Test 1 step4==");
	}
	
	@Test
	public void assertPracticeTest2()
	{
		SoftAssert sa=new SoftAssert();
		System.out.println("==Test 2 step1==");
		System.out.println("==Test 2 step2==");
		Assert.assertEquals("A", "A");//hard assert
		System.out.println("==Test 2 step3==");
		sa.assertEquals(0,1);//softassert
		System.out.println("==Test 2 step4==");
		sa.assertAll();
	}
	

}
