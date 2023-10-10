package RUBank;

public class Checking extends Account {
    @Override
    public double monthlyInterest() {
        return .01/12;
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
