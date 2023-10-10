package RUBank;

/**
 * The Profile class defines the profile of an account holder with their
 * first and last names, and their date of birth.
 * @author Fiona Wang
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;
    @Override
    public int compareTo(Profile o) {
        return 0;
    }
}
