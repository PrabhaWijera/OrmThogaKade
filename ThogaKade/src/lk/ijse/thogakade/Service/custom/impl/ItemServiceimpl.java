package lk.ijse.thogakade.Service.custom.impl;

import lk.ijse.thogakade.DAO.DaoFactory;
import lk.ijse.thogakade.DAO.DaoType;
import lk.ijse.thogakade.DAO.custom.ItemDAO;
import lk.ijse.thogakade.DAO.exception.ConstraintViolationException;
import lk.ijse.thogakade.DTO.ItemDTO;
import lk.ijse.thogakade.Service.custom.ItemService;
import lk.ijse.thogakade.Service.exception.DuplicateException;
import lk.ijse.thogakade.Service.exception.InUseException;
import lk.ijse.thogakade.Service.exception.NotFoundException;
import lk.ijse.thogakade.Service.util.Convertor;
import lk.ijse.thogakade.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemServiceimpl implements ItemService {

    private Connection connection;
    private Convertor convertor;
    private ItemDAO itemDAO;

    public ItemServiceimpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        itemDAO= DaoFactory.getInstance().getDAO(connection, DaoType.ITEM);
        convertor=new Convertor();
    }


    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) throws DuplicateException, SQLException, ClassNotFoundException {
        if (itemDAO.existByPk(itemDTO.getCode())) throw new DuplicateException("ItemDTO Already Saved!!");
        itemDAO.save(convertor.toItem(itemDTO));
        return itemDTO;
    }

    @Override
    public boolean deleteItem(String ItemCode) throws NotFoundException, InUseException, SQLException, ClassNotFoundException {
        if (itemDAO.existByPk(ItemCode)) throw new NotFoundException("ItemDTO not found!!");
        try{
            itemDAO.deleteByPk(ItemCode);
        }catch (ConstraintViolationException e){
            throw new InUseException("item Already in!!!!");
        }
        return false;
    }

    @Override
    public ItemDTO updateItem(ItemDTO itemDTO) throws NotFoundException, SQLException, ClassNotFoundException {
        if (!itemDAO.existByPk(itemDTO.getCode())) throw new NotFoundException("ItemDTO not Found!!!");
        itemDAO.update(convertor.toItem(itemDTO));
        return itemDTO;
    }


}
