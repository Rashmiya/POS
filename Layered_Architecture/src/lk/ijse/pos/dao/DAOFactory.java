package lk.ijse.pos.dao;

// eka object ekk hadala reuse kireema

import dao.impl.*;
import lk.ijse.pos.dao.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
//     Singleton
    public static DAOFactory getInstance(){
        return (daoFactory == null ? daoFactory = new DAOFactory() : daoFactory);
    }
//     public , static , final, integer values.
    public enum DAOTypes{
        CUSTOMER,ITEM,ORDER,ORDERDETAILS,JOINQUERYDAO
    }
//    method for hide the object creation logic and return what client wants
    public SuperDAO getDAO(DAOTypes types ){
        switch (types){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl();
            case JOINQUERYDAO:
                return new JoinQueryDAOImpl();
            default:
                return null;
        }
    }
}
