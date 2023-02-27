package lk.ijse.thogakade.Service.custom;

import lk.ijse.thogakade.DTO.CustomerDTO;
import lk.ijse.thogakade.Service.exception.DuplicateException;

import java.sql.SQLException;

public interface CustomerService {
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) throws DuplicateException, SQLException, ClassNotFoundException;

}
