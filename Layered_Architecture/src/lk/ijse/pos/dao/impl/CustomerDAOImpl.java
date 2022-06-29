package lk.ijse.pos.dao.impl;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.*;
import java.util.ArrayList;

//  To write SQL Query for each related classes.

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",entity.getId(),entity.getName(),entity.getAddress());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE Customer SET name=?, address=? WHERE id=?",entity.getName(),entity.getAddress(),entity.getId());
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
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> allCustomers = new ArrayList<>();
        while(resultSet.next()){
            allCustomers.add(
                    new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3))
            );
        }
        return allCustomers;
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT * FROM Customer WHERE id = ?",id);
        if (resultSet.next()) {
            return new Customer(resultSet.getString(1),resultSet.getString(2), resultSet.getString(3));
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAllCustomersByAddress(String address) {
        return null;
    }
}
