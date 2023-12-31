package framework;

import java.time.LocalDateTime;
import static io.restassured.RestAssured.given;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.config.SSLConfig;
import org.testng.annotations.Test;


public class put_ref {

	public static void main(String[] args) {
	      //declear the baseURL
		RestAssured.baseURI="https://reqres.in/";
		//declear the request boy string veriables
		String RequestBody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		//declear expected result
		JsonPath JspRequest =new JsonPath(RequestBody);
		String Req_name = JspRequest.getString("name");
		String Req_job = JspRequest.getString("job");
		LocalDateTime currentdate= LocalDateTime.now();
		String expecteddate= currentdate.toString().substring(0,11);
		//declear the given,when,then method
		
		String ResponseBody = given().header("Content-Type","application/json").body(RequestBody).when().put("api/users/2").then().extract().response().asString();
		
		System.out.println(ResponseBody);
		//create an object of json path to parse the responsebody
		
		JsonPath JspResponse= new JsonPath(ResponseBody);
		String Res_name =JspResponse.getString("name");
		String Res_Job = JspResponse.getString("job");
		String Res_updatedAt = JspResponse.getString("updatedAt");
		Res_updatedAt = Res_updatedAt.substring(0,11);
				
		//validate responsebody parameters
		
		Assert.assertEquals(Res_name, Req_name);
		Assert.assertEquals(Res_Job, Req_job);
		Assert.assertEquals(Res_updatedAt, expecteddate);
		
	}

	

}