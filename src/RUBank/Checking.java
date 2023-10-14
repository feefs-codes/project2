package RUBank;

public class Checking extends Account {
    private static final double MONTHLY_FEE = 12.0;
    private static final double INTEREST_RATE = 0.01;
    private static final double MIN_BALANCE = 1000.0;
    private static final double NO_MONTHLY_FEE = 0.0;
    
    public Checking(Profile holder, double balance) {
        super(holder, balance);
    }

    // constructor to close checking account
    public Checking(Profile holder) {
        super(holder);
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }

    @Override
    public double monthlyInterest() {
        return balance * INTEREST_RATE;
    }

    @Override
    public double monthlyFee() {
        if (balance >= MIN_BALANCE){
            return NO_MONTHLY_FEE;
        }
        return MONTHLY_FEE;
    }

    @Override
    public String toString() {
        return getAccountType() + "::" + holder.toString() + "::Balance $" + String.format("%.2f", balance);
    }

}
