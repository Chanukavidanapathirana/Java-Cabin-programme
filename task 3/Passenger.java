public class Passenger {
    private String firstName;
    private String secondName;
    private String fullName ;
    private double costPerCustomer;

    public Passenger(){
        this.firstName = "empty";
        this.secondName = "empty";
        this.fullName = "empty";
        this.costPerCustomer = 0;
    }
    public Passenger(String firstName, String secondName, double costPerCustomer){
        this.firstName = firstName;
        this.secondName = secondName;
        this.fullName = firstName + " " + secondName;
        this.costPerCustomer = costPerCustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFullName() {
        return fullName;
    }

    public double getCostPerCustomer() {
        return costPerCustomer;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setFullName(String firstName, String secondName) {
        this.fullName = firstName + " "+ secondName;
    }

    public void setCostPerCustomer(double costPerCustomer) {
        this.costPerCustomer = costPerCustomer;
    }
}
