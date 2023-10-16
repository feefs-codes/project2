package RUBank;

public class MoneyMarket extends Account {
    private int withdrawal;
    private static final double MONTHLY_FEE = 25.0;
    private static final double LOYALTY_INTEREST_RATE = 0.0475;
    private static final double INTEREST_RATE = 0.045;
    private static final double MIN_BALANCE = 2000.0;
    private static final double NO_MONTHLY_FEE = 0.0;
    private static final int MAX_WITHDRAWAL = 3;
    private static final int NUM_OF_MONTHS = 12;

    // default constructor
    public MoneyMarket(Profile holder, double balance) {
        super(holder, balance);
        this.withdrawal = 0;
    }

    // constructor to close money market account
    public MoneyMarket(Profile holder) {
        super(holder);
    }

    public boolean isLoyal() {
        return balance >= MIN_BALANCE;
    }

    @Override
    public String getAccountType() {
        return "Money Market";
    }

    @Override
    public void withdrawMoney(Account account) {
        balance -= account.balance;
        withdrawal++;
    }

    @Override
    public double monthlyInterest() {
        if (isLoyal()) return (balance * LOYALTY_INTEREST_RATE) / NUM_OF_MONTHS;
        return (balance * INTEREST_RATE) / NUM_OF_MONTHS;
    }

    @Override
    public double monthlyFee() {
        if (balance >= 2000) {
            if (withdrawal < MAX_WITHDRAWAL)
                return NO_MONTHLY_FEE;
            else return 10.0;
        }
        else
            if (withdrawal < MAX_WITHDRAWAL)
                return MONTHLY_FEE;
            return MONTHLY_FEE + 10.0;
    }

    @Override
    public String toString() {
        if (isLoyal()) return "Money Market::Savings::" + holder.toString() + "::Balance $" + String.format("%.2f", balance) + "::is loyal::withdrawal: " + withdrawal;
        return "Money Market::Savings::" + holder.toString() + "::Balance $" + String.format("%.2f", balance) + "::withdrawal: " + withdrawal;
    }
}
