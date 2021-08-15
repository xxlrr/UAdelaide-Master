/**
 * Base Class
 * + Bicycle
 * ++ attributes: is_serviced (bool), inDate (String), outDate (String), serviceResponsible (String)
 * ++ methods: checkoutService()
 */

public class Bicycle extends VehicleAbstract {
    
    private boolean is_serviced;
    private String inDate;
    private String outDate;
    private String serviceResponsible;

    public boolean isIs_serviced() {
        return is_serviced;
    }

    public void setIs_serviced(boolean is_serviced) {
        this.is_serviced = is_serviced;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getServiceResponsible() {
        return serviceResponsible;
    }

    public void setServiceResponsible(String serviceResponsible) {
        this.serviceResponsible = serviceResponsible;
    }
    
    public Bicycle(String color, String year, int numberGears) {
        super(color, year, numberGears);
    }

    @Override
    public void changeGear(int numberGears) {
        this.setNumberGears(numberGears);
    }

    @Override
    public boolean checkBreak() {
        return true;
    }
    
}
