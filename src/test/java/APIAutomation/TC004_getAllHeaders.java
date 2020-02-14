package APIAutomation;



import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_getAllHeaders {
	
	//Specify Base URI
@Test	
public void  getAllHeaders()
{
	//Specify Base URI
	RestAssured.baseURI="https://reqres.in";
	
	//Request Object
	RequestSpecification httpRequest=RestAssured.given();
	
	//Response object 
	Response response=httpRequest.request(Method.GET,"api/users?page=2");
	
	//Response body
	String responseBody=response.getBody().asString();
	System.out.println("Response Body :"+responseBody);
	
	
	//Get All Headers from the response
	Headers allHeaders=response.getHeaders();

	for(Header header:allHeaders)
	{
	System.out.println(header.getName()+"  -----> "+header.getValue());
	}

}
}
