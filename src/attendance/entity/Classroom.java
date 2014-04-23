package attendance.entity;

import java.util.ArrayList;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Classroom {
	@Id Long id;

	String roomNumber;
	String classTitle;
	int classUnique;
	double latitude;
	double longitude;
	ArrayList<String> days;
	ArrayList<Integer> times;

	
	static {
		ObjectifyService.register(Classroom.class);
	}
	
	public Classroom(){
		this.roomNumber = null;
		this.classTitle = null;
		this.classUnique = 0;
		this.latitude = 0.0;
		this.longitude = 0.0;
		this.days = null;
		this.times = null;
	}
	
	public Classroom(String roomNumber, String classTitle, int classUnique){
		this.roomNumber = new String(roomNumber); 
		this.classTitle = new String(classTitle);
		this.classUnique = classUnique;
		this.latitude = 30.290408;
		this.longitude = -97.736172;
		this.days = new ArrayList<String>();
		days.add("M");
		days.add("W");
		days.add("F");
		this.times = new ArrayList<Integer>();
		times.add(900);
	}
	
	public String getClassTitle() {
		return classTitle;
	}
	public void setClassTitle(String classTitle) {
		this.classTitle = classTitle;
	}
	
	public int getClassUnique() {
		return classUnique;
	}
	public void setClassUnique(int classUnique) {
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
