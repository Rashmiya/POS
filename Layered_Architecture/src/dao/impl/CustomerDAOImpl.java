package dao.impl;
import dao.SQLUtil;
import dao.custom.CustomerDAO;
import model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

//  To write SQL Query for each related classes.

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",dto.getId(),dto.getName(),dto.getAddress());
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Customer SET name=?, address=? WHERE id=?",dto.getName(),dto.getAddress(),dto.getId());
    }

    @Override
    public boolean exit(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT id FROM Customer WHERE id=?", id);
        return rst.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Customer WHERE id=?",id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (resultSet.next()) {
            String id = resultSet.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        while(resultSet.next()){
            allCustomers.add(
                    new CustomerDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3))
            );
        }
        return allCustomers;
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM Customer WHERE id = ?",id);
        if (resultSet.next()) {
            return new CustomerDTO(resultSet.getString(1),resultSet.getString(2), resultSet.getString(3));
        }
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomersByAddress(String address) {
        return null;
    }
}