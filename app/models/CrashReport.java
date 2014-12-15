package models;

import java.util.List;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;
import play.modules.mongodb.jackson.MongoDB;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.BasicDBObject;

public class CrashReport {

	@JsonIgnore
	@Id
	@ObjectId
	public String id;

	public String Package;
	public String Version;
	public String Android;
	public String Manufacturer;
	public String Model;
	public String Date;
	public String StackTrace;
	
	@JsonIgnore
	public boolean hide;

	public static JacksonDBCollection<CrashReport, String> coll = MongoDB
			.getCollection("CrashReport", CrashReport.class, String.class);

	public CrashReport(String Package, String Version, String Android,
			String Manufacturer, String Model, String Date, String StackTrace) {

		this.Package = Package;
		this.Version = Version;
		this.Android = Android;
		this.Manufacturer = Manufacturer;
		this.Model = Model;
		this.Date = Date;
		this.StackTrace = StackTrace;
		this.hide = false;

	}

	public CrashReport() {

	}

	public static void create(CrashReport report) {

		CrashReport.coll.save(report);
	}

	public static List<CrashReport> displayAll() {
		
		BasicDBObject query = new BasicDBObject("hide", false);
		return CrashReport.coll.find(query).toArray();
	}

}