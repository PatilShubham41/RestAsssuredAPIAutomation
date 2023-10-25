package day4.rest.assured;

import static io.restassured.RestAssured.given;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ParsingJSONResponse {
	
	//@Test
	public void validatePriceLimit()
	{
		int minPrice = 900, maxPrice = 1000;
		Response res = given()
			.baseUri("https://api.escuelajs.co/api/v1/")
			.pathParam("product", "products")
			.queryParam("price_min", minPrice)
			.queryParam("price_max", maxPrice)
			
		.when()
			.get("/{product}");
		
		//res.then().statusCode(200).log().body();
		
		
		
		JSONArray respJSON = new JSONArray(res.asString());
		
		for(int i =0 ; i<respJSON.length();i++)
		{
			JSONObject product = respJSON.getJSONObject(i);
			int price = product.getInt("price");
			Assert.assertEquals(price>= minPrice, price <= maxPrice);
			System.out.println(product.getString("title")+" - "+price+" : Pass");
			
		}
	
		
	}
	
	//@Test
	public void categoryNavigation()
	{
		int minPrice = 900, maxPrice = 1000;
		
		Response resp = given()
							.baseUri("https://api.escuelajs.co/api/v1/")
							.basePath("products")
							.queryParam("price_min", minPrice)
							.queryParam("price_max", maxPrice)
						.when()
							.get();
		
		JSONArray respJSONArray = new JSONArray(resp.asString());
		
		for(int i =0; i<respJSONArray.length(); i++)
		{
			JSONObject categoryJSON = respJSONArray.getJSONObject(i).getJSONObject("category");
			
			System.out.println(categoryJSON.get("name"));
		}
		
	}
	
	@Test
	public void getFieldArray()
	{
		int minPrice = 900, maxPrice = 1000;
		Response resp = given()
			.baseUri("https://api.escuelajs.co/api/v1/")
			.basePath("products")
			.queryParam("price_min", minPrice)
			.queryParam("price_max", maxPrice)
		.when()
			.get();
		
		
		JSONArray respJSONArray = new JSONArray(resp.asString());
		
		for(int i =0; i < respJSONArray.length() ; i++)
		{
			JSONObject product = respJSONArray.getJSONObject(i);
			JSONArray imageArray = (JSONArray)product.get("images");
			
			for(int j =0; j<imageArray.length(); j++) {
				System.out.println(imageArray.get(j));
			}
		}
	}
	
	@Test
	public void validateNumberOfItemPerPage()
	{
		Response res = given()
			.baseUri("https://reqres.in/api/")
			.basePath("users")
			.queryParam("page", 2)
		.when()
			.get();
		
		
		JSONObject resJSON = new JSONObject(res.asString());
		
		int perPageCountGiven = resJSON.getInt("per_page");
		
		JSONArray dataArray = resJSON.getJSONArray("data");
		int listedItemsOnPage = dataArray.length();
		
		Assert.assertEquals(perPageCountGiven, listedItemsOnPage);
	}

}
