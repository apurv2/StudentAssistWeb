package com.studentAssist.rest;

import com.studentAssist.response.RAccommodationAdd;
import com.studentAssist.response.RAccommodationNotification;
import com.studentAssist.response.RApartmentNames;
import com.studentAssist.rules.AccommodationBO;
import java.io.PrintStream;
import java.util.List;
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
		return bo.createUser(firstName, lastName, phoneNumber, emailId, gcmId, userId);
	}

	@GET
	@Path("/createAccommodationAddFromFacebook")
	public String createAccommodationAddFromFacebook(@QueryParam("apartmentName") String apartmentName,
			@QueryParam("noOfRooms") String noOfRooms, @QueryParam("vacancies") String vacancies,
			@QueryParam("cost") String cost, @QueryParam("gender") String gender, @QueryParam("fbId") String fbId,
			@QueryParam("notes") String notes, @Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");

		AccommodationBO bo = new AccommodationBO();
		return bo.createAccommodationAddFromFacebook(fbId, apartmentName, noOfRooms, vacancies, cost, gender, fbId,
				notes);
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

		return "hwllo world";

	}
}
