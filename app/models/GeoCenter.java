package models;


import java.util.List;

import play.libs.Json;


public class GeoCenter {

	public double latitudeCenter;
	public double longitudeCenter;
	public String userid;

	public GeoCenter(double lat, double lon, String userid) {

		this.userid = userid;
		this.latitudeCenter = lat;
		this.longitudeCenter = lon;
	}

	public GeoCenter() {

	}
	
	/**
	 * Calculate the center of the collected GPS-data of one Tourist.
	 * This Function is to display the movement pattern of the Tourists.
	 * @param gpslogs
	 * @param userid
	 * @return List witch one GPS-Date for each Tourist
	 */
	public static String getCenter(List<GPSLog> singelUserGPSLogs){
		
		String json = Json.toJson(singelUserGPSLogs).toString();

		return json;

	}
}
