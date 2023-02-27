package lk.ijse.thogakade.DAO.custom.impl;



import lk.ijse.thogakade.db.DBConnection;
import lk.ijse.thogakade.DTO.OrderDTO;
import lk.ijse.thogakade.DTO.PlaceOrder;

import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrderModel {

    public static boolean placeOrder(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isOrderAdded = OrderModel.save(new OrderDTO(placeOrder.getOrderId(), LocalDate.now(), placeOrder.getCustomerId()));
            if (isOrderAdded) {
                boolean isUpdated = ItemModel.updateQty(placeOrder.getOrderDetails());
                if (isUpdated) {
                    boolean isOrderDetailAdded = OrderDetailModel.saveOrderDetails(placeOrder.getOrderDetails());
                    if (isOrderDetailAdded) {
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
