package lk.ijse.pos.bo.impl;

import lk.ijse.pos.bo.custom.PurchaseOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Orders;
import lk.ijse.pos.dao.custom.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {
//    DAO part eka handle wenne BO Layer eka ethule.

    /*  private final CustomerDAO customerDAO = new CustomerDAOImpl();
        private final ItemDAO itemDAO = new ItemDAOImpl();
        private final OrderDAO orderDAO = new OrderDAOImpl();
        private final OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();
        private final JoinQueryDAO joinQueryDAO = new JoinQueryDAOImpl();*/

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    JoinQueryDAO joinQueryDAO = (JoinQueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.JOINQUERYDAO);

    @Override
      public boolean purchaseOrder(OrderDTO dto) throws SQLException,ClassNotFoundException{
        /*Transaction*/
            Connection connection = DBConnection.getDbConnection().getConnection();
//            if order id already exist
            if (orderDAO.exit(dto.getOrderId())) {

            }
            connection.setAutoCommit(false);
            // Save order
            boolean save = orderDAO.save(new Orders(dto.getOrderId(),dto.getOrderDate(),dto.getCustomerId())
            );

            if (!save) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
            //  Save orderDetails
            for (OrderDetailDTO detailsDTO : dto.getOrderDetails()) {
                boolean save1 = orderDetailsDAO.save(new OrderDetails(detailsDTO.getOid(),detailsDTO.getItemCode(),detailsDTO.getQty(),detailsDTO.getUnitPrice()));
                if (!save1) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                //Search & Update Item
                ItemDTO item = searchItem(detailsDTO.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detailsDTO.getQty());

                //update item
                System.out.println(item);
                boolean update = itemDAO.update(new Item(item.getCode(),item.getDescription(),item.getQtyOnHand(),item.getUnitPrice()));

                if (!update) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer searchEntity = customerDAO.search(id);
        return new CustomerDTO(searchEntity.getId(),searchEntity.getName(),searchEntity.getAddress());
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item searchEntity = itemDAO.search(code);
        return new ItemDTO(searchEntity.getCode(),searchEntity.getDescription(),searchEntity.getUnitPrice(),searchEntity.getQtyOnHand());
    }

    @Override
    public boolean exitItem(String code) throws SQLException, ClassNotFoundException {
         return itemDAO.exit(code);
    }

    @Override
    public boolean exitCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exit(id);
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomerIDs() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();

        for (Customer customerEntity :all) {
            allCustomers.add(new CustomerDTO(customerEntity.getId(),customerEntity.getName(),customerEntity.getAddress()));
        }
        return allCustomers;
    }

    @Override
    public ArrayList<ItemDTO> loadAllItemCodes() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItems = new ArrayList<>();

        for (Item itemEntity: all) {
            allItems.add(new ItemDTO(itemEntity.getCode(),itemEntity.getDescription(),itemEntity.getUnitPrice(),itemEntity.getQtyOnHand()));
        }
            return allItems;
    }
}
