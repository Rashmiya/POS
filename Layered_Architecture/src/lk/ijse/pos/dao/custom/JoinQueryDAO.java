package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

// To help JoinQueryDAOImpl for loose coupling.

public interface JoinQueryDAO extends SuperDAO {
    public ArrayList<CustomDTO> searchOrderByOrderID(String id) throws SQLException, ClassNotFoundException;
}
