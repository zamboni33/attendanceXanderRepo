package attendance.servlet;
 
import attendance.entity.Classroom;
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

import static com.googlecode.objectify.ObjectifyService.ofy; 

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
		ObjectifyService.register(Classroom.class);
	}

	// doPost Function
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
//		Professor newProfessor = new Professor (req.getParameter("first"), req.getParameter("last"), req.getParameter("username"), req.getParameter("password"), req.getParameter("email"), req.getParameter("coursename"));
		req.getParameter("first");
		req.getParameter("last");
		req.getParameter("course_name");
		req.getParameter("latitude");
		req.getParameter("longitude");
		
		resp.getWriter().println(req.getParameter("first"));
		resp.getWriter().println(req.getParameter("last"));
		resp.getWriter().println(req.getParameter("courseDropDown"));
		resp.getWriter().println(req.getParameter("latitude"));
		resp.getWriter().println(req.getParameter("longitude"));
		
		
		
//		ofy().save().entities(newProfessor).now();
	
//		resp.sendRedirect("/DashboardProfessor.jsp");
		
	}
}