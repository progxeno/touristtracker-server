package models;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;
import play.modules.mongodb.jackson.MongoDB;

public class Tracking {

	@Id
	@ObjectId
	public String id;

	public String userId;
	// @JsonIgnore
	// public String email;
	// @JsonIgnore
	// public int birth;
	// @JsonIgnore
	// public int plz;
	// @JsonIgnore
	// public String gender;
	// @JsonIgnore
	// public double dFootOrBikeDist;
	// @JsonIgnore
	// public double dCarDist;
	// @JsonIgnore
	// public double dTrainOrBusDist;
	// @JsonIgnore
	// public double dShipDist;

	public int ivehicle;
	public int iTimestamp;
	public double dLong;
	public double dLat;
	public double dDist;

	// @JsonIgnore
	// public boolean bFilterMail = false;
	// @JsonIgnore
	// public boolean bFilterGender = false;
	// @JsonIgnore
	// public boolean bFilterBirth = false;
	// @JsonIgnore
	// public boolean bFilterPlz = false;
	// @JsonIgnore
	// boolean bFilterZeitraum = false;

	public static JacksonDBCollection<Tracking, String> coll = MongoDB
			.getCollection("tracking", Tracking.class, String.class);

	public Tracking(String userId, int ivehicle, int iTimestamp, double dLat,
			double dLong, double dDist) {// Date daDate,

		this.userId = userId;
		this.ivehicle = ivehicle;
		this.iTimestamp = iTimestamp;
		this.dLong = dLong;
		this.dLat = dLat;
		this.dDist = dDist;
	}

	public Tracking() {

	}

	public static void create(Tracking tracking) {

		Tracking.coll.save(tracking);
	}

}
