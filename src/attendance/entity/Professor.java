
package attendance.entity;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Professor {
	@Id Long id;

	String email;
	boolean registered;
	List<String> students = null;

	
	static {
		ObjectifyService.register(Professor.class);
	}
	
	
	public Professor (String email)
	{

		this.email = email;
		this.registered = false;
		
		this.students = new ArrayList<String>();
		String steven = new String("steven@utexas.edu");
		students.add(steven);
		String jim = new String("jim@utexas.edu");
		students.add(jim);
		String robert = new String("robert@utexas.edu");
		students.add(robert);
		String sally = new String("sally@utexas.edu");
		students.add(sally);
		String rachael = new String("rachael@utexas.edu");
		students.add(rachael);

	}
	public Professor(){}
	
	
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
	
	public List<String> getStudents() {
		return students;
	}
	public void setStudents() {
		// TODO: Do nothing for the time being
	}
}
