package dao.custom;

import dao.SuperDAO;
import dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

// To help JoinQueryDAOImpl for loose coupling.

public interface JoinQueryDAO extends SuperDAO {
    public ArrayList<CustomDTO> searchOrderByOrderID(String id) throws SQLException, ClassNotFoundException;
}
