package models;

import java.util.ArrayList;
import java.util.List;

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
	 * @return List witch one GPS-Date for each Tourist
	 */
	public static List<GeoCenter> getCenter(List<GPSLog> gpslogs) {

		List<GeoCenter> geolist = new ArrayList<GeoCenter>();

		for (int i = 0; i < gpslogs.size(); i++) {
			GeoCenter geo = new GeoCenter();
			int counter = 0;
			GPSLog log = gpslogs.get(i);

			for (int j = 1; j < gpslogs.size(); j++) {

				if (log.userid.equals(gpslogs.get(j).userid)) {

					geo.longitudeCenter = geo.longitudeCenter
							+ gpslogs.get(j).longitude;
					geo.latitudeCenter = geo.latitudeCenter
							+ gpslogs.get(j).latitude;
					counter++;
					gpslogs.remove(j);
				}
			}

			if (counter != 0) {
				geo.longitudeCenter = geo.longitudeCenter / counter;
				geo.latitudeCenter = geo.latitudeCenter / counter;
			}

			geolist.add(geo);

		}

		return geolist;

	}
}
