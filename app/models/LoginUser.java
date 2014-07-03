package models;

import java.util.List;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;
import play.data.Form;
import play.modules.mongodb.jackson.MongoDB;

import com.mongodb.BasicDBObject;

public class LoginUser {

	@Id
	@ObjectId
	public String id;

	public String email;
	public String password;


	public static JacksonDBCollection<LoginUser, String> coll = MongoDB
			.getCollection("Users", LoginUser.class, String.class);

	public LoginUser(String email, String password) {
		this.email = email;
		this.password = password;

	}

	public LoginUser() {

	}

	public static void create(Form<LoginUser> loginForm) {

		LoginUser user = loginForm.get();
		int isizeUser = LoginUser.coll.find().size();
		boolean bNewUser = true;
		List<LoginUser> Luser = LoginUser.coll.find().toArray();
		if (user != null)

			for (int i = 0; isizeUser > i; i++) {
				if (user.email.equals(Luser.get(i).email)) {
					bNewUser = false;
				}
			}

		if (bNewUser) 
			LoginUser.coll.save(user);
	}
	
	public static boolean checkUser(Form<LoginUser> loginForm) {

		LoginUser user = loginForm.get();
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("email", user.email);
		searchQuery.put("password", user.password);
		if (LoginUser.coll.find(searchQuery).size() == 1)
			return true;
		else
			return false;
	}

	public static void myUserUpdate(User user) {

		User.coll.removeById(user.id);
		User.create(user);
	}


}