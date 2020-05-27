package setupmenu;

import setupmenu.ButtonBean;
import setupmenu.DeptButtonBean;
import setupmenu.ListButtonBean;
import setupmenu.PluButtonBean;
import setupmenu.SubButtonBean;

public class StoreSetupControl {

    public boolean storeSetup(ButtonBean bean) {
        boolean success = false;
        if (bean instanceof PluButtonBean) {
            PluButtonBean obj = (PluButtonBean) bean;
            success = new StorePlu().store(obj);
        } else if (bean instanceof SubButtonBean) {
            SubButtonBean obj = (SubButtonBean) bean;
            success = new StoreSub().store(obj);
        } else if (bean instanceof DeptButtonBean) {
            DeptButtonBean obj = (DeptButtonBean) bean;
            success = new StoreDept().store(obj);
        } else if (bean instanceof ListButtonBean) {
            ListButtonBean obj = (ListButtonBean) bean;
            success = new StoreList().store(obj);
        }
        return success;
    }

    public boolean storeUpdate(ButtonBean bean) {
        boolean success = false;
        if (bean instanceof PluButtonBean) {
            PluButtonBean obj = (PluButtonBean) bean;
            success = new StorePlu().storeUpdate(obj);
        } else if (bean instanceof SubButtonBean) {
            SubButtonBean obj = (SubButtonBean) bean;
            success = new StoreSub().storeUpdate(obj);
        } else if (bean instanceof DeptButtonBean) {
            DeptButtonBean obj = (DeptButtonBean) bean;
            success = new StoreDept().storeUpdate(obj);
        } else if (bean instanceof ListButtonBean) {
            ListButtonBean obj = (ListButtonBean) bean;
            success = new StoreList().storeUpdate(obj);
        }
        return success;
    }
}
