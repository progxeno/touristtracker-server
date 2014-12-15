package models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import net.vz.mongodb.jackson.DBCursor;
import play.libs.Json;
import play.mvc.Http.Request;

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

				query.put("progressed", false);
				List<GPSLog> track = GPSLog.coll.find(query).toArray();

				int isizeTracking = track.size();

				// For-Loop to add every Tracking-data to the Tourist

				for (int j = 0; isizeTracking > j; j++) {

					// Converts the Timestamp into a Date Format
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
					mixedList.get(i).dateTo = sdf
							.format(track.get(j).timestamp * 1000L);

					// Adds the received Distance of this GPS-Log to the
					// Total Distance of the User and
					// rounds the Total Distance so that it only has 2 decimals
					mixedList.get(i).dTotalDist = Math
							.round((mixedList.get(i).dTotalDist + track.get(j).distance)
									* Math.pow(10d, 2))
							/ Math.pow(10d, 2);

					// Switch-Case to detect with what vehicle the Tourist
					// has traveled
					// and sums them to the corresponding distance for this
					// Tourist
					switch (track.get(j).vehicle) {
					case 0:
						mixedList.get(i).dFootOrBikeDist = Math
								.round((mixedList.get(i).dFootOrBikeDist + track
										.get(j).distance) * Math.pow(10d, 2))
								/ Math.pow(10d, 2);
						break;
					case 1:
						mixedList.get(i).dCarDist = Math.round((mixedList
								.get(i).dCarDist + track.get(j).distance)
								* Math.pow(10d, 2)) / Math.pow(10d, 2);
						break;
					case 2:
						mixedList.get(i).dTrainOrBusDist = Math
								.round((mixedList.get(i).dTrainOrBusDist + track
										.get(j).distance) * Math.pow(10d, 2))
								/ Math.pow(10d, 2);
						break;
					case 3:
						mixedList.get(i).dShipDist = Math.round((mixedList
								.get(i).dShipDist + track.get(j).distance)
								* Math.pow(10d, 2)) / Math.pow(10d, 2);
						break;
					}

					// Updates the GPS-Log to detect that this log is
					// already progressed
					GPSLog.myGPSUpdate(track.get(j));

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

	public static List<GPSLog> singelUser(String userid) {
		BasicDBObject query = new BasicDBObject("userid", userid);

		DBCursor<GPSLog> cursor = GPSLog.coll.find(query);
		cursor.sort(new BasicDBObject("timestamp", 1));

		List<GPSLog> singelUserGPSLogs = cursor.toArray();

		return singelUserGPSLogs;
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
	 * Deletes a Single Rating by the Object id
	 * 
	 * @param id
	 */
	public static void deleteCrashLog(String id) {

		CrashReport workedReport = CrashReport.coll.findOneById(id);
		workedReport.hide = true;
		CrashReport.coll.updateById(id, workedReport);
		// CrashReport.coll.save(workedReport);

	}

	// /**
	// * Reads the selected filter criteria and puts these criteria in the
	// search
	// * query
	// *
	// * @param filterOptions
	// * @return List with the Tourist that equals the selected filter criteria
	// */
	//
	// public static List<User> multiFilter(Form<String> filterOptions) {
	//
	// BasicDBObject searchQuery = new BasicDBObject();
	//
	// String email = "email";
	// String zipcode = "zipcode";
	// String year1 = "year1";
	// String year2 = "year2";
	// String country = "country";
	// String gender = "gender";
	// String returner = "returner";
	// String date1 = "date1";
	// String date2 = "date2";
	// List<User> userlist = new ArrayList<User>();
	//
	// String foundmail = filterOptions.data().get(email);
	// String foundzipcode = filterOptions.data().get(zipcode);
	// String foundyear1 = filterOptions.data().get(year1);
	// String foundyear2 = filterOptions.data().get(year2);
	// String foundcountry = filterOptions.data().get(country);
	// String foundDate1 = filterOptions.data().get(date1);
	// String foundDate2 = filterOptions.data().get(date2);
	//
	//
	// if (!foundmail.isEmpty()) {
	// searchQuery.put(email, foundmail);
	// }
	// if (!foundzipcode.isEmpty()) {
	//
	// searchQuery.put(zipcode, foundzipcode);
	// }
	//
	// if (!foundcountry.isEmpty()) {
	// searchQuery.put(country, foundcountry);
	// }
	// if (filterOptions.data().containsKey("male")
	// && !filterOptions.data().containsKey("female")) {
	//
	// searchQuery.put(gender, true);
	// }
	//
	// if (filterOptions.data().containsKey("female")
	// && !filterOptions.data().containsKey("male")) {
	// searchQuery.put(gender, false);
	// }
	//
	// if (filterOptions.data().containsKey("returner")) {
	// searchQuery.put(returner, true);
	// }
	// if (!foundyear1.isEmpty() && !foundyear2.isEmpty()) {
	//
	// int iyear1 = Integer.parseInt(foundyear1);
	// int iyear2 = Integer.parseInt(foundyear2);
	// userlist = User.coll.find(searchQuery)
	// .greaterThanEquals("year", iyear1)
	// .lessThanEquals("year", iyear2).toArray();
	// } else if (!foundyear1.isEmpty() && foundyear2.isEmpty()) {
	// int iyear1 = Integer.parseInt(foundyear1);
	// searchQuery.put("year", iyear1);
	// userlist = User.coll.find(searchQuery)
	// .greaterThanEquals("year", iyear1).toArray();
	// } else if (foundyear1.isEmpty() && !foundyear2.isEmpty()) {
	// int iyear2 = Integer.parseInt(foundyear2);
	// userlist = User.coll.find(searchQuery)
	// .lessThanEquals("year", iyear2).toArray();
	// } else {
	// userlist = User.coll.find(searchQuery).toArray();
	// }
	//
	// if (filterOptions.data().containsKey("footbike")) {
	// userlist = User.coll.find(searchQuery)
	// .greaterThan("dFootOrBikeDist", 0.01).toArray();
	// }
	// if (filterOptions.data().containsKey("car")) {
	// userlist = User.coll.find(searchQuery)
	// .greaterThan("dCarDist", 0.01).toArray();
	// }
	// if (filterOptions.data().containsKey("bustrain")) {
	// userlist = User.coll.find(searchQuery)
	// .greaterThan("dTrainOrBusDist", 0.01).toArray();
	// }
	// if (filterOptions.data().containsKey("ship")) {
	// userlist = User.coll.find(searchQuery)
	// .greaterThan("dShipDist", 0.01).toArray();
	// }
	//
	// for (int i = 0; i < userlist.size(); i++) {
	// if (!foundDate1.isEmpty()) {
	// Timestamp tDate1 = Timestamp.valueOf(foundDate1
	// + " 00:00:00.000000000");
	//
	// Timestamp tUserDate = Timestamp
	// .valueOf(userlist.get(i).dateFrom
	// + " 00:00:00.000000000");
	//
	// if (!foundDate2.isEmpty())
	// userlist.get(i).dateTo = foundDate2;
	//
	// if (!tUserDate.after(tDate1)) {
	// userlist.remove(i);
	// i--;
	// }
	//
	// }
	//
	// }
	//
	// return userlist;
	// }
	//
	// /**
	// * Search in the List of GPS-logs for the Tourists witch the selected
	// filter
	// * criteria and finds the corresponding GPS-data. These Data are filtered
	// * with two different selection criteria. Vehicle and Date From.
	// *
	// * @param userlist
	// * @param filterOptions
	// * @return The to the List of Tourists corresponding GPS-data
	// */
	// public static List<GPSLog> gpsfilter(List<User> userlist,
	// Form<String> filterOptions) {
	//
	// BasicDBObject searchQuery = new BasicDBObject();
	// BasicDBObject sortquery = new BasicDBObject("timestamp", 1);
	// String foundDate1 = filterOptions.data().get("date1");
	// String foundDate2 = filterOptions.data().get("date2");
	// int isizeUser = userlist.size();
	//
	// List<GPSLog> filtertLogs = new ArrayList<GPSLog>();
	//
	// if (userlist.isEmpty()) {
	// return filtertLogs;
	// } else {
	//
	// for (int i = 0; isizeUser > i; i++) {
	// searchQuery.put("userid", userlist.get(i).userid);
	//
	// if (filterOptions.data().containsKey("footbike")) {
	// searchQuery.put("vehicle", 0);
	// }
	// if (filterOptions.data().containsKey("car")) {
	// searchQuery.put("vehicle", 1);
	// }
	// if (filterOptions.data().containsKey("bustrain")) {
	// searchQuery.put("vehicle", 2);
	// }
	// if (filterOptions.data().containsKey("ship")) {
	// searchQuery.put("vehicle", 3);
	// }
	//
	// if (!foundDate1.isEmpty() && !foundDate2.isEmpty()) {
	//
	// Timestamp tDate1 = Timestamp.valueOf(foundDate1
	// + " 00:00:00.000000000");
	//
	// long lDate1 = tDate1.getTime();
	//
	// Timestamp tDate2 = Timestamp.valueOf(foundDate2
	// + " 00:00:00.000000000");
	//
	// long lDate2 = tDate2.getTime();
	// filtertLogs.addAll(GPSLog.coll.find(searchQuery)
	// .greaterThanEquals("timestamp", lDate1)
	// .lessThanEquals("timestamp", lDate2).sort(sortquery).toArray());
	//
	// } else if (!foundDate1.isEmpty() && foundDate2.isEmpty()) {
	//
	// Timestamp tDate1 = Timestamp.valueOf(foundDate1
	// + " 00:00:00.000000000");
	// long lDate1 = tDate1.getTime();
	//
	// filtertLogs.addAll(GPSLog.coll.find(searchQuery)
	// .greaterThanEquals("timestamp", lDate1).sort(sortquery).toArray());
	//
	// } else if (foundDate1.isEmpty() && !foundDate2.isEmpty()) {
	//
	// Timestamp tDate2 = Timestamp.valueOf(foundDate2
	// + " 00:00:00.000000000");
	//
	// long lDate2 = tDate2.getTime();
	//
	// filtertLogs.addAll(GPSLog.coll.find(searchQuery)
	// .lessThanEquals("timestamp", lDate2).sort(sortquery).toArray());
	// } else {
	// filtertLogs.addAll(GPSLog.coll.find(searchQuery).sort(sortquery).toArray());
	// }
	// }
	// }
	//
	// return filtertLogs;
	// }

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

	/**
	 * Reads the selected filter criteria and puts these criteria in the search
	 * query
	 * 
	 * @param filterOptions
	 * @return List with the Tourist that equals the selected filter criteria
	 */

	public static List<User> multiFilter(Request filterOptions) {

		BasicDBObject searchQuery = new BasicDBObject();

		String email = "email";
		String zipcode = "zipcode";
		String year1 = "yearFrom";
		String year2 = "yearTo";
		String country = "country";
		String gender = "gender";
		String returner = "returner";
		String date1 = "dateFrom";
		String date2 = "dateTo";
		List<User> userlist = new ArrayList<User>();

		String foundmail = filterOptions.getQueryString(email);
		String foundzipcode = filterOptions.getQueryString(zipcode);
		String foundyear1 = filterOptions.getQueryString(year1);
		String foundyear2 = filterOptions.getQueryString(year2);
		String foundcountry = filterOptions.getQueryString(country);
		String foundDate1 = filterOptions.getQueryString(date1);
		String foundDate2 = filterOptions.getQueryString(date2);

		if (filterOptions.queryString().containsKey("email")) {

			if (!filterOptions.getQueryString("email").isEmpty()) {
				searchQuery.put(email, foundmail);
			}
			if (!filterOptions.getQueryString("zipcode").isEmpty()) {

				searchQuery.put(zipcode, foundzipcode);
			}

			if (!filterOptions.getQueryString("country").isEmpty()) {
				searchQuery.put(country, foundcountry);
			}
			if (filterOptions.queryString().containsKey("gender")
					&& filterOptions.getQueryString("gender").equalsIgnoreCase(
							"male")
					&& !filterOptions.getQueryString("gender")
							.equalsIgnoreCase("female")) {

				searchQuery.put(gender, true);
			}

			if (filterOptions.queryString().containsKey("gender")
					&& filterOptions.getQueryString("gender").equalsIgnoreCase(
							"female")
					&& !filterOptions.getQueryString("gender")
							.equalsIgnoreCase("male")) {
				searchQuery.put(gender, false);
			}

			if (filterOptions.queryString().containsKey("returner")
					&& filterOptions.getQueryString("returner")
							.equalsIgnoreCase("yes")) {
				searchQuery.put(returner, true);
			}

			if (!filterOptions.getQueryString("yearFrom").isEmpty()
					&& !filterOptions.getQueryString("yearTo").isEmpty()) {

				int iyear1 = Integer.parseInt(foundyear1);
				int iyear2 = Integer.parseInt(foundyear2);
				userlist = User.coll.find(searchQuery)
						.greaterThanEquals("year", iyear1)
						.lessThanEquals("year", iyear2).toArray();
			} else if (!filterOptions.getQueryString("yearFrom").isEmpty()
					&& filterOptions.getQueryString("yearTo").isEmpty()) {
				int iyear1 = Integer.parseInt(foundyear1);
				searchQuery.put("year", iyear1);
				userlist = User.coll.find(searchQuery)
						.greaterThanEquals("year", iyear1).toArray();
			} else if (filterOptions.getQueryString("yearFrom").isEmpty()
					&& !filterOptions.getQueryString("yearTo").isEmpty()) {
				int iyear2 = Integer.parseInt(foundyear2);
				userlist = User.coll.find(searchQuery)
						.lessThanEquals("year", iyear2).toArray();
			} else {
				userlist = User.coll.find(searchQuery).toArray();
			}

			if (filterOptions.queryString().containsKey("vehicle")
					&& filterOptions.getQueryString("vehicle")
							.equalsIgnoreCase("0")) {
				userlist = User.coll.find(searchQuery)
						.greaterThan("dFootOrBikeDist", 0.01).toArray();
			}
			if (filterOptions.queryString().containsKey("vehicle")
					&& filterOptions.getQueryString("vehicle")
							.equalsIgnoreCase("1")) {
				userlist = User.coll.find(searchQuery)
						.greaterThan("dCarDist", 0.01).toArray();
			}
			if (filterOptions.queryString().containsKey("vehicle")
					&& filterOptions.getQueryString("vehicle")
							.equalsIgnoreCase("2")) {
				userlist = User.coll.find(searchQuery)
						.greaterThan("dTrainOrBusDist", 0.01).toArray();
			}
			if (filterOptions.queryString().containsKey("vehicle")
					&& filterOptions.getQueryString("vehicle")
							.equalsIgnoreCase("3")) {
				userlist = User.coll.find(searchQuery)
						.greaterThan("dShipDist", 0.01).toArray();
			}

			for (int i = 0; i < userlist.size(); i++) {
				if (!filterOptions.getQueryString("dateFrom").isEmpty()) {
					Timestamp tDate1 = Timestamp.valueOf(foundDate1
							+ " 00:00:00.000000000");

					Timestamp tUserDateFrom = Timestamp
							.valueOf(userlist.get(i).dateFrom
									+ " 00:00:00.000000000");

					if (!tUserDateFrom.after(tDate1)
							&& !tUserDateFrom.equals(tDate1)) {
						userlist.remove(i);
						i--;
					}

				}

			}
			for (int i = 0; i < userlist.size(); i++) {
				if (!filterOptions.getQueryString("dateTo").isEmpty()) {
					// userlist.get(i).dateTo = foundDate2;
					if (userlist.get(i).dateTo != null) {
						Timestamp tDate2 = Timestamp.valueOf(foundDate2
								+ " 00:00:00.000000000");

						System.out.print("   " + userlist.get(i).dateTo
								+ "      ");
						System.out.print(userlist.get(i).dateFrom);
						Timestamp tUserDateTo = Timestamp.valueOf(userlist
								.get(i).dateTo + " 00:00:00.000000000");

						if (tUserDateTo.after(tDate2)) {
							userlist.remove(i);
							i--;
						}

					}
				}
			}
			return userlist;
		} else {
			return User.coll.find().toArray();
		}

	}

	/**
	 * Search in the List of GPS-logs for the Tourists witch the selected filter
	 * criteria and finds the corresponding GPS-data. These Data are filtered
	 * with two different selection criteria. Vehicle and Date From.
	 * 
	 * @param userlist
	 * @param filterOptions
	 * @return The to the List of Tourists corresponding GPS-data
	 */
	public static String gpsfilter(String userid, Request filterOptions) {

		// BasicDBObject query = new BasicDBObject("userid", userid);

		BasicDBObject searchQuery = new BasicDBObject("userid", userid);
		BasicDBObject sortquery = new BasicDBObject("timestamp", 1);
		String foundDate1 = filterOptions.getQueryString("dateFrom");
		String foundDate2 = filterOptions.getQueryString("dateTo");
		List<GPSLog> filtertLogs = new ArrayList<GPSLog>();

		// System.out.print(filterOptions.getQueryString("vehicle"));

		if (filterOptions.getQueryString(foundDate1) == "dateFrom") {
			// System.out.print("date");
			// searchQuery.put("userid", userid);

			if (filterOptions.queryString().containsKey("vehicle")) {

				// System.out.print("vehicle");
				if (filterOptions.getQueryString("vehicle").equalsIgnoreCase(
						"0")) {
					searchQuery.put("vehicle", 0);
				}
				if (filterOptions.getQueryString("vehicle").equalsIgnoreCase(
						"1")) {
					searchQuery.put("vehicle", 1);
				}
				if (filterOptions.getQueryString("vehicle").equalsIgnoreCase(
						"2")) {
					searchQuery.put("vehicle", 2);
				}
				if (filterOptions.getQueryString("vehicle").equalsIgnoreCase(
						"3")) {
					searchQuery.put("vehicle", 3);
				}

			}
			if (!filterOptions.getQueryString("dateFrom").isEmpty()
					&& !foundDate2.isEmpty()) {

				Timestamp tDate1 = Timestamp.valueOf(foundDate1
						+ " 00:00:00.000000000");

				long lDate1 = tDate1.getTime();

				Timestamp tDate2 = Timestamp.valueOf(foundDate2
						+ " 00:00:00.000000000");

				long lDate2 = tDate2.getTime();
				filtertLogs.addAll(GPSLog.coll.find(searchQuery)
						.greaterThanEquals("timestamp", lDate1)
						.lessThanEquals("timestamp", lDate2).sort(sortquery)
						.toArray());

			} else if (!filterOptions.getQueryString("dateFrom").isEmpty()
					&& filterOptions.getQueryString("dateTo").isEmpty()) {

				Timestamp tDate1 = Timestamp.valueOf(foundDate1
						+ " 00:00:00.000000000");
				long lDate1 = tDate1.getTime();

				filtertLogs.addAll(GPSLog.coll.find(searchQuery)
						.greaterThanEquals("timestamp", lDate1).sort(sortquery)
						.toArray());

			} else if (filterOptions.getQueryString("dateFrom").isEmpty()
					&& !filterOptions.getQueryString("dateTo").isEmpty()) {

				Timestamp tDate2 = Timestamp.valueOf(foundDate2
						+ " 00:00:00.000000000");

				long lDate2 = tDate2.getTime();

				filtertLogs.addAll(GPSLog.coll.find(searchQuery)
						.lessThanEquals("timestamp", lDate2).sort(sortquery)
						.toArray());
			} else {
				filtertLogs.addAll(GPSLog.coll.find(searchQuery)
						.sort(sortquery).toArray());
			}

			return Json.toJson(filtertLogs).toString();
		} else {
			return Json.toJson(
					GPSLog.coll.find(searchQuery).sort(sortquery).toArray())
					.toString();
		}
	}

}
