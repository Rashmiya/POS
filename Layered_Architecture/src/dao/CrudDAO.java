package dao;

import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
public interface CrudDAO <T,ID>{
    public boolean save(T dto) throws SQLException, ClassNotFoundException;

    public boolean update(T dto) throws SQLException, ClassNotFoundException;

    public boolean exit(ID id) throws SQLException, ClassNotFoundException;

    public boolean delete(ID id) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;

    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    public T search(ID id) throws SQLException,ClassNotFoundException;
}
