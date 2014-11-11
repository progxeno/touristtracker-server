package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;
import play.modules.mongodb.jackson.MongoDB;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

	@JsonIgnore
	@Id
	@ObjectId
	public String id;

	public String userid;
	public String email;
	public int year;
	public String zipcode;
	public String country;
	public boolean gender;
	public boolean returner;
	public String version;
	public String manufacturer;
	public String model;

	@JsonIgnore
	public String dateFrom;
	@JsonIgnore
	public String dateTo;
	@JsonIgnore
	public double dFootOrBikeDist;
	@JsonIgnore
	public double dCarDist;
	@JsonIgnore
	public double dTrainOrBusDist;
	@JsonIgnore
	public double dShipDist;
	@JsonIgnore
	public double dTotalDist;

	public static JacksonDBCollection<User, String> coll = MongoDB
			.getCollection("Tourists", User.class, String.class);

	public User(String userid, String email, int year, String zipcode,
			String country, boolean gender, boolean returner, String version, String manufacturer, String model) {
		
		this.userid = userid;
		this.email = email;
		this.year = year;
		this.zipcode = zipcode;
		this.country = country;
		this.gender = gender;
		this.returner = returner;
		this.version = version;
		this.manufacturer = manufacturer;
		this.model = model;

	}

	public User() {

	}

	public static void create(User user, String dateFrom) {

		int isizeUser = User.coll.find().size();
		boolean bNewUser = true;
		List<User> Luser = User.coll.find().toArray();
		if (user != null)

			//TODO: check entry
			for (int i = 0; isizeUser > i; i++) {
				if (user.userid.equals(Luser.get(i).userid)) {
					bNewUser = false;
				}
			}

		if (bNewUser && dateFrom.contains("newUser")) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
			user.dateFrom = sdf.format(date);
			User.coll.save(user);
		}
		else {
			user.dateFrom = dateFrom;
			User.coll.save(user);
		}
	}

	/**
	 * Updates the given Tourist by deleting the existing Tourist with the same userid
	 * and adds him new to the Database.
	 * I´ve implemented this because the "update()" Function of the jacksondriver didn´t work
	 * correct.
	 * @param user
	 */
	public static void myUserUpdate(User user) {

		String dateFrom = user.dateFrom;
		User.coll.removeById(user.id);
		User.create(user, dateFrom);
	}

	public static List<User> displayAll() {
		return User.coll.find().limit(1000).toArray();
	}
	
}