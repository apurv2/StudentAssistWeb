package com.studentAssist.classes;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AccommodationAdd
{
  private String vacancies;
  private String gender;
  private String noOfRooms;
  private String cost;
  private String fbId;
  private String notes;
  @ManyToOne
  @JoinColumn(name="USER_ID")
  private Users user;
  @Id
  @GeneratedValue
  @Column(name="ADD_ID")
  private int addId;
  @ManyToOne
  @JoinColumn(name="APARTMENT_ID")
  private Apartments apartment;
  private Date datePosted;
  
  public AccommodationAdd() {}
  
  public AccommodationAdd(String vacancies, String gender, String noOfRooms, String cost, String fbId, String notes, Date datePosted)
  {
    this.vacancies = vacancies;
    this.gender = gender;
    this.noOfRooms = noOfRooms;
    this.cost = cost;
    this.fbId = fbId;
    this.notes = notes;
    this.datePosted = datePosted;
  }
  
  public AccommodationAdd(int addId)
  {
    this.addId = addId;
  }
  
  public Apartments getApartment()
  {
    return this.apartment;
  }
  
  public void setApartment(Apartments apartment)
  {
    this.apartment = apartment;
  }
  
  public String getVacancies()
  {
    return this.vacancies;
  }
  
  public void setVacancies(String vacancies)
  {
    this.vacancies = vacancies;
  }
  
  public String getGender()
  {
    return this.gender;
  }
  
  public void setGender(String gender)
  {
    this.gender = gender;
  }
  
  public String getNoOfRooms()
  {
    return this.noOfRooms;
  }
  
  public void setNoOfRooms(String noOfRooms)
  {
    this.noOfRooms = noOfRooms;
  }
  
  public String getCost()
  {
    return this.cost;
  }
  
  public void setCost(String cost)
  {
    this.cost = cost;
  }
  
  public String getFbId()
  {
    return this.fbId;
  }
  
  public void setFbId(String fbId)
  {
    this.fbId = fbId;
  }
  
  public String getNotes()
  {
    return this.notes;
  }
  
  public void setNotes(String notes)
  {
    this.notes = notes;
  }
  
  public Users getUser()
  {
    return this.user;
  }
  
  public void setUser(Users user)
  {
    this.user = user;
  }
  
  public int getAddId()
  {
    return this.addId;
  }
  
  public void setAddId(int addId)
  {
    this.addId = addId;
  }
  
  public Date getDatePosted()
  {
    return this.datePosted;
  }
  
  public void setDatePosted(Date datePosted)
  {
    this.datePosted = datePosted;
  }
}
