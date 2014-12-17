package controllers;


import models.CrashReport;
import models.Function;
import models.GPSLog;
import models.GeoCenter;
import models.LoginUser;
import models.User;
import models.UserRating;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Application extends Controller {
	static Form<String> stringForm = Form.form(String.class);

	private final static String JSON_PARSE_OK = "ok";

	@Security.Authenticated(Secured.class)
	public static Result index() {

		return ok(views.html.user.render(User.displayAll()));
	}

	public static Result newUser() {
//		String date = "newUser";
//		User user = new User("3289712368", "Rout@Test.de", 1991, "64285", "D",
//				true, false, "14", "Lenovo", "Yoga 10", "400x800");
//		User.create(user, date);

		// User user2 = new User("456abc", "Tourist2@tracking.de", 1988,
		// "78462", "I", true, false);
		// User.create(user2);
		//
		// User user3 = new User("789abc", "Tourist3@tracking.de", 1993,
		// "12345", "F", true, true);
		// User.create(user3);
		//
		// User user4 = new User("123def", "Tourist4@tracking.de", 1980,
		// "84327", "CH", false, false);
		// User.create(user4);
		//
		// User user5 = new User("456def", "Tourist5@tracking.de", 1992,
		// "64285", "D", false, true);
		// User.create(user5);
		//
		// User user6 = new User("789def", "Tourist6@tracking.de", 1989,
		// "42535",
		// "EN", true, true);
		// User.create(user6);
		return redirect(routes.Application.index());
	}

	//
	// public static Result newRating() {
	//
	// UserRating rating = new UserRating("123abc", "Strandbar",
	// "Da is schon schön!", 5);
	// UserRating.create(rating);
	//
	// UserRating rating1 = new UserRating("123abc", "Hafen",
	// "Tolle Kneipen und Biergärten.", 4);
	// UserRating.create(rating1);
	// UserRating rating2 = new UserRating("123abc", "Hörnle",
	// "Ziemlich überfüllt aber sonst schön.", 2);
	// UserRating.create(rating2);
	//
	// UserRating rating3 = new UserRating("789abc", "Seereihn",
	// "Es macht viel spaß den Abend dort aus klingen zu lassen", 4);
	// UserRating.create(rating3);
	// UserRating rating4 = new UserRating("123def", "Hafen",
	// "Sehr viele schöne boote..perfekt in der Abendämmerung", 5);
	// UserRating.create(rating4);
	//
	// UserRating rating5 = new UserRating("456def", "Imperia",
	// "Das Wahrzeichen von konstanz...einfach ein must see!!", 4);
	// UserRating.create(rating5);
	//
	// UserRating rating6 = new UserRating("789def", "Altstadt",
	// "Super schön", 5);
	// UserRating.create(rating6);
	//
	// UserRating rating7 = new UserRating(
	// "789ghi",
	// "Altstadt",
	// "Altstadt zwar schön dennoch viel zu voll, zu wenig Cafe möglichkeiten",
	// 3);
	// UserRating.create(rating7);
	//
	// UserRating rating8 = new UserRating("123mno", "Schiffsrungfahrt",
	// "Die fart war schön, doch leider war das SChiff viel zu voll",
	// 2);
	// UserRating.create(rating8);
	//
	// UserRating rating9 = new UserRating("456mno", "Autofähre",
	// "Keine Probleme und keine Wartezeit", 5);
	// UserRating.create(rating9);
	//
	// return redirect(routes.Application.index());
	// }
	//
	public static Result newTracking() {

		GPSLog track = new GPSLog("3289712368", 0, 1407717243, 47.67356,
				9.18467, 0.2);
		GPSLog.create(track);
		GPSLog track1 = new GPSLog("3289712368", 0, 1407717244, 47.67369,
				9.18576, 0.2);
		GPSLog.create(track1);
		GPSLog track2 = new GPSLog("3289712368", 0, 1407717245, 47.67379,
				9.18717, 0.2);
		GPSLog.create(track2);
		GPSLog track3 = new GPSLog("3289712368", 0, 1407717246, 47.67389,
				9.18913, 0.2);
		GPSLog.create(track3);
		GPSLog track4 = new GPSLog("3289712368", 0, 1407717247, 47.67385,
				9.19231, 0.2);
		GPSLog.create(track4);
		GPSLog track5 = new GPSLog("3289712368", 0, 1407717248, 47.67311,
				9.19290, 0.2);
		GPSLog.create(track5);
		GPSLog track6 = new GPSLog("3289712368", 1, 1407717249, 47.67094,
				9.18882, 0.2);
		GPSLog.create(track6);
		GPSLog track7 = new GPSLog("3289712368", 1, 1407717250, 47.67034,
				9.18745, 0.2);
		GPSLog.create(track7);
		GPSLog track8 = new GPSLog("3289712368", 1, 1407717251, 47.66901,
				9.19247, 0.2);
		GPSLog.create(track8);
		GPSLog track9 = new GPSLog("3289712368", 1, 1407717252, 47.66892,
				9.19238, 0.2);
		GPSLog.create(track9);
		GPSLog tracka = new GPSLog("3289712368", 1, 1407717253, 47.66690,
				9.19899, 0.2);
		GPSLog.create(tracka);
		GPSLog tracks = new GPSLog("3289712368", 1, 1407717254, 47.66588,
				9.20311, 0.2);
		GPSLog.create(tracks);
		GPSLog trackd = new GPSLog("3289712368", 1, 1407717255, 47.66684,
				9.20650, 0.2);
		GPSLog.create(trackd);
		GPSLog trackf = new GPSLog("3289712368", 1, 1407717256, 47.66722,
				9.21148, 0.2);
		GPSLog.create(trackf);
		GPSLog trackg = new GPSLog("3289712368", 0, 1407717257, 47.66681,
				9.21551, 0.2);
		GPSLog.create(trackg);
		GPSLog trackh = new GPSLog("3289712368", 0, 1407717258, 47.66609,
				9.21770, 0.2);
		GPSLog.create(trackh);
		GPSLog trackj = new GPSLog("3289712368", 3, 1407717259, 47.66419,
				9.22886, 0.2);
		GPSLog.create(trackj);
		GPSLog trackk = new GPSLog("3289712368", 3, 1407717260, 47.66203,
				9.25598, 0.2);
		GPSLog.create(trackk);
		GPSLog trackq = new GPSLog("3289712368", 3, 1407717261, 47.65972,
				9.27864, 0.2);
		GPSLog.create(trackq);
		GPSLog trackw = new GPSLog("3289712368", 3, 1407717262, 47.65694,
				9.30130, 0.2);
		GPSLog.create(trackw);
		GPSLog tracky = new GPSLog("3289712368", 3, 1407717263, 47.65601,
				9.32877, 0.2);
		GPSLog.create(tracky);
		GPSLog trackx = new GPSLog("3289712368", 3, 1407717264, 47.65323,
				9.35211, 0.2);
		GPSLog.create(trackx);
		GPSLog trackc = new GPSLog("3289712368", 3, 1407717265, 47.65277,
				9.37683, 0.2);
		GPSLog.create(trackc);
		GPSLog trackv = new GPSLog("3289712368", 3, 1407717266, 47.64859,
				9.42009, 0.2);
		GPSLog.create(trackv);
		GPSLog trackb = new GPSLog("3289712368", 3, 1407717267, 47.64765,
				9.45305, 0.2);
		GPSLog.create(trackb);
		GPSLog trackn = new GPSLog("3289712368", 3, 1407717268, 47.64769,
				9.47708, 0.2);
		GPSLog.create(trackn);
		GPSLog trackm = new GPSLog("3289712368", 3, 1407717269, 47.64993,
				9.48395, 0.2);
		GPSLog.create(trackm);

		Function.withTrack();
		return redirect(routes.Application.index());
	}

	/**
	 * Checks the HTTP request and convert it into a Json Node. The Json Node is
	 * convert into an Tourist (User) Object. If this Tourist Object matches my
	 * defined Object, it is store in the Database.
	 * 
	 * @return "ok" if the Tourist is store in the database
	 */
	public static Result jsonUser() {

		JsonNode jUser = request().body().asJson();
		String date = "newUser";
		if (jUser == null) {

			return ok("Wrong Message! addUser  Json == null");

		} else {

			User user = Json.fromJson(jUser, User.class);
			User.create(user, date);
			return ok(JSON_PARSE_OK);
		}

	}

	/**
	 * Checks the HTTP request and convert it into an Array of Json Nodes. The
	 * Json Nodes are convert into my GPSLog Object. If this GPSLog Object
	 * matches my defined Object, it is store in the Database.
	 * 
	 * @return "ok" if the Tourist is store in the database
	 */
	public static Result jsonTracking() {

		JsonNode jTracking = request().body().asJson()
				.withArray("gpscollection");

		if (jTracking == null) {
			return ok("Wrong Message! addTracking Json == null");
		} else {

			for (int i = 0; i < jTracking.size(); i++) {
				GPSLog gpsData = Json.fromJson(jTracking.get(i), GPSLog.class);
				GPSLog.create(gpsData);
			}
			Function.withTrack();
			return ok(JSON_PARSE_OK);
		}

	}

	/**
	 * Checks the HTTP request and convert it into an Array of Json Nodes. The
	 * Json Nodes are convert into my UserRating Object. If this UserRating
	 * Object matches my defined Object, it is store in the Database.
	 * 
	 * @return "ok" if the Tourist is store in the database
	 */
	public static Result jsonRating() {
		JsonNode jRating = request().body().asJson()
				.withArray("ratingcollection");

		if (jRating == null) {
			return ok("Wrong Message! addRating Json == null");
		} else {

			for (int i = 0; i < jRating.size(); i++) {
				UserRating rating = Json.fromJson(jRating.get(i),
						UserRating.class);
				UserRating.create(rating);
			}

			return ok(JSON_PARSE_OK);
		}

	}

	public static Result crashReport() {
		JsonNode report = request().body().asJson();

		if (report == null) {

			return ok("Wrong Message! CrashReport  Json == null");

		} else {

			CrashReport user = Json.fromJson(report, CrashReport.class);
			CrashReport.create(user);
			return ok(JSON_PARSE_OK);
		}

	}

	/**
	 * Calls Delete Function
	 * 
	 * @param id
	 * @return redirect to the main page
	 */
	public static Result delete(String id) {
		Function.delete(id);
		return redirect(routes.Application.index());
	}

	/**
	 * Calls Delete CrashLogs Function
	 * 
	 * @param id
	 * @return redirect to the main page
	 */
	public static Result deleteCrashLog(String id) {
		Function.deleteCrashLog(id);
		return redirect(routes.Application.displayCrash());
	}

	/**
	 * Calls Delete rating Function
	 * 
	 * @param id
	 * @return redirect to the main page
	 */
	public static Result deleteRating(String id) {
		Function.deleteRating(id);
		return ok(views.html.user.render(Function.withTrack()));
	}

	/**
	 * Calls Function to display all Ratings given by the Tourist
	 * 
	 * @param id
	 * @return Opens Window with the Ratings
	 */
	public static Result displayRating(String userid) {
		return ok(views.html.Rating.render(Function.UserRating(userid)));
	}

	/**
	 * Calls Function to display all Distances of the Tourist
	 * 
	 * @param id
	 * @return Opens Window with the Distances
	 */
	public static Result displayDistance(String userid) {
		return ok(views.html.Distance.render(Function.UserDistance(userid)));
	}

	/**
	 * Calls Function to Filter Database of the Tourists and their corresponding
	 * GPS-Logs.
	 * 
	 * @param id
	 * @return Opens main page with the List of Tourist that match the selected
	 *         criteria
	 */
	@Security.Authenticated(Secured.class)
	public static Result filter() {

		return ok(views.html.user.render(Function.multiFilter(request())));

	}

	/**
	 * Opens Login Screen
	 * 
	 * @return Displays the login window
	 */
	public static Result login() {
		return ok(views.html.login.render(Form.form(LoginUser.class)));
	}

	/**
	 * Clears the Login-Data of the current session and opens the Login-Screen
	 * again
	 * 
	 * @return redirect to the Login-Screen
	 */
	public static Result logout() {
		session().clear();
		flash("success", "You've been logged out");
		return redirect(routes.Application.login());
	}

	/**
	 * Checks if the entered Login-data match a Login-data in the Database
	 * 
	 * @return redirects to the main page
	 */
	public static Result authenticate() {
		Form<LoginUser> loginForm = Form.form(LoginUser.class)
				.bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(views.html.login.render(loginForm));
		} else {
			session().clear();
			session("email", loginForm.get().email);
			session("password",
					String.valueOf(loginForm.get().password.hashCode()));
			if (LoginUser.checkUser(loginForm))
				return redirect(routes.Application.index());
			else
				return badRequest(views.html.login.render(loginForm));
		}
	}

	/**
	 * Adds a new User with the entered Information to the Database
	 * 
	 * @return redirect to the main page
	 */
	public static Result register() {
		Form<LoginUser> loginForm = Form.form(LoginUser.class)
				.bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(views.html.login.render(loginForm));
		} else {
			LoginUser.create(loginForm);
			return redirect(routes.Application.index());
		}
	}

	public static Result authenticateToRegister() {
		Form<LoginUser> loginForm = Form.form(LoginUser.class)
				.bindFromRequest();
		return ok(views.html.addUser.render(loginForm));
	}

	public static Result getRout(String userid) {
		return ok(Json.toJson(GeoCenter.getCenter(Function.singelUser(userid))));

	}

	public static Result search(String userid) {
		// System.out.print(request());
		return ok(Json.toJson(Function.gpsfilter(userid, request())));

	}

	public static Result release() {

		double version = 1.4;
		int release = 5;
		ObjectNode jsonObject = Json.newObject();

		jsonObject.put("version", version);
		jsonObject.put("release", release);

		return ok(jsonObject);
	}

	public static Result download() {

		return ok(views.html.download.render());
	}

	@Security.Authenticated(Secured.class)
	public static Result displayCrash() {

		return ok(views.html.crashLogs.render(CrashReport.displayAll()));
	}
	
	@Security.Authenticated(Secured.class)
	public static Result displayUserInformations() {

		return ok(views.html.userInfo.render(User.diplayUserInformations()));
	}
	

}
