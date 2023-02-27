package lk.ijse.thogakade.DAO.custom;

import lk.ijse.thogakade.DAO.CrudDAO;
import lk.ijse.thogakade.DTO.CustomerDTO;
import lk.ijse.thogakade.entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    Customer save(CustomerDTO customerDTO);
}
