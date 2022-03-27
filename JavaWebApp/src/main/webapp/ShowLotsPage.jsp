<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="redis.clients.jedis.Jedis"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Lots</title>
</head>
<body BGCOLOR="#FFDEAD">
<form action="Home">
  <input type="button" value="Home" onclick="window.location='Homepage.jsp'" >
  </form>
<CENTER> 
  <div align="center">
  <h1 align="center" style="color: black; font-size: 32px"><b>PARKING LOT</b></h1>
  <br>
 </div>
 <table class="table Show Lots" border="2px single black">
	      <h1 align="center" style="color: black; font-size: 20px"><b>Show Lots</b></h1>
	      <tbody>
		  <thead>
		    <tr>
		      <th scope="col" border="2px single black"><b>Floor</b></th>
		      <th scope="col" border="2px single black"><b>Capacity</b></th>
		      <th scope="col" border="2px single black"><b>No. Of Vehicles</b></th>
		      <th scope="col" border="2px single black"><b>Availability</b></th>
		    </tr>
		  </thead>
 <br>
 <% Jedis jedis = new Jedis("redis",6379);
 int carsParked,available;
 for(char c = 'A'; c <= 'D'; c++) {
	 carsParked=0;
	 for(int i=1;i<=10;i++) {
		 String space= Character.toString(c)+i;
			if(jedis.hget(space, "vehicleNumber")!=null) {
				carsParked++;
			}
	 }
	 available= 10-carsParked;%>
	            <tr>
				<td border="thin single black collapse"><h1 align="center" style="color: blue; font-size: 12px"><b><%=c%></b></h1></td>
			    <td border="thin single black collapse"><h1 align="center" style="color: blue; font-size: 12px"><b><%="10"%></b></h1></td>
				<td border="thin single black collapse"><h1 align="center" style="color: blue; font-size: 12px"><b><%=carsParked%></b></h1></td>
				<td border="thin single black collapse"><h1 align="center" style="color: blue; font-size: 12px"><b><%=available%></b></h1></td>	
				</tr>	 
<% }%>
				
</CENTER> 
</body>
</html>