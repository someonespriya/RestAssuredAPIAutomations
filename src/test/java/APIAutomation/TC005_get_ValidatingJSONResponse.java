package APIAutomation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC005_get_ValidatingJSONResponse {
	@Test
	public void validateJsonResponse()
	{
		//Specify base URI
		RestAssured.baseURI="https://reqres.in/";
		
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		//Response Object
		Response response=httpRequest.request(Method.GET,"api/users/2");
		
		//Print responsebody
		String respnoseBody=response.getBody().asString();
		System.out.println("ResponseBody : "+respnoseBody.toString());
		Assert.assertEquals(respnoseBody.contains("last_name"), true);
		
	
	}

}
