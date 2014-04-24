package attendance.entity;

import java.util.ArrayList;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Course {

	String roomNumber;
	String classTitle;
	@Id String classUnique;
	double latitude;
	double longitude;
	ArrayList<String> days;
	ArrayList<Integer> times;
	
	String professor;

	
	static {
		ObjectifyService.register(Course.class);
	}
	
	public Course(){
		this.roomNumber = null;
		this.classTitle = null;
		this.classUnique = null;
		this.latitude = 0.0;
		this.longitude = 0.0;
		this.days = null;
		this.times = null;
		this.professor = null;
	}
	
	public Course(String roomNumber, String classTitle, String classUnique, 
					ArrayList<String> days, ArrayList<Integer> times){
		
		
		this.roomNumber = new String(roomNumber); 
		this.classTitle = new String(classTitle);
		this.classUnique = classUnique;
		this.latitude = 0.0;
		this.longitude = 0.0;
		
		this.professor = null;
		
		this.days = days;
		this.times = times;
	}
	
	// Copy Constructor
	public Course(Course course){
		this.roomNumber = course.getRoomNumber(); 
		this.classTitle = course.getClassTitle(); 
		this.classUnique = course.getClassUnique();
		this.latitude = course.getLatitude(); 
		this.longitude = course.getLongitude();
		this.professor = course.getProfessor();
		this.days = course.getDays();
		this.times = course.getTimes();
	}

	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}	
	
	public String getClassTitle() {
		return classTitle;
	}
	public void setClassTitle(String classTitle) {
		this.classTitle = classTitle;
	}
	
	public String getClassUnique() {
		return classUnique;
	}
	public void setClassUnique(String classUnique) {
		this.classUnique = classUnique;
	}	
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getProfessor() {
		return professor;
	}
	public void getProfessor(Professor professor) {
		this.professor = professor.getEmail();
	}	
	
	public ArrayList<String> getDays() {
		return this.days;
	}
	public void setDays(ArrayList<String> schedule) {
		this.days = schedule;
	}
	
	public ArrayList<Integer> getTimes() {
		return this.times;
	}
	public void setTimes(ArrayList<Integer> schedule) {
		this.times = schedule;
	}
}
