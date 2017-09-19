package pl.lodz.sda.exercise.basic;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.lodz.sda.environment.DB;
import pl.lodz.sda.tools.HibernateSessionFactory;

public class AppMysql {

    public static void main(String[] args) {
        System.out.println("create session");
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateSessionFactory.createSession(DB.MYSQL);
//            tx = session.beginTransaction();
//            tx.begin();
//            tx.commit();
        } catch (Exception e) {

        } finally {
            HibernateSessionFactory.closeAll(session);
        }

    }
}
