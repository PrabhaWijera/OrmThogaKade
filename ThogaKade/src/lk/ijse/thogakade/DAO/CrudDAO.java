package lk.ijse.thogakade.DAO;


import lk.ijse.thogakade.DAO.exception.ConstraintViolationException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T extends Serializable,ID extends Serializable> extends SuperDAO {

    T save(T entity) throws ConstraintViolationException, SQLException, ClassNotFoundException;

    boolean update(T entity) throws ConstraintViolationException, SQLException, ClassNotFoundException;

    boolean deleteByPk(String pk) throws ConstraintViolationException, SQLException, ClassNotFoundException;

    List<T> findAll() ;

    T findByPk(ID pk) throws SQLException, ClassNotFoundException;

    boolean existByPk(String pk) ;

    long count() ;


}
