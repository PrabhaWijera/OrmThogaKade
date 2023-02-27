package lk.ijse.thogakade.util;

import lk.ijse.thogakade.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfigure {
    public static FactoryConfigure factoryConfigure;
    private SessionFactory sessionFactory;
    private FactoryConfigure(){
        Configuration configuration=new Configuration().configure().addAnnotatedClass(Customer.class);
        sessionFactory=configuration.buildSessionFactory();
    }
    public static FactoryConfigure getInstance(){
        return factoryConfigure==null ? factoryConfigure=new FactoryConfigure() : factoryConfigure;
    }
    public Session getsession(){
        return sessionFactory.openSession();
    }
}
