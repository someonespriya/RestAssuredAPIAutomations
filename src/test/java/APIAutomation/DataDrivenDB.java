package APIAutomation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;


public class DataDrivenDB {
	
		
		public static void main (String args[]) throws Exception, IOException {
			
			// Run the main test
			runTest();
		}
		
		public static void runTest() throws IOException, ClassNotFoundException {
			
			try {
				
				// Retrieve database connection properties from the properties file
				String driver = DBDrivenProperties.getProperty("db.driver");
				String dburl = DBDrivenProperties.getProperty("db.url");
				String dbname = DBDrivenProperties.getProperty("db.dbname");
				String dbquery = DBDrivenProperties.getProperty("db.query");
				String dbuser = DBDrivenProperties.getProperty("db.username");
				String dbpassword = DBDrivenProperties.getProperty("db.password");
				
				// Load the MySQL JDBC driver
				Class.forName(driver);
				
				// Create a connection to the MySQL database
				Connection conn = DriverManager.getConnection(dburl + dbname, dbuser, dbpassword);
				
				// Create a statement to be executed
				Statement stmt = conn.createStatement();
				
				// Execute the query
				ResultSet rs = stmt.executeQuery(dbquery);
				
				// Loop through the query results and run the REST service test for every row
				while (rs.next()) {
					String zipcode = rs.getString("zipcode");
					String city = rs.getString("city");
					String state = rs.getString("state");
					try {
						testService(zipcode,city,state);
					} catch (JSONException e) {
						System.out.println(e.toString());
					}
				}
				
				// Close the database connection
				conn.close();
				
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
			
		}
		
		public static void testService(String zipcode, String city, String state) throws IOException, JSONException {
			
			System.out.println("Validating response for " + DBDrivenProperties.getProperty("rest.url") + zipcode + "...");
			
			// Retrieve the base URL for the REST service and append the zipcode parameter
			String restURL = DBDrivenProperties.getProperty("rest.url") + zipcode;
			
			// Call the REST service and store the response
			HttpUriRequest request = new HttpGet(restURL);
			HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

			// Convert the response to a String format
			String result = EntityUtils.toString(httpResponse.getEntity());

			// Convert the result as a String to a JSON object
			JSONObject jo = new JSONObject(result);
			
			// Get the array containing the places that correspond to the requested zipcode
			JSONArray ja = jo.getJSONArray("places");
			
			// Assert that the values returned by the REST service match the expected values in our database
			Assert.assertEquals(city, ja.getJSONObject(0).getString("place name"));
			Assert.assertEquals(state, ja.getJSONObject(0).getString("state"));
			
		}
	}