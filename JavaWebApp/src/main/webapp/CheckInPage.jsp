<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body BGCOLOR="#FFDEAD">
<form action="Home">
  <input type="button" value="Home" onclick="window.location='Homepage.jsp'" >
  </form>
<CENTER> 
  <div align="center">
  <h1 align="center" style="color: black; font-size: 32px"><b>PARKING LOT</b></h1>
  <br>
  <h1 align="center" style="color: black;font-size: 18px">CHECK IN</h1>
  <form action="<%= request.getContextPath() %>/Check-IN" method="post">
   <table style="with: 80%">
    <tr>
     <td>Vehicle Number</td>
     <td><input type="text" name="vehicleNumber" /></td>
    </tr>
    <tr>
     <td>Vehicle Type :</td>
         <td>
         <input type="radio" name="vehicleType" value="Car" />Car
         <input type="radio" name="vehicleType" value="Bike" />Bike
         </td>
    </tr>
    <tr>
     <td>Enter Lot</td>
     <td><input type="text" name="Lot" /></td>
    </tr>
    <tr>
     <td>Vehicle CheckIn Time</td>
     <td><input type="text" name="vehicleCheckInTime" /></td>
    </tr>
   </table>
    <br>
   <input type="submit" value="CHECK IN" />
  </form>
 </div>
 <br>
 <%String space = (String)request.getAttribute("ParkedSpace");
 if(space!=null){%>
 <h1 align="center" style="color: black; font-size: 18px"><b><%=space%></b></h1>
<%} %>
</CENTER> 
</body>
</html>