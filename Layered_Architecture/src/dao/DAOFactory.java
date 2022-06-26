package dao;

// eka object ekk hadala reuse kireema

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
        CUSTOMER,ITEM,ORDER,ORDERDETAILS,QUERYDAO
    }
//    method for hide the object creation logic and return what client wants
    public void getDAO(DAOTypes types ){
        switch (types){
            case CUSTOMER:
                return;
            case ITEM:
                return;
            case ORDER:
                return;
            case ORDERDETAILS:
                return;
            case QUERYDAO:
                return;
            default:
                return;
        }
    }
}
