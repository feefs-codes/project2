package RUBank;

/**
 * College Checking class is an extension of Checking
 * and includes the instance variable campus.
 * A person cannot hold a Checking and a College Checking account at the same time.
 * @author Fiona Wang
 */
public class CollegeChecking extends Checking {
    //private Campus campus;

    @Override
    public double monthlyFee() {
        return 0.00;
    }
}
