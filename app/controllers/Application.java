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

	public static Result newUser() {

		User user = new User("123bc", "mamiosga@htwg-konstanz.de", 1991,
				"78467", "DE", true, true);
		User.create(user);

		// User user2 = new User("456ef", "bjtriben@htwg-konstanz.de", 1988,
		// "78462", "PL", true, false);
		// User.create(user2);
		//
		// User user3 = new User("789hi", "hakleiner@htwg-konstanz.de", 1993,
		// "78467", "A", true, true);
		// User.create(user3);
		//
		// User user4 = new User("123kl", "Frau.Schweiz@gmail.com", 1980,
		// "64285",
		// "CH", false, false);
		// User.create(user4);
		//
		// User user5 = new User("123no", "Frau.Schweiz@gmail.com", 1980,
		// "64285",
		// "CH", false, false);
		// User.create(user5);
		//
		// User user6 = new User("456no", "Engländer@gmail.com", 1980, "64285",
		// "EN", true, true);
		// User.create(user6);
		return redirect(routes.Application.index());
	}

	public static Result newRating() {

		UserRating rating = new UserRating("123kl", "Strandbar",
				"Da is schon schön!", 5);
		UserRating.create(rating);

		UserRating rating1 = new UserRating("123kl", "Hafen",
				"Tolle Kneipen und Biergärten.", 4);
		UserRating.create(rating1);
		UserRating rating2 = new UserRating("123kl", "Hörnle",
				"Ziemlich überfüllt aber sonst schön.", 2);
		UserRating.create(rating2);

		UserRating rating3 = new UserRating("456def", "Seereihn",
				"Es macht viel spaß den Abend dort aus klingen zu lassen", 4);
		UserRating.create(rating3);
		UserRating rating4 = new UserRating("456def", "Hafen",
				"Sehr viele schöne boote..perfekt in der Abendämmerung", 5);
		UserRating.create(rating4);

		UserRating rating5 = new UserRating("456def", "Imperia",
				"Das Wahrzeichen von konstanz...einfach ein must see!!", 4);
		UserRating.create(rating5);

		UserRating rating6 = new UserRating("789ghi", "Altstadt",
				"Super schön", 5);
		UserRating.create(rating6);

		UserRating rating7 = new UserRating(
				"789ghi",
				"Altstadt",
				"Altstadt zwar schön dennoch viel zu voll, zu wenig Cafe möglichkeiten",
				3);
		UserRating.create(rating7);

		UserRating rating8 = new UserRating("123mno", "Schiffsrungfahrt",
				"Die fart war schön, doch leider war das SChiff viel zu voll",
				2);
		UserRating.create(rating8);

		UserRating rating9 = new UserRating("456mno", "Autofähre",
				"Keine Probleme und keine Wartezeit", 5);
		UserRating.create(rating9);

		return redirect(routes.Application.index());
	}

	public static Result newTracking() {

		GPSLog track = new GPSLog("123bc", 1, 1504572800, 47.8804704, 9.191972,
				30.4);
		GPSLog.create(track);

		GPSLog track2 = new GPSLog("123bc", 2, 1464572800, 47.6684804, 9.2719,
				30.9);
		GPSLog.create(track2);

		GPSLog track3 = new GPSLog("123bc", 3, 1444472800, 47.4604204, 9.07211,
				42.3);
		GPSLog.create(track3);

		GPSLog track0 = new GPSLog("123no", 3, 1434272800, 47.6204764, 9.373,
				30.6);
		GPSLog.create(track0);

		GPSLog track8 = new GPSLog("123no", 2, 1454772800, 47.610479, 9.0710,
				49.6);
		GPSLog.create(track8);

		GPSLog track4 = new GPSLog("123kl", 0, 1494972800, 47.6204784, 9.87191,
				10.0);
		GPSLog.create(track4);

		GPSLog track5 = new GPSLog("123kl", 2, 1414272800, 47.6004104,
				9.271272, 14.0);
		GPSLog.create(track5);

		GPSLog track6 = new GPSLog("456no", 0, 1434392800, 47.69047099,
				9.471572, 19.0);
		GPSLog.create(track6);

		GPSLog track7 = new GPSLog("123bc", 1, 1474342800, 47.8604285,
				9.473972, 100.5);
		GPSLog.create(track7);

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
	 * Clears the Logindata of the current session and opens the Login-Screen
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
