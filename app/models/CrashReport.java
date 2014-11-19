package models;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;
import play.modules.mongodb.jackson.MongoDB;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	public static JacksonDBCollection<CrashReport, String> coll = MongoDB
			.getCollection("CrashReport", CrashReport.class, String.class);

	public CrashReport(String Package, String Version, String Android, String Manufacturer,
			String Model, String Date, String StackTrace) {

		this.Package = Package;
		this.Version = Version;
		this.Android = Android;
		this.Manufacturer = Manufacturer;
		this.Model = Model;
		this.Date = Date;
		this.StackTrace = StackTrace;
	

	}

	public CrashReport() {

	}

	public static void create(CrashReport report) {
		
		CrashReport.coll.save(report);
	}

}