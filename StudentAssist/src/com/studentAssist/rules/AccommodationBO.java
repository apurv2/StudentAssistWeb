package com.studentAssist.rules;

import com.studentAssist.classes.AccommodationAdd;
import com.studentAssist.classes.AccommodationNotification;
import com.studentAssist.classes.AdvancedNotifications;
import com.studentAssist.classes.Apartments;
import com.studentAssist.classes.GCMIds;
import com.studentAssist.classes.SimpleNotifications;
import com.studentAssist.classes.Users;
import com.studentAssist.dao.StudentAssistDAO;
import com.studentAssist.response.RAccommodationAdd;
import com.studentAssist.response.RAccommodationNotification;
import com.studentAssist.response.RApartmentNames;
import com.studentAssist.util.HibernateSession;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

public class AccommodationBO {
	SessionFactory sessionFactory;

	public AccommodationBO() {
		this.sessionFactory = HibernateSession.getSessionFactory();
	}

	public String createUser(String firstName, String lastName, String phoneNumber, String email, String gcmId,
			String userId, String deviceId) {
		Users user = new Users();

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPhoneNumber(phoneNumber);
		user.setEmail(email);
		user.setUserId(userId);
		user.setRegisteredDate(new Date());

		GCMIds id;
		if (gcmId == "" || deviceId == "") {
			id = null;
		} else {
			id = new GCMIds(gcmId, deviceId, new Date());
		}

		Session session = this.sessionFactory.openSession();

		StudentAssistDAO studentAssist = new StudentAssistDAO();
		return studentAssist.createUser(user, session, id);
	}

	public String createAccommodationAddFromFacebook(String userId, String apartmentName, String noOfRooms,
			String vacancies, String cost, String gender, String fbId, String notes, String firstName,
			String lastName) {
		Session session = this.sessionFactory.openSession();

		Users user = new Users();

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPhoneNumber("Fbuser");
		user.setEmail("Fbuser");
		user.setUserId(userId);

		user.setRegisteredDate(new Date());

		AccommodationAdd advertisement = new AccommodationAdd(vacancies, gender, noOfRooms, cost, fbId, notes,
				new Date());

		StudentAssistDAO studentAssist = new StudentAssistDAO();
		return studentAssist.createAccommodationAddFromFacebook(user, advertisement, apartmentName, session);

	}

	public String createAccommodationAdd(String userId, String apartmentName, String noOfRooms, String vacancies,
			String cost, String gender, String fbId, String notes) {
		Session session = this.sessionFactory.openSession();

		AccommodationAdd advertisement = new AccommodationAdd(vacancies, gender, noOfRooms, cost, fbId, notes,
				new Date());

		StudentAssistDAO studentAssist = new StudentAssistDAO();
		return studentAssist.createAccommodationAdd(userId, advertisement, apartmentName, session);
	}

	public String insertNotifications(int notificationType, String spinner1, String spinner2, String userId,String gcmId,
			String deviceId) {

		 GCMIds id = new GCMIds(gcmId, deviceId, new Date());
		 

		AccommodationNotification notification = null;
		Session session = this.sessionFactory.openSession();
		StudentAssistDAO studentAssist = new StudentAssistDAO();

		List<AccommodationNotification> notifications = studentAssist.getNotificationSettings(userId, session);

		int count = 0;
		if ((notifications != null) && (notifications.size() > 0)) {
			for (AccommodationNotification not : notifications) {
				if ((not instanceof SimpleNotifications)) {
					if ((((SimpleNotifications) not).getLeftSpinner().equals(spinner1))
							&& (((SimpleNotifications) not).getRightSpinner().equals(spinner2))) {
						count++;
					}
				} else if ((not instanceof AdvancedNotifications)) {
					if ((((AdvancedNotifications) not).getGender().equals(spinner2))
							&& (((AdvancedNotifications) not).getApartmentName().equals(spinner1))) {
						System.out.println("came here");
						count++;
					}
				}
			}
		}
		if (count == 0) {
			if (notificationType == 0) {
				notification = new SimpleNotifications(spinner1, spinner2);
			} else {
				notification = new AdvancedNotifications(spinner1, spinner2);
			}
			return studentAssist.insertNotifications(userId, notification, session,id);
		}
		return "already Subscribed";
	}

	public String deleteAccommodationAdd(int addId) {
		Session session = this.sessionFactory.openSession();
		StudentAssistDAO studentAssist = new StudentAssistDAO();
		return studentAssist.deleteAccommodationAdd(addId, session);
	}

	public String deleteNotificationSetting(int notificationId) {
		Session session = this.sessionFactory.openSession();
		StudentAssistDAO studentAssist = new StudentAssistDAO();
		return studentAssist.deleteNotificationSetting(notificationId, session);
	}

