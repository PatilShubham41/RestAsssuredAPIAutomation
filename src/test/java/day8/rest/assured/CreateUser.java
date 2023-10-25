package day8.rest.assured;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {
	
	@Test
	public void createUser(ITestContext context)
	{
		
		String token = "262543d4a0fcb6b827677c0ccbf31b91c784486f6c9207fede457204f7f6d3c1";
		Faker faker = new Faker();
		
		JSONObject userData = new JSONObject();
		
		userData.put("name", faker.name().fullName());
		userData.put("email", faker.internet().emailAddress());
		userData.put("gender", "male");
		userData.put("status", "inactive");
		
		int id = given()
			.baseUri("https://gorest.co.in")
			.basePath("public/v2/users")
			.headers("Authorization", "Bearer "+token)
			.headers("Content-Type", "application/json")
			.body(userData.toString())
		.when()
			.post().body().jsonPath().getInt("id");
		
		
		context.getSuite().setAttribute("id", id);
		context.getSuite().setAttribute("token", token);
	
		
	}
	

}
