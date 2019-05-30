import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ReizigerDAO {
    private static SessionFactory factory;
    private Session currentSession;
    private Transaction currentTransaction;

    public ReizigerDAO() {
        try {
            factory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private Session openSession() {
        this.currentSession = factory.openSession();
        return currentSession;
    }

    private Session openSessionWithTransaction() {
        this.currentSession = factory.openSession();
        this.currentTransaction = currentSession.beginTransaction();

        return currentSession;
    }

    public ArrayList<Reiziger> findAll() {
        openSession();
        ArrayList<Reiziger> reizigers = (ArrayList<Reiziger>) openSession().createQuery("from Reiziger").list();
        currentSession.close();

        return reizigers;
    }

    public void createReiziger(Reiziger reiziger) {
        try {
            openSessionWithTransaction();
            openSession().save(reiziger);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            currentTransaction.commit();
            currentSession.close();
        }
    }

    public void updateReiziger(Reiziger reiziger) {
        try {
            openSessionWithTransaction();
            openSession().update(reiziger);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            currentTransaction.commit();
            currentSession.close();
        }
    }

    public void deleteReiziger(Reiziger reiziger) {
        try {
            openSessionWithTransaction();
            openSession().delete(reiziger);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            currentTransaction.commit();
            currentSession.close();
        }
    }

    public void getReiziger(int id) {
        try {
            openSessionWithTransaction();
            openSession().get(Reiziger.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            currentTransaction.commit();
            currentSession.close();
        }
    }
}
