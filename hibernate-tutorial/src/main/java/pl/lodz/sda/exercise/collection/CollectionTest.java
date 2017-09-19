package pl.lodz.sda.exercise.collection;

import org.hibernate.*;
import pl.lodz.sda.dao.Address;
import pl.lodz.sda.dao.Company;
import pl.lodz.sda.dao.Department;
import pl.lodz.sda.dao.DepartmentAdress;
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

        Session session = null;
        Transaction tx;

        try {
            session = HibernateSessionFactory.createSession(DB.H2);
            session.setCacheMode(CacheMode.IGNORE);
            tx = session.beginTransaction();

            session.save(company);
            tx.commit();

//            Criteria criteria = session.createCriteria(Company.class);
//            List list = criteria.list();
//            list.forEach(o -> System.out.println(list));

            String sql = "SELECT * FROM company c";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Company.class);
            List<Company> list = query.list();
            list.forEach(System.out::println);

//            tx = session.beginTransaction();
//            session.delete(company);
//            tx.commit();

//            System.out.println(company);
//            System.out.println(dp);
//            System.out.println(dp2);


        } catch (Exception e) {
            System.out.println("Exception occured. " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeAll(session);
        }

    }
}
