/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  GCM.PushNotification
 *  com.studentAssist.classes.AccommodationAdd
 *  com.studentAssist.classes.AccommodationNotification
 *  com.studentAssist.classes.Airport
 *  com.studentAssist.classes.Apartments
 *  com.studentAssist.classes.Users
 *  com.studentAssist.response.RAccommodationAdd
 *  org.hibernate.Criteria
 *  org.hibernate.FetchMode
 *  org.hibernate.Query
 *  org.hibernate.Session
 *  org.hibernate.Transaction
 *  org.hibernate.criterion.Criterion
 *  org.hibernate.criterion.Example
 *  org.hibernate.criterion.LogicalExpression
 *  org.hibernate.criterion.Restrictions
 *  org.hibernate.criterion.SimpleExpression
 */
package com.studentAssist.dao;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.studentAssist.GCM.PushNotification;
import com.studentAssist.classes.AccommodationAdd;
import com.studentAssist.classes.AccommodationNotification;
import com.studentAssist.classes.Airport;
import com.studentAssist.classes.Apartments;
import com.studentAssist.classes.GCMIds;
import com.studentAssist.classes.Users;
import com.studentAssist.response.RAccommodationAdd;

public class StudentAssistDAO {
	public String createUser(Users user, Session session, GCMIds id) {
		try {
			try {
				session.beginTransaction();
				session.saveOrUpdate((Object) user);
				
				// adding gcm Ids to User and vice versa
				this.addGcmIdToUser(user, id);
				session.saveOrUpdate((Object)id);
				
				
				session.getTransaction().commit();
			} catch (Exception e) {
				StringWriter errors = new StringWriter();
				e.printStackTrace();
				e.printStackTrace(new PrintWriter(errors));
				String string = errors.toString();
				if (session != null) {
					session.close();
				}
				return string;
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return "success";
	}

	public String createAccommodationAddFromFacebook(Users user, AccommodationAdd advertisement, String apartmentName,
			Session session) {

		try {
			try {
				session.beginTransaction();
				session.saveOrUpdate((Object) user);

				Apartments examplApartment = new Apartments();

				// fetch apartement type using apartmentName and update it in
				// Apartments class
				examplApartment.setApartmentName(apartmentName);
				Example example = Example.create((Object) examplApartment);
				Criteria criteria = session.createCriteria((Class) Apartments.class).add((Criterion) example);
				List apartments = criteria.list();

				// add the user and accommodation to each other
				Apartments apartment = (Apartments) apartments.get(0);
				this.addAccommodationAddToApartment(apartment, advertisement);
				this.addAccommodationToUser(user, advertisement);

				session.save((Object) advertisement);
				session.getTransaction().commit();
				sendNotification(session, apartment, advertisement, user);

			} catch (Exception e) {
				StringWriter errors = new StringWriter();
				e.printStackTrace();
				e.printStackTrace(new PrintWriter(errors));
				String string = errors.toString();
				if (session != null) {
					session.close();
				}
				return string;
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return "success";

	}

	public String createAccommodationAdd(String userId, AccommodationAdd advertisement, String apartmentName,
			Session session) {
		try {
			try {
				session.beginTransaction();
				Users user = (Users) session.get((Class) Users.class, (Serializable) ((Object) userId));
				Apartments examplApartment = new Apartments();

				examplApartment.setApartmentName(apartmentName);
				Example example = Example.create((Object) examplApartment);
				Criteria criteria = session.createCriteria((Class) Apartments.class).add((Criterion) example);
				List apartments = criteria.list();

				Apartments apartment = (Apartments) apartments.get(0);
				this.addAccommodationAddToApartment(apartment, advertisement);
				this.addAccommodationToUser(user, advertisement);

				session.save((Object) advertisement);
				session.getTransaction().commit();
				this.sendNotification(session, apartment, advertisement, user);
			} catch (Exception e) {
				e.printStackTrace();
				if (session != null) {
					session.close();
				}
				return "Failure";
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return "success";
	}

	public String insertNotifications(String userId, AccommodationNotification notification, Session session) {
		try {
			try {
				session.beginTransaction();
				notification.setCreateDate(new Date());
				Users user = (Users) session.get((Class) Users.class, (Serializable) ((Object) userId));
				this.addNotificationToUser(user, notification);
				session.save((Object) notification);
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				if (session != null) {
					session.close();
				}
				return "Failure";
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return "success";
	}

	public String deleteAccommodationAdd(int addId, Session session) {
		try {
			try {
				session.beginTransaction();
				AccommodationAdd add = (AccommodationAdd) session.get((Class) AccommodationAdd.class,
						(Serializable) Integer.valueOf(addId));
				session.delete((Object) add);
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				if (session != null) {
					session.close();
				}
				return "Failure";
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return "success";
	}

	public String deleteNotificationSetting(int notificationId, Session session) {
		try {
			try {
				session.beginTransaction();
				AccommodationNotification notification = (AccommodationNotification) session
						.get((Class) AccommodationNotification.class, (Serializable) Integer.valueOf(notificationId));
				session.delete((Object) notification);
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				if (session != null) {
					session.close();
				}
				return "Failure";
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return "success";
	}

	public List<AccommodationAdd> getUserPosts(String userId, Session session) {
		List userAdds = null;
		try {
			Users user = (Users) session.get((Class) Users.class, (Serializable) ((Object) userId));
			userAdds = user.getAccommodationAdd();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userAdds;
	}

	public List<AccommodationNotification> getNotificationSettings(String userId, Session session) {
		List notifications = null;
		try {
			Users user = (Users) session.get((Class) Users.class, (Serializable) ((Object) userId));
			notifications = user.getListOfNotifications();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notifications;
	}

	public List<Apartments> getAllApartmentNames(Session session) {
		List apartments;
		apartments = null;
		try {
			try {
				session.beginTransaction();
				Query query = session.createQuery("from Apartments");
				apartments = query.list();
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				if (session != null) {
					session.close();
				}
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return apartments;
	}

	public List<AccommodationAdd> getAdvancedAdvertisements(String apartmentName, String gender, Session session) {
		List adds;
		adds = null;
		try {
			try {
				session.beginTransaction();
				AccommodationAdd add = new AccommodationAdd();
				Apartments apartment = new Apartments();
				apartment.setApartmentName(apartmentName);
				this.addAccommodationAddToApartment(apartment, add);
				add.setGender(gender);
				Example example = Example.create((Object) add);
				Criteria criteria = session.createCriteria((Class) AccommodationAdd.class).add((Criterion) example)
						.setFetchMode("apartment", FetchMode.JOIN).createAlias("apartment", "a")
						.add((Criterion) Restrictions.eq((String) "a.apartmentName", (Object) apartmentName));
				adds = criteria.list();
			} catch (Exception e) {
				e.printStackTrace();
				if (session != null) {
					session.close();
				}
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return adds;
	}

	public List<AccommodationAdd> getSimpleSearchAdds(String leftSpinner, String rightSpinner, Session session) {
		List apartments;
		Apartments apt = new Apartments();
		String secondParameter = "";
		AccommodationAdd exampleAccAdd = new AccommodationAdd();
		apartments = null;
		if (leftSpinner.equals("apartmentType")) {
			apt.setApartmentType(rightSpinner);
			secondParameter = "a.apartmentType";
		} else if (leftSpinner.equals("apartmentName")) {
			apt.setApartmentName(rightSpinner);
			secondParameter = "a.apartmentName";
		} else if (leftSpinner.equals("gender")) {
			exampleAccAdd.setGender(rightSpinner);
		}
		try {
			try {
				session.beginTransaction();
				this.addAccommodationAddToApartment(apt, exampleAccAdd);
				Example example = Example.create((Object) exampleAccAdd);
				Criteria criteria = session.createCriteria((Class) AccommodationAdd.class).add((Criterion) example);
				if (!leftSpinner.equals("gender")) {
					criteria.setFetchMode("apartment", FetchMode.JOIN).createAlias("apartment", "a")
							.add((Criterion) Restrictions.eq((String) secondParameter, (Object) rightSpinner));
				}
				apartments = criteria.list();
			} catch (Exception e) {
				e.printStackTrace();
				if (session != null) {
					session.close();
				}
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return apartments;
	}

	public List<Apartments> getApartmentNames(String apartmentType, Session session) {
		List apartments;
		apartments = null;
		try {
			try {
				session.beginTransaction();
				Apartments exampleApartments = new Apartments();
				exampleApartments.setApartmentType(apartmentType);
				Example example = Example.create((Object) exampleApartments);
				Criteria criteria = session.createCriteria((Class) Apartments.class).add((Criterion) example);
				apartments = criteria.list();
			} catch (Exception e) {
				e.printStackTrace();
				if (session != null) {
					session.close();
				}
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return apartments;
	}

	public String addNewApartment(String apartmentName, String apartmentType, Session session) {
		try {
			session.beginTransaction();
			Apartments apartments = new Apartments();
			apartments.setApartmentName(apartmentName);
			apartments.setApartmentType(apartmentType);
			session.save((Object) apartments);
			session.getTransaction().commit();
		} catch (Exception e) {
			return "Failure";
		}
		return "success";
	}

	public List<AccommodationAdd> recentListChecker(Integer[] accommodationAdds, Session session) {
		List adds = new ArrayList<AccommodationAdd>();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria((Class) AccommodationAdd.class)
					.add(Restrictions.in((String) "addId", (Object[]) accommodationAdds));
			adds = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adds;
	}

	/**
	 * Fetches the list of users to send notification and sends them.
	 * 
	 * @param session
	 * @param apartment
	 * @param advertisement
	 * @param user
	 */
	public void sendNotification(Session session, Apartments apartment, AccommodationAdd advertisement, Users user) {
		try {

			session.beginTransaction();
			HashSet<AccommodationNotification> notificationSet = new HashSet<AccommodationNotification>();
			HashSet<String> userIds = new HashSet<String>();
			HashMap<String, String> apartmentTypeCodeMap = new HashMap<String, String>();

			apartmentTypeCodeMap.put("on", "On-Campus");
			apartmentTypeCodeMap.put("off", "Off-Campus");
			apartmentTypeCodeMap.put("dorms", "Dorms");
			String apartmentType = (String) apartmentTypeCodeMap.get(apartment.getApartmentType());

			String apartmentName = apartment.getApartmentName();
			String gender = advertisement.getGender();
			SimpleExpression condition1 = Restrictions.eq((String) "rightSpinner", (Object) apartmentType);
			SimpleExpression condition2 = Restrictions.eq((String) "rightSpinner", (Object) apartmentName);
			SimpleExpression condition3 = Restrictions.eq((String) "rightSpinner", (Object) gender);

			LogicalExpression condition4 = Restrictions.and(
					(Criterion) Restrictions.eq((String) "apartmentName", (Object) apartmentName),
					(Criterion) Restrictions.eq((String) "gender", (Object) gender));

			LogicalExpression jointCriteria = Restrictions.or(
					(Criterion) Restrictions.or((Criterion) condition1, (Criterion) condition2),
					(Criterion) Restrictions.or((Criterion) condition3, (Criterion) condition4));

			Criteria criteria = session.createCriteria((Class) AccommodationNotification.class);
			criteria.add((Criterion) jointCriteria);
			List<AccommodationNotification> notifications = criteria.list();

			for (AccommodationNotification notification : notifications) {
				String userId = notification.getUser().getUserId();
				if (userId.equals(user.getUserId()))
					continue;
				if (!userIds.contains(userId)) {
					notificationSet.add(notification);
				}
				userIds.add(userId);
			}

			System.out.println(notificationSet.size());
			PushNotification pushNotification = new PushNotification();
			pushNotification.processData(
					new RAccommodationAdd(advertisement.getVacancies(), advertisement.getGender(),
							advertisement.getNoOfRooms(), advertisement.getCost(), advertisement.getFbId(),
							advertisement.getNotes(), user.getUserId(), apartmentName, user.getFirstName(),
							user.getLastName(), user.getEmail(), user.getPhoneNumber(), advertisement.getAddId()),
					notificationSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Airport> getAirportServices(Session session) {
		List services;
		services = null;
		try {
			try {
				session.beginTransaction();
				Query query = session.createQuery("from Airport");
				services = query.list();
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				if (session != null) {
					session.close();
				}
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return services;
	}

	private void addGcmIdToUser(Users user, GCMIds gcmId) {

		user.getGcmIds().add(gcmId);
		gcmId.setUser(user);

	}

	private void addAccommodationToUser(Users user, AccommodationAdd add) {
		user.getAccommodationAdd().add(add);
		add.setUser(user);
	}

	private void addNotificationToUser(Users user, AccommodationNotification notification) {
		user.getListOfNotifications().add(notification);
		notification.setUser(user);
	}

	private void addAccommodationAddToApartment(Apartments apartment, AccommodationAdd add) {
		apartment.getAccommodationAdd().add(add);
		add.setApartment(apartment);
	}
}
