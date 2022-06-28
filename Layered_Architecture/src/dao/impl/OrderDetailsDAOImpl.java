package dao.impl;

import dao.SQLUtil;
import dao.custom.OrderDetailsDAO;
import dto.OrderDetailDTO;
import entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean save(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO OrderDetails (oid, itemCode, unitPrice,qty) VALUES (?,?,?,?)", dto.getOid(), dto.getItemCode(), dto.getUnitPrice(), dto.getQty());
    }

    @Override
    public boolean update(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exit(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDetails search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
