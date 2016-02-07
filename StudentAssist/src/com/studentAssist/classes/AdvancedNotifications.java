package com.studentAssist.classes;

import javax.persistence.Entity;

@Entity
public class AdvancedNotifications
  extends AccommodationNotification
{
  String apartmentName;
  String gender;
  
  public AdvancedNotifications() {}
  
  public AdvancedNotifications(String apartmentName, String gender)
  {
    this.apartmentName = apartmentName;
    this.gender = gender;
  }
  
  public String getApartmentName()
  {
    return this.apartmentName;
  }
  
  public void setApartmentName(String apartmentName)
  {
    this.apartmentName = apartmentName;
  }
  
  public String getGender()
  {
    return this.gender;
  }
  
  public void setGender(String gender)
  {
    this.gender = gender;
  }
}
