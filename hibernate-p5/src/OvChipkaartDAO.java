import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.ArrayList;

public class OvChipkaartDAO {
    private static SessionFactory factory;
    private Session currentSession;
    private Transaction currentTransaction;

    public OvChipkaartDAO() {
        try {
            factory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private Session openSession() {
        this.currentSession = this.openSession();
        return currentSession;
    }

    private Session openSessionWithTransaction() {
        this.currentSession = this.openSession();
        this.currentTransaction = currentSession.beginTransaction();

        return currentSession;
    }

    public ArrayList<Reiziger> findAll() {
        openSession();
        ArrayList<Reiziger> reizigers = (ArrayList<Reiziger>) openSession().createQuery("from Reiziger").list();
        currentSession.close();

        return reizigers;
    }

    public void createCard(OvChipkaart card) {
        try {
            openSessionWithTransaction();
            openSession().save(card);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            currentTransaction.commit();
            currentSession.close();
        }
    }

    public void updateCard(OvChipkaart card) {
        try {
            openSessionWithTransaction();
            openSession().update(card);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            currentTransaction.commit();
            currentSession.close();
        }
    }

    public void deleteCard(OvChipkaart card) {
        try {
            openSessionWithTransaction();
            openSession().delete(card);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            currentTransaction.commit();
            currentSession.close();
        }
    }

    public void getCard(int id) {
        try {
            openSessionWithTransaction();
            openSession().get(OvChipkaart.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            currentTransaction.commit();
            currentSession.close();
        }
    }
}