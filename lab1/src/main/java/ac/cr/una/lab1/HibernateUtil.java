/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.una.lab1;

import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Sergio
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory() throws HibernateException{
        return new Configuration().configure().buildSessionFactory();
    }
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
