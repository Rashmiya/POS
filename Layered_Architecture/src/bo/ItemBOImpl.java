package bo;

import dao.custom.ItemDAO;
import dao.impl.ItemDAOImpl;

public class ItemBOImpl {
    // Property Injection
    private ItemDAO itemDAO = new ItemDAOImpl();

}
