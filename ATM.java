import java.util.Objects;

/**
 *
 *
 *
 */
public class ATM {
    private Customer[] customers = new Customer[10];
    private int serialNumber;
    private String location;
    private String bank;
    private double funds;
    private int withdrawalSuccess;
    private int withdrawalFail;
    private int depositSuccess;
    private int depositFail;
    private int transferSuccess;
    private int transferFail;

    public ATM(){
        this.serialNumber = 0;
        this.location = "UNKNOWN";
        this.bank = "UNKNOWN";
        this.funds = 100.00;
        this.withdrawalSuccess = 0;
        this.withdrawalFail = 0;
        this.depositSuccess = 0;
        this.depositFail = 0;
        this.transferSuccess= 0;
        this.transferFail = 0;
    }

    public ATM(String bank){
        this();
        populateCustomers();
        this.bank = bank;
    }

    public ATM(int serialNumber, String bank, String location) {
        this();
        populateCustomers();
        this.serialNumber = serialNumber;
        this.location = location;
        this.bank = bank;
    }

    private final void populateCustomers() {
        customers[0] = new Customer("Alice", 1234, 5000.00, "OtterUnion");
        customers[1] = new Customer("Tom", 2000, 200.00, "OtterUnion");
        customers[2] = new Customer("Monica", 3000, 50.00, "OtterUnion");
        customers[3] = new Customer("Michael", 7777, 0.00, "OtterUnion");
        customers[4] = new Customer("John", 8000, 500.00, "OtterUnion");
        customers[5] = new Customer("Jane", 1234, 5000.00, "OtterUnion");
        customers[6] = new Customer("Robert", 1234, 5000.00, "BOA");
        customers[7] = new Customer("Owen", 1234, 5000.00, "BOA");
        customers[8] = new Customer("Chris", 1234, 5000.00, "BOA");
        customers[9] = new Customer("Rebecca", 1234, 5000.00, "BOA");
    }

    public void setATM(int serial, String location) {
        this.setSerialNumber(serial);
        this.setLocation(location);
    }

    public void addFund(double amount) {
        amount += getFunds();
        setFunds(amount);
    }
    
    public void displayMenu() {
        //TODO
    }
    
    public void status() {
        //TODO
    }
    
    public boolean isCustomer(String name) {
        //TODO
        for (Customer item : customers) {
            if (item.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void withdrawal(String name, int pin, double amount) {
        //TODO
    }

    public void deposit(String name, int pin, double amount) {

    }

    public boolean transfer(String nameOrigin, int pinOrigin, double amount, String nameDest, int pinDest) {
        boolean b = false;
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ATM atm = (ATM) o;
        return getSerialNumber() == atm.getSerialNumber() &&
                Double.compare(atm.getFunds(), getFunds()) == 0 &&
                Objects.equals(getLocation(), atm.getLocation());
    }

    @Override
    public String toString() {
        return "Serial number: " + this.serialNumber + "\n" +
                "Bank name: " + this.bank + "\n" +
                "Location: " + this.location + "\n" +
                "Balance: " + this.funds;
    }
    /**
     * Setters and getters are below.
     */
    public Customer getCustomer(String name) {
        for (Customer item : customers) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    private void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLocation() {
        return location;
    }

    private void setLocation(String location) {
        this.location = location;
    }

    public double getFunds() {
        return funds;
    }

    private void setFunds(double funds) {
        this.funds = funds;
    }
}