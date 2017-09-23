package pl.lodz.sda.exercise.collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pl.lodz.sda.model.Address;
import pl.lodz.sda.model.Company;
import pl.lodz.sda.model.Department;
import pl.lodz.sda.model.DepartmentAdress;
import pl.lodz.sda.environment.DB;
import pl.lodz.sda.tools.HibernateSessionFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionTest {

    private static String NEW_LINE = "------------------------------------------------------------";

    public static void main(String[] args) {

        DepartmentAdress departmentAdress = new DepartmentAdress("street", "city", "country");

        Address address = new Address("Piotrkowska",
                101,
                "93-333",
                "Poland",
                null);

        Company company = new Company();
        company.setName("test");
        company.setAddress(address);

        Department dp = new Department(company);
        dp.setDepartmentAdress(departmentAdress);
        Department dp2 = new Department(company);
        dp2.setDepartmentAdress(departmentAdress);
        Set<Department> dps = new HashSet<>();
        dps.add(dp);
        dps.add(dp2);
        company.setDepartment(dps);
        address.setCompany(company);

        Transaction tx;
        Session session = null;
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = HibernateSessionFactory.createSessionFactory(DB.H2);
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(company);
            System.out.println(company.getDepartment());
            tx.commit();

            session = sessionFactory.openSession();
            session.beginTransaction();
            Company company1 = session.get(Company.class, 1l);
            company1.getDepartment();
            session.getTransaction().commit();

            System.out.println(company1.getDepartment());

            Criteria criteria = session.
                    createCriteria(Company.class)
                    .add(Restrictions.
                            eq("name", "test"));
            List<Company> list = criteria.list();
            System.out.println("TUTAJ JESTEM!"+list.get(0));
            session.close();
            System.out.println("Moje sessionFactory: "+
                    session.getSessionFactory());
            SessionFactory sessionFactory1 = session.getSessionFactory();
            System.out.println("moja nwoa sesja"+sessionFactory1.openSession());

//
//            String sql = "SELECT * FROM company c";
//            SQLQuery query = session.createSQLQuery(sql);
//            query.addEntity(Company.class);
//            List<Company> list = query.list();
//            list.forEach(System.out::println);
//            System.out.println(Hibernate.isInitialized(Address.class));


//            tx = session.beginTransaction();
//            session.delete(company);
//            tx.commit();

//            System.out.println(company);
//            System.out.println(dp);
//            System.out.println(dp2);


        } catch (Exception e) {
            System.out.println("Exception occured. " + e.getMessage());
            HibernateSessionFactory.closeSessionFactory();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSessionFactory();
        }

    }
}
