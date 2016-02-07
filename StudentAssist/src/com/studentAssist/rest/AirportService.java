package com.studentAssist.rest;

import com.studentAssist.classes.Airport;
import com.studentAssist.rules.AirportBO;
import com.studentAssist.util.HibernateSession;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Path("/airportService")
public class AirportService
{
  @GET
  @Path("/createService")
  @Produces({"application/json"})
  public void createService()
  {
    Airport airport = new Airport();
    Airport airport2 = new Airport();
    
    SessionFactory sessionFactory = HibernateSession.getSessionFactory();
    
    Session session = sessionFactory.openSession();
    
    airport.setAddedDate(new Date());
    airport.setServiceName("FSI (Fine Arts Society of India)");
    airport.setGroupLink("http://www.uta.edu/studentorgs/fsi/frontend/#new-student");
    airport.setDescription("Free Airport pickup and temporary accommodation available");
    
    airport2.setAddedDate(new Date());
    airport2.setServiceName("BIG HOWDY");
    airport2.setGroupLink("http://utabighowdy.isisites.net/airport-pickup-form/");
    airport2.setDescription("Free Airport Pickup now available");
    
    session.beginTransaction();
    
    session.save(airport);
    session.save(airport2);
    
    session.getTransaction().commit();
  }
  
  @GET
  @Path("/getAllServices")
  @Produces({"application/json"})
  public List<Airport> getAllServices()
  {
    AirportBO bo = new AirportBO();
    
    return bo.getAirportServices();
  }
}
