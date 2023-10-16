package rubank;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit test class for AccountDatabase class's close() method.
 *
 * @author Fiona Wang
 */
public class AccountDatabaseTest {

    // create savings account for test
    Date fDob = new Date(2002, 1, 1);
    Profile fiona = new Profile("Fiona", "Wang", fDob);
    Account fAccount = new Savings(fiona, 1000);

    // create checking account for test
    Date pDob = new Date(2003, 12, 31);
    Profile pranay = new Profile("Pranay", "Bhatt", pDob);
    Account pAccount = new Checking(pranay, 1000);


    /**
     * JUnit test true case
     * Asserts true when fAccount closes after being opened.
     */
    @Test
    public void testValidClose() {
        AccountDatabase database = new AccountDatabase();
        //assertFalse(database.close(fAccount));
        database.open(fAccount);
        assertTrue(database.close(fAccount));
    }

    /**
     * JUnit test false case
     * Asserts false when closing checking account pAccount after
     * opening a college checking account with the same profile.
     */
    @Test
    public void testInvalidClose() {
        AccountDatabase database = new AccountDatabase();
        Account pCC = new CollegeChecking(pranay, 1000, Campus.NEW_BRUNSWICK);
        database.open(pCC);
        assertFalse(database.close(pAccount));
    }
}