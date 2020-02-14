package APIAutomation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC007_get_RequestAuthorization {
	@Test
	public void authorizationTest(){
		
		        //Specify base uri
				RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
				
				//Basic Authentication
				PreemptiveBasicAuthScheme authScheme=new PreemptiveBasicAuthScheme();
				authScheme.setUserName("ToolsQA");
				authScheme.setPassword("TestPassword");
				
				RestAssured.authentication=authScheme;
				
				//Request Object
				RequestSpecification httpRequest=RestAssured.given();
				
				//Response Object
				Response response=httpRequest.request(Method.GET,"/");
				int statusCode=response.getStatusCode();
				System.out.println(statusCode);
				Assert.assertEquals(statusCode, 200);
				
				String responseBody=response.getBody().asString();
				System.out.println("Response Body : "+responseBody);
		
	}
	

}
