public class ATMDemo
{
    public static void main(String[] args)
    {
        ATM machine1 = new ATM("OtterUnion");
        ATM machine2 = new ATM(200, "BOA", "Library");
        Customer alice;
        System.out.println("===== Welcome to Demo Program =====");
        System.out.println(machine1); System.out.println("");
        System.out.println(machine2);
        System.out.println("\n===== Equality Checking =====");
        System.out.println(machine1.equals(machine2));
        System.out.println("");

        machine1.setATM(100, "BIT");
        machine1.addFund(400);// In this method, we assume that an ATM machine
                        // administrator adds $400 more cash to the machine.
        System.out.println(machine1);
        System.out.println("");
        machine1.displayMenu();

        machine1.withdrawal("Alice", 7777, 10.50); // In the method, we assume
        // that the customer "Alice" wants $10.50 withdrawal with PIN 7777.

        machine1.withdrawal("Robert", 2323, 10.50);
        machine1.withdrawal("Alice", 1234, 10000);
        machine1.withdrawal("Alice", 1234, 10);
        machine1.withdrawal("Alice", 1234, 2000);

        System.out.println("\n===== Machine Status =====");
        machine1.status();
        System.out.println("");
        if (machine1.isCustomer("Alice")) {
            alice = machine1.getCustomer("Alice");
            System.out.println(alice);
            System.out.println("");
        }
        machine1.deposit("Alice", 1234, 10); // In the method, we assume that
         // "Alice" conducts the cash deposit $10
         // to the machine with PIN 1234.
        System.out.println("\n===== Machine Status =====");
        machine1.status();
        System.out.println("");

        //The following method conducts a money transfer transaction from
        // "Alice" to "Tom" about $10.00 dollars.

        if (machine1.transfer("Alice", 1234, 10, "Tom", 2000)) {
            System.out.println("Good transfer!!!\n");
        }
        if (!machine1.transfer("Chris", 8787, 10, "Tom", 2000)) {
            System.out.println("Bad transfer!!!\n");
        }
        System.out.println("\n===== Machine Status =====");
        machine1.status();
        System.out.println("\n===== Thank you! =====");
    }
}

