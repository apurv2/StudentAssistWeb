package com.studentAssist.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Users {
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;


	private Date registeredDate;

	@OneToMany(mappedBy = "user")
	private List<GCMIds> gcmIds = new ArrayList();

	public List<GCMIds> getGcmIds() {
		return gcmIds;
	}

	public void setGcmIds(List<GCMIds> gcmIds) {
		this.gcmIds = gcmIds;
	}

	@Id
	private String userId;
	@OneToMany(mappedBy = "user")
	private List<AccommodationAdd> accommodationAdd = new ArrayList();
	@OneToMany(mappedBy = "user")
	private List<AccommodationNotification> listOfNotifications = new ArrayList();

	public List<AccommodationNotification> getListOfNotifications() {
		return this.listOfNotifications;
	}

	public void setListOfNotifications(List<AccommodationNotification> listOfNotifications) {
		this.listOfNotifications = listOfNotifications;
	}

	public Date getRegisteredDate() {
		return this.registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public List<AccommodationAdd> getAccommodationAdd() {
		return this.accommodationAdd;
	}

	public void setAccommodationAdd(List<AccommodationAdd> accommodationAdd) {
		this.accommodationAdd = accommodationAdd;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
