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
		System.out.print(request());

		return ok(views.html.user.render(User.displayAll(), stringForm,
				GPSLog.all())); // GeoCenter.getCenter(GPSLog.all())
	}

	public static Result newUser() {

		User user = new User("3289712368", "Rout@Test.de", 1991, "64285", "D",
				true, false);
		User.create(user);

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

		GPSLog track = new GPSLog("3289712368", 0, 1405031643, 47.67352,
				9.18467, 13.1);
		GPSLog.create(track);
		GPSLog track1 = new GPSLog("3289712368", 0, 1405031644, 47.67361,
				9.18576, 13.1);
		GPSLog.create(track1);
		GPSLog track2 = new GPSLog("3289712368", 0, 1405031645, 47.67372,
				9.18717, 13.1);
		GPSLog.create(track2);
		GPSLog track3 = new GPSLog("3289712368", 0, 1405031646, 47.67382,
				9.18913, 13.1);
		GPSLog.create(track3);
		GPSLog track4 = new GPSLog("3289712368", 0, 1405031647, 47.67379,
				9.19231, 13.1);
		GPSLog.create(track4);
		GPSLog track5 = new GPSLog("3289712368", 0, 1405031648, 47.67306,
				9.19290, 13.1);
		GPSLog.create(track5);
		GPSLog track6 = new GPSLog("3289712368", 1, 1405031649, 47.67087,
				9.18882, 13.1);
		GPSLog.create(track6);
		GPSLog track7 = new GPSLog("3289712368", 1, 1405031650, 47.67029,
				9.18745, 13.1);
		GPSLog.create(track7);
		GPSLog track8 = new GPSLog("3289712368", 1, 1405031651, 47.66896,
				9.19247, 13.1);
		GPSLog.create(track8);
		GPSLog track9 = new GPSLog("3289712368", 1, 1405031652, 47.66887,
				9.19238, 13.1);
		GPSLog.create(track9);
		GPSLog tracka = new GPSLog("3289712368", 1, 1405031653, 47.66685,
				9.19899, 13.1);
		GPSLog.create(tracka);
		GPSLog tracks = new GPSLog("3289712368", 1, 1405031654, 47.66581,
				9.20311, 13.1);
		GPSLog.create(tracks);
		GPSLog trackd = new GPSLog("3289712368", 1, 1405031655, 47.66679,
				9.20650, 13.1);
		GPSLog.create(trackd);
		GPSLog trackf = new GPSLog("3289712368", 1, 1405031656, 47.66714,
				9.21148, 13.1);
		GPSLog.create(trackf);
		GPSLog trackg = new GPSLog("3289712368", 0, 1405031657, 47.66679,
				9.21551, 13.1);
		GPSLog.create(trackg);
		GPSLog trackh = new GPSLog("3289712368", 0, 1405031658, 47.66604,
				9.21770, 13.1);
		GPSLog.create(trackh);
		GPSLog trackj = new GPSLog("3289712368", 3, 1405031659, 47.66414,
				9.22886, 13.1);
		GPSLog.create(trackj);
		GPSLog trackk = new GPSLog("3289712368", 3, 1405031660, 47.66197,
				9.25598, 13.1);
		GPSLog.create(trackk);
		GPSLog trackq = new GPSLog("3289712368", 3, 1405031661, 47.65966,
				9.27864, 13.1);
		GPSLog.create(trackq);
		GPSLog trackw = new GPSLog("3289712368", 3, 1405031662, 47.65688,
				9.30130, 13.1);
		GPSLog.create(trackw);
		GPSLog tracky = new GPSLog("3289712368", 3, 1405031663, 47.65596,
				9.32877, 13.1);
		GPSLog.create(tracky);
		GPSLog trackx = new GPSLog("3289712368", 3, 1405031664, 47.65318,
				9.35211, 13.1);
		GPSLog.create(trackx);
		GPSLog trackc = new GPSLog("3289712368", 3, 1405031665, 47.65272,
				9.37683, 13.1);
		GPSLog.create(trackc);
		GPSLog trackv = new GPSLog("3289712368", 3, 1405031666, 47.64856,
				9.42009, 13.1);
		GPSLog.create(trackv);
		GPSLog trackb = new GPSLog("3289712368", 3, 1405031667, 47.64763,
				9.45305, 13.1);
		GPSLog.create(trackb);
		GPSLog trackn = new GPSLog("3289712368", 3, 1405031668, 47.64763,
				9.47708, 13.1);
		GPSLog.create(trackn);
		GPSLog trackm = new GPSLog("3289712368", 3, 1405031669, 47.64987,
				9.48395, 13.1);
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

		if (jUser == null) {

			return ok("Wrong Message! addUser  Json == null");

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
		return ok(views.html.user
				.render(Function.withTrack(), stringForm, test)); // ,
																	// GeoCenter.getCenter(GPSLog.all())
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
		return ok(views.html.user.render(userlist, stringForm, gpslog));

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
		System.out.print(request().queryString());
		return ok(Json.toJson(GeoCenter.getCenter(Function.singelUser(userid))));

	}
	
	public static Result search() {
		System.out.print(request().queryString());
		return ok(Json.toJson(request().queryString()));

	}

}
