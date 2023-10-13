package RUBank;

public class MoneyMarket extends Account {
    private int withdrawal;
    private static final double MONTHLY_FEE = 25.0;
    private static final double INTEREST_RATE = 0.0475;
    private static final double MIN_BALANCE = 2000.0;

    // default constructor
    public MoneyMarket(Profile holder, double balance) {
        super(holder, balance);
        this.withdrawal = 0;
    }

    // constructor to close money market account
    public MoneyMarket(Profile holder) {
        super(holder);
    }

    @Override
    public String getAccountType() {
        return "Money Market";
    }

    @Override
    public double monthlyInterest() {
        return balance * INTEREST_RATE;
    }

    @Override
    public double monthlyFee() {
        if (balance >= MIN_BALANCE){
            return 0;
        }
        return MONTHLY_FEE;
    }

    @Override
    public String toString() {
        return ""; 
    }
}
