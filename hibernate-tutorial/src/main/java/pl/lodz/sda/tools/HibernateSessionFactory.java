package pl.lodz.sda.tools;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.lodz.sda.environment.DB;

import static pl.lodz.sda.environment.DB.H2;

public class HibernateSessionFactory {

    private static SessionFactory sessionFactory;

    public HibernateSessionFactory(DB db) {
        sessionFactory = createSessionFactory(db);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static String getConfigurationFileName(DB db) {
        if (db == H2) {
            return "hibernate.cfg-h2.xml";
        } else {
            return "hibernate.cfg-mysql.xml";
        }
    }

    public static SessionFactory createSessionFactory(DB db) {

        // Tworzymy obiekt SessionFactory na podstawie którego będziemy tworzyć osobne sesje
        // Pamiętamy, że sessionFactory powinno być tworzone jedno per połączenie do DB
        // Ustawiamy konfigurację dla naszego sessionFactory
        String configurationFileName = getConfigurationFileName(db);
        sessionFactory = SingletonSessionFactory.getInstance(configurationFileName);
        return sessionFactory;
    }

    public static Session createSession() {
        // czy sessionFactory otwarte
        return sessionFactory.openSession();
    }
    public static void closeSessionFactory() {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.close();
        } catch (HibernateException e) {
            System.out.println(e);
        } finally {
            sessionFactory.close();
            System.out.println("Session factory has been closed!");
        }

    }

    public static void closeAll(Session session) {
        session.close();
        sessionFactory.close();
    }


}
