package day8.rest.assured;

import org.testng.annotations.Test;
import org.testng.ITestContext;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GetUser {
	
	
	@Test
	public void getUser(ITestContext context)
	{
		
		given()
			.headers("Authorization", "Bearer "+context.getSuite().getAttribute("token"))
			.baseUri("https://gorest.co.in")
			.basePath("public/v2/users")
			.pathParam("id", context.getSuite().getAttribute("id"))
		.when()
			.get("/{id}")
		.then()
			.statusCode(200)
			.body("id", equalTo(context.getSuite().getAttribute("id")));
		
	}

}
