package RUBank;

import java.util.Scanner;

public class TransactionManager {
    private AccountDatabase database;
    private boolean quit = false;

    private static final String OPEN = "O";
    private static final String CLOSE = "C";
    private static final String WITHDRAW = "W";
    private static final String DEPOSIT = "D";
    private static final String PRINT = "P";
    private static final String CHECKING = "C";
    private static final String COLLEGE_CHECKING = "CC";
    private static final String SAVINGS = "S";
    private static final String MONEY_MARKET = "MM";
    private static final String QUIT = "Q";

    public TransactionManager() {
        database = new AccountDatabase();
    }

    private Date parseDate(String dateString) throws Exception {
        String[] dateStrings = dateString.split("/");
        int month = Integer.parseInt(dateStrings[0]);
        int day = Integer.parseInt(dateStrings[1]);
        int year = Integer.parseInt(dateStrings[2]);

        Date date = new Date(year, month, day);
        if(!date.isValid()) throw new Exception(dateString + ": Invalid Calendar Date!");
        if(date.isFutureDate()) throw new Exception(dateString + ": Date can't be a future date!");
        return date;
    }

    private void processOpenCommand(String[] inputParts) {
        try {
            switch (inputParts[1]) {
                case CHECKING -> openCheckingCommand(inputParts);
                case COLLEGE_CHECKING -> openCollegeCheckingCommand(inputParts);
                case SAVINGS -> openSavingsCommand(inputParts);
                case MONEY_MARKET -> openMoneyMarketCommand(inputParts);
                default -> System.out.println(inputParts[1] + " is an invalid account!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for opening an account.");
        }
    }

    private void openCheckingCommand(String[] inputParts) {
        String firstName = inputParts[2];
        String lastName = inputParts[3];
        String dateString = inputParts[4];

        Date dob;
        try {
            dob = parseDate(dateString);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        if (!dob.isOver16()) {
            System.out.println("DOB Invalid: " + dateString + " under 16");
            return;
        }

        Profile profile = new Profile(firstName, lastName, dob);
        double balance;
        try {
            balance = Double.parseDouble(inputParts[5]);
            if (balance <= 0) {
                System.out.println("Initial deposit cannot be 0 or negative.");
                return;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
            return;
        }
        // if (balance <= 0) {
        //     System.out.println("Initial deposit must be greater than 0!");
        //     return;
        // }
        Account account = new Checking(profile, balance);
        if (database.open(account)) {
            System.out.println("Account opened and added to the database.");
        }
        else {
            System.out.println("Account is already in the database.");
        }
    }

    private void openCollegeCheckingCommand(String[] inputParts) {
        String firstName = inputParts[2];
        String lastName = inputParts[3];
        String dobString = inputParts[4];

        Date dob;
        try {
            dob = parseDate(dobString);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        if (!dob.isOver16()) {
            System.out.println("DOB Invalid: " + dobString + " under 16");
            return;
        }
        if (!dob.isUnder24()) {
            System.out.println("DOB Invalid: " + dobString + " over 24");
            return;
        }

        Profile profile = new Profile(firstName, lastName, dob);
        double balance;
        try {
            balance = Double.parseDouble(inputParts[5]);
            if (balance <= 0) {
                System.out.println("Initial deposit cannot be 0 or negative.");
                return;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
            return;
        }
        // if (balance <= 0) {
        //     System.out.println("Initial deposit must be greater than 0!");
        //     return;
        // }
        Campus campus;
        switch (inputParts[6]) {
            case "0" -> campus = Campus.NEW_BRUNSWICK;
            case "1" -> campus = Campus.NEWARK;
            case "2" -> campus = Campus.CAMDEN;
            default -> {
                System.out.println(inputParts[6] + " is an invalid campus!");
                return;
            }
        }
        Account account = new CollegeChecking(profile, balance, campus);
        if (database.open(account)) {
            System.out.println("Account opened and added to the database.");
        }
        else {
            System.out.println("Account is already in the database.");
        }
    }

    private void openSavingsCommand(String[] inputParts) {
        String firstName = inputParts[2];
        String lastName = inputParts[3];
        String dobString = inputParts[4];

        Date dob;
        try { dob = parseDate(dobString); }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        if (!dob.isOver16()) {
            System.out.println("DOB Invalid: " + dobString + " under 16");
            return;
        }

        Profile profile = new Profile(firstName, lastName, dob);
        double balance;
        try {
            balance = Double.parseDouble(inputParts[5]);
            if (balance <= 0) {
                System.out.println("Withdraw amount must be greater than 0!");
                return;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Withdraw amount must be a number!");
            return;
        }
        if (balance <= 0) {
            System.out.println("Initial deposit must be greater than 0!");
            return;
        }
        int loyalty = Integer.parseInt(inputParts[6]);
        boolean isLoyal = (loyalty == 1);
        Account account = new Savings(profile, balance, isLoyal);
        if (database.open(account)) { System.out.println("Account opened and added to the database."); }
        else { System.out.println("Account is already in the database."); }
    }

    private void openMoneyMarketCommand(String[] inputParts) {
        String firstName = inputParts[2];
        String lastName = inputParts[3];
        String dobString = inputParts[4];

        Date dob;
        try {
            dob = parseDate(dobString);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        if (!dob.isOver16()) {
            System.out.println("DOB Invalid: " + dobString + " under 16");
            return;
        }

        Profile profile = new Profile(firstName, lastName, dob);
        double balance;
        try {
            balance = Double.parseDouble(inputParts[5]);
            if (balance < 2000) {
                System.out.println("Initial deposit must be minimum $2000!");
                return;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Withdraw amount must be a number!");
            return;
        }
        if (balance < 2000) {
            System.out.println("Initial deposit must be minimum $2000!");
            return;
        }
        Account account = new MoneyMarket(profile, balance);
        if (database.open(account)) {
            System.out.println("Account opened and added to the database.");
        }
        else {
            System.out.println("Account is already in the database.");
        }

    }


    private void processCloseCommand(String[] inputParts) {
        String accountType = inputParts[1];
        String firstName = inputParts[2];
        String lastName = inputParts[3];
        String dobString = inputParts[4];

        Date dob;
        try {
            dob = parseDate(dobString);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        Profile profile = new Profile(firstName, lastName, dob);
        Account account;
        switch (accountType) {
            case CHECKING -> account = new Checking(profile);
            case COLLEGE_CHECKING -> account = new CollegeChecking(profile);
            case SAVINGS -> account = new Savings(profile);
            case MONEY_MARKET -> account = new MoneyMarket(profile);
            default -> {
                System.out.println(accountType + " is an invalid account!");
                return;
            }
        }
        if (database.close(account)) {
            System.out.println("Account closed and removed from the database.");
        }
        else {
            System.out.println("Account does not exist.");
        }

    }

    private void processDepositCommand(String[] inputParts) {
        String firstName = inputParts[2];
        String lastName = inputParts[3];
        String dobString = inputParts[4];

        Date dob;
        try { dob = parseDate(dobString); }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        Profile profile = new Profile(firstName, lastName, dob);
        double amount;
        try {
            amount = Double.parseDouble(inputParts[5]);
            if (amount <= 0) {
                System.out.println("Deposit amount must be greater than 0!");
                return;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Deposit amount must be a number!");
            return;
        }
        
        Account account;
        String accountType = inputParts[1];
        switch (accountType) {
            case CHECKING -> account = new Checking(profile, amount);
            case COLLEGE_CHECKING -> account = new CollegeChecking(profile, amount);
            case SAVINGS -> account = new Savings(profile, amount);
            case MONEY_MARKET -> account = new MoneyMarket(profile, amount);
            default -> {
                System.out.println(accountType + " is an invalid account!");
                return;
            }
        }
        if (database.deposit(account)) { System.out.println(String.format("%.2f deposited to account.", amount)); }
        else { System.out.println("Account does not exist."); }        
    }

    private void processWithdrawCommand(String[] inputParts) {
        String firstName = inputParts[2];
        String lastName = inputParts[3];
        String dobString = inputParts[4];

        Date dob;
        try { dob = parseDate(dobString); }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        Profile profile = new Profile(firstName, lastName, dob);
        double amount;
        try {
            amount = Double.parseDouble(inputParts[5]);
            if (amount <= 0) {
                System.out.println("Withdraw amount must be greater than 0!");
                return;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Withdraw amount must be a number!");
            return;
        }
        
        Account account;
        String accountType = inputParts[1];
        switch (accountType) {
            case CHECKING -> account = new Checking(profile, amount);
            case COLLEGE_CHECKING -> account = new CollegeChecking(profile, amount);
            case SAVINGS -> account = new Savings(profile, amount);
            case MONEY_MARKET -> account = new MoneyMarket(profile, amount);
            default -> {
                System.out.println(accountType + " is an invalid account!");
                return;
            }
        }

        if (database.withdraw(account)) {
            System.out.println(String.format("%.2f withdrawn from account.", amount));
        }
        else {
            System.out.println("Account does not exist.");
        }
    }


    private void processPrintCommand() {
        if (database.isEmpty()) {
            System.out.println("Database is empty.");
        }
        else {
            database.printSorted();
        }
    }





    public void run() {
        System.out.println("Transaction Manager is running.");
        Scanner scanner = new Scanner(System.in);

        while (!quit && scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] inputParts = input.split("\\s+");

            switch (inputParts[0]) {
                case OPEN -> processOpenCommand(inputParts);
                case CLOSE -> processCloseCommand(inputParts);
                case WITHDRAW -> processWithdrawCommand(inputParts);
                case DEPOSIT -> processDepositCommand(inputParts);
                case PRINT -> processPrintCommand();
                case QUIT -> quit = true;
                default -> System.out.println(inputParts[0] + " is an invalid command!");
            }
        }
        scanner.close();
        System.out.println("Transaction Manager is terminated.");
    }
}
