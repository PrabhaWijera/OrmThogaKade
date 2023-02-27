package lk.ijse.thogakade.controller;

/*
    @author DanujaV
    @created 11/1/22 - 10:04 AM   
*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.thogakade.DAO.custom.impl.CustomerModel;
import lk.ijse.thogakade.DTO.CustomerDTO;
import lk.ijse.thogakade.Service.ServiceFactory;
import lk.ijse.thogakade.Service.ServiceType;
import lk.ijse.thogakade.Service.custom.CustomerService;
import lk.ijse.thogakade.Service.custom.impl.CustomerServiceimpl;
import lk.ijse.thogakade.util.FactoryConfigure;
import lk.ijse.thogakade.util.Navigation;
import lk.ijse.thogakade.util.Routes;
import org.hibernate.Session;
import org.hibernate.exception.internal.StandardSQLExceptionConverter;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtSalary;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colAction;


    private CustomerService customerService;
    double salary;
    String address;
    String name;
    String id;

    public void initialize() throws SQLException, ClassNotFoundException {
        customerService=ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
    }
    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
         id = txtId.getText();
         name = txtName.getText();
          address= txtAddress.getText();
          salary = Double.parseDouble(txtSalary.getText());

        CustomerDTO customerDTO = new CustomerDTO(id, name, address, salary);
        CustomerServiceimpl cs= ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
        if (cs!=null) {
            customerService.saveCustomer(customerDTO);
            new Alert(Alert.AlertType.CONFIRMATION, "CustomerDTO Added!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, pane);
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
         id=txtId.getText();
         name=txtName.getText();
         address=txtAddress.getText();
        salary=Double.parseDouble(txtSalary.getText());

        CustomerDTO customerDTO=new CustomerDTO(id,name,address,salary);
        CustomerServiceimpl cs=ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
        boolean c =customerService.DeleteCustomer(id);
        if (c!=true){
            new Alert(Alert.AlertType.CONFIRMATION,"CustomerDTo Delete").show();
             }else {
            new Alert(Alert.AlertType.WARNING,"somthing happend").show();
        }
        txtId.clear();txtName.clear();txtAddress.clear();txtSalary.clear();
    }

    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            CustomerDTO customer = CustomerModel.search(id);
            if (customer != null) {
                fillData(customer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(CustomerDTO customer) {
        txtId.setText(customer.getId());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(String.valueOf(customer.getSalary()));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=txtId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        double salary=Double.parseDouble(txtSalary.getText());

        CustomerDTO customerDTO = new CustomerDTO(id, name, address, salary);
       /* CustomerServiceimpl cs= ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);*/
        customerService.updateCustomer(customerDTO);
        if (customerDTO!=null) {
            new Alert(Alert.AlertType.CONFIRMATION, "CustomerDTO Added!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }
        txtId.clear();txtName.clear();txtAddress.clear();txtSalary.clear();
    }
}
