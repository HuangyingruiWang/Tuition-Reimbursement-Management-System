package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    //Abstract away creating our SessionFactory and the process of creating Sessions
    //from that SessionFactory

    private static SessionFactory sf = new Configuration().setProperty("hibernate.connection.url", System.getenv("DB_URL")).setProperty("hibernate.connection.username", System.getenv("DB_USERNAME")).setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD")).configure().buildSessionFactory();

    public static Session getSession() {
        return sf.openSession();
    }

}