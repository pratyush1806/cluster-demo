<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<!DOCTYPE html>
<html lang="en">
  <head>
  	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
  	<title>java cluster demo</title>
  	<link rel="stylesheet" type="text/css" href="css/style.css">
  </head>
  
  <body>
  	<% Map<String, String> hostInfo = (HashMap<String, String>)request.getAttribute("hostInfo"); %>
  	
  	<table>
  		<tr><td>JSESSIONID</td><td><%= request.getSession().getId() %></td></tr>
  		<tr><td>Counter</td><td><%= request.getSession().getAttribute("counter") %></td></tr>
  		<tr><td>Node Name</td><td><%= hostInfo.get("node-name") %></td></tr>
  		<tr><td>Host Name</td><td><%= hostInfo.get("host-name") %></td></tr>
  		<tr><td>x-forwarded-for</td><td><%= hostInfo.get("x-forwarded-for") %></td></tr>
  		<tr><td>localAddr</td><td><%= hostInfo.get("localAddr") %></td></tr>
  	</table>
  </body>
</html>