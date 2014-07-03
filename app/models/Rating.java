package models;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;
import play.modules.mongodb.jackson.MongoDB;

public class Rating {

	@Id
	@ObjectId
	public String id;

	public String userId;
	public String sTitle;
	public String sText;
	public int iRating;

	public static JacksonDBCollection<Rating, String> coll = MongoDB
			.getCollection("rating", Rating.class, String.class);

	public Rating(String userId, String sTitle, String sText, int iRating) {
		this.userId = userId;
		this.sTitle = sTitle;
		this.sText = sText;
		this.iRating = iRating;
	}

	public Rating() {
	}

	public static void create(Rating rating) {

		Rating.coll.save(rating);
	}

}
