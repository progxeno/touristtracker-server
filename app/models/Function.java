package models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import net.vz.mongodb.jackson.DBCursor;
import play.data.Form;

import com.mongodb.BasicDBObject;

public class Function {

	public Function() {
	}

	/**
	 * This Method updates the in the Database collected Tourist and updates
	 * them with their send Tracking-data
	 * 
	 * @return Updated list of Tourists
	 */
	public static List<User> withTrack() {

		int isizeUser = User.coll.find().limit(1000).size();

		List<User> mixedList = User.coll.find().limit(1000).toArray();

		if (mixedList.isEmpty()) {
			return mixedList;
		} else {

			/**
			 * For-Loop to update every Tourist with the corresponding
			 * Tracking-data
			 */
			for (int i = 0; isizeUser > i; i++) {

				BasicDBObject query = new BasicDBObject("userid",
						mixedList.get(i).userid);

				List<GPSLog> track = GPSLog.coll.find(query).toArray();

				int isizeTracking = track.size();
				/**
				 * For-Loop to add every Tracking-data to the Tourist
				 */
				for (int j = 0; isizeTracking > j; j++) {

					if (!track.get(j).progressed) {

						// Converts the Timestamp into a Date Format
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd"); // the format of your date
						sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
						mixedList.get(i).dateTo = sdf
								.format(track.get(j).timestamp);

						// Adds the received Distance of this GPS-Log to the
						// Total Distance of the User
						mixedList.get(i).dTotalDist = mixedList.get(i).dTotalDist
								+ track.get(j).distance;

						// Switch-Case to detect with what vehicle the Tourist
						// has traveled
						// and sums them to the corresponding distance for this
						// Tourist
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

						// Updates the GPS-Log to detect that this log is
						// already progressed
						GPSLog.myGPSUpdate(track.get(j));
					}

				}
				User.myUserUpdate(mixedList.get(i));
			}

			// All GPS-Logs that didn´t have a corresponding Tourist
			// are going to be deleted
			BasicDBObject delete = new BasicDBObject("progressed", false);
			GPSLog.coll.remove(delete);
			return mixedList;
		}
	}

	/**
	 * This Method search for all Ratings that are given by the Tourist
	 * 
	 * @param userId
	 * @return List of Ratings given by the User
	 */
	public static List<UserRating> UserRating(String userId) {

		BasicDBObject query = new BasicDBObject("userid", userId);

		DBCursor<UserRating> cursor = UserRating.coll.find(query);

		List<UserRating> selectedUserRating = cursor.toArray();

		return selectedUserRating;
	}

	/**
	 * This Method search for all distances that are given by the Tourist
	 * 
	 * @param userid
	 * @return
	 */
	public static User UserDistance(String userid) {

		BasicDBObject query = new BasicDBObject("userid", userid);
		User user = User.coll.findOne(query);
		return user;
	}

	/**
	 * Deletes all collected Information of the given Tourists
	 * 
	 * @param id
	 */
	public static void delete(String id) {
		User user = User.coll.findOneById(id);
		BasicDBObject searchQuery = new BasicDBObject("userid", user.userid);

		// If the Tourist id exists in the Database
		// Delete all Ratings and GPS-data with the corresponding userid
		// and then deletes the user by the Object id
		if (user != null) {

			GPSLog.coll.remove(searchQuery);
			UserRating.coll.remove(searchQuery);
			User.coll.removeById(id);
		}
	}

	/**
	 * Deletes a Single Rating by the Object id
	 * 
	 * @param id
	 */
	public static void deleteRating(String id) {

		UserRating.coll.removeById(id);

	}

	/**
	 * Reads the selected filter criteria and puts these criteria in the search
	 * query
	 * 
	 * @param filterOptions
	 * @return List with the Tourist that equals the selected filter criteria
	 */

	public static List<User> multiFilter(Form<String> filterOptions) {

		BasicDBObject searchQuery = new BasicDBObject();

		String email = "email";
		String zipcode = "zipcode";
		String year1 = "year1";
		String year2 = "year2";
		String country = "country";
		String gender = "gender";
		String returner = "returner";
		String date1 = "date1";
		String date2 = "date2";
		List<User> userlist = new ArrayList<User>();

		String foundmail = filterOptions.data().get(email);
		String foundzipcode = filterOptions.data().get(zipcode);
		String foundyear1 = filterOptions.data().get(year1);
		String foundyear2 = filterOptions.data().get(year2);
		String foundcountry = filterOptions.data().get(country);
		String foundDate1 = filterOptions.data().get(date1);
		String foundDate2 = filterOptions.data().get(date2);

		if (!foundmail.isEmpty()) {
			searchQuery.put(email, foundmail);
		}
		if (!foundzipcode.isEmpty()) {

			searchQuery.put(zipcode, foundzipcode);
		}

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
				Timestamp tDate1 = Timestamp.valueOf(foundDate1
						+ " 00:00:00.000000000");

				Timestamp tUserDate = Timestamp
						.valueOf(userlist.get(i).dateFrom
								+ " 00:00:00.000000000");

				userlist.get(i).dateFrom = foundDate1;
				if (!foundDate2.isEmpty())
					userlist.get(i).dateTo = foundDate2;

				if (!tUserDate.after(tDate1)) {
					userlist.remove(i);
					i--;
				}

			}

		}

		return userlist;
	}

	/**
	 * Search in the List of GPS-logs for the Tourists witch the selected filter
	 * creteria and finds the corresponding GPS-data. These Data are filtered
	 * with two different selection criteria. Vehicle and Date From.
	 * 
	 * @param userlist
	 * @param filterOptions
	 * @return The to the List of Tourists corresponding GPS-data
	 */
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

				Timestamp tDate1 = Timestamp.valueOf(foundDate1
						+ " 00:00:00.000000000");

				long lDate1 = tDate1.getTime();

				Timestamp tDate2 = Timestamp.valueOf(foundDate2
						+ " 00:00:00.000000000");

				long lDate2 = tDate2.getTime();
				filtertLogs = GPSLog.coll.find(searchQuery)
						.greaterThanEquals("timestamp", lDate1)
						.lessThanEquals("timestamp", lDate2).toArray();

			} else if (!foundDate1.isEmpty() && foundDate2.isEmpty()) {

				Timestamp tDate1 = Timestamp.valueOf(foundDate1
						+ " 00:00:00.000000000");
				long lDate1 = tDate1.getTime();

				filtertLogs = GPSLog.coll.find(searchQuery)
						.greaterThanEquals("timestamp", lDate1).toArray();

			} else if (foundDate1.isEmpty() && !foundDate2.isEmpty()) {

				Timestamp tDate2 = Timestamp.valueOf(foundDate2
						+ " 00:00:00.000000000");

				long lDate2 = tDate2.getTime();

				filtertLogs = GPSLog.coll.find(searchQuery)
						.lessThanEquals("timestamp", lDate2).toArray();
			} else {
				filtertLogs = GPSLog.coll.find(searchQuery).toArray();
			}

		}

		return filtertLogs;
	}

	/**
	 * 
	 * @param user
	 * @param userList
	 * @return If the Tourist already is in the given List of Tourists, return
	 *         false
	 */
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