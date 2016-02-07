package com.studentAssist.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Apartments
{
  @Id
  @GeneratedValue
  @Column(name="ID")
  int id;
  String apartmentName;
  String apartmentType;
  Date addedDate;
  @OneToMany(mappedBy="apartment")
  private List<AccommodationAdd> accommodationAdd = new ArrayList();
  
  public List<AccommodationAdd> getAccommodationAdd()
  {
    return this.accommodationAdd;
  }
  
  public void setAccommodationAdd(List<AccommodationAdd> accommodationAdd)
  {
    this.accommodationAdd = accommodationAdd;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getApartmentName()
  {
    return this.apartmentName;
  }
  
  public void setApartmentName(String apartmentName)
  {
    this.apartmentName = apartmentName;
  }
  
  public String getApartmentType()
  {
    return this.apartmentType;
  }
  
  public void setApartmentType(String apartmentType)
  {
    this.apartmentType = apartmentType;
  }
  
  public Date getAddedDate()
  {
    return this.addedDate;
  }
  
  public void setAddedDate(Date addedDate)
  {
    this.addedDate = addedDate;
  }
}
