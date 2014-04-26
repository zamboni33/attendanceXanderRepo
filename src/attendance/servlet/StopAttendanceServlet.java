package attendance.servlet;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.lang.Math;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attendance.entity.Classroom;
import attendance.entity.Course;
import attendance.entity.Professor;
import attendance.entity.Student;
import attendance.entity.Attendance;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;


public class StopAttendanceServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    int dayOfWeek;
    int hourOfDay;
    int minuteOfDay;

	public StopAttendanceServlet(){
	}
	
	static {
		ObjectifyService.register(Professor.class);
		ObjectifyService.register(Student.class);
		ObjectifyService.register(Course.class);
		ObjectifyService.register(Classroom.class);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {	
		Calendar c = Calendar.getInstance();
		dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		DateFormat dateFormatCalendar = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String dateCalendar = dateFormatCalendar.format(c.getTime());
        String dateTotal = dateFormat.format(c.getTime());
        String[] timeParts = dateTotal.split(":");
		hourOfDay = Integer.parseInt(timeParts[0]);
		minuteOfDay = Integer.parseInt(timeParts[0]);
		
		List<Course> courses = ObjectifyService.ofy().load().type(Course.class).list();	
		for(Course course : courses ) {
			
			if(course.getDays().contains(dayOfWeek)){
				ArrayList<String> times = course.getTimes();
				for(String time : times){
					String[] parts = time.split(":");
					if(Integer.parseInt(parts[0]) == hourOfDay && Integer.parseInt(parts[1]) == minuteOfDay - 15){
						ArrayList<String> theseStudents = course.getStudents();
						for(String studentEmail : theseStudents){
							Query<Student> students = ofy().load().type(Student.class).filter("email", studentEmail );
							for(Student student : students ) {
								student.stopAttendance();
							
								if(student.getLatitude() != 0.0 && student.getLongitude() != 0.0){
		//							TODO THIS IS WHERE WE RUN THE HAVERSINE AND MARK ATTENDANT OR ABSENT
									Double distance = haversine(course.getLatitude(), course.getLongitude(),
															student.getLatitude(), student.getLongitude());
								
									if(distance < 50){
										String attendanceKey = new String(course.getClassUnique() + student.getEmail());
										Query<Attendance> attendance = ofy().load().type(Attendance.class).filter("attendanceKey", attendanceKey );
										for(Attendance dayTable : attendance){
											dayTable.assignPresent(dateCalendar);
											ofy().save().entities(dayTable).now();
										}
									}
									else{
										String attendanceKey = new String(course.getClassUnique() + student.getEmail());
										Query<Attendance> attendance = ofy().load().type(Attendance.class).filter("attendanceKey", attendanceKey );
										for(Attendance dayTable : attendance){
											dayTable.assignAbsent(dateCalendar);
											ofy().save().entities(dayTable).now();
										}
									}
								}
								else {
									String attendanceKey = new String(course.getClassUnique() + student.getEmail());
									Query<Attendance> attendance = ofy().load().type(Attendance.class).filter("attendanceKey", attendanceKey );
									for(Attendance dayTable : attendance){
										dayTable.assignAbsent(dateCalendar);
										ofy().save().entities(dayTable).now();
									}
								}
								
								student.setLatitude(0.0);
								student.setLongitude(0.0);
								
								ofy().save().entities(student).now();
								
							}
							
						}
					}
				}
			}
		}
		
	}
	
	public Double haversine(Double lat1, Double lon1, Double lat2, Double lon2) {
        // TODO Auto-generated method stub
        final int R = 6371000; // Radius of the earth
        Double latDistance = toRad(lat2-lat1);
        Double lonDistance = toRad(lon2-lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
                   Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * 
                   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double distance = R * c;
         
        return distance;
 
    }
     
    private Double toRad(Double value) {
        return value * Math.PI / 180;
    }
 

}
