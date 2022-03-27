package zoho1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

/**
 * Servlet implementation class CheckOutServlet
 */
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		RequestDispatcher rd = request.getRequestDispatcher("CheckOutPage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String vehicleNumber =request.getParameter("vehicleNumber");
		String vehicleCheckOutTime =request.getParameter("vehicleCheckOutTime");
		
		CheckOutInputs CheckOutInputs = new CheckOutInputs();
		
		
//		PrintWriter out = response.getWriter();
//		out.print("checkout");
		
		RequestDispatcher rd = request.getRequestDispatcher("CheckOutPage.jsp");
		CheckInInputs CheckInInputs = new CheckInInputs();
		System.out.println("vehicleNumber=="+vehicleNumber);
		System.out.println("vehicleCheckOutTime=="+vehicleCheckOutTime);
		
		if(vehicleNumber!=null && vehicleNumber!="" && CheckInInputs.checkIfTimeFormatIsCorrect(vehicleCheckOutTime) 
				&&vehicleCheckOutTime!=null &&vehicleCheckOutTime!="") {
			CheckOutInputs.setVehicleNumber(vehicleNumber);	
			CheckOutInputs.setVehicleCheckOutTime(vehicleCheckOutTime);
			String space =CheckOutInputs.findParkedArea(vehicleNumber);
			if(space!=null) {
				double time;
					try {
						time = CheckOutInputs.totalParKTime(space, vehicleCheckOutTime);
						if(time>0) {
							String vehicle = CheckOutInputs.getVehicleName(space);
							System.out.println("vehicleType=="+vehicle);
							double cost = CheckOutInputs.parkingCost(vehicle ,time);
							request.setAttribute("CheckOutMsg","Vehicle checked out successfully !");
							request.setAttribute("CheckOutDetails","The Parking duration of "+(int)time/60+" hours "+(int)time%60+" minutes costs Rs."+cost+"");
							rd.forward(request, response);
							CheckOutInputs.storeParkingHistory(space,vehicleCheckOutTime,vehicleNumber);
							CheckOutInputs.checkOutMyVehicle(space);
						}else {
							request.setAttribute("PlsCheckInputs","Please check your inputs");
							rd.forward(request, response);
						}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}else {
				request.setAttribute("NotAvailable","Vehicle not Checked In");
				rd.forward(request, response);
			}		
		}else {
			request.setAttribute("PlsCheckInputs","Please check your inputs");
			rd.forward(request, response);
		}
	}

}
