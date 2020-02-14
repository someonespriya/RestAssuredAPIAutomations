package APIAutomation;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC001_getUsersDetails {
	@Test
	public void getNameDetails()
	{
		//Specify Base URI
		RestAssured.baseURI="https://reqres.in/";
		
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
	
		//Response Object
		Response response=httpRequest.request(Method.GET,"api/users?page=2");
		
		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		
		//status code validation
		int statusCode=response.getStatusCode();
		System.out.println("Status Code is : "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		// status line validation
		String statusLine=response.getStatusLine();
		System.out.println("Status Line is :"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}	

}
