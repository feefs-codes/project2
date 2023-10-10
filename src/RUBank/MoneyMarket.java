package RUBank;

/**
 * The MoneyMarket class is an extension of the Savings class
 * and includes the instance variable withdrawal.
 * @author Fiona Wang
 */
public class MoneyMarket extends Savings {
    private int withdrawal;

    @Override
    public double monthlyInterest() {
        return balance * 0.045 / 12;
    }

    @Override
    public double monthlyFee() {
        return 25.00;
    }
}
