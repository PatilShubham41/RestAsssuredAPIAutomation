package day6.rest.assured;

import static io.restassured.RestAssured.when;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;


public class JSONSchemaValidation {
	
	
	@Test
	public void jsonSchemaValidation()
	{
		when()
			.get("http://localhost:3000/students")
		.then()
			
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("StudentsSchema.json"));
	}

	
}
