package bo;

import dao.custom.CustomerDAO;
import dao.impl.CustomerDAOImpl;

public class CustomerBOImpl {
    //    Property Injection
    private CustomerDAO customerDAO = new CustomerDAOImpl();
}
