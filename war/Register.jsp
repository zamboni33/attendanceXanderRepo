<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>

<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.googlecode.objectify.Objectify" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="attendance.entity.Professor" %>
<%@ page import="attendance.entity.Student" %>
<%@ page import="attendance.entity.Classroom" %>
 
<html>
 
 <head>
 <link type="text/css" rel="stylesheet" href="main.css">
 
 <style type="text/css">
#map_canvas {display:none;}
</style>
 
<script
    src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>

 </head>
 
  <body>
 
  	<div class="header">
		Attendance Application Take 2
	</div>
 
	<div class="main-wrap">	
	
		<div class="container">
		
			<div class="menu">
				<%
				    UserService userService = UserServiceFactory.getUserService();
				    User user = userService.getCurrentUser();
				%>
				<p>Hello, ${fn:escapeXml(user.nickname)}! <br>
				Not you?
				
				<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>
				 		<input type="button" onClick="SignOut();" value="Sign Out" />
						<script>
							function SignOut(){
								window.location.href=(" <%= userService.createLogoutURL(request.getRequestURI())%> ")
								window.location.href= "/NotInDB.jsp";
							}
						</script>
			</div>
			
			<div class="content">
			
				    It looks like you haven't registered!
				    
<!-- 				    TODO -->

<!-- 				    <form action="/Register" method="post"> -->
<!-- 					<form> -->
						<h3>Professor | Register</h3>
						<br>
						<h4>First Name:</h4>
							<div>
								<input name="first" rows="1" cols="60"></input>
							</div>
						<h4>Last Name:</h4>
							<div>
								<input name="last" rows="1" cols="60"></input>
							</div>
						<h4>Course name:</h4>
							<div>
							<%
							    ObjectifyService.register(Classroom.class);
								List<Classroom> classrooms = ObjectifyService.ofy().load().type(Classroom.class).list();
							    if (classrooms.isEmpty()) {
							%>
							        <p>The courses are empty.</p>
							<%
							        // This should re-direct.
							    } 
							    else {
							%>
							        <select class="form-control" id="courseDropDown" name="courseDropDown" style="width: 500px">
							<%
							        int i = 0;
							        for (Classroom classroom : classrooms) {
							            pageContext.setAttribute("course_name", classroom.getClassTitle());
							 %>
							        	<option value=" ${fn:escapeXml(course_name)} "> ${fn:escapeXml(course_name)}
							            </option>
							 <%
							        }
							    }				
							 %>	
							    </select>	
							</div>
							<h4>Course Location:</h4>
							<div>
								<input name="latitude" rows="1" cols="60" readonly></input>
								<input name="longitude" rows="1" cols="60"readonly></input>
							</div>
							<div>
							
						<br>
						<br>
						<div>
							<input name="registerClass" type="button" rows="1" cols="60" value="Submit"></input>
						</div>
<!-- 					</form> -->
					
					<button onclick="displayMap()">Show Map</button>
					<input name="locationButton" type="button" rows="1" cols="60" value="Use Current Location"></input>
					</div>
					
					<div id="map_canvas" style="height:300px; width:500px"></div>
					
					<script>
					var map;
					function displayMap() {
					    document.getElementById('map_canvas').style.display="block";
					    initialize();
					}
					function initialize() {
					  var mapOptions = {
					    zoom: 8,
					    center: new google.maps.LatLng(30.2500, -97.7500)
					  };
					  map = new google.maps.Map(document.getElementById('map_canvas'),
					      mapOptions);
					}
					
					google.maps.event.addDomListener(window, 'load', initialize);
					google.maps.event.addDomListener(window, "resize", function() {
						 var center = map.getCenter();
						 google.maps.event.trigger(map, "resize");
						 map.setCenter(center); 
						});
					</script>
			</div>
			
			
		</div>
	</div>

	<div class="footer">
		Software Development 461L
	</div>


  </body>
</html>
