package pl.lodz.sda.exercise.inheritance;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.lodz.sda.dao.ContractEmployee;
import pl.lodz.sda.environment.DB;
import pl.lodz.sda.tools.HibernateSessionFactory;

import java.util.List;

public class App {

    public static void main(String[] args) {


        ContractEmployee contractEmployee = new ContractEmployee();
        contractEmployee.setName("Zdzichu");
        contractEmployee.setHourRate(10000000);
        Session session = null;
        Transaction tx;

        try {
            session = HibernateSessionFactory.createSession(DB.H2);
            Transaction transaction = session.beginTransaction();
            session.save(contractEmployee);
            transaction.commit();

            SQLQuery sqlQuery = session
                    .createSQLQuery("select * from employee");
            sqlQuery.addEntity(ContractEmployee.class);
            List list = sqlQuery.list();
            list.forEach(System.out::println);

        } catch (Exception e) {

        } finally {
            HibernateSessionFactory.closeAll(session);
        }
    }
}
