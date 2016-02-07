package com.studentAssist.classes;

import javax.persistence.Entity;

@Entity
public class SimpleNotifications
  extends AccommodationNotification
{
  String leftSpinner;
  String rightSpinner;
  
  public SimpleNotifications() {}
  
  public SimpleNotifications(String leftSpinner, String rightSpinner)
  {
    this.leftSpinner = leftSpinner;
    this.rightSpinner = rightSpinner;
  }
  
  public String getLeftSpinner()
  {
    return this.leftSpinner;
  }
  
  public void setLeftSpinner(String leftSpinner)
  {
    this.leftSpinner = leftSpinner;
  }
  
  public String getRightSpinner()
  {
    return this.rightSpinner;
  }
  
  public void setRightSpinner(String rightSpinner)
  {
    this.rightSpinner = rightSpinner;
  }
}
