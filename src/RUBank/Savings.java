package RUBank;

public class Savings extends Account{
    protected boolean isLoyal;
    private static final double NO_MONTHLY_FEE = 0.0;
    private static final double MONTHLY_FEE = 25.0;
    private static final double INTEREST_RATE = 0.04;
    private static final double LOYALTY_INTEREST_RATE = 0.0425;
    
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
        if(isLoyal){
            return balance * LOYALTY_INTEREST_RATE;
        }
        return balance * INTEREST_RATE;
    }

    @Override
    public double monthlyFee() {
        if (balance >= 500){
            return NO_MONTHLY_FEE;
        }
        return MONTHLY_FEE;
    }

    @Override
    public String toString() {
        if(isLoyal){
            return getAccountType() + "::" + holder.toString() + "::Balance $" + String.format("%.2f", balance) + "::is loyal";
        }
        return getAccountType() + "::" + holder.toString() + "::Balance $" + String.format("%.2f", balance);
    }
}
