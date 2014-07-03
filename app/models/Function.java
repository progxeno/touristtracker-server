package models;

<<<<<<< HEAD
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
=======
import java.text.SimpleDateFormat;
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import net.vz.mongodb.jackson.DBCursor;
import play.data.Form;

import com.mongodb.BasicDBObject;
<<<<<<< HEAD
=======
import com.mongodb.DBObject;
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b

public class Function {

	public Function() {
		// TODO Auto-generated constructor stub
	}

	public static List<User> withTrack() {

		int isizeUser = User.coll.find().limit(1000).size();

		List<User> mixedList = User.coll.find().limit(1000).toArray();
<<<<<<< HEAD

=======
		
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
		if (mixedList.isEmpty()) {
			return mixedList;
		} else {

			for (int i = 0; isizeUser > i; i++) {
				BasicDBObject query = new BasicDBObject("userid",
						mixedList.get(i).userid);
<<<<<<< HEAD
				List<GPSLog> track = GPSLog.coll.find(query).toArray();

				int isizeTracking = track.size();
				for (int j = 0; isizeTracking > j; j++) {

					if (!track.get(j).progressed) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd"); // the format of your date
						sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
						mixedList.get(i).dateTo = sdf
								.format(track.get(j).timestamp);

=======
				 List<GPSLog> track = GPSLog.coll.find(query).toArray();

				int isizeTracking = track.size();
				for (int j = 0; isizeTracking > j; j++) {
					
					if (!track.get(j).progressed) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // the format of your date
						sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
						mixedList.get(i).dateTo = sdf.format(track.get(j).timestamp * 1000L);
						
						
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
						mixedList.get(i).dTotalDist = mixedList.get(i).dTotalDist
								+ track.get(j).distance;

						switch (track.get(j).vehicle) {
						case 0:
							mixedList.get(i).dFootOrBikeDist = mixedList.get(i).dFootOrBikeDist
									+ track.get(j).distance;
							break;
						case 1:
							mixedList.get(i).dCarDist = mixedList.get(i).dCarDist
									+ track.get(j).distance;
							break;
						case 2:
							mixedList.get(i).dTrainOrBusDist = mixedList.get(i).dTrainOrBusDist
									+ track.get(j).distance;
							break;
						case 3:
							mixedList.get(i).dShipDist = mixedList.get(i).dShipDist
									+ track.get(j).distance;
							break;
						}
<<<<<<< HEAD

						GPSLog.myGPSUpdate(track.get(j));
=======
						
						
						GPSLog.myGPSUpdate(track
								.get(j));
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
					}

				}
				User.myUserUpdate(mixedList.get(i));
			}

			BasicDBObject delete = new BasicDBObject("progressed", false);
			GPSLog.coll.remove(delete);
			return mixedList;
		}
	}

	public static List<UserRating> UserRating(String userId) {

		int isizeRating = UserRating.coll.find().size();
<<<<<<< HEAD

		BasicDBObject query = new BasicDBObject("ratingtitle", -1);

		DBCursor<UserRating> cursor = UserRating.coll.find().sort(query);

		List<UserRating> selectedUserRating = cursor.toArray();

=======
		
		BasicDBObject query = new BasicDBObject("ratingtitle",-1);
		
		DBCursor<UserRating> cursor = UserRating.coll.find().sort(query);
		
		List<UserRating> selectedUserRating = cursor.toArray();
		
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
		List<UserRating> allUserRating = UserRating.coll.find().toArray();

		if (allUserRating.isEmpty()) {
			return selectedUserRating;
		} else {
			int i = 0;
			for (int j = 0; isizeRating > j; j++) {

				if (userId.equals(allUserRating.get(j).userid)) {

					selectedUserRating.set(i, allUserRating.get(j));
					i++;

				} else {
					selectedUserRating.remove(i);
				}
			}

			return selectedUserRating;
		}
	}

	public static User UserDistance(String userid) {

		BasicDBObject query = new BasicDBObject("userid", userid);
		User user = User.coll.findOne(query);
		return user;
	}

