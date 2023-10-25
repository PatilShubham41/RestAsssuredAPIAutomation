package day2.rest.assured;

import java.util.Arrays;

public class StudentPOJO {
	
	private String name;
	private String location;
	private String phone;
	private String courses[];
	
	public StudentPOJO(String name, String location, String phone, String[] courses)
	{
		this.name = name;
		this.location = location;
		this.phone = phone;
		this.courses = courses;
	}
	
	public StudentPOJO()
	{
		
	}
	
	@Override
	public String toString()
	{
		String printObject = "Name: "+name+"\nLocation: "+location+"\nPhone: "+phone+"\nCourses: "+Arrays.toString(courses);
		
		return printObject;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String[] getCourses() {
		return courses;
	}
	public void setCourses(String[] courses) {
		this.courses = courses;
	}
	

}
