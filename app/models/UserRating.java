package models;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;
import play.modules.mongodb.jackson.MongoDB;

public class UserRating {

	@Id
	@ObjectId
	public String id;

	public String userid;
	public String ratingtitle;
	public String ratingtext;
	public int ratingstars;

	public static JacksonDBCollection<UserRating, String> coll = MongoDB
			.getCollection("UserRatings", UserRating.class, String.class);

	public UserRating(String userid, String ratingtitle, String ratingtext,
			int ratingstars) {
		this.userid = userid;
		this.ratingtitle = ratingtitle;
		this.ratingtext = ratingtext;
		this.ratingstars = ratingstars;
	}

	public UserRating() {
	}

	public static void create(UserRating rating) {

		UserRating.coll.save(rating);
	}

}
