package attendance.servlet;
 
import attendance.entity.Course;
import attendance.entity.Professor;
import attendance.entity.Student;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.apphosting.api.DatastorePb.DatastoreService;
import com.googlecode.objectify.*;
import com.googlecode.objectify.cmd.Query;

import static com.googlecode.objectify.ObjectifyService.ofy; 

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@SuppressWarnings("unused")
public class InitServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Register the class in the servlet system
	static {
		ObjectifyService.register(Professor.class);
		ObjectifyService.register(Student.class);
		ObjectifyService.register(Course.class);
	}

	// doPost Function
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		if (req.getParameter("initButton") != null) {
			
			ArrayList<String> students = new ArrayList<String>();
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
			
			Professor newProfessor = new Professor (req.getParameter("email"), students);
			ofy().save().entities(newProfessor).now();
			
//			Query<Professor> fetched = ofy().load().type(Professor.class).filter("email", req.getParameter("email") );
//			Professor thisProfessor = null;
//			for (Professor professor : fetched){
//				thisProfessor = professor;
//			}
			
			
			ArrayList<String> days = new ArrayList<String>();
			days.add("M");
			days.add("W");
			days.add("F");
			ArrayList<Integer> times = new ArrayList<Integer>();
			times.add(900);
			Course newCourse = new Course ("CPE 2.238", "Software Development", "12345", 
												days, times);
			ofy().save().entities(newCourse).now();
			Course newCourse2 = new Course ("CPE 2.238", "Basket Weaving", "67890", 
					days, times);
			ofy().save().entities(newCourse2).now();
		}
		resp.sendRedirect("/");
		
	}
}