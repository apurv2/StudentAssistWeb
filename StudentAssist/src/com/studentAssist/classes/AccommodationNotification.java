package com.studentAssist.classes;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class AccommodationNotification
{
  @ManyToOne
  @JoinColumn(name="USER_ID")
  private Users user;
  @Id
  @GeneratedValue
  int notificationId;
  Date createDate;
  
  public Users getUser()
  {
    return this.user;
  }
  
  public Date getCreateDate()
  {
    return this.createDate;
  }
  
  public void setCreateDate(Date createDate)
  {
    this.createDate = createDate;
  }
  
  public void setUser(Users user)
  {
    this.user = user;
  }
  
  public int getNotificationId()
  {
    return this.notificationId;
  }
  
  public void setNotificationId(int notificationId)
  {
    this.notificationId = notificationId;
  }
  
  public int hashCode()
  {
    int prime = 31;
    int result = 1;
    result = 31 * result + (this.user == null ? 0 : this.user.hashCode());
    return result;
  }
}
