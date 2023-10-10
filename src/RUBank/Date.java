package RUBank;

import java.util.Calendar;

/**
 * Date class represents a calendar date as an object with a year, month, and day.
 *
 * @author Pranay Bhatt, Fiona Wang
 */
public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;

    private static final int QUADRENNIAL = 4;
    private static final int CENTENNIAL = 100;
    private static final int QUATERCENTENNIAL = 400;
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;

    private static final int DAYS_IN_FEB_LEAP = 29;
    private static final int DAYS_IN_FEB_NONLEAP = 28;
    private static final int DAYS_IN_SHORT_MONTH = 30;
    private static final int DAYS_IN_LONG_MONTH = 31;

    /**
     * Constructs a Date object with the given year, month, and day.
     * @param year the year
     * @param month the month
     * @param day the day
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Checks to see if the date is valid.
     * @return true if the date is valid, false otherwise
     */
    public boolean isValid() {
        if (year < 0 || month < JANUARY || month > DECEMBER || day < 1) {
            return false;
        }
        if (month == FEBRUARY) {
            if (isLeapYear()) {
                return day <= DAYS_IN_FEB_LEAP;
            } else {
                return day <= DAYS_IN_FEB_NONLEAP;
            }
        }
        if (month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) {
            return day <= DAYS_IN_SHORT_MONTH;
        }
        return day <= DAYS_IN_LONG_MONTH;
    }

    /**
     * Checks to see if the year is a leap year.
     * @return true if the year is a leap year, false otherwise
     */
    public boolean isLeapYear() {
        return year % QUATERCENTENNIAL == 0 || (year % QUADRENNIAL == 0 && year % CENTENNIAL != 0);
    }

    /**
     * Checks to see if the date is a future date.
     * @return true if the date is a future date, false otherwise
     */
    public boolean isFutureDate() {
        Calendar currentCalendar = Calendar.getInstance();
        final Date today = new Date(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH) + 1, currentCalendar.get(Calendar.DAY_OF_MONTH));
        return compareTo(today) > 0;
    }

    /**
     * Checks to see if the date is within 6 months.
     * @return true if the date is within 6 months, false otherwise
     */
    public boolean isWithinSixMonths() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 6);
        final Date sixMonthsFromToday = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        return compareTo(sixMonthsFromToday) <= 0;
    }

    /**
     * Compares this date to another date.
     * @param other the other date
     * @return a negative integer if this date is before the other date, a positive integer if this date is after the other date, and 0 if the dates are equal
     */
    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return this.year - other.year;
        }
        if (this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }

    /**
     * Returns a string representation of the date.
     * @return a string representation of the date
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /**
     * Checks to see if this date is equal to another date.
     * @param other the other date
     * @return true if the dates are equal, false otherwise
     */
    @Override
    public boolean equals(Object other)
    {
        if(!(other instanceof Date))
            return false;

        Date date = (Date)other;
        return day == date.day && month == date.month && year == date.year;
    }

    /**
     * Gets the day.
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * Gets the month.
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Gets the year.
     * @return the year
     */
    public int getYear() {
        return year;
    }

    public static void main(String[] args) {
        testDaysInFeb_Nonleap();
        testDaysInFeb_Leap();
        testMonth_OutOfRange();
    }

    /**
     * Tests the isValid method for a non-leap year.
     */
    private static void testDaysInFeb_Nonleap() {
        Date date = new Date(2019, 2, 29);
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
    }

    /**
     * Tests the isValid method for a leap year.
     */
    private static void testDaysInFeb_Leap() {
        Date date = new Date(2020, 2, 29);
        boolean expectedOutput = true;
        boolean actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
    }

    /**
     * Tests the isValid method for a month that is out of range.
     */
    private static void testMonth_OutOfRange() {
        Date date = new Date(2019, 13, 29);
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);
    }

    /**
     * Tests the result of a test.
     * @param date the date
     * @param expectedOutput the expected output
     * @param actualOutput the actual output
     */
    public static void testResult(Date date, boolean expectedOutput, boolean actualOutput) {
        System.out.println("Test Input: " + date.toString());
        System.out.println("Expected Output: " + expectedOutput);
        System.out.println("Actual Output: " + actualOutput);
        if (expectedOutput == actualOutput) {
            System.out.println(" (PASS) \n");
        } else {
            System.out.println(" (FAIL) \n");
        }

    }

}
