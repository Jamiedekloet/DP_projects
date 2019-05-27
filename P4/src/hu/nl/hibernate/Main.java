package hu.nl.hibernate;

import java.sql.SQLException;
import java.text.ParseException;


import org.hibernate.Session;
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Main {
  private static SessionFactory factory;
  public static void main(String[] args) throws SQLException, ParseException {
      StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

      Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

      SessionFactory factory = meta.getSessionFactoryBuilder().build();
      Session session = factory.openSession();
      Transaction t = session.beginTransaction();

      Employee e1=new Employee();
      e1.setId(101);
      e1.setFirstName("Gaurav");
      e1.setLastName("Chawla");

      session.save(e1);
      t.commit();
      System.out.println("successfully saved");
      factory.close();
      session.close();
  }
}
