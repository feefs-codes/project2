package RUBank;

import static org.junit.Assert.*;

public class DateTest {

    // 5 invalid cases
    @org.junit.Test
    public void testDaysInFebNonLeap() {
        Date date = new Date(2011, 2, 29);
        assertFalse(date.isValid());
    }

    @org.junit.Test
    public void testInvalidMonth() {
        Date date = new Date(2015, 13, 1);
        assertFalse(date.isValid());
    }

    @org.junit.Test
    public void testInvalidDay() {
        Date date = new Date(2015, 12, 32);
        assertFalse(date.isValid());
    }

    @org.junit.Test
    public void testInvalidYear() {
        Date date = new Date(-2000, 1, 1);
        assertFalse(date.isValid());
    }

    @org.junit.Test
    public void testDaysInShortMonth() {
        Date date = new Date(2022, 9, 31);
        assertFalse(date.isValid());
    }

    // 2 valid cases
    @org.junit.Test
    public void testDaysInFebLeap() {
        Date date = new Date(2008, 2, 29);
        assertTrue(date.isValid());
    }

    @org.junit.Test
    public void testDaysInLongMonth() {
        Date date = new Date(2015, 8, 31);
        assertTrue(date.isValid());
    }
}