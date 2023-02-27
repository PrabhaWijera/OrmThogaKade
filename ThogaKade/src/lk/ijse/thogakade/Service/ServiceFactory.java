package lk.ijse.thogakade.Service;

import lk.ijse.thogakade.Service.custom.impl.CustomerServiceimpl;
import lk.ijse.thogakade.Service.custom.impl.ItemServiceimpl;

import java.sql.SQLException;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    private ServiceFactory(){

    }
    public static ServiceFactory getInstance(){
        return serviceFactory==null?(serviceFactory=new ServiceFactory()): serviceFactory;
    }
    public <T extends SuperService>T getService(ServiceType serviceType) throws SQLException, ClassNotFoundException {
        switch (serviceType){
            case CUSTOMER:
                return (T) new CustomerServiceimpl();

            case ITEM:
                return (T) new ItemServiceimpl();

            default:
                return null;
        }
    }
}
