package dao.custom;

import dao.CrudDAO;
import model.CustomerDTO;

import java.util.ArrayList;

//To write unique methods for customer table
// must extend CrudDAO interface

public interface CustomerDAO extends CrudDAO<CustomerDTO,String> {
    public ArrayList<CustomerDTO> getAllCustomersByAddress(String address);
}
