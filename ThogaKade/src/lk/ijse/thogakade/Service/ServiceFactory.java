package lk.ijse.thogakade.Service;

import lk.ijse.thogakade.Service.custom.impl.CustomerServiceimpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    private ServiceFactory(){

    }
    public static ServiceFactory getInstance(){
        return serviceFactory==null?(serviceFactory=new ServiceFactory()): serviceFactory;
    }
    public <T extends SuperService>T getService(ServiceType serviceType){
        switch (serviceType){
            case CUSTOMER:
                return (T) new CustomerServiceimpl();

            default:
                return null;
        }
    }
}