	public static void delete(String id) {
		User user = User.coll.findOneById(id);

		int isizeTracking = GPSLog.coll.find().size();
		int isizeRating = UserRating.coll.find().size();

<<<<<<< HEAD
		BasicDBObject searchQuery = new BasicDBObject("userid", user.userid);

		List<GPSLog> track = GPSLog.coll.find(searchQuery).toArray();
		List<UserRating> rating = UserRating.coll.find(searchQuery).toArray();
		if (user != null) {
			if (!track.isEmpty()) {
				for (int j = 0; isizeTracking > j; j++) {
					if (user.userid.equals(track.get(j).userid)) {
						GPSLog.coll.remove(track.get(j));
					}
				}
			}
			if (!rating.isEmpty()) {
				for (int i = 0; isizeRating > i; i++) {
					if (user.userid.equals(rating.get(i).userid)) {
						UserRating.coll.remove(rating.get(i));
					}
				}
			}
=======
		List<GPSLog> track = GPSLog.coll.find().toArray();
		List<UserRating> rating = UserRating.coll.find().toArray();
		if (user != null) {

			for (int j = 0; isizeTracking > j; j++) {
				if (user.userid.equals(track.get(j).userid)) {
					GPSLog.coll.remove(track.get(j));
				}
			}
			for (int i = 0; isizeRating > i; i++) {
				if (user.userid.equals(rating.get(i).userid)) {
					UserRating.coll.remove(rating.get(i));
				}
			}

>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
			User.coll.removeById(id);
		}
	}

	public static void deleteRating(String id) {

		UserRating.coll.removeById(id);

	}

<<<<<<< HEAD
=======
	// GENDER
	public static List<User> fResult(boolean value) {

		DBObject query = new BasicDBObject("gender", value);
		BasicDBObject fields = new BasicDBObject();

		return User.coll.find(query, fields).toArray();
	}

	// mail
	public static List<User> fResultmail(String findmail) {

		DBObject query = new BasicDBObject("email", findmail);
		BasicDBObject fields = new BasicDBObject();

		return User.coll.find(query, fields).toArray();
	}

	// plz
	public static List<User> fResultplz(String findplz) {

		DBObject query = new BasicDBObject("plz", findplz);
		BasicDBObject fields = new BasicDBObject();

		return User.coll.find(query, fields).toArray();
	}

	// SELECT
	public static List<User> fResultselect(String type, int value) {

		DBObject query = new BasicDBObject(type, value);
		BasicDBObject fields = new BasicDBObject();

		return User.coll.find(query, fields).toArray();
	}

>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
	public static List<User> multiFilter(Form<String> filterOptions) {

		BasicDBObject searchQuery = new BasicDBObject();

		String email = "email";
		String zipcode = "zipcode";
		String year1 = "year1";
		String year2 = "year2";
		String country = "country";
		String gender = "gender";
		String returner = "returner";
<<<<<<< HEAD
		List<User> userlist = null;
=======
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b

		String foundmail = filterOptions.data().get(email);
		String foundzipcode = filterOptions.data().get(zipcode);
		String foundyear1 = filterOptions.data().get(year1);
		String foundyear2 = filterOptions.data().get(year2);
		String foundcountry = filterOptions.data().get(country);
<<<<<<< HEAD
		String foundDate1 = filterOptions.data().get("date1");
		String foundDate2 = filterOptions.data().get("date2");
=======
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b

		if (!foundmail.isEmpty()) {
			searchQuery.put(email, foundmail);
		}
		if (!foundzipcode.isEmpty()) {

			searchQuery.put(zipcode, foundzipcode);
		}
<<<<<<< HEAD

=======
		
		if (!foundyear1.isEmpty() && foundyear2.isEmpty()) {
			int iyear1 = Integer.parseInt(foundyear1);
			searchQuery.put("year", iyear1);
		}
		if (!foundyear1.isEmpty() && !foundyear2.isEmpty()) {
			int iyear1 = Integer.parseInt(foundyear1);
			int iyear2 = Integer.parseInt(foundyear2);
			int temp;
			
//			searchQuery.put("year", iyear1);
			
			int diff = iyear2 - iyear1;
			for (int i=1; i<diff; i++) {
				temp = iyear1 + i;
//				searchQuery.put("year", temp);
				searchQuery.append("year", temp);

			}
		}
		
		
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
		if (!foundcountry.isEmpty()) {
			searchQuery.put(country, foundcountry);
		}
		if (filterOptions.data().containsKey("male")
				&& !filterOptions.data().containsKey("female")) {

			searchQuery.put(gender, true);
		}

		if (filterOptions.data().containsKey("female")
				&& !filterOptions.data().containsKey("male")) {
			searchQuery.put(gender, false);
		}

		if (filterOptions.data().containsKey("returner")) {
			searchQuery.put(returner, true);
		}
<<<<<<< HEAD
		if (!foundyear1.isEmpty() && !foundyear2.isEmpty()) {

			int iyear1 = Integer.parseInt(foundyear1);
			int iyear2 = Integer.parseInt(foundyear2);
			userlist = User.coll.find(searchQuery)
					.greaterThanEquals("year", iyear1)
					.lessThanEquals("year", iyear2).toArray();
		} else if (!foundyear1.isEmpty() && foundyear2.isEmpty()) {
			int iyear1 = Integer.parseInt(foundyear1);
			searchQuery.put("year", iyear1);
			userlist = User.coll.find(searchQuery)
					.greaterThanEquals("year", iyear1).toArray();
		} else if (foundyear1.isEmpty() && !foundyear2.isEmpty()) {
			int iyear2 = Integer.parseInt(foundyear2);
			userlist = User.coll.find(searchQuery)
					.lessThanEquals("year", iyear2).toArray();
		} else {
			userlist = User.coll.find(searchQuery).toArray();
		}

		for (int i = 0; i < userlist.size(); i++) {
			if (!foundDate1.isEmpty()) {
				Timestamp lDate1 = Timestamp.valueOf(foundDate1
						+ " 00:00:00.000000000");

				if (!userlist.get(i).dateFrom.equals(lDate1)) {
					userlist.remove(i);
					i--;
				}
			}
			if (!foundDate2.isEmpty()) {
				Timestamp lDate2 = Timestamp.valueOf(foundDate2
						+ " 00:00:00.000000000");
				if (!userlist.get(i).dateTo.equals(lDate2)) {
					userlist.remove(i);
					i--;
				}
			}
		}

		return userlist;
	}

	public static List<GPSLog> gpsfilter(List<User> userlist,
			Form<String> filterOptions) {

		BasicDBObject searchQuery = new BasicDBObject();

		String foundDate1 = filterOptions.data().get("date1");
		String foundDate2 = filterOptions.data().get("date2");
		int isizeUser = userlist.size();

		List<GPSLog> filtertLogs = new ArrayList<GPSLog>();

		if (userlist.isEmpty()) {
			return filtertLogs;
		} else {

			for (int i = 0; isizeUser > i; i++) {
				searchQuery.put("userid", userlist.get(i).userid);

			}
			if (filterOptions.data().containsKey("footbike")) {
				searchQuery.put("vehicle", 0);
			}
			if (filterOptions.data().containsKey("car")) {
				searchQuery.put("vehicle", 1);
			}
			if (filterOptions.data().containsKey("bustrain")) {
				searchQuery.put("vehicle", 2);
			}
			if (filterOptions.data().containsKey("ship")) {
				searchQuery.put("vehicle", 3);
			}

			if (!foundDate1.isEmpty() && !foundDate2.isEmpty()) {

				Timestamp lDate1 = Timestamp.valueOf(foundDate1
						+ " 00:00:00.000000000");
				Timestamp lDate2 = Timestamp.valueOf(foundDate2
						+ " 00:00:00.000000000");
				filtertLogs = GPSLog.coll.find(searchQuery)
						.greaterThanEquals("timestamp", lDate1)
						.lessThanEquals("timestamp", lDate2).toArray();
			} else if (!foundDate1.isEmpty() && foundDate2.isEmpty()) {

				Timestamp lDate1 = Timestamp.valueOf(foundDate1
						+ " 00:00:00.000000000");
				searchQuery.put("timestamp", lDate1);
				filtertLogs = GPSLog.coll.find(searchQuery)
						.greaterThanEquals("timestamp", lDate1).toArray();
			} else if (foundDate1.isEmpty() && !foundDate2.isEmpty()) {
				Timestamp lDate2 = Timestamp.valueOf(foundDate2
						+ " 00:00:00.000000000");
				filtertLogs = GPSLog.coll.find(searchQuery)
						.lessThanEquals("timestamp", lDate2).toArray();
			} else {
				filtertLogs = GPSLog.coll.find(searchQuery).toArray();
			}

		}

		return filtertLogs;
	}
=======
		List<User> userlist = User.coll.find(searchQuery).toArray();
		return userlist;
	}

>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b

	public static boolean checkDoubleEntry(User user, List<User> userList) {

		int isizeUser = userList.size();
		boolean bNewUser = true;

		if (user != null)

			for (int i = 0; isizeUser > i; i++) {
				if (user.userid.equals(userList.get(i).userid)) {
					bNewUser = false;
					break;
				}
			}
		return bNewUser;
	}

}