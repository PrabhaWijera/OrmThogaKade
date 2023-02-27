package lk.ijse.thogakade.repo;

import lk.ijse.thogakade.entity.Customer;
import lk.ijse.thogakade.util.FactoryConfigure;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerRepo {
    Session session= FactoryConfigure.getInstance().getsession();
    public  boolean saveCus(Customer customer){
        Transaction transaction=session.beginTransaction();
        try{
            Integer id=(Integer) session.save(customer);
            transaction.commit();
            System.out.println("Id"+id);
        return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }
}
}
