package RUBank;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountDatabaseTest {

    Date fDob = new Date(2002, 1, 1);
    Profile fiona = new Profile("Fiona", "Wang", fDob);
    Account fAccount = new Savings(fiona, 1000);

    Date pDob = new Date(2003, 12, 31);
    Profile pranay = new Profile("Pranay", "Bhatt", pDob);
    Account pAccount = new Checking(pranay, 1000);


    // true case
    @Test
    public void testValidClose() {
        AccountDatabase database = new AccountDatabase();
        //assertFalse(database.close(fAccount));
        database.open(fAccount);
        assertTrue(database.close(fAccount));
    }

    // false case
    @Test
    public void testInvalidClose() {
        AccountDatabase database = new AccountDatabase();
        Account pCC = new CollegeChecking(pranay, 1000, Campus.NEW_BRUNSWICK);
        database.open(pCC);
        assertFalse(database.close(pAccount));
    }
}