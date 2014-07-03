package controllers;

import java.util.List;
<<<<<<< HEAD
import java.util.Map;

import models.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
=======

import models.Function;
import models.GPSLog;
import models.User;
import models.UserRating;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b

import com.fasterxml.jackson.databind.JsonNode;

public class Application extends Controller {
	static Form<String> stringForm = Form.form(String.class);

	private final static String JSON_PARSE_OK = "ok";

<<<<<<< HEAD
	@Security.Authenticated(Secured.class)
	public static Result index() {
		return ok(views.html.user.render(User.displayAll(), stringForm,
				GPSLog.all(), GeoCenter.getCenter(GPSLog.all())));
		// return redirect(routes.Application.users());
	}

//	public static Result users() {
//		// List<GeoCenter> test = null;
//
//		return ok(views.html.user.render(User.displayAll(), stringForm,
//				GPSLog.all(), GeoCenter.getCenter(GPSLog.all())));
//	}

	public static Result newUser() {

		User user = new User("123bc", "mamiosga@htwg-konstanz.de", 1991,
				"78467", "DE", true, true);
		User.create(user);

//		User user2 = new User("456ef", "bjtriben@htwg-konstanz.de", 1988,
//				"78462", "PL", true, false);
//		User.create(user2);
//
//		User user3 = new User("789hi", "hakleiner@htwg-konstanz.de", 1993,
//				"78467", "A", true, true);
//		User.create(user3);
//
//		User user4 = new User("123kl", "Frau.Schweiz@gmail.com", 1980, "64285",
//				"CH", false, false);
//		User.create(user4);
//
//		User user5 = new User("123no", "Frau.Schweiz@gmail.com", 1980, "64285",
//				"CH", false, false);
//		User.create(user5);
//
//		User user6 = new User("456no", "Engländer@gmail.com", 1980, "64285",
//				"EN", true, true);
//		User.create(user6);
		return redirect(routes.Application.index());
=======
	public static Result index() {
		return redirect(routes.Application.users());
	}

	public static Result users() {
		return ok(views.html.user.render(User.displayAll(), stringForm));
	}

	public static Result newUser() {

		User user = new User("123abc", "mamiosga@htwg-konstanz.de", 1991, "78467", "DE",
				true, true);
		User.create(user);
		
		User user2 = new User("456def", "bjtriben@htwg-konstanz.de", 1988, "78462", "PL",
				true, false);
		User.create(user2);
		
		User user3 = new User("789ghi", "hakleiner@htwg-konstanz.de", 1993, "78467", "A",
				true, true);
		User.create(user3);
		
		User user4 = new User("123jkl", "Frau.Schweiz@gmail.com", 1980, "64285", "CH",
				false, false);
		User.create(user4);
		
		User user5 = new User("123mno", "Frau.Schweiz@gmail.com", 1980, "64285", "CH",
				false, false);
		User.create(user5);
		
		User user6 = new User("456mno", "Engländer@gmail.com", 1980, "64285", "EN",
				true, true);
		User.create(user6);
		return redirect(routes.Application.users());
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
	}

	public static Result newRating() {

<<<<<<< HEAD
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

=======
		UserRating rating = new UserRating("123abc", "Strandbar",
				"Da is schon schön!", 5);
		UserRating.create(rating);

		UserRating rating1 = new UserRating(
				"123abc",
				"Hafen",
				"Tolle Kneipen und Biergärten.",
				4);
		UserRating.create(rating1);
		UserRating rating2 = new UserRating("123abc", "Hörnle",
				"Ziemlich überfüllt aber sonst schön.", 2);
		UserRating.create(rating2);
		
		UserRating rating3 = new UserRating("456def", "Seereihn", "Es macht viel spaß den Abend dort aus klingen zu lassen", 4);
		UserRating.create(rating3);
		UserRating rating4 = new UserRating("456def", "Hafen", "Sehr viele schöne boote..perfekt in der Abendämmerung", 5);
		UserRating.create(rating4);
		
		UserRating rating5 = new UserRating("456def", "Imperia", "Das Wahrzeichen von konstanz...einfach ein must see!!", 4);
		UserRating.create(rating5);
		
		UserRating rating6 = new UserRating("789ghi", "Altstadt", "Super schön", 5);
		UserRating.create(rating6);
		
		UserRating rating7 = new UserRating("789ghi", "Altstadt", "Altstadt zwar schön dennoch viel zu voll, zu wenig Cafe möglichkeiten", 3);
		UserRating.create(rating7);
		
		UserRating rating8 = new UserRating("123mno", "Schiffsrungfahrt", "Die fart war schön, doch leider war das SChiff viel zu voll", 2);
		UserRating.create(rating8);
		
		UserRating rating9 = new UserRating("456mno", "Autofähre", "Keine Probleme und keine Wartezeit", 5);
		UserRating.create(rating9);

		return redirect(routes.Application.users());
	}

	public static Result newTracking() {
	
		GPSLog track = new GPSLog("123abc", 1, 1404172800, 55.4, 11.3, 200.4);
		GPSLog.create(track);

		GPSLog track2 = new GPSLog("123abc", 2, 1404172800, 55.4, 11.3, 30.9);
		GPSLog.create(track2);

		GPSLog track3 = new GPSLog("123ac", 3, 1404172800, 55.4, 11.3, 42.3);
		GPSLog.create(track3);

		GPSLog track0 = new GPSLog("123mno", 3, 1404172800, 55.4, 11.3, 30.6);
		GPSLog.create(track0);

		GPSLog track8 = new GPSLog("123no", 2, 1404172800, 55.4, 11.3, 49.6);
		GPSLog.create(track8);

		GPSLog track4 = new GPSLog("123jkl", 0, 1404172800, 55.4, 11.3, 10.0);
		GPSLog.create(track4);

		GPSLog track5 = new GPSLog("123kl", 2, 1404172800, 55.4, 11.3, 14.0);
		GPSLog.create(track5);

		GPSLog track6 = new GPSLog("46mno", 0, 1404172800, 55.4, 11.3, 19.0);
		GPSLog.create(track6);

		GPSLog track7 = new GPSLog("12abc", 1, 1404172800, 55.4, 11.3, 100.5);
		GPSLog.create(track7);
		
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
		Function.withTrack();
		return redirect(routes.Application.index());
	}

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

	public static Result jsonTracking() {

<<<<<<< HEAD
		JsonNode jTracking = request().body().asJson()
				.withArray("gpscollection");
=======
		JsonNode jTracking = request().body().asJson().withArray("gpscollection");

>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b

		if (jTracking == null) {
			return ok("Du schickst das Falsch!! Tracking Json == null");
		} else {
<<<<<<< HEAD

			for (int i = 0; i < jTracking.size(); i++) {
=======
			
			for(int i = 0; i < jTracking.size(); i++){
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
				GPSLog gpsData = Json.fromJson(jTracking.get(i), GPSLog.class);
				GPSLog.create(gpsData);
			}
			Function.withTrack();
			return ok(JSON_PARSE_OK);
		}

	}

	public static Result jsonRating() {
<<<<<<< HEAD
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

=======
		JsonNode jRating = request().body().asJson().withArray("ratingcollection");
		
		if (jRating == null) {
			return ok("Du schickst das Falsch!! Rating Json == null");
		} else {
			
			for(int i = 0; i < jRating.size(); i++){
				UserRating rating = Json.fromJson(jRating.get(i), UserRating.class);
				UserRating.create(rating);
			}
			
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
			return ok(JSON_PARSE_OK);
		}

	}

	public static Result delete(String id) {
		Function.delete(id);
<<<<<<< HEAD
		return redirect(routes.Application.index());
=======
		return redirect(routes.Application.users());
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
	}

	public static Result deleteRating(String id) {
		Function.deleteRating(id);
<<<<<<< HEAD
		List<GPSLog> test = null;
		return ok(views.html.user.render(Function.withTrack(), stringForm, test, GeoCenter.getCenter(GPSLog.all())));
=======
		return ok(views.html.user.render(Function.withTrack(), stringForm));
>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
	}

	public static Result displayRating(String userid) {
		return ok(views.html.Rating.render(Function.UserRating(userid)));
	}

	public static Result displayDistance(String userid) {
		return ok(views.html.Distance.render(Function.UserDistance(userid)));
	}

	public static Result filter() {
<<<<<<< HEAD
	   	
		Form<String> filterOptions = stringForm.bindFromRequest();
		List<User> userlist = Function.multiFilter(filterOptions);
		List<GPSLog> gpslog= Function.gpsfilter(userlist, filterOptions);
		return ok(views.html.user.render(userlist, stringForm, gpslog, GeoCenter.getCenter(gpslog)));

	}

	public static Result login() {
		return ok(views.html.login.render(Form.form(LoginUser.class)));
	}
	
	public static Result logout() {
	    session().clear();
	    flash("success", "You've been logged out");
	    return redirect(
	        routes.Application.login()
	    );
	}

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

	public static Result authenticateAdmin() {
		Form<LoginUser> loginForm = Form.form(LoginUser.class)
				.bindFromRequest();
		return ok(views.html.addUser.render(loginForm));
	}
	
=======
   	
		Form<String> filterOptions = stringForm.bindFromRequest();
		
		List<User> userlist = Function.multiFilter(filterOptions);
		return ok(views.html.user.render(userlist, stringForm));

	}
	public static Result filtergender(boolean value) {
		return ok(views.html.user.render(Function.fResult(value), stringForm));
	}


>>>>>>> 0a6b2366ed4f3b96503baaa4ed7ee48c1354079b
}