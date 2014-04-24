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
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import static com.googlecode.objectify.ObjectifyService.ofy; 

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.lang.String;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@SuppressWarnings("unused")
public class RegisterServlet extends HttpServlet {
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
		
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
		
		ObjectifyService.register(Professor.class);
		ObjectifyService.register(Student.class);
		
		// Update Professor Entity
		Query<Professor> professors = ofy().load().type(Professor.class)
				.filter("email", Professor.normalize(user.getEmail()) );
		Professor thisProfessor = null;
		
		for(Professor professor : professors ) {
			// Grab professor out of query
				thisProfessor = professor;
		}
		
		thisProfessor.setFirst(req.getParameter("first"));
		thisProfessor.setLast(req.getParameter("last"));
		thisProfessor.setRegistered(true);
		ofy().save().entities(thisProfessor).now();
		////////////////////
		
		List<String> students = thisProfessor.getStudents();
		for(String student : students){
			resp.getWriter().println(student);
			
			Query<Student> queryStudent = ofy().load().type(Student.class)
					.filter("email", Student.normalize(student) );
			
			if(queryStudent.count() != 0){
				Student existingStudent = null;
				for(Student scanStudent : queryStudent ) {
					// Grab professor out of query
					existingStudent = scanStudent;
				}
				existingStudent.setCourseMap(req.getParameter("courseDropDown"));
				ofy().save().entities(existingStudent).now();
			}
			else {
				Student thisStudent = new Student(student, req.getParameter("courseDropDown"));
				ofy().save().entities(thisStudent).now();
			}
		}				
	
//		resp.sendRedirect("/DashboardProfessor.jsp");
		
	}
}