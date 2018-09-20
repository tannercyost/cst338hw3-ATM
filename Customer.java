/**
 *
 *
 *
 */
public class Customer {
    private String name;
    private int pin;
    private double balance;
    private String bank;

    public Customer() {
        this.name = "UNKNOWN";
        this.pin = 0000;
        this.balance = 0.00;
        this.bank = "UNKNOWN";
    }

    public Customer(String name, int pin, double balance, String bank) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return this.name +
                ": Balance $" + balance;
    }
}
