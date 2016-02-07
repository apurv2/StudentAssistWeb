package com.studentAssist.rules;

import com.studentAssist.classes.Airport;
import com.studentAssist.dao.StudentAssistDAO;
import com.studentAssist.util.HibernateSession;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AirportBO
{
  SessionFactory sessionFactory;
  
  public AirportBO()
  {
    this.sessionFactory = HibernateSession.getSessionFactory();
  }
  
  public List<Airport> getAirportServices()
  {
    Session session = this.sessionFactory.openSession();
    StudentAssistDAO studentAssist = new StudentAssistDAO();
    
    return studentAssist.getAirportServices(session);
  }
}
