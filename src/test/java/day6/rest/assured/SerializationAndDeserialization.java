package day6.rest.assured;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import day2.rest.assured.StudentPOJO;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.IOException;

public class SerializationAndDeserialization {

	// @Test
	public void serializePOJO() throws JsonProcessingException {
		String[] courses = { "Python", "Web developement" };

		StudentPOJO stud = new StudentPOJO("abc", "France", "98746538923", courses);

		ObjectMapper objMapper = new ObjectMapper();

		String jsonString = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stud);

		System.out.println(jsonString);

	}

	@Test
	public void deserializeResponse() throws IOException {
		Response res = when().get("http://localhost:3000/students");

		JSONArray studentsJSON = new JSONArray(res.asString());

		System.out.println(studentsJSON.getJSONObject(2).toString());

		for (int i = 0; i < studentsJSON.length() - 1; i++) {
			JSONObject student = studentsJSON.getJSONObject(i);
			System.out.println(student.toString());

			ObjectMapper objmap = new ObjectMapper();

			objmap.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			StudentPOJO studObj = objmap.reader().readValue(student.toString(), StudentPOJO.class);

			System.out.println(studObj);

		}
	}

}
