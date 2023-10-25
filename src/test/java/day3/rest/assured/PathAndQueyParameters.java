package day3.rest.assured;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

import java.util.Map;

public class PathAndQueyParameters {
	
	
	@Test
	public void pathAndQueryParameters()
	{
		
		given()
			.pathParam("uPath", "users")
			.queryParam("page", 2)
			
		.when()
			.get("https://reqres.in/api/{uPath}")
		
		.then()
			.statusCode(200)
			.log().headers();
		
		
	}
	

	@Test
	public void multipleFiltersForAPI()
	{
		given()
			.pathParam("products", "v1")
			.pathParam("p", "products")
			.queryParam("price_min",100)
			.queryParam("price_max", 1000)
			.queryParam("offset", 10)
			.queryParam("limit", 10)
			.baseUri("https://api.escuelajs.co/api/")
			.basePath("{products}")
		.when()
			.get("/{p}")
		.then()
			.statusCode(200)
			.log().body();
	}
	
	
	@Test
	public void validateCookies()
	{
		given()
			.baseUri("https://www.google.com/")
		.when()
			.get()
		.then()
			.cookie("AEC")
			.cookie("NID")
			.cookie("1P_JAR");
	}
	
	@Test
	public void listAllCookies()
	{
		Map<String, String> cookies = given()
			.baseUri("https://www.google.com/")
		.when()
			.get()
			.getCookies();
		
		for(String cookie : cookies.keySet())
		{
			System.out.println(cookie+" ----> "+cookies.get(cookie));
		}
			
	}
	
	@Test
	public void headersValidation()
	{
		given()
			.baseUri("https://api.escuelajs.co/api/v1/products/")
			.queryParam("price", 100)
		.when()
			.get()
		.then()
			.header("Content-Type", "application/json; charset=utf-8")
			.header("Server", "Cowboy")
			.statusCode(200);
	}

}
