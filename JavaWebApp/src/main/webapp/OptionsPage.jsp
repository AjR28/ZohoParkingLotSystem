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
  <h1 align="center" style="color: black; font-size: 32px"><b>PARKING LOT</b></h1>
  <br>
  <form action="Check-IN">
  <input id="checkin" type="submit" value="CHECK IN" />
  </form>
  <br>
  <form action="Check-OUT">
  <input id="checkout" type="submit" value="CHECK OUT" />
  </form>
  <br>
  <form action="Find-My-Vehicle">
  <input id="findvehicle" type="submit" value="FIND VEHICLE" />
  </form>
  </CENTER>   
   <form action="Show-Lots">
  <input id="showLots" type="submit" value="SHOW LOTS" />
  </form>
    
</body>
</html>