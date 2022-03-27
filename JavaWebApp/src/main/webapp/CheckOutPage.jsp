<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Check Out</title>
</head>
<body BGCOLOR="#FFDEAD">
<form action="Home">
  <input type="button" value="Home" onclick="window.location='Homepage.jsp'" >
  </form>
<CENTER> 
  <div align="center">
  <h1 align="center" style="color: black; font-size: 32px"><b>PARKING LOT</b></h1>
  <br>
  <h1 align="center" style="color: black;font-size: 18px">CHECK OUT</h1>
  <form action="<%= request.getContextPath() %>/Check-OUT" method="post">
   <table style="with: 80%">
    <tr>
     <td>Vehicle Number</td>
     <td><input type="text" name="vehicleNumber" /></td>
    </tr>
    <tr>
     <td>Vehicle CheckOut Time</td>
     <td><input type="text" name="vehicleCheckOutTime" /></td>
    </tr>
   </table>
   <br>
   <input type="submit" value="CHECK OUT" />
  </form>
 </div>
 <br>
 <%String CheckOutMsg = (String)request.getAttribute("CheckOutMsg");
 String CheckOutDetails = (String)request.getAttribute("CheckOutDetails");
 String PlsCheckInputs = (String)request.getAttribute("PlsCheckInputs");
 String NotAvailable = (String)request.getAttribute("NotAvailable");
 if(CheckOutMsg!=null && CheckOutDetails!=null &&PlsCheckInputs==null){%>
 <h1 align="center" style="color: black; font-size: 20px"><b><%=CheckOutMsg%></b></h1>
 <h1 align="center" style="color: green; font-size: 18px"><b><%=CheckOutDetails%></b></h1>
<%}else if(PlsCheckInputs!=null){%>
	<h1 align="center" style="color: red; font-size: 18px"><b><%=PlsCheckInputs%></b></h1>
<%}else if(NotAvailable!=null){%>
	<h1 align="center" style="color: red; font-size: 18px"><b><%=NotAvailable%></b></h1>
<%}%>
</CENTER> 
</body>
</html>