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
