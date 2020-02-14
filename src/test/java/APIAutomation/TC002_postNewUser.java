package APIAutomation;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_postNewUser {
	@Test
	public void newUserCreation(){
	    //Specify the base URI
		RestAssured.baseURI="https://reqres.in/";
		// Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
        //request PAYload sending along with post request
		JSONObject requestParams=new JSONObject();
		
		requestParams.put("name","morpheus12387");
		requestParams.put("job","leader12387");
		
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParams.toJSONString());
		
		//Response Object
		Response response=httpRequest.request(Method.POST,"api/users");
		
		//status code validation
				int statusCode=response.getStatusCode();
				System.out.println("Status Code is : "+statusCode);
				Assert.assertEquals(statusCode, 201);
				
				// status line validation
				String statusLine=response.getStatusLine();
				System.out.println("Status Line is :"+statusLine);
				Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
			/*//Success code validation
				String successCode=response.jsonPath().get("SuccessCode");
				Assert.assertEquals(successCode,null);*/
				//Deserialization
				userResponse usrResponse=response.as(userResponse.class);
				Assert.assertEquals(null,usrResponse.Successcode);
		
	}

}
