package com.studentAssist.rest;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.studentAssist.GCM.GCMNotificationData;
import com.studentAssist.classes.AccommodationNotification;
import com.studentAssist.classes.ApartmentDetails;
import com.studentAssist.response.RAccommodationAdd;
import com.studentAssist.response.RAccommodationNotification;
import com.studentAssist.response.RApartmentNames;
import com.studentAssist.rules.AccommodationBO;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Path("/accommodation")
public class Accommodation {
	@GET
	@Path("/createUser")
	public String createUser(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName,
			@QueryParam("phoneNumber") String phoneNumber, @QueryParam("emailId") String emailId,
			@QueryParam("gcmId") String gcmId, @QueryParam("userId") String userId,
			@Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");

		AccommodationBO bo = new AccommodationBO();
		return bo.createUser(firstName, lastName, phoneNumber, emailId, gcmId, userId,"12345");
	}

	@GET
	@Path("/createAccommodationAddFromFacebook")
	public String createAccommodationAddFromFacebook(@QueryParam("apartmentName") String apartmentName,
			@QueryParam("noOfRooms") String noOfRooms, @QueryParam("vacancies") String vacancies,
			@QueryParam("cost") String cost, @QueryParam("gender") String gender, @QueryParam("fbId") String fbId,
			@QueryParam("notes") String notes, @QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName, @Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");

		AccommodationBO bo = new AccommodationBO();
		return bo.createAccommodationAddFromFacebook(fbId, apartmentName, noOfRooms, vacancies, cost, gender, fbId,
				notes, firstName, lastName);
	}

	@GET
	@Path("/createAccommodationAdd")
	public String createAccommodationAdd(@QueryParam("apartmentName") String apartmentName,
			@QueryParam("noOfRooms") String noOfRooms, @QueryParam("vacancies") String vacancies,
			@QueryParam("cost") String cost, @QueryParam("gender") String gender, @QueryParam("fbId") String fbId,
			@QueryParam("notes") String notes, @QueryParam("userId") String userId,
			@Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");

		AccommodationBO bo = new AccommodationBO();
		return bo.createAccommodationAdd(userId, apartmentName, noOfRooms, vacancies, cost, gender, fbId, notes);
	}

	@GET
	@Path("/insertNotifications")
	public String insertNotifications(@QueryParam("notificationType") String notificationType,
			@QueryParam("spinner1") String spinner1, @QueryParam("spinner2") String spinner2,
			@QueryParam("userId") String userId, @Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");

		AccommodationBO bo = new AccommodationBO();
		return bo.insertNotifications(Integer.parseInt(notificationType), spinner1, spinner2, userId);
	}

	@GET
	@Path("/deleteAccommodationAdd")
	public String deleteAccommodationAdd(@QueryParam("addId") String addId, @Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");

		AccommodationBO bo = new AccommodationBO();
		return bo.deleteAccommodationAdd(Integer.parseInt(addId));
	}

	@GET
	@Path("/deleteNotificationSetting")
	public String deleteNotificationSetting(@QueryParam("notificationId") String notificationId,
			@Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");

		AccommodationBO bo = new AccommodationBO();
		return bo.deleteNotificationSetting(Integer.parseInt(notificationId));
	}

	@GET
	@Path("/getUserPosts")
	@Produces({ "application/json" })
	public List<RAccommodationAdd> getUserPosts(@QueryParam("userId") String userId,
			@Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");

		AccommodationBO bo = new AccommodationBO();

		return bo.getUserPosts(userId);
	}

	@GET
	@Path("/getNotificationSettings")
	@Produces({ "application/json" })
	public List<RAccommodationNotification> getNotificationSettings(@QueryParam("userId") String userId,
			@Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");

		AccommodationBO bo = new AccommodationBO();

		return bo.getNotificationSettings(userId);
	}

	@GET
	@Path("/getAllApartmentNames")
	@Produces({ "application/json" })
	public List<RApartmentNames> getAllApartmentNames(@Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");

		AccommodationBO bo = new AccommodationBO();

		return bo.getAllApartmentNames();
	}

	@GET
	@Produces({ "application/json" })
	@Path("/getAdvancedAdvertisements")
	public List<RAccommodationAdd> getAdvancedAdvertisements(@QueryParam("apartmentName") String apartmentName,
			@QueryParam("gender") String gender, @Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");

		AccommodationBO bo = new AccommodationBO();
		return bo.getAdvancedAdvertisements(apartmentName, gender);
	}

	@GET
	@Produces({ "application/json" })
	@Path("/getSimpleSearchAdds")
	public List<RAccommodationAdd> getSimpleSearchAdds(@QueryParam("leftSpinner") String leftSpinner,
			@QueryParam("rightSpinner") String rightSpinner, @Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");

		AccommodationBO bo = new AccommodationBO();
		return bo.getSimpleSearchAdds(leftSpinner, rightSpinner);
	}

	@GET
	@Path("/getApartmentNames")
	@Produces({ "application/json" })
	public List<RApartmentNames> getApartmentNames(@QueryParam("apartmentType") String apartmentType,
			@Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");

		AccommodationBO bo = new AccommodationBO();

		return bo.getApartmentNames(apartmentType);
	}

	@GET
	@Path("/addNewApartment")
	public String addNewApartment(@QueryParam("apartmentName") String apartmentName,
			@QueryParam("apartmentType") String apartmentType, @Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");

		AccommodationBO bo = new AccommodationBO();
		return bo.addNewApartment(apartmentName, apartmentType);
	}

	@GET
	@Path("/recentListChecker")
	@Produces({ "application/json" })
	public List<RAccommodationAdd> recentListChecker(@QueryParam("addId") String addId,
			@Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");

		AccommodationBO bo = new AccommodationBO();
		return bo.recentListChecker(addId);
	}

	@GET
	@Path("/test")
	public String test() {
		
		AccommodationBO bo = new AccommodationBO();
		
		//ApartmentDetails details = new ApartmentDetails();
		//details.addAptsToNewDb();
		
		//return "success";

		return bo.createUser("Apurv", "Kamalapuri", "", "", "6789", "1118294135","56789");
			//return	createBuilder();


	}

	private String sendMessage(Message.Builder builder, List<String> gcmIds) throws IOException {
		String API_KEY = "AIzaSyBN22-yHrj9iZSISNIiG0VdJaJghLtmjCg";
		Sender sender = new Sender(API_KEY);

		Message message = builder.build();
		
			
		
		MulticastResult result = sender.send(message, gcmIds, 1);

		System.out.println("result = " + result);
			return result+"";
	}
	

	private String createBuilder() {
		Message.Builder builder = new Message.Builder();

		String collpaseKey = "gcm_message";
		builder.collapseKey(collpaseKey);
		builder.timeToLive(30);
		builder.delayWhileIdle(true);

		builder.addData("hello", "world");

		List<String> gcmIds = new ArrayList();
		gcmIds.add(
				"cHeqJP4rZGI:APA91bET1vrf0wjNQsNkfyOM7ePJNLwhFeG92t6m_x4pf5fHgYtEVEbfc5b8ZZ5dv1KLykhaYD74-2zp_38c0aXgXacbCap5bKKGi3w8wQzY4CojFueF-ZJZ6It1QUlw5uvNKq0UyQnZ");

		try {
			return sendMessage(builder, gcmIds);
		} catch (Exception e) {
			e.printStackTrace();
			return "exception in catch";
		}
	}

}








