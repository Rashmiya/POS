package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Customer;

import java.util.ArrayList;

//To write unique methods for customer table
// must extend CrudDAO interface

public interface CustomerDAO extends CrudDAO<Customer,String> {
    public ArrayList<Customer> getAllCustomersByAddress(String address);
}
