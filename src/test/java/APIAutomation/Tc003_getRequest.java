package APIAutomation;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Tc003_getRequest {
	@Test
	public void validateHeader()
	{
		
		//Specify Base URI
		RestAssured.baseURI="https://reqres.in/";
		
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		//Response Object
		Response response=httpRequest.request(Method.GET,"api/users/page/2");
		
		String responseBody=response.getBody().asString();
		System.out.println("Response Bodyis : "+responseBody);
		
		//Validating Headers
		String contentType=response.getHeader("Content-Type");
		System.out.println("Content Type is  :"+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		
		
	/*	String contentEncoding=response.header("Content-Encoding");
		System.out.println("Content Encoding is  :"+contentEncoding);
		Assert.assertEquals(contentEncoding,"gzip");*/
		
		String connection=response.header("Connection");
		System.out.println("connection is  :"+connection);
		Assert.assertEquals(connection,"keep-alive");
	
	}

}
