package bo;

import bo.impl.CustomerBOImpl;
import bo.impl.ItemBOImpl;
import bo.impl.PurchaseOrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }
    public static BOFactory getInstance(){
        return (boFactory == null ? boFactory = new BOFactory() : boFactory);
    }
    public enum BOTypes{
        CUSTOMER,ITEM,PURCHASEORDER
    }
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PURCHASEORDER:
                return new PurchaseOrderBOImpl();
            default:
                return null;
        }
    }
}
