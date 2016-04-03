package com.studentAssist.classes;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GCMIds {

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private Users user;

	String gcmId;
	@Id
	String deviceId;
	private Date createDate;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getGcmId() {
		return gcmId;
	}

	public void setGcmId(String gcmId) {
		this.gcmId = gcmId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public GCMIds(String gcmId, String deviceId, Date createDate) {
		this.gcmId = gcmId;
		this.deviceId = deviceId;
		this.createDate = createDate;
	}

}
