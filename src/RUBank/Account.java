package RUBank;

/**
 * Account class is the general type of the other account types.
 * @author Fiona Wang
 */
public abstract class Account implements Comparable<Account> {
    protected Profile holder;
    protected double balance;

    public abstract double monthlyInterest();
    public abstract double monthlyFee();
}
