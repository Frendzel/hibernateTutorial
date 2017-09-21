package pl.lodz.sda.exercise.basic;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.lodz.sda.model.Task;
import pl.lodz.sda.environment.DB;
import pl.lodz.sda.tools.HibernateSessionFactory;

/**
 *
 */
public class App {
    public static void main(String[] args) {

        // Tworzymy sesję
        System.out.println("create session");
        Session session = HibernateSessionFactory.createSession(DB.H2);
        try {

            // Rozpoczynamy transakcję w sesji
            System.out.println("Begin transaction");
            Transaction tx = session.beginTransaction();
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000000; i++) {
                Task task = new Task("1" + i, "2");
                // zapisujemy w sesji nasz obiekt
                System.out.println("save: " + i + " " + task);
                session.save(task);
                if( i % 50 == 0 ) {
                    session.flush();
                    session.clear();
                }
            }
            // Commitujemy zmiany
            System.out.println("commit");
            //bez batcha 19788 // 19852
            System.out.println(System.currentTimeMillis() - startTime);
            tx.commit();
            //bez batcha 43668 // 46279 // 33s //32287 // 33238
            System.out.println(System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            //log
        } finally {
            // Zamykamy sessionFactory
            HibernateSessionFactory.closeAll(session);
        }

    }
}
