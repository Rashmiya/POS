package lk.ijse.pos.bo.impl;

import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    // Property Injection
    /*private ItemDAO itemDAO = new ItemDAOImpl();*/
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
       ArrayList<ItemDTO> allItems = new ArrayList<>();
       
        ArrayList<Item> all = itemDAO.getAll();
        for (Item item: all) {
            allItems.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return  allItems;
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(
                new Item(dto.getCode(),dto.getDescription(),dto.getQtyOnHand(),dto.getUnitPrice())
        );
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(
                new Item(dto.getCode(),dto.getDescription(),dto.getQtyOnHand(),dto.getUnitPrice())
        );
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exit(code);
    }

    @Override
    public String generateNewItemCode() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();
    }
}
