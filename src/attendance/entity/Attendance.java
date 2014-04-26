package attendance.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.jdo.annotations.Persistent;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Attendance {

	@Id String attendanceKey;
	@Persistent(serialized="true")
	HashMap<String, Boolean> attendance;
	
	public Attendance(){}
	
	public Attendance(String attendanceKey){
		this.attendanceKey = attendanceKey;
		this.attendance = new HashMap<String, Boolean>();
	}
	
	public String getAttendanceKey(){
		return this.attendanceKey;
	}
	
	public void setAttendanceKey(String key){
		this.attendanceKey = key;
	}

	// Altering Map Data
	
	public void assignPresent(String day) {
		attendance.put(day, true);
	}
	
	public void assignAbsent(String day) {
		attendance.put(day, false);
	}
	
	// Access Map Data
	
	public List<String> getKeys () {
		System.out.println("Retrieving Keys");
		Set<String> keySet = this.attendance.keySet();
		List<String> keys = new ArrayList<String>(keySet);
		return (keys);
	}		
}
