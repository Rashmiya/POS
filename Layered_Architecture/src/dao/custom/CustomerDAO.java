package dao.custom;

import dao.CrudDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.util.ArrayList;

//To write unique methods for customer table
// must extend CrudDAO interface

public interface CustomerDAO extends CrudDAO<Customer,String> {
    public ArrayList<CustomerDTO> getAllCustomersByAddress(String address);
}
