package lk.ijse.thogakade.DAO.custom.impl;



import lk.ijse.thogakade.DAO.util.DBUtil;
import lk.ijse.thogakade.DTO.CustomerDTO;
import lk.ijse.thogakade.Service.custom.CustomerService;
import lk.ijse.thogakade.entity.Customer;
import lk.ijse.thogakade.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static CustomerService customerService;
    private Connection connection;

    public CustomerModel(Connection connection){
        this.connection=connection;
    }

    public  Customer save(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO CustomerDTO VALUES (?, ?, ?, ?)";
        boolean b= DBUtil.executeUpdate(sql,customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary());
      return b ? new Customer(): null;
    }

    public static CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM CustomerDTO WHERE id = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new CustomerDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDouble(4)
            );
        }
        return null;
    }

    public static ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT id FROM CustomerDTO";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }
}
