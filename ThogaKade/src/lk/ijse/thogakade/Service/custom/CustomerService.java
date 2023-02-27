package lk.ijse.thogakade.Service.custom;

import lk.ijse.thogakade.DTO.CustomerDTO;
import lk.ijse.thogakade.Service.SuperService;
import lk.ijse.thogakade.Service.exception.DuplicateException;
import lk.ijse.thogakade.Service.exception.NotFoundException;

import java.sql.SQLException;

public interface CustomerService extends SuperService {
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) throws DuplicateException, SQLException, ClassNotFoundException;
   public boolean DeleteCustomer(String CustomerID)throws DuplicateException, SQLException, ClassNotFoundException;
   public boolean updateCustomer(CustomerDTO customerDTO)throws DuplicateException, SQLException, ClassNotFoundException;

    public CustomerDTO findByCustomerId(String CustomerID) throws NotFoundException, SQLException, ClassNotFoundException;
}
