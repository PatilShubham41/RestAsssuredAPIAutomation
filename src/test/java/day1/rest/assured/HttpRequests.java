package day1.rest.assured;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 * given()
 * PRE-REQUESITE:
 * 	content-type, query parameter, cookies, authentication etc
 * 
 * when()
 * 	request types: GET, POST, PUT, DELETE, PATCH etc
 * 
 * then()
 * Validation: 
 * 	response code, body details validation, cookies validation
 * 
 * */

public class HttpRequests {
	
	private int id;
	
	@Test(priority =1)
	public void getUsers()
	{
		when().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("page", equalTo(2)).log().all();
		
	}
	
	
	@Test(priority=2)
	public void createUser()
	{
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("name", "ABC");
		hm.put("job", "XYZ");
		
		
		id = given()
			
			.contentType("application/json")
			.body(hm)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
			
	
	}
	
	
	@Test(priority=3, dependsOnMethods="createUser")
	public void updateUser()
	{
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("name", "DEF");
		hm.put("job", "LMN");
		
		given()
			.contentType("application/json")
			.body(hm)
		
		.when()
			.put("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(200)
			.log()
			.all();
	}
	
	
	@Test(priority=4, dependsOnMethods="createUser")
	public void deleteUser()
	{
		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(204);
	}
	

}
