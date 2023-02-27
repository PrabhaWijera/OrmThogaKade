package lk.ijse.thogakade.Service.custom.impl;

import lk.ijse.thogakade.DAO.DaoFactory;
import lk.ijse.thogakade.DAO.DaoType;
import lk.ijse.thogakade.DAO.custom.CustomerDAO;
import lk.ijse.thogakade.DAO.exception.ConstraintViolationException;
import lk.ijse.thogakade.DTO.CustomerDTO;
import lk.ijse.thogakade.Service.custom.CustomerService;
import lk.ijse.thogakade.Service.exception.DuplicateException;
import lk.ijse.thogakade.Service.exception.InUseException;
import lk.ijse.thogakade.Service.exception.NotFoundException;
import lk.ijse.thogakade.Service.util.Convertor;
import lk.ijse.thogakade.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerServiceimpl implements CustomerService {
    private   Connection connection;
    private Convertor convertor;
    private CustomerDAO customerDAO;


    public CustomerServiceimpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        customerDAO= DaoFactory.getInstance().getDAO(connection, DaoType.CUSTOMER);
        convertor=new Convertor();
    }
    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) throws DuplicateException, SQLException, ClassNotFoundException {
        if (customerDAO.existByPk(customerDTO.getId()))throw new DuplicateException("CustomerDTO Already in!!");
        System.out.println("hello");
        customerDAO.save(convertor.toCustomer(customerDTO));
        return customerDTO;
    }

    @Override
    public boolean DeleteCustomer(String CustomerID) {
        if (customerDAO.existByPk(CustomerID))throw new NotFoundException("CustomerDTO not found!!!");
        try{
            customerDAO.deleteByPk(CustomerID);
        }catch (ConstraintViolationException | ClassNotFoundException | SQLException e){
            throw new InUseException("CustomerDTO Already in here!!");
        }
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws DuplicateException, SQLException, ClassNotFoundException {
        if (customerDAO.existByPk(customerDTO.getId())) throw new NotFoundException("CustomerDTO not Found!!!");
        return customerDAO.update(convertor.toCustomer(customerDTO));

    }

    @Override
    public CustomerDTO findByCustomerId(String CustomerID) throws NotFoundException, SQLException, ClassNotFoundException {
        return null;
    }


}
