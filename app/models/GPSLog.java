package models;

import java.util.List;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;
import play.modules.mongodb.jackson.MongoDB;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GPSLog {

	@Id
	@ObjectId
	public String id;

	public String userid;
	public int vehicle;
	public long timestamp;
	public double latitude;
	public double longitude;
	public double distance;
	@JsonIgnore
	public boolean progressed;

	public static JacksonDBCollection<GPSLog, String> coll = MongoDB
			.getCollection("GPSLog", GPSLog.class, String.class);

	public GPSLog(String userid, int vehicle, long timestamp, double latitude,
			double longitude, double distance) {// Date daDate,

		this.userid = userid;
		this.vehicle = vehicle;
		this.timestamp = timestamp;
		this.latitude = latitude;
		this.longitude = longitude;
		this.distance = distance;
		this.progressed = false;
	}

	public GPSLog() {

	}

	public static void create(GPSLog tracking) {

		GPSLog.coll.save(tracking);
	}

	/**
	 * 
	 * @return All collected GPS-Data
	 */
	public static List<GPSLog> all() {
		return GPSLog.coll.find().toArray();
	}

	/**
	 * Updates the given GPS-Data and set the attribute "progressed" to true.
	 * I´ve implemented this because the "update()" Function of the jacksondriver didn´t work
	 * correct.
	 * @param log
	 */
	public static void myGPSUpdate(GPSLog log) {

		GPSLog.coll.removeById(log.id);
		log.progressed = true;
		GPSLog.create(log);
	}

}
