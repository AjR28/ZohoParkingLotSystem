package zoho1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CheckInServlet
 */
public class CheckInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		RequestDispatcher rd = request.getRequestDispatcher("CheckInPage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String vehicleNumber =request.getParameter("vehicleNumber");
		String vehicleType =request.getParameter("vehicleType");
		String Lot =request.getParameter("Lot");
		String vehicleCheckInTime =request.getParameter("vehicleCheckInTime");
		CheckInInputs CheckInInputs = new CheckInInputs();
		RequestDispatcher rd = request.getRequestDispatcher("CheckInPage.jsp");
		
		System.out.println(vehicleNumber);
		System.out.println(CheckInInputs.checkIfVehicleNumberIsUnique(vehicleNumber));
		System.out.println(vehicleType);
		System.out.println(CheckInInputs.checkIfVehicleType(vehicleType));
		System.out.println(Lot);
		System.out.println(CheckInInputs.checkIfValidLot(Lot));
		System.out.println(vehicleCheckInTime);
		System.out.println(CheckInInputs.checkIfTimeFormatIsCorrect(vehicleCheckInTime));
		
		if(CheckInInputs.checkIfVehicleNumberIsUnique(vehicleNumber)==true && CheckInInputs.checkIfVehicleType(vehicleType)==true 
				&& CheckInInputs.checkIfValidLot(Lot)==true && CheckInInputs.checkIfTimeFormatIsCorrect(vehicleCheckInTime)==true) {
			CheckInInputs.setVehicleNumber(vehicleNumber);
			CheckInInputs.setvehicleType(vehicleType);
			CheckInInputs.setLot(Lot);
			CheckInInputs.setVehicleCheckInTime(vehicleCheckInTime);
			
			String space = CheckInInputs.getVacantSpaceInLot();
			if(space!=null) {
				request.setAttribute("ParkedSpace","The Vehicle is parked at "+space+"");
				rd.forward(request, response);
			}else {
				request.setAttribute("ParkedSpace","The Lot "+Lot+" is full");
				rd.forward(request, response);
			}
		}else {
			request.setAttribute("ParkedSpace","Please check your inputs");
			rd.forward(request, response);
		}
	}

}
