package models;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
import com.fasterxml.jackson.annotation.JsonIgnore;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;
import play.modules.mongodb.jackson.MongoDB;

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

<<<<<<< HEAD
	public GPSLog(String userid, int vehicle, long timestamp, double latitude,
			double longitude, double distance) {// Date daDate,
=======
	public GPSLog(String userid, int vehicle, long timestamp, double longitude,
			double latitude, double distance) {// Date daDate,
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b

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

<<<<<<< HEAD
	public static List<GPSLog> all(){
		return GPSLog.coll.find().toArray();
	}
	
=======
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
	public static void myGPSUpdate(GPSLog log) {

		GPSLog.coll.removeById(log.id);
		log.progressed = true;
		GPSLog.create(log);
	}

}
