package zoho1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.Jedis;

import java.io.IOException;

/**
 * Servlet implementation class FindMyVehicleServlet
 */
public class FindMyVehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindMyVehicleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		RequestDispatcher rd = request.getRequestDispatcher("FindMyVehiclePage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String vehicleNumber =request.getParameter("vehicleNumber");
		FindMyVehicleInputs FindMyVehicleInputs = new FindMyVehicleInputs();
		RequestDispatcher rd = request.getRequestDispatcher("FindMyVehiclePage.jsp");
		if(vehicleNumber!=null && vehicleNumber!="") {
			FindMyVehicleInputs.setVehicleNumber(vehicleNumber);
			CheckOutInputs CheckOutInputs = new CheckOutInputs();
			String space = CheckOutInputs.findParkedArea(vehicleNumber);
			Jedis jedis = new Jedis("redis",6379);
			System.out.println("space="+space);
			if(space!=null) {
				System.out.println("space="+space);
				String checkInTime = jedis.hget(space, "vehicleCheckInTime");
				System.out.println("vehicleNumber="+vehicleNumber);
				System.out.println("checkInTime="+checkInTime);
				request.setAttribute("ParkedVehicle","Currently the Vehicle "+vehicleNumber+" is parked at "+space+" lot at "+checkInTime+"");
				
				if(jedis.lindex("vehicleNumber", 0)!=null) {
					request.setAttribute("VehicleNumber",vehicleNumber);
					rd.forward(request, response);
				} 
			}else {
				request.setAttribute("NotParked","Currently the Vehicle "+vehicleNumber+" is not Checked In");
				rd.forward(request, response);
			}
		}else {
			request.setAttribute("PlsCheckInputs","Please check your inputs");
			rd.forward(request, response);
		}
		
//		PrintWriter out = response.getWriter();
//		out.print("find my Vehicle");
	}

}
