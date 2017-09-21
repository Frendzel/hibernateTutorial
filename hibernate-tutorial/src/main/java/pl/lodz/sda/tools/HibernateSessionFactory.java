package pl.lodz.sda.tools;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.lodz.sda.environment.DB;

import static pl.lodz.sda.environment.DB.H2;

public class HibernateSessionFactory {

    private SessionFactory sessionFactory;

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
        return SingletonSessionFactory.getInstance(configurationFileName);
    }

    public static Session createSession(DB db) {
        // czy sessionFactory otwarte
        SessionFactory sessionFactory = createSessionFactory(db);
        return sessionFactory.openSession();
    }

    public static void closeSession(Session session) {
        session.close();
    }

    public static void closeSessionFactory(SessionFactory sessionFactory) {
        try {
            Session currentSession =
                    sessionFactory.getCurrentSession();
            currentSession.close();
        } catch (HibernateException e) {
            System.out.println(e);
        } finally {
            sessionFactory.close();
        }

    }

    public static void closeAll(Session session) {
        SessionFactory sessionFactory = session.getSessionFactory();
        session.close();
        sessionFactory.close();
    }


}
