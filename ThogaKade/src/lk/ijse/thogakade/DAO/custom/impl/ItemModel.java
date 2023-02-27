package lk.ijse.thogakade.DAO.custom.impl;

import lk.ijse.thogakade.DAO.custom.ItemDAO;
import lk.ijse.thogakade.DAO.exception.ConstraintViolationException;
import lk.ijse.thogakade.DAO.util.DBUtil;
import lk.ijse.thogakade.DTO.CartDetail;
import lk.ijse.thogakade.DTO.ItemDTO;
import lk.ijse.thogakade.Service.custom.ItemService;
import lk.ijse.thogakade.Service.exception.NotFoundException;
import lk.ijse.thogakade.db.DBConnection;
import lk.ijse.thogakade.entity.Item;
import lk.ijse.thogakade.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel implements ItemDAO {

    private Connection connection;
    private static ItemService itemService;

    public ItemModel(Connection connection) {
        this.connection=connection;
    }

    public static ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT code FROM ItemDTO";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }

    public static ItemDTO search(String code) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM ItemDTO WHERE code = ?";
        ResultSet result = CrudUtil.execute(sql, code);

        if (result.next()) {
            return new ItemDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getInt(4)
            );
        }
        return null;
    }

    public static boolean updateQty(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail : cartDetails) {
            if (!updateQty(new ItemDTO(cartDetail.getCode(), cartDetail.getDescription(), cartDetail.getUnitPrice(), cartDetail.getQty()))) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(ItemDTO item) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE ItemDTO SET qtyOnHand = qtyOnHand - ? WHERE code = ?";
        return CrudUtil.execute(sql, item.getQtyOnHand(), item.getCode());
    }

    public static boolean update(ItemDTO itemdto) throws SQLException, ClassNotFoundException, NotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Update ItemDTO set  unitPrice=?,qtyOnHand=?,Description=? where Code=?");
        //   itemService.updateItem(itemdto);
        stm.setObject(4, itemdto.getCode());
        stm.setObject(3, itemdto.getDescription());
        stm.setObject(2, itemdto.getQtyOnHand());
        stm.setObject(1, itemdto.getUnitPrice());
        return stm.executeUpdate() > 0;
        //return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Update CustomerDTO set Email=?, PhoneNumber=?, Address=? ,CustomerName=?, CustomerNIC where CustomerID=?") > 0;
    }

    @Override
    public Item save(Item item) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        String sql = "INSERT INTO thogakadeorm.item VALUES (?, ?, ?, ?)";
        boolean b= DBUtil.executeUpdate(sql,item.getCode(),item.getDescription(),item.getQtyOnHand(),item.getUnitPrice());
        return b ? new Item() : null;
    }

    @Override
    public boolean update(Item entity) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return Boolean.parseBoolean(null);
    }

    @Override
    public boolean deleteByPk(String pk) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Item where code='" + pk + "'") > 0;

    }

    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public Item findByPk(String pk) throws SQLException, ClassNotFoundException {
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
}
