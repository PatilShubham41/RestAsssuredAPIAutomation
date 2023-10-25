package day8.rest.assured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	
	@Test
	public void updateUser(ITestContext context)
	{
		
		JSONObject updatedData = new JSONObject();
		Faker fake = new Faker();
		updatedData.put("name", fake.name().fullName());
		updatedData.put("email", fake.internet().emailAddress());
		updatedData.put("gender", "female");
		updatedData.put("status", "active");
		
		
		given()
			.baseUri("https://gorest.co.in")
			.basePath("public/v2/users")
			.headers("Authorization", "Bearer "+ context.getSuite().getAttribute("token"))
			.headers("Content-Type", "application/json")
			.pathParam("id", context.getSuite().getAttribute("id"))
			.body(updatedData.toString())
		.when()
			.put("/{id}")
		.then()
			.statusCode(200)
			.body("id", equalTo(context.getSuite().getAttribute("id")))
			.body("status", equalTo("active"))
			.body("gender", equalTo("female"));
		
	}

}
