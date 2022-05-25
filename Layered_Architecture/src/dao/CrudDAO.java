package dao;

import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
public interface CrudDAO {
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    public boolean exitCustomer(String id) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
}
