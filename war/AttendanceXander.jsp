<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
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
		
			<div class="menu">
				<%
				    UserService userService = UserServiceFactory.getUserService();
				    User user = userService.getCurrentUser();
				    if (user != null) {
				    	System.out.println(user);
				%>
								
						<form action="/SignIn" method="get" name="autoForm">
						</form>
						
						<script>
						window.onload = function(){
				  			document.autoForm.submit();
						};
						</script>
					<%
					} 
				    
				    else {
					%>
						<form method="post" action="">
						<input type="text" name="UserName" placeholder="Username" id="UserName" size="16" maxlength="16" /><br>
						<input type="password" name="Password" placeholder="Password" id="Password" size="16" maxlength="16" /><br>
						<input  type="button" value="Sign In" onclick="window.location.href='SignIn.jsp'"/>
						<input  type="button" value="Stock DataStore" onclick="window.location.href='Init.jsp'"/>
						<input name="mapsButton" type="button" rows="1" cols="60" value="Use Map" onClick="locate();"></input>
				<%
				    }
				%>				
				
				
				
				
				</form>
				
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
</html>

