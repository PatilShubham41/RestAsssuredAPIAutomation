package day5.rest.assured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {
	
	
	@Test
	public void validateWithoutCapturingResponse()
	{
		int pageNum = 700;
		
		 given()
		 	.baseUri("http://restapi.adequateshop.com/api/")
		 	.basePath("Traveler")
		 	.queryParam("page", pageNum)
		 .when()
		 	.get()
		 .then()
		 	.statusCode(200)
		 	.body("TravelerinformationResponse.page", equalTo(Integer.toString(pageNum)))
		 	.body("TravelerinformationResponse.per_page", equalTo(Integer.toString(10)))
		 	.log().body();
		
	}
	
	
	@Test
	public void validationWithXMLPath()
	{
		int pageNum = 700;
		Response res = given()
			.baseUri("http://restapi.adequateshop.com/api/")
		 	.basePath("Traveler")
		 	.queryParam("page", pageNum)
		 .when()
		 	.get();
		
		Assert.assertEquals(res.xmlPath().getInt("TravelerinformationResponse.page"), pageNum);
		
		//Making xml object
		
		XmlPath xmlRes = new XmlPath(res.asString());
		
		List<String> travelers = xmlRes.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		
		Assert.assertEquals(travelers.size(), xmlRes.getInt("TravelerinformationResponse.per_page"));
		
		boolean status = false;
		for(String traveler : travelers)
		{
			if(traveler.equals("Himanshu")) {
				status = true;
				break;
			}
		}
		
		Assert.assertTrue(status);
		
	}
	
	
	/*
	 * file upload
	 * 	key point
	 * 		01. get the file with file class
	 * 		02. attach in given section with .multiPart("file", FILE_OBJECT)
	 * 		03. mention the contentType as "multipart/form-data 
	 */

}
