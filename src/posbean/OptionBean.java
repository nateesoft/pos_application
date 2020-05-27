package posbean;

public class OptionBean {

    private String optName;
    private int optQty;
    private String optCode;
    private double optPrice;
    private String[] listOpt;

    public String[] getListOpt() {
        return listOpt;
    }

    public void setListOpt(String[] listOpt) {
        this.listOpt = listOpt;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public int getOptQty() {
        return optQty;
    }

    public void setOptQty(int optQty) {
        this.optQty = optQty;
    }

    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }

    public double getOptPrice() {
        return optPrice;
    }

    public void setOptPrice(double optPrice) {
        this.optPrice = optPrice;
    }

}
