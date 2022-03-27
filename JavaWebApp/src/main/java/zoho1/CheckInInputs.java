package zoho1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import redis.clients.jedis.Jedis;

public class CheckInInputs {
	Jedis jedis = new Jedis("redis",6379);
	private String vehicleNumber;
	private String vehicleType;
	private String Lot;
	private String vehicleCheckInTime;
	
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setvehicleType(String vehicleType) {
		this.vehicleType =vehicleType;
	}
	public String getLot() {
		return Lot;
	}
	public void setLot(String lot) {
		Lot = lot.toUpperCase();
	}
	public String getVehicleCheckInTime() {
		return vehicleCheckInTime;
	}
	public void setVehicleCheckInTime(String vehicleCheckInTime) {
		this.vehicleCheckInTime = vehicleCheckInTime;
	}
	
	public String getVacantSpaceInLot() {
		 String lot= getLot();
		 for(int i=1;i<=10;i++) {
				String space= lot+i;
				if(jedis.hget(space, "vehicleNumber")==null) {
					jedis.hset(space, "vehicleNumber", getVehicleNumber());
					jedis.hset(space, "vehicleType", getVehicleType());
					jedis.hset(space, "vehicleLot", getLot());
					jedis.hset(space, "vehicleCheckInTime",getVehicleCheckInTime());
					return space;
				}
			}	
		 return null;
	 }
	
	public boolean checkIfVehicleNumberIsUnique(String number) {
		if(number!=null) {
			for(char c = 'A'; c <= 'D'; c++) {
				for(int i=1;i<=10;i++) {
					String space= Character.toString(c)+i;
					if(jedis.hget(space, "vehicleNumber")!=null) {
						if(jedis.hget(space, "vehicleNumber").equals(number)) {
						return false;
					}
				}
		    }
			}
		}
		
		return true;
	}
	
	public boolean checkIfValidLot(String lot) {
		if(lot.length()!=0) {
			if('A'<=lot.charAt(0) && lot.charAt(0)<='D') {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkIfVehicleType(String vehicle) {
		if(vehicle!=""&&vehicle!=null) {
			if(vehicle.equals("Car")||vehicle.equals("Bike")) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkIfTimeFormatIsCorrect(String time) {
		if(time!=null) {
			Pattern pattern;
		    Matcher matcher;
		    String TIME12HOURS_PATTERN = "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";
			pattern = Pattern.compile(TIME12HOURS_PATTERN);
			matcher = pattern.matcher(time);
	        return matcher.matches(); 
		}
        return false;
   }
	
   public String getTodayDate() {
	   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    Date date = new Date();  
	    System.out.println(formatter.format(date));  
	    return formatter.format(date);
   }

}
