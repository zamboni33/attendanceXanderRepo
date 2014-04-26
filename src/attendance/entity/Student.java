
package attendance.entity;

import java.util.HashMap;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.lang.String;

import javax.jdo.annotations.Persistent;

@Entity
public class Student {
	@Id String email;
	boolean registered;
	int courseCount;
	boolean recordLocation;
	double latitude;
	double longitude;
	
//	// Prelim idea: Pass day string as "YYYY-MM-DD". Preserves sorting ability.
	@Persistent(serialized="true")
	HashMap<String, String> legend;
	@Persistent(serialized="true")
	HashMap<String, Boolean> course1;
	@Persistent(serialized="true")
	HashMap<String, Boolean> course2;
	@Persistent(serialized="true")
	HashMap<String, Boolean> course3;
	@Persistent(serialized="true")
	HashMap<String, Boolean> course4;
	@Persistent(serialized="true")
	HashMap<String, Boolean> course5;
	
	/** Use this method to normalize email addresses for lookup */
	public static String normalize(String email) {
		return email.toLowerCase();
	}	
	
	static {
		ObjectifyService.register(Student.class);
	}
	
	public Student(){}
	
	public Student (String email, String course)
	{
		this.email = email;
		this.registered = false;
		this.recordLocation = false;
		this.latitude = 0.0;
		this.longitude = 0.0;
		
		this.legend = new HashMap<String, String>();   // CourseName, Course1
		this.course1 = new HashMap<String, Boolean>();
		this.course2 = new HashMap<String, Boolean>();
		this.course3 = new HashMap<String, Boolean>();
		this.course4 = new HashMap<String, Boolean>();
		this.course5 = new HashMap<String, Boolean>();
		this.setCourseMap(course);
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getRegistered() {
		return registered;
	}
	public void setRegistered(boolean eval) {
		this.registered = eval;
	}
	
	public boolean getAttendance(){
		return this.recordLocation;
	}

	public void startAttendance(){
		this.recordLocation = true;
	}
	public void stopAttendance(){
		this.recordLocation = false;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return this.longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}	
	
	public void setCourseMap(String course){
		this.courseCount += 1;
		String temp = new String("course" + courseCount);
		this.legend.put(course, temp);
	}
	
	// Decypher map key
	
	public HashMap<String, Boolean> getAttendanceMap(String course){
		String result = this.legend.get(course);
		if(result.equals("course1")){
			return this.course1;
		}
		else if(result.equals("course2")){
			return this.course2;
		}
		else if(result.equals("course3")){
			return this.course3;
		}
		else if(result.equals("course4")){
			return this.course4;
		}
		else if(result.equals("course5")){
			return this.course5;
		}
		// WTF Happened!
		return null;
	}
	
	// Altering Map Data
	
	public void assignPresent(HashMap<String, Boolean> attendance, String day) {
		attendance.put(day, true);
	}
	
	public void assignAbsent(HashMap<String, Boolean> attendance, String day) {
		attendance.put(day, false);
	}
	
	// Access Map Data
	
	public List<String> getKeys (HashMap<String, Boolean> attendance) {
		System.out.println("Retrieving Keys");
		Set<String> keySet = attendance.keySet();
		List<String> keys = new ArrayList<String>(keySet);
		return (keys);
	}		
}