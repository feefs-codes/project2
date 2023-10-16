package RUBank;

public abstract class Account implements Comparable<Account> {
    protected Profile holder;
    protected double balance;

    public Account(Profile holder, double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    // account constructor for close command
    public Account(Profile holder) {
        this.holder = holder;
    }

    public abstract double monthlyInterest();
    public abstract double monthlyFee();

    public abstract String getAccountType();
    
    public Profile getHolder() {
        return holder;
    }

    public void withdrawMoney(Account account) {
        balance -= account.balance;
    }

    @Override
    public int compareTo(Account account) {
        if (this.getAccountType().compareTo(account.getAccountType()) == 0) {
            return this.holder.compareTo(account.holder);
        }
        return this.getAccountType().compareTo(account.getAccountType());
    }

    @Override
    public abstract String toString();

    /**
     * Tests the result of a test.
     * @param account the event
     * @param expectedOutput the expected output
     * @param actualOutput the actual output
     */
    public static void testResult(Account account, boolean expectedOutput, boolean actualOutput) {
        System.out.println("Test Input: " + account.toString());
        System.out.println("Expected Output: " + expectedOutput);
        System.out.println("Actual Output: " + actualOutput);
        if (expectedOutput == actualOutput) {
            System.out.println(" (PASS) \n");
        } else {
            System.out.println(" (FAIL) \n");
        }
    }
}