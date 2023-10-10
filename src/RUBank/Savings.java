package RUBank;

/**
 * Saving class is one of the types of Account.
 * @author Fiona Wang
 */
public class Savings extends Account {
    protected boolean isLoyal;

    @Override
    public double monthlyInterest() {
        if (this.isLoyal = true)
            return balance * 0.0425 / 12;
        else return balance * 0.04;
    }

    @Override
    public double monthlyFee() {
        if (this.balance >= 500)
            return 0;
        else return 25.00;
    }

    @Override
    public int compareTo(Account o) {
        return 0;
    }
}
