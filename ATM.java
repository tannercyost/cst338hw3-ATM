/**
 * ATM.java
 * Abstract: object representing an ATM, contains various methods that allow objects of type Customer to interact
 *           with the ATM in various ways. Getter and setter methods are located at the bottom because there are many
 *           of them and they aren't particularly interesting.
 *
 * Tanner Yost
 * 9/17/2018
 */
import java.util.Objects;

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
        System.out.println("===== ATM Transaction Menu =====");
        System.out.println("\t1. Withdrawal");
        System.out.println("\t2. Deposit");
        System.out.println("\t3. Transfer");
    }
    
    public void status() {
        int totalTransactions = (this.getWithdrawalFail()
                                +this.getWithdrawalSuccess()
                                +this.getDepositSuccess()
                                +this.getDepositFail()
                                +this.getTransferFail()
                                +this.getTransferSuccess());
        int withdrawals = this.getWithdrawalFail() + this.getWithdrawalSuccess();
        int deposits = this.getDepositFail() + this.getDepositSuccess();
        int transfers = this.getTransferFail() + this.getTransferSuccess();
        System.out.println("\tSerial Number: "+this.getSerialNumber());
        System.out.println("\tBank name: "+this.getBank());
        System.out.println("\tLocation: "+this.getLocation());
        System.out.println("\tBalance: "+this.getFunds());
        System.out.println("\t"+totalTransactions+" Transactions so far: ");
        System.out.printf("\t\tWithdrawal: %d (%d success, %d fail)\n", withdrawals, this.getWithdrawalSuccess(), this.getWithdrawalFail());
        System.out.printf("\t\tDeposit: %d (%d success, %d fail)\n", deposits, this.getDepositSuccess(), this.getDepositFail());
        System.out.printf("\t\tTransfer: %d (%d success, %d fail)\n", transfers, this.getTransferSuccess(), this.getTransferFail());
    }

    /**
     * Checks the customer array to determine if the customer exists or not.
     * @param name, name of customer.
     * @return whether or not the customer exists.
     */
    public boolean isCustomer(String name) {
        for (Customer item : customers)
            if (item.getName().equals(name) && (item.getBank() == this.getBank())) {
                return true;
            }
        return false;
    }

    /**
     * Checks a) if the customer exists, b) if the pin matches, c) if there are enough funds in the atm,
     *        d) if the customer's balance is high enough, and finally e) if the bank matches.
     *        It will set the ATMs balance and the customer's balance accordingly.
     * @param name, customer name
     * @param pin, customer pin
     * @param amount, amount to be withdrawn.
     */
    public void withdrawal(String name, int pin, double amount) {
        if (isCustomer(name)) {
            Customer c = getCustomer(name);
            if (c.getPin() == pin && (this.getFunds() >= amount) && (c.getBalance() > amount) && (c.getBank().equals(this.getBank()))) {
                c.setBalance(c.getBalance() - amount);
                this.setFunds(this.getFunds() - amount);
                System.out.println("Succeed - withdrawal");
                withdrawalSuccess++;
            }
            else {
                System.out.println("Fail - withdrawal");
                withdrawalFail++;
            }
        }
        else {
            System.out.println("Fail - withdrawal");
            withdrawalFail++;
        }
    }

    /**
     *
     * @param name, customer name
     * @param pin, customer pin
     * @param amount, amount to be deposited
     */
    public void deposit(String name, int pin, double amount) {
        if (isCustomer(name)) {
            Customer c = getCustomer(name);
            if (c.getPin() == pin && (c.getBank().equals(this.getBank()))) {
                c.setBalance(c.getBalance() + amount);
                this.setFunds(this.getFunds() + amount);
                System.out.println("Succeed - deposit");
                depositSuccess++;
            }
            else {
                System.out.println("Fail - deposit");
                depositFail++;
            }
        }
        else {
            System.out.println("Fail - deposit");
            depositFail++;
        }
    }

    /**
     *
     * @param nameOrigin, name of initiator of transfer
     * @param pinOrigin, pin of the initiator
     * @param amount, amount to be transferred
     * @param nameDest, destination customer name
     * @param pinDest, destination customer pin
     * @return boolean detailing if the transfer was successful or not.
     */
    public boolean transfer(String nameOrigin, int pinOrigin, double amount, String nameDest, int pinDest) {
        boolean b = false;
        if (isCustomer(nameOrigin) && isCustomer(nameDest)) {
            Customer cOrigin = getCustomer(nameOrigin);
            Customer cDest = getCustomer(nameDest);
            if (cOrigin.getPin() == pinOrigin && cDest.getPin() == pinDest && (cOrigin.getBank().equals(this.getBank())) && (cDest.getBank().equals(this.getBank()))) {
                cOrigin.setBalance(cOrigin.getBalance() - amount);
                cDest.setBalance(cDest.getBalance() + amount);
                System.out.println("Succeed - transfer");
                transferSuccess++;
                b = true;
            }
            else {
                System.out.println("Fail - transfer");
                transferFail++;
            }
        }
        else {
            System.out.println("Fail - transfer");
            transferFail++;
        }
        return b;
    }

    /**
     *
     * @param o, object to be compared
     * @return boolean whether or not Object o is equal to the invoking object.
     */
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

    /*
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

    private int getSerialNumber() {
        return serialNumber;
    }

    private void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    private String getLocation() {
        return location;
    }

    private void setLocation(String location) {
        this.location = location;
    }

    private double getFunds() {
        return funds;
    }

    private String getBank() {
        return this.bank;
    }

    private void setFunds(double funds) {
        this.funds = funds;
    }

    private int getWithdrawalSuccess() {
        return withdrawalSuccess;
    }

    private int getWithdrawalFail() {
        return withdrawalFail;
    }

    private int getDepositSuccess() {
        return depositSuccess;
    }

    private int getDepositFail() {
        return depositFail;
    }

    private int getTransferSuccess() {
        return transferSuccess;
    }

    private int getTransferFail() {
        return transferFail;
    }
}