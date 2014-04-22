
package attendance.entity;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
//import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.jdo.annotations.Persistent;

@Entity
public class Student {
	@Id Long id;
	String email;
	boolean registered;
	
	// Prelim idea: Pass day string as "YYYY-MM-DD". Preserves sorting ability.
	@Persistent(serialized="true")
	Map<String, Boolean> attendance; // = new HashMap<String, Boolean>();
	
	static {
		ObjectifyService.register(Student.class);
	}
	
	public Student (String email)
	{
		this.email = email;
		this.registered = false;
		this.attendance = new HashMap<String, Boolean>();
		
		// FOR TESTING PURPOSES ONLY!
		//assignAbsent("2014-03-31");
		
	}

	public Student(){}
	
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
	
	
	// Altering Map Data
	
	public void assignPresent(String day) {
		this.attendance.put(day, true);
	}
	
	public void assignAbsent(String day) {
		this.attendance.put(day, false);
	}
	
	// Access Map Data
	
	public boolean getAttendance(String day) {
		return ( this.attendance.get(day) );
	}
	
	public List<String> getKeys () {
		System.out.println("Retrieving Keys");
		Set<String> keySet = attendance.keySet();
		List<String> keys = new ArrayList<String>(keySet);
		return (keys);
	}
	
	public Set<String> getKeysSafe () {
		System.out.println("Retrieving Keys");
		Set<String> keys = attendance.keySet();
		return (keys);
	}
	
}