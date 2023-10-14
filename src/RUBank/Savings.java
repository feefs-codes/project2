package RUBank;

public class Savings extends Account {

    protected boolean isLoyal;
    private static final double NO_MONTHLY_FEE = 0.0;
    private static final double MONTHLY_FEE = 25.0;
    private static final double INTEREST_RATE = 0.04;
    private static final double LOYALTY_INTEREST_RATE = 0.0425;
    private static final double MIN_BALANCE = 500.0;
    private static final double NUM_OF_MONTHS = 12.0;
    
    public Savings(Profile holder, double balance, boolean isLoyal) {
        super(holder, balance);
        this.isLoyal = isLoyal;
    }

    // constructor to deposit into savings account
    public Savings(Profile holder, double balance) {
        super(holder, balance);
    }

    // constructor to close savings account
    public Savings(Profile holder) {
        super(holder);
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }

    @Override
    public double monthlyInterest() {
        if(isLoyal) return (balance * LOYALTY_INTEREST_RATE) / NUM_OF_MONTHS;
        return (balance * INTEREST_RATE) / NUM_OF_MONTHS;
    }

    @Override
    public double monthlyFee() {
        if (balance >= MIN_BALANCE) return NO_MONTHLY_FEE;
        return MONTHLY_FEE;
    }

    @Override
    public String toString() {
        if(isLoyal) return getAccountType() + "::" + holder.toString() + "::Balance $" + String.format("%.2f", balance) + "::is loyal";
        return getAccountType() + "::" + holder.toString() + "::Balance $" + String.format("%.2f", balance);
    }
}
