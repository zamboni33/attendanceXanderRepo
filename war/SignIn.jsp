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

 
<html>
 
  <head>
  <link type="text/css" rel="stylesheet" href="main.css">
  </head>
 
  <body>
 
<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
      pageContext.setAttribute("user", user);
      System.out.println("Logged In");
%>
		<form action="/SignIn" method="get" name="autoForm">

		</form>
		
		<script>
		window.onload = function(){
  			document.autoForm.submit();
		};
		</script>
		
<%
    } else {
    	System.out.println("Logged Out");
%>
		<script>
			window.location.href=("<%= userService.createLoginURL(request.getRequestURI()) %>")
		</script>
<%
    }
    
    System.out.println("After Log In");    

%>

  </body>
</html>