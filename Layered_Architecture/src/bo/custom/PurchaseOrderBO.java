package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PurchaseOrderBO extends SuperBO {
    public boolean purchaseOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException,ClassNotFoundException;

    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;

    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;

    public boolean exitItem(String code) throws SQLException, ClassNotFoundException;

    public boolean exitCustomer(String id) throws SQLException, ClassNotFoundException;

    public String generateNewOrderId() throws SQLException, ClassNotFoundException;

    public ArrayList<CustomerDTO> getAllCustomerIDs() throws SQLException, ClassNotFoundException;

    public ArrayList<ItemDTO> loadAllItemCodes() throws SQLException, ClassNotFoundException;

}
