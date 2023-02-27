package lk.ijse.thogakade.Service.custom;

import lk.ijse.thogakade.DTO.ItemDTO;
import lk.ijse.thogakade.Service.SuperService;
import lk.ijse.thogakade.Service.exception.DuplicateException;
import lk.ijse.thogakade.Service.exception.InUseException;
import lk.ijse.thogakade.Service.exception.NotFoundException;

import java.sql.SQLException;

public interface ItemService extends SuperService {

    public ItemDTO saveItem(ItemDTO itemDTO) throws DuplicateException, SQLException, ClassNotFoundException;
    public boolean deleteItem(String Code) throws NotFoundException, InUseException, SQLException,ClassNotFoundException;
    public ItemDTO updateItem(ItemDTO itemDTO) throws NotFoundException, SQLException, ClassNotFoundException;



}
