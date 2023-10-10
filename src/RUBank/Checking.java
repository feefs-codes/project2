package RUBank;

/**
 * Checking class is one of the types of Account.
 * A person cannot hold a Checking and a College Checking account at the same time.
 * @author Fiona Wang
 */
public class Checking extends Account {
    @Override
    public double monthlyInterest() {
        return balance * .01 / 12;
    }

    @Override
    public double monthlyFee() {
        return 12.00;
    }

    @Override
    public int compareTo(Account o) {
        return 0;
    }
}
