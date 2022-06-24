package bo;

import db.DBConnection;
import model.ItemDTO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class PurchaseOrderBOImpl {
    public void purchaseOrder(){
        /*Transaction*/
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            /*if order id already exist*/
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
                ItemDTO item = findItem(detail.getItemCode());
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }
}
