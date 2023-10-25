package day8.rest.assured;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	
	@Test
	public void deleteUser(ITestContext context)
	{
		int id = (Integer) context.getSuite().getAttribute("id");
		
		given()
		.baseUri("https://gorest.co.in")
		.basePath("public/v2/users")
		.headers("Authorization", "Bearer "+context.getSuite().getAttribute("token"))
		.pathParam("id", id)
		.when()
		.delete("/{id}")
		.then()
		.statusCode(204);
		
		
	}

}
