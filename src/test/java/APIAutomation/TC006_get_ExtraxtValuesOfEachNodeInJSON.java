package APIAutomation;




import groovy.json.JsonSlurper;

import java.util.Map;

import org.json.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.RestAssured;
import io.restassured.http.Method;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_get_ExtraxtValuesOfEachNodeInJSON {
	@Test
	public void ExtraxtValuesOfEachNodeInJSON(){
		//Specify base uri
		RestAssured.baseURI="https://reqres.in/";
		
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		//Response Object
		Response response=httpRequest.request(Method.GET,"api/users/2");
		
		String responseBody=response.getBody().asString();
		System.out.println("Response Body : "+responseBody);
		
		JSONObject obj = new JSONObject(responseBody);
	    int id = obj.getJSONObject("data").getInt("id");
	    System.out.println("id : "+id);
	    Assert.assertEquals(id, 2);
	    
	    String email=obj.getJSONObject("data").getString("email");
	    System.out.println("email : "+email);
	    Assert.assertEquals(email, "janet.weaver@reqres.in");
	  
	    
	  
	 
	}

}

