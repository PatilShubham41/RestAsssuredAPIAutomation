package day7.rest.assured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONArray;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Authentication {

	// Basic
	// @Test
	public void basicAuthentication() {

		given().auth().basic("postman", "password").when().get("https://postman-echo.com/basic-auth").then()
				.statusCode(200).body("authenticated", equalTo(true));

	}

	// Digest
	// @Test
	public void digestAuth() {
		given().auth().digest("postman", "password").when().get("https://postman-echo.com/basic-auth").then()
				.statusCode(200).body("authenticated", equalTo(true));

	}

	// Preemptive
	// @Test
	public void preeemptiveAuth() {
		given().auth().preemptive().basic("postman", "password").when().get("https://postman-echo.com/basic-auth")
				.then().statusCode(200).body("authenticated", equalTo(true));
	}

	@Test
	public void bearerAuth() {
		String token = "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";

		Response res = given().headers("Authorization", "Bearer " + token).baseUri("https://api.github.com/user/repos")
				.when().get();

		JSONArray githubReposJSON = new JSONArray(res.asString());
		System.out.println(githubReposJSON.length());
		for (int i = 0; i < githubReposJSON.length(); i++) {
			String repoName = githubReposJSON.getJSONObject(i).getString("name");
			System.out.println(i + repoName);
		}

	}

	@Test
	public void OAuth2Auth() {

		String token = "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";

		Response res = given().auth().oauth2(token).baseUri("https://api.github.com/user/repos")

				.when().get();

		JSONArray reposJSON = new JSONArray(res.asString());

		int arrayLength = reposJSON.length();

		for (int i = 0; i < arrayLength; i++) {
			String repoFullName = reposJSON.getJSONObject(i).get("full_name").toString();
			System.out.println(i + ". " + repoFullName);
		}

	}

}
