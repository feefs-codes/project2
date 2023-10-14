package RUBank;

public class CollegeChecking extends Checking{
    private Campus campus;
    private static final double MONTHLY_FEE = 0.0;
    private static final double INTEREST_RATE = 0.0005;
    private static final double NUM_OF_MONTHS = 12.0;

    // default constructor
    public CollegeChecking(Profile holder, double balance, Campus campus) {
        super(holder, balance);
        this.campus = campus;
    }

    // constructor to deposit into college checking account
    public CollegeChecking(Profile holder, double balance) {
        super(holder, balance);
    }

    // constructor to close college checking account
    public CollegeChecking(Profile holder) {
        super(holder);
    }

    @Override
    public String getAccountType() {
        return "College Checking";
    }

    @Override
    public double monthlyInterest() {
        return (balance * INTEREST_RATE) / NUM_OF_MONTHS;
    }

    @Override
    public double monthlyFee() {
        return MONTHLY_FEE;
    }

    @Override
    public String toString() {
        return getAccountType() + "::" + holder.toString() + "::Balance $" + String.format("%.2f", balance) + "::" + campus;
    }


}
