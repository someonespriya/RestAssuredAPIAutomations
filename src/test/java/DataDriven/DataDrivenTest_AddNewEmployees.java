package DataDriven;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class DataDrivenTest_AddNewEmployees {
	@Test(dataProvider="empdataprovider")
	public void postNewEmployees(String ename,String ejob)
	{
		//Specify base URI
		//RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		RestAssured.baseURI="https://reqres.in";
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		//Transport data along with post request
		
		JSONObject requestParam=new JSONObject();
		/*requestParam.put("name", "priya787867866788");
		requestParam.put("salary", "1,00,000");
		requestParam.put("age", "300");*/
		requestParam.put("name", ename);
		requestParam.put("job", ejob);
		
		//add header string to the request body 
		httpRequest.header("Content-Type","application/json");
		
		//add json to the body of the request
		httpRequest.body(requestParam.toJSONString());
		
		//Response Object
		Response response =httpRequest.request(Method.POST,"/api/users");
		
		//Capture response body
	    String responseBody=response.getBody().asString();
	    
	    System.out.println("Response Body :"+responseBody);
	    
	    Assert.assertEquals(responseBody.contains(ename), true);
	    Assert.assertEquals(responseBody.contains(ejob), true);
	    //Assert.assertEquals(responseBody.contains("300"), true);
	    
	    
	 
	    int statusCode=response.getStatusCode();
	    //System.out.println("Status code : "+statusCode);
	    Assert.assertEquals(statusCode, 201);
	    
	    String statusLine=response.getStatusLine();
	    System.out.println("Status Line : "+statusLine);
	    Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
	    
		
	}
	@DataProvider(name="empdataprovider")
	public String[][] getEmpdata() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/DataDriven/empdata.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colnum=XLUtils.getCellCount(path, "Sheet1", 1);
		String empdata[][]=new String[rownum][colnum];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colnum;j++)
			{
				empdata[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j);
			}
	
		}
		//String empdata[][]={{"priya1","tester1"},{"priya2","tester2"},{"priya3","tester3"}};
		return empdata;
	}
}