	public List<RAccommodationAdd> getUserPosts(String userId) {
		Session session = this.sessionFactory.openSession();
		StudentAssistDAO studentAssist = new StudentAssistDAO();

		List<AccommodationAdd> userAdds = studentAssist.getUserPosts(userId, session);
		List<RAccommodationAdd> rAdds = new ArrayList();
		for (AccommodationAdd add : userAdds) {
			Users user = add.getUser();

			rAdds.add(new RAccommodationAdd(add.getVacancies(), add.getGender(), add.getNoOfRooms(), add.getCost(),
					add.getFbId(), add.getNotes(), user.getUserId(), add.getApartment().getApartmentName(),
					user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), add.getAddId()));
		}
		return rAdds;
	}

	public List<RAccommodationNotification> getNotificationSettings(String userId) {
		Session session = this.sessionFactory.openSession();
		StudentAssistDAO studentAssist = new StudentAssistDAO();

		List<RAccommodationNotification> rNotifications = new ArrayList();
		List<AccommodationNotification> notifications = studentAssist.getNotificationSettings(userId, session);

		String spinner1 = "";
		String spinner2 = "";
		int notificationType = 0;
		for (AccommodationNotification notification : notifications) {
			if ((notification instanceof SimpleNotifications)) {
				spinner1 = ((SimpleNotifications) notification).getLeftSpinner();
				spinner2 = ((SimpleNotifications) notification).getRightSpinner();
				notificationType = 0;
			} else {
				spinner1 = ((AdvancedNotifications) notification).getApartmentName();
				spinner2 = ((AdvancedNotifications) notification).getGender();
				notificationType = 1;
			}
			rNotifications.add(new RAccommodationNotification(notification.getNotificationId() + "", spinner1, spinner2,
					notificationType));
		}
		return rNotifications;
	}

	public List<RApartmentNames> getAllApartmentNames() {
		Session session = this.sessionFactory.openSession();
		StudentAssistDAO studentAssist = new StudentAssistDAO();

		List<Apartments> apartments = studentAssist.getAllApartmentNames(session);

		List<RApartmentNames> rApartments = new ArrayList();
		for (Apartments apt : apartments) {
			rApartments.add(new RApartmentNames(apt.getApartmentName()));
		}
		return rApartments;
	}

	public List<RAccommodationAdd> getAdvancedAdvertisements(String apartmentName, String gender) {
		Session session = this.sessionFactory.openSession();
		StudentAssistDAO studentAssist = new StudentAssistDAO();

		List<AccommodationAdd> advancedAdds = studentAssist.getAdvancedAdvertisements(apartmentName, gender, session);
		List<RAccommodationAdd> rAdds = new ArrayList();
		for (AccommodationAdd add : advancedAdds) {
			Users user = add.getUser();

			rAdds.add(new RAccommodationAdd(add.getVacancies(), add.getGender(), add.getNoOfRooms(), add.getCost(),
					add.getFbId(), add.getNotes(), user.getUserId(), add.getApartment().getApartmentName(),
					user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), add.getAddId()));
		}
		return rAdds;
	}

	public List<RAccommodationAdd> getSimpleSearchAdds(String leftSpinner, String rightSpinner) {
		Session session = this.sessionFactory.openSession();
		StudentAssistDAO studentAssist = new StudentAssistDAO();

		List<AccommodationAdd> advancedAdds = studentAssist.getSimpleSearchAdds(leftSpinner, rightSpinner, session);
		List<RAccommodationAdd> rAdds = new ArrayList();
		for (AccommodationAdd add : advancedAdds) {
			Users user = add.getUser();

			rAdds.add(new RAccommodationAdd(add.getVacancies(), add.getGender(), add.getNoOfRooms(), add.getCost(),
					add.getFbId(), add.getNotes(), user.getUserId(), add.getApartment().getApartmentName(),
					user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), add.getAddId()));
		}
		return rAdds;
	}

	public List<RApartmentNames> getApartmentNames(String apartmentType) {
		Session session = this.sessionFactory.openSession();
		StudentAssistDAO studentAssist = new StudentAssistDAO();

		List<Apartments> apartments = studentAssist.getApartmentNames(apartmentType, session);
		List<RApartmentNames> rApartments = new ArrayList();
		for (Apartments apt : apartments) {
			rApartments.add(new RApartmentNames(apt.getApartmentName()));
		}
		return rApartments;
	}

	public String addNewApartment(String apartmentName, String apartmentType) {
		Session session = this.sessionFactory.openSession();
		StudentAssistDAO studentAssist = new StudentAssistDAO();

		return studentAssist.addNewApartment(apartmentName, apartmentType, session);
	}

	public List<RAccommodationAdd> recentListChecker(String json) {
		Session session = this.sessionFactory.openSession();
		StudentAssistDAO studentAssist = new StudentAssistDAO();
		String addIds = "";
		try {
			JSONArray jArray = new JSONArray(json);
			if (jArray.length() > 0) {
				for (int i = jArray.length() - 1; i >= 0; i--) {
					JSONObject json_data = jArray.getJSONObject(i);
					int addId = Integer.parseInt(json_data.getString("addId"));

					addIds = addIds + addId + ",";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		addIds = addIds.substring(0, addIds.length() - 1);
		String[] strArray = addIds.split(",");
		Integer[] intArray = new Integer[strArray.length];
		for (int i = 0; i < strArray.length; i++) {
			intArray[i] = Integer.valueOf(Integer.parseInt(strArray[i]));
		}
		List<AccommodationAdd> accommodationAdds = studentAssist.recentListChecker(intArray, session);

		List<RAccommodationAdd> rAcommodationAdds = new ArrayList();
		for (AccommodationAdd add : accommodationAdds) {
			rAcommodationAdds.add(new RAccommodationAdd(add.getAddId()));
		}
		return rAcommodationAdds;
	}
}
