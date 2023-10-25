package day2.rest.assured;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/*
 *  01. using Hashmaps
 *  02. using org.json
 *  03. using POJO (Plain Old Java Object)
 *  04. External JSON file
 * 
 * */

public class DifferentWaysToCreatePOSTRequestBody {
	
	private int studId;

	@Test
	public void postByHashMap()
	{
		HashMap<Object, Object> data = new HashMap<Object, Object>();
		
		data.put("name", "Scotty");
		data.put("location", "ScottLand");
		data.put("phone", "7568392009");
		
		String coursesArr[] = {"C", "C++"};
		data.put("courses", coursesArr);
		
		
		
		Response res=
			given()
				.contentType("application/json")
				.body(data)
			
			.when()
				.post("http://localhost:3000/Students");
			
		studId = res.jsonPath().getInt("id");
			
		res.then()
				.statusCode(201)
				.body("name", equalTo("Scotty"))
				.body("location", equalTo("ScottLand"))
				.body("courses[0]", equalTo("C"))
				.body("courses[1]", equalTo("C++"));
	}
	
	
	@Test
	public void postByJSONorg()
	{
		JSONObject data = new JSONObject();
		
		String courseArr[] = {"Java", "J#"};
		data.put("name", "byJSON");
		data.put("location", "JSONville");
		data.put("phone", "7464839323");
		data.put("courses", courseArr);
		
		Response res = 
				given()
					.contentType("application/json")
					.body(data.toString())
				.when()
					.post("http://localhost:3000/Students");
		
		
		studId = res.jsonPath().getInt("id");
		
		res.then()
			.statusCode(201)
			.body("name", equalTo("byJSON"))
			.body("location", equalTo("JSONville"))
			.body("courses[1]", equalTo("J#"));
			
		
	}
	
	
	@Test
	public void postByPOJO()
	{
		StudentPOJO data = new StudentPOJO();
		data.setName("byPOJO");
		data.setLocation("POJOville");
		data.setPhone("1234456723");
		
		String courseArr[] = {"C", "C++"};
		data.setCourses(courseArr);
		
		
		Response res = 
				given()
					.contentType("application/json")
					.body(data)
				.when()
					.post("http://localhost:3000/Students");
		
		studId = res.jsonPath().getInt("id");
		 
		res.then()
			.body("name", equalTo("byPOJO"))
			.body("courses[0]", equalTo("C"))
			.statusCode(201);
			
	}
	
	
	
	@Test
	public void postByJSONFile() throws FileNotFoundException
	{
		File f = new File(".\\body.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		Response res = 
				given()
					.contentType("application/json")
					.body(data.toString())
				.when()
					.post("http://localhost:3000/Students");
		
		studId = res.jsonPath().getInt("id");
		
		res.then()
			.body("name", equalTo("byJSONFile"))
			.statusCode(201);
	}
	
	
	@AfterMethod
	public void deleteStudent()
	{
		when()
			.delete("http://localhost:3000/Students/"+studId)
		.then()
			.statusCode(200);
		
	}
	
	
	
	
	
	
	
}
