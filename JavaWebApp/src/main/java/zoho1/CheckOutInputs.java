package zoho1;

import java.text.ParseException;

import redis.clients.jedis.Jedis;

public class CheckOutInputs {
	private String vehicleNumber;
	private String vehicleCheckOutTime;
	
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getVehicleCheckOutTime() {
		return vehicleCheckOutTime;
	}
	public void setVehicleCheckOutTime(String vehicleCheckOutTime) {
		this.vehicleCheckOutTime = vehicleCheckOutTime;
	}
	Jedis jedis = new Jedis("redis",6379);
	
	public void checkOutMyVehicle(String space) {
		jedis.del(space);
	}
	
	public String findParkedArea(String number) {
		if(number!=null) {
			for(char c = 'A'; c <= 'D'; c++) {
				for(int i=1;i<=10;i++) {
					String space= Character.toString(c)+i;
					String a= jedis.hget(space, "vehicleNumber");
					System.out.println("a=="+a+"  number=="+number+"  space=="+space+"");
					if(a!=null) {
						if(a.equals(number)) {
							return space;
					    }
					}
			    }
		    }
		}
		return null;
	}
	
	public double totalParKTime(String space,String checkOutTime) throws ParseException  {
		System.out.println("totalParKTime...............");
		String checkInTime = jedis.hget(space, "vehicleCheckInTime");
		System.out.println("checkInTime=="+checkInTime);
		long epochcheckInTime;
		long epochcheckOutTime;
		long timeDifference;
			epochcheckInTime = new java.text.SimpleDateFormat("hh:mm aa").parse(checkInTime).getTime();
			epochcheckInTime = epochcheckInTime/1000;
			epochcheckOutTime = new java.text.SimpleDateFormat("hh:mm aa").parse(checkOutTime).getTime();
			epochcheckOutTime = epochcheckOutTime/1000;
			timeDifference = (epochcheckOutTime- epochcheckInTime)/60;
			return timeDifference;
	}
	 
	public double parkingCost(String vehicleType ,double time) {
		float car = 50;
		float bike = 10;
		double cost;
		System.out.println("time=="+time);
		if(vehicleType.equals("Car")) {
			cost = Math.ceil((time/60)*car);
			return cost;
		}else if(vehicleType.equals("Bike")) {
			cost = Math.ceil((time/60)*bike);
			return cost;
		}
		return -1;
	}
	
	public String getVehicleName(String space) {
		String vehicle = jedis.hget(space, "vehicleType");
		return vehicle;
	}
	
	public void storeParkingHistory(String space,String vehicleCheckOutTime,String vehicleNumber) {
		System.out.println("History vehicleCheckOutTime=="+jedis.hget(space, "vehicleCheckOutTime"));
		if(space!=null&&vehicleNumber!=null&&jedis.hget(space, "vehicleCheckInTime")!=null&&vehicleCheckOutTime!=null) {
			 	System.out.println("Store Park history");
			  jedis.lpush("vehicleNumber",  vehicleNumber); 
		      jedis.lpush("vehicleSpaceList", space); 
		      jedis.lpush("vehicleCheckInTimeList", jedis.hget(space, "vehicleCheckInTime"));
		      jedis.lpush("vehicleCheckOutTimeList", vehicleCheckOutTime);
		      CheckInInputs CheckInInputs = new CheckInInputs();
		      jedis.lpush("vehicleCheckInDate", CheckInInputs.getTodayDate());
		}  
	}
}
