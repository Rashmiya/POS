package bo.impl;

import bo.custom.PurchaseOrderBO;
import dao.DAOFactory;
import dao.custom.*;
import db.DBConnection;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
      public boolean purchaseOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException,ClassNotFoundException{
        /*Transaction*/
            Connection connection = DBConnection.getDbConnection().getConnection();
//            if order id already exist
            if (orderDAO.exit(orderId)) {

            }
            connection.setAutoCommit(false);
            // Save order
            boolean save = orderDAO.save(new OrderDTO(orderId, orderDate, customerId));

            if (!save) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
            //  Save orderDetails
            for (OrderDetailDTO detail : orderDetails) {
                boolean save1 = orderDetailsDAO.save(detail);
                if (!save1) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                //Search & Update Item
                ItemDTO item = null /*findItem(detail.getItemCode())*/;
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                //update item
                System.out.println(item);
                boolean update = itemDAO.update(item);

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
          return customerDAO.search(id);
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
          return itemDAO.search(code);
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
        return customerDAO.getAll();
    }

    @Override
    public ArrayList<ItemDTO> loadAllItemCodes() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
}
