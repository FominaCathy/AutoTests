package FominaKat.ProjectSQL;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;


abstract class AbstractHQLTest {

    private static SessionFactory sessionFactory;

    @BeforeAll
    static void initSession() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();

        } catch (Throwable e) {
            System.err.println("Error initial SessionFactory. Error: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    @AfterAll
    static void closeSession() {
        getSession().close();
    }
}
