package lk.ijse.thogakade.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.thogakade.DTO.CustomerDTO;
import lk.ijse.thogakade.DTO.ItemDTO;
import lk.ijse.thogakade.Service.ServiceFactory;
import lk.ijse.thogakade.Service.ServiceType;
import lk.ijse.thogakade.Service.custom.ItemService;
import lk.ijse.thogakade.Service.custom.impl.CustomerServiceimpl;
import lk.ijse.thogakade.Service.custom.impl.ItemServiceimpl;
import lk.ijse.thogakade.util.Navigation;
import lk.ijse.thogakade.util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class ManageItemFormController {

    public AnchorPane pane;
    public TableView tblItem;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public TextField txtDescription;
    public TextField txtQtyOnHand;
    public TextField txtUnitPrice;
    public JFXButton btnUpdate;
    public JFXButton btnUpdate1;
    public TextField txtId;

    private String code;
    private String description;
    private double unitPrice;
    private int qtyOnHand;

    public ItemService itemService;

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        code = txtId.getText();
        description = txtDescription.getText();
        qtyOnHand=  Integer.parseInt(txtQtyOnHand.getText());
        unitPrice = Double.parseDouble(txtUnitPrice.getText());

        ItemDTO itemDTO = new ItemDTO(code,description,unitPrice,qtyOnHand);
        //ItemServiceimpl cs= ServiceFactory.getInstance().getService(ServiceType.ITEM);
        itemService.saveItem(itemDTO);
        if (itemDTO!=null) {
            new Alert(Alert.AlertType.CONFIRMATION, "Item Added!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }
        initialize();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        code=txtId.getText();
        description=txtDescription.getText();
        qtyOnHand=  Integer.parseInt(txtQtyOnHand.getText());
        unitPrice = Double.parseDouble(txtUnitPrice.getText());

        ItemDTO itemDTO=new ItemDTO(code,description,unitPrice,qtyOnHand);
        try{
            itemService.deleteItem(code);
            boolean c=itemService.deleteItem(code);
            if (itemDTO!=null){
                new Alert(Alert.AlertType.CONFIRMATION,"Ok").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"wrong").show();
            }
        }catch (SQLException | ClassNotFoundException exception){
            throw new RuntimeException(exception);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        code = txtId.getText();
        description = txtDescription.getText();
        qtyOnHand=  Integer.parseInt(txtQtyOnHand.getText());
        unitPrice = Double.parseDouble(txtUnitPrice.getText());

        ItemDTO itemDTO = new ItemDTO(code,description,unitPrice,qtyOnHand);
        //ItemServiceimpl cs= ServiceFactory.getInstance().getService(ServiceType.ITEM);
        if (itemDTO!=null) {
            itemService.updateItem(itemDTO);
            new Alert(Alert.AlertType.CONFIRMATION, "Item Added!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

    }
    public  void initialize() throws SQLException, ClassNotFoundException {
        itemService=ServiceFactory.getInstance().getService(ServiceType.ITEM);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD,pane);
    }
}
