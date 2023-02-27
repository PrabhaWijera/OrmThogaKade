package lk.ijse.thogakade.DAO;

import lk.ijse.thogakade.DAO.custom.impl.CustomerModel;

import java.sql.Connection;

public class DaoFactory {

    private static DaoFactory daoFactory;
    private DaoFactory(){}
    public static DaoFactory getInstance(){
        return daoFactory==null?(daoFactory=new DaoFactory()): daoFactory;
    }
    public <T extends SuperDAO>T getDAO(Connection connection,DaoType daoType){
        switch (daoType){
            case CUSTOMER:
                return (T) new CustomerModel(connection);

            default:
                return null;
        }
    }
}
