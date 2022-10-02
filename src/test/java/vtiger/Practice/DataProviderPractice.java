package vtiger.Practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice {

	@Test(dataProvider="product")
	public void addProductToCartTest(String name, String feature, int cost , int qty, String Model)
	{
		System.out.println(name+"-"+feature+"-"+cost+"-"+qty+"-"+Model);
		
	}
	@DataProvider(name="product")
	public Object[][] data()
	{
		Object[][] d=new Object[4][5];//row //col
		
		d[0][0]="samsung";
		d[0][1]="security";
		d[0][2]=30000;
		d[0][3]=10;
		d[0][4]="A80";
		
		d[1][0]="Nokia";
		d[1][1]="usefriendly";
		d[1][2]=10000;
		d[1][3]=20;
		d[1][4]="i10";
		
		d[2][0]="Oppo";
		d[2][1]="Camera";
		d[2][2]=35000;
		d[2][3]=15;
		d[2][4]="v1";
		
		d[3][0]="LG";
		d[3][1]="secure";
		d[3][2]=35000;
		d[3][3]=25;
		d[3][4]="v1";
		
				
		return d;
	}
	@DataProvider(name= "wishlist")
	public Object[][] getD()
	{
		Object[][] d=new Object[2][3];
		return d;
	}
	
}
