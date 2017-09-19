package pl.lodz.sda.tools;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SingletonSessionFactory {

    private static SessionFactory instance;

    private SingletonSessionFactory() {
    }

    // lazy
    public static SessionFactory getInstance(String configurationFile) {
        if (instance == null) {
            instance = new Configuration().configure(configurationFile).
                    buildSessionFactory();
        }
        return instance;
    }
}
