package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderBO extends SuperBO {
    public boolean purchaseOrder(OrderDTO dto) throws SQLException,ClassNotFoundException;

    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;

    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;

    public boolean exitItem(String code) throws SQLException, ClassNotFoundException;

    public boolean exitCustomer(String id) throws SQLException, ClassNotFoundException;

    public String generateNewOrderId() throws SQLException, ClassNotFoundException;

    public ArrayList<CustomerDTO> getAllCustomerIDs() throws SQLException, ClassNotFoundException;

    public ArrayList<ItemDTO> loadAllItemCodes() throws SQLException, ClassNotFoundException;

}
