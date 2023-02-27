package lk.ijse.thogakade.DAO.custom.impl;



import lk.ijse.thogakade.DAO.custom.CustomerDAO;
import lk.ijse.thogakade.DAO.exception.ConstraintViolationException;
import lk.ijse.thogakade.DAO.util.DBUtil;
import lk.ijse.thogakade.DTO.CustomerDTO;
import lk.ijse.thogakade.Service.custom.CustomerService;
import lk.ijse.thogakade.db.DBConnection;
import lk.ijse.thogakade.entity.Customer;
import lk.ijse.thogakade.repo.CustomerRepo;
import lk.ijse.thogakade.util.CrudUtil;
import lk.ijse.thogakade.util.FactoryConfigure;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel implements CustomerDAO {
    public static CustomerService customerService;
    private Connection connection;
    private static CustomerRepo customerRepo;

    public CustomerModel(Connection connection){
        this.connection=connection;
    }


    @Override
    public Customer save(Customer entity) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer VALUES (?, ?, ?, ?)";
        boolean b= DBUtil.executeUpdate(sql,entity.getId(),entity.getName(),entity.getAddress(),entity.getSalary());
        return b ? new Customer(): null;
    }

    @Override
    public boolean update(Customer customer) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return DBUtil.executeUpdate("UPDATE customer SET  Salary=?, Address=?, Name=?, WHERE Id= ? ",customer.getSalary(),customer.getAddress(),customer.getName(),customer.getId());

    }

    @Override
    public boolean deleteByPk(String pk) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Customer where id='" + pk + "'") > 0;

    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findByPk(String pk) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    public static CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM Customer WHERE id = ?";
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
        String sql = "SELECT id FROM Customer";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }

    @Override
    public Customer save(CustomerDTO customerDTO) {
     return null;
    }
}
