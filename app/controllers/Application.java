package controllers;

import java.util.List;

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

public class Application extends Controller {
	static Form<String> stringForm = Form.form(String.class);

	private final static String JSON_PARSE_OK = "ok";

	@Security.Authenticated(Secured.class)
	public static Result index() {
		return ok(views.html.user.render(User.displayAll(), stringForm,
				GPSLog.all(), GeoCenter.getCenter(GPSLog.all())));
	}

//	public static Result newUser() {
//
//		User user = new User("123abc", "Tourist1@tracking.de", 1991,
//				"78467", "D", true, true);
//		User.create(user);
//
//		 User user2 = new User("456abc", "Tourist2@tracking.de", 1988,
//		 "78462", "I", true, false);
//		 User.create(user2);
//		
//		 User user3 = new User("789abc", "Tourist3@tracking.de", 1993,
//		 "12345", "F", true, true);
//		 User.create(user3);
//		
//		 User user4 = new User("123def", "Tourist4@tracking.de", 1980,
//		 "84327", "CH", false, false);
//		 User.create(user4);
//		
//		 User user5 = new User("456def", "Tourist5@tracking.de", 1992,
//		 "64285", "D", false, true);
//		 User.create(user5);
//		
//		 User user6 = new User("789def", "Tourist6@tracking.de", 1989, "42535",
//		 "EN", true, true);
//		 User.create(user6);
//		return redirect(routes.Application.index());
//	}
//
//	public static Result newRating() {
//
//		UserRating rating = new UserRating("123abc", "Strandbar",
//				"Da is schon schön!", 5);
//		UserRating.create(rating);
//
//		UserRating rating1 = new UserRating("123abc", "Hafen",
//				"Tolle Kneipen und Biergärten.", 4);
//		UserRating.create(rating1);
//		UserRating rating2 = new UserRating("123abc", "Hörnle",
//				"Ziemlich überfüllt aber sonst schön.", 2);
//		UserRating.create(rating2);
//
//		UserRating rating3 = new UserRating("789abc", "Seereihn",
//				"Es macht viel spaß den Abend dort aus klingen zu lassen", 4);
//		UserRating.create(rating3);
//		UserRating rating4 = new UserRating("123def", "Hafen",
//				"Sehr viele schöne boote..perfekt in der Abendämmerung", 5);
//		UserRating.create(rating4);
//
//		UserRating rating5 = new UserRating("456def", "Imperia",
//				"Das Wahrzeichen von konstanz...einfach ein must see!!", 4);
//		UserRating.create(rating5);
//
//		UserRating rating6 = new UserRating("789def", "Altstadt",
//				"Super schön", 5);
//		UserRating.create(rating6);
//
//		UserRating rating7 = new UserRating(
//				"789ghi",
//				"Altstadt",
//				"Altstadt zwar schön dennoch viel zu voll, zu wenig Cafe möglichkeiten",
//				3);
//		UserRating.create(rating7);
//
//		UserRating rating8 = new UserRating("123mno", "Schiffsrungfahrt",
//				"Die fart war schön, doch leider war das SChiff viel zu voll",
//				2);
//		UserRating.create(rating8);
//
//		UserRating rating9 = new UserRating("456mno", "Autofähre",
//				"Keine Probleme und keine Wartezeit", 5);
//		UserRating.create(rating9);
//
//		return redirect(routes.Application.index());
//	}
//
//	public static Result newTracking() {
//
//		GPSLog track = new GPSLog("123abc", 0, 1405031643, 47.679258, 9.154358, 
//				13.1);
//		GPSLog.create(track);
//
//		GPSLog track0 = new GPSLog("123abc", 3, 1405550043, 47.680183, 9.179077, 
//				19.3);
//		GPSLog.create(track0);
//
//		GPSLog track4 = new GPSLog("789abc", 1, 1404599643, 47.660763, 9.172211,
//				11.2);
//		GPSLog.create(track4);
//
//		GPSLog track5 = new GPSLog("123def", 3, 1405031643, 47.664462, 
//				9.456482, 12.3);
//		GPSLog.create(track5);
//
//		GPSLog track6 = new GPSLog("456def", 2, 1434392800, 47.696823, 
//				9.268341, 14.0);
//		GPSLog.create(track6);
//
//		GPSLog track7 = new GPSLog("789def", 3, 1474342800, 47.693126, 
//				9.185944, 10.5);
//		GPSLog.create(track7);
//
//		Function.withTrack();
//		return redirect(routes.Application.index());
//	}

	/**
	 * Checks the HTTP request and convert it into a Json Node. The Json Node is
	 * convert into an Tourist (User) Object. If this Tourist Object matches my
	 * defined Object, it is store in the Database.
	 * 
	 * @return "ok" if the Tourist is store in the database
	 */
	public static Result jsonUser() {

		JsonNode jUser = request().body().asJson();

		if (jUser == null) {

			return ok("Du schickst das Falsch!! User  Json == null");

		} else {

			User user = Json.fromJson(jUser, User.class);

			User.create(user);
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
			return ok("Du schickst das Falsch!! Tracking Json == null");
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
			return ok("Du schickst das Falsch!! Rating Json == null");
		} else {

			for (int i = 0; i < jRating.size(); i++) {
				UserRating rating = Json.fromJson(jRating.get(i),
						UserRating.class);
				UserRating.create(rating);
			}

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
	 * Calls Delete rating Function
	 * 
	 * @param id
	 * @return redirect to the main page
	 */
	public static Result deleteRating(String id) {
		Function.deleteRating(id);
		List<GPSLog> test = null;
		return ok(views.html.user.render(Function.withTrack(), stringForm,
				test, GeoCenter.getCenter(GPSLog.all())));
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
	public static Result filter() {

		Form<String> filterOptions = stringForm.bindFromRequest();
		List<User> userlist = Function.multiFilter(filterOptions);
		List<GPSLog> gpslog = Function.gpsfilter(userlist, filterOptions);
		return ok(views.html.user.render(userlist, stringForm, gpslog,
				GeoCenter.getCenter(gpslog)));

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
			session("password", loginForm.get().password);
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

}
