package lk.ijse.thogakade.DAO.custom.impl;

import lk.ijse.thogakade.DTO.OrderDTO;
import lk.ijse.thogakade.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {
    public static boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Orders VALUES(?, ?, ?)";
        return CrudUtil.execute(sql, orderDTO.getOrderId(), orderDTO.getDate(), orderDTO.getCustomerId());
    }

    public static String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT id FROM Orders ORDER BY id DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return generateNextOrderId(result.getString(1));
        }
        return generateNextOrderId(result.getString(null));
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("D0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "D0" + id;
        }
        return "O001";

    }

}
