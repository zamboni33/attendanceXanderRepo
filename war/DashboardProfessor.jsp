<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.googlecode.objectify.Objectify" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="com.googlecode.objectify.cmd.Query"%>

<%@ page import="attendance.entity.Professor" %>
<%@ page import="attendance.entity.Student" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link type="text/css" rel="stylesheet" href="main.css">

    <title>Attendance Application Take 2</title>

    <script type="text/javascript" language="javascript" src="attendancexander/attendancexander.nocache.js"></script>
  </head>            
  
                              
  
  <body>
  
 	<div class="header">
		Attendance Application Take 2
	</div>

	<div class="main-wrap">	
		<div class="container">
		
		<script type="text/javascript">
		window.onload = initializePosition();
		</script>
		
		<%
		    UserService userService = UserServiceFactory.getUserService();
		    User user = userService.getCurrentUser();
		    if (user != null) {
		      pageContext.setAttribute("userEmail", user.getEmail());
		    }
		    
// 			Query<Student> queryStudent = ObjectifyService.ofy().load().type(Student.class)
// 							.filter("email", Student.normalize(user.getEmail()) );
			
// 			for(Student student : queryStudent ) {
// 				if(student.getAttendance() == true){
// 					student.setLatitude((double) pageContext.getAttribute("userLatitude"));
// 					student.setLatitude((double) pageContext.getAttribute("userLatitude"));
// 				}
// 			}	
		%>
			
			
			
		   	<div class="menu">
				<p>Hello, ${fn:escapeXml(userEmail)}!</p> 
				<br>
				
				Not you?
				
				 		<input 	type="button" 
				 				onclick="SignOut();" 
				 				value="Sign Out">
				 				
						<script>
							function SignOut(){
								window.location.href=(" <%=userService.createLogoutURL("/")%> ")
							}
						</script>
			</div>
			
			<div class="content">
			
				   
			
			</div>
			
			
		</div>
	</div>

	<div class="footer">
		Software Development 461L
	</div>


<!--     OPTIONAL: include this if you want history support -->
    <iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position:absolute;width:0;height:0;border:0"></iframe>
    
<!--     RECOMMENDED if your web app will not function without JavaScript enabled -->
    <noscript>
      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
      </div>
    </noscript>

  </body>
  
			<script type="text/javascript">
			var initialLocation;
			var austin = new google.maps.LatLng(30.2500, -97.7500);
			var browserSupportFlag =  new Boolean();
			
			function initializePosition() {
			  var myOptions = {
			    zoom: 6,
			    mapTypeId: google.maps.MapTypeId.ROADMAP
			  };
// 			  var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
			
			  // Try W3C Geolocation (Preferred)
			  if(navigator.geolocation) {
			    browserSupportFlag = true;
			    navigator.geolocation.getCurrentPosition(function(position) {
			      initialLocation = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
// 			      alert(position.coords.latitude);
					pageContext.setAttribute("userLatitude", position.coords.latitude);
					pageContext.setAttribute("userLongitude", position.coords.longitude);
// 			      document.getElementById('latitude').value = position.coords.latitude;
// 			      document.getElementById('longitude').value = position.coords.longitude;
// 			      map.setCenter(initialLocation);
			    }, function() {
			      handleNoGeolocation(browserSupportFlag);
			    });
			  }
			  // Browser doesn't support Geolocation
			  else {
			    browserSupportFlag = false;
			    handleNoGeolocation(browserSupportFlag);
			  }
			
			  function handleNoGeolocation(errorFlag) {
			    if (errorFlag == true) {
			      alert("Geolocation service failed. Placing you in Austin.");
			      initialLocation = austin;
			    } else {
			      alert("Your browser doesn't support geolocation. We've placed you in Austin.");
			      initialLocation = austin;
			    }
// 			    map.setCenter(initialLocation);
			  }
			}		
			</script>  
  
  
  
  
</html>

