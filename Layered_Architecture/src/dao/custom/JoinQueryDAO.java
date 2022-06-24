package dao.custom;

import model.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

// To help JoinQueryDAOImpl for loose coupling.

public interface JoinQueryDAO {
    public ArrayList<CustomDTO> searchOrderByOrderID(String id) throws SQLException, ClassNotFoundException;
}
