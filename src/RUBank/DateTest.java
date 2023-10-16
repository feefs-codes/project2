package RUBank;

import static org.junit.Assert.*;

public class DateTest {

    /**
     * JUnit test invalid case 1
     * Asserts false that 2/29 exists in a non leap year.
     */
    @org.junit.Test
    public void testDaysInFebNonLeap() {
        Date date = new Date(2011, 2, 29);
        assertFalse(date.isValid());
    }

    /**
     * JUnit test invalid case 2
     * Asserts false that there exists a 13th month.
     */
    @org.junit.Test
    public void testInvalidMonth() {
        Date date = new Date(2015, 13, 1);
        assertFalse(date.isValid());
    }

    /**
     * JUnit test invalid case 3
     * Asserts false that there exists a 32nd day in a month.
     */
    @org.junit.Test
    public void testInvalidDay() {
        Date date = new Date(2015, 12, 32);
        assertFalse(date.isValid());
    }

    /**
     * JUnit test invalid case 4
     * Asserts false that there exists a negative year.
     */
    @org.junit.Test
    public void testInvalidYear() {
        Date date = new Date(-2000, 1, 1);
        assertFalse(date.isValid());
    }

    /**
     * JUnit test invalid case 5
     * Asserts false that a 31st day exists in a short month.
     */
    @org.junit.Test
    public void testDaysInShortMonth() {
        Date date = new Date(2022, 9, 31);
        assertFalse(date.isValid());
    }

    /**
     * JUnit test valid case 1
     * Asserts true that 2/29 exists in a leap year.
     */
    @org.junit.Test
    public void testDaysInFebLeap() {
        Date date = new Date(2008, 2, 29);
        assertTrue(date.isValid());
    }

    /**
     * JUnit test valid case 2
     * Asserts true that a 31st exists in a long month.
     */
    @org.junit.Test
    public void testDaysInLongMonth() {
        Date date = new Date(2015, 8, 31);
        assertTrue(date.isValid());
    }
}