<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="redis.clients.jedis.Jedis"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find My Vehicle</title>
</head>
<body BGCOLOR="#FFDEAD">
<form action="Home">
  <input type="button" value="Home" onclick="window.location='Homepage.jsp'" >
  </form>
<CENTER> 
  <div align="center">
  <h1 align="center" style="color: black; font-size: 32px"><b>PARKING LOT</b></h1>
  <br>
  <h1 align="center" style="color: black;font-size: 18px">FIND MY VEHICLE</h1>
  <form action="<%= request.getContextPath() %>/Find-My-Vehicle" method="post">
   <table>
    <tr>
     <td>Vehicle Number</td>
     <td><input type="text" name="vehicleNumber" /></td>
    </tr>
   </table>
    <br>
   <input type="submit" value="FIND" />
  </form>
 </div>
  <br>
 <%String ParkedVehicle = (String)request.getAttribute("ParkedVehicle");
 String NotParked = (String)request.getAttribute("NotParked");
 String PlsCheckInputs = (String)request.getAttribute("PlsCheckInputs");
 if(ParkedVehicle!=null && NotParked==null){%>
 <h1 align="center" style="color: green; font-size: 15px"><b><%=ParkedVehicle%></b></h1>
<%}else if(PlsCheckInputs!=null){%>
<h1 align="center" style="color: red; font-size: 15px"><b><%=PlsCheckInputs%></b></h1>
<%}else if(ParkedVehicle==null && NotParked!=null){%>
	<h1 align="center" style="color: red; font-size: 15px"><b><%=NotParked%></b></h1>
<%}%>


 
  <% Jedis jedis = new Jedis("redis",6379);
  String VehicleNumber = (String)request.getAttribute("VehicleNumber");
  if(VehicleNumber!=null){
	  %>
	      <table class="table Park History" border="2px single black">
	      <tbody>
	      <h1 align="center" style="color: black; font-size: 20px"><b>Parking History</b></h1>
		  <thead>
		    <tr>
		      <th scope="col" border="2px single black"><b>Date</b></th>
		      <th scope="col" border="2px single black"><b>Lot</b></th>
		      <th scope="col" border="2px single black"><b>CheckIn</b></th>
		      <th scope="col" border="2px single black"><b>CheckOut</th>
		    </tr>
		  </thead>
		  <%int c=0;
		  for(int i=0;i<jedis.llen("vehicleNumber");i++) {
				if(VehicleNumber.equals(jedis.lindex("vehicleNumber", i))&& c<5) {%>
				<tr>
				<td border="thin single black collapse"><h1 align="center" style="color: blue; font-size: 12px"><b><%=jedis.lindex("vehicleCheckInDate", i)%></b></h1></td>
			    <td border="thin single black collapse"><h1 align="center" style="color: blue; font-size: 12px"><b><%=jedis.lindex("vehicleSpaceList", i)%></b></h1></td>
				<td border="thin single black collapse"><h1 align="center" style="color: blue; font-size: 12px"><b><%=jedis.lindex("vehicleCheckInTimeList", i)%></b></h1></td>
				<td border="thin single black collapse"><h1 align="center" style="color: blue; font-size: 12px"><b><%=jedis.lindex("vehicleCheckOutTimeList", i)%></b></h1></td>	
				</tr>	
				<%c++;	
				}
			  }
		  if(c==0){ %>
			  <h1 align="center" style="color: red; font-size: 15px"><b><%="No Parking History available"%></b></h1>
		 <% }
  }
  %>
 </tbody>
</table>
</CENTER> 
</body>
</html>