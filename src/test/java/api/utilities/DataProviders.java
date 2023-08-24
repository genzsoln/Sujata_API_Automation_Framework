package api.utilities;

import java.io.IOException;
import api.utilities.XLUtility;
import org.testng.annotations.DataProvider;

public class DataProviders 
{	
	//get all data from Excelsheet
	//suppose any perticulat method is define with @DataProvider anotation
	//that method generating the data and passing the data to another test method in a project
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException 
	{
		String path=System.getProperty("user.dir")+"//testData//userData.xlsx"; //getting data file here , this is currrent prj location
		XLUtility x1=new XLUtility(path);  //excel utility obj
		
		//find total num of rows and column
		int rownum=x1.getRowCount("Sheet1");
		int colcount=x1.getCellCount("Sheet1", 1);
		
		String apidata[][]=new String[rownum][colcount]; //created 2D array & passing here row and column
		
		for(int i=1;i<=rownum;i++)   //get the data fromsheet & send data to 2D array
		{
			for(int j=0;j<colcount;j++)
			{
				apidata[i-1][j]=x1.getCellData("Sheet1", i, j);
			}
		} // after this loop 2D array having some data
		return apidata;
	}
	
	//get only user name fromexcelsheet 
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//userData.xlsx";
		XLUtility x1=new XLUtility(path);
		
		int rownum=x1.getRowCount("Sheet1");
		
		String apidata[]=new String[rownum]; //return only name 1D array used here 
		
		for(int i=1;i<=rownum;i++)
		{
			apidata[i-1]=x1.getCellData("Sheet1", i, 1);
		}
		return apidata;
	}
}