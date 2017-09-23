package pl.lodz.sda.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pl.lodz.sda.model.Company;
import pl.lodz.sda.tools.HibernateSessionFactory;
import pl.lodz.sda.tools.PropertiesLoader;

import java.io.IOException;
import java.util.List;

public class CompanyManager implements IManager<Company> {

    private Session session;

    private Transaction transaction;

    PropertiesLoader propertiesLoader = new PropertiesLoader();

    public CompanyManager() throws IOException {
        propertiesLoader.init();
    }

    private void init() throws IOException {
        session = HibernateSessionFactory.
                createSession();
    }

    private void openTransaction() {
        this.transaction = session.beginTransaction();
    }

    private void commitTransaction() {
        this.transaction.commit();
    }

    private void closeSession() {
        session.close();
    }

    @Override
    public Company save(Company entity) {
        try {
            init();
            openTransaction();
            session.save(entity);
            commitTransaction();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeSession();
        }
        return entity;
    }

    @Override
    public Company update(Company entity) {
        try {
            init();
            openTransaction();
            session.update(entity);
            commitTransaction();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeSession();
        }
        return entity;
    }

    @Override
    public Company find(Long id) {
        try {
            init();
            Criteria criteria = session.createCriteria(Company.class).
                    add(Restrictions.
                            eq("id", id));
            List<Company> list = criteria.list();
            // CollectionUtils
            return !list.isEmpty() ? list.get(0) : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeSession();
        }
    }

    @Override
    public List<Company> findAll() {
        try {
            init();
            return session.createCriteria(Company.class).list();
        } catch (IOException e) {
            System.out.println(e);
            return null;
        } finally {
            closeSession();
        }
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Company delete(long id) {
        //TODO
        return null;
    }



}
