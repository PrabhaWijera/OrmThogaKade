package lk.ijse.thogakade.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.thogakade.util.Navigation;
import lk.ijse.thogakade.util.Routes;

import java.io.IOException;



public class DashboardFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    void btnCustomerFormOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.CUSTOMER, pane);

    }

    @FXML
    void btnItemFormOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ITEM, pane);
    }

    @FXML
    void btnPlaceOrderFormOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.PLACE_ORDER, pane);

    }
}
