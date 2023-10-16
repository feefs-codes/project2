package RUBank;

public class AccountDatabase {
    private Account[] accounts;
    private int numAcct;
    private static final int NOT_FOUND = -1;
    private static final int ARRAY_GROWTH_FACTOR = 4;

    public AccountDatabase() {
        this.accounts = new Account[4];
        this.numAcct = 0;
    }
    private int find(Account account) {
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].getAccountType().equals(account.getAccountType()) && accounts[i].getHolder().equals(account.getHolder())) {
                return i;
            }
        }
        return NOT_FOUND;
    }
    private void grow() {
        Account[] newAccounts = new Account[accounts.length + ARRAY_GROWTH_FACTOR];
        for (int i = 0; i < accounts.length; i++) {
            newAccounts[i] = accounts[i];
        }
        accounts = newAccounts;
    }

    public boolean contains(Account account) {
        return find(account) != NOT_FOUND;
    }

    public boolean open(Account account) {
        if (contains(account)) {
            return false;
        }
        else if (account.getAccountType().equals("Checking")) {
            CollegeChecking collegeChecking = new CollegeChecking(account.holder, account.balance);
            if (contains(collegeChecking)) {
                return false;
            }
        }
        else if (account.getAccountType().equals("College Checking")) {
            Checking checking = new Checking(account.holder, account.balance);
            if (contains(checking)) {
                return false;
            }
        }
        if (numAcct == accounts.length) {
            grow();
        }
        accounts[numAcct++] = account;
        return true;
    }

    public boolean close(Account account) {
        int index = find(account);
        if (index == NOT_FOUND) {
            return false;
        }
        accounts[index] = accounts[--numAcct];
        accounts[numAcct] = null;
        return true;
    }

    public boolean withdraw(Account account) {
        int index = find(account);
        if (index == NOT_FOUND) return false;
        if (accounts[index].balance < account.balance) return false;

        accounts[index].withdrawMoney(account);
        return true;
    }

    public boolean deposit(Account account) {
        int index = find(account);
        if (index == NOT_FOUND) {
            return false;
        }
        accounts[index].balance += account.balance;
        return true;
    }

    public boolean isEmpty() {
        return numAcct == 0;
    }

    public void printSorted() {
        // display all the accounts in the account database, sorted by the account types
        // For the same account type, sort by the account holder’s profile (last name, first name and dob.)
        bubbleSort();
        System.out.println("\n*Accounts sorted by account type and profile.");
        for (int i = 0; i < numAcct; i++) {
            System.out.println(accounts[i].toString());
        }
        System.out.println("*end of list.\n");
    }
    private void bubbleSort() {
        int n = numAcct;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (accounts[j].compareTo(accounts[j + 1]) > 0) {
                    Account temp = accounts[j];
                    accounts[j] = accounts[j + 1];
                    accounts[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public void printFeesAndInterests() {
        bubbleSort();
        System.out.println("\n*list of accounts with fee and monthly interest");
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].monthlyFee() > 0 || accounts[i].monthlyInterest() > 0) {
                System.out.println(accounts[i].toString() + "::fee $" + String.format("%.2f", accounts[i].monthlyFee())
                        + "::monthly interest $" + String.format("%.2f", accounts[i].monthlyInterest()));
            }
        }
        System.out.println("*end of list.\n");
    }

    public void printUpdatedBalances() {
        bubbleSort();
        System.out.println("\n*list of accounts with fees and interests applied.");
        for (int i = 0; i < numAcct; i++) {
            accounts[i].balance += accounts[i].monthlyInterest() - accounts[i].monthlyFee();
            System.out.println(accounts[i].toString());
        }
        System.out.println("*end of list.\n");
    }

    public static void main(String[] args) {
        System.out.println("Opening accounts tests");
        testOpenDifferentCheckingAccounts();
    }

    private static void testOpenDifferentCheckingAccounts() {
        AccountDatabase database = new AccountDatabase();
        Date dob = new Date(2000, 1, 1);
        Profile profile = new Profile("Fiona", "Wang", dob);
        Account collegeChecking = new CollegeChecking(profile, 1000, Campus.NEW_BRUNSWICK);
        database.open(collegeChecking);

        Account checking = new Checking(profile, 1000);
        boolean actualOutput = database.open(checking);
        boolean expectedOutput = false;

        Account.testResult(checking, expectedOutput, actualOutput);
    }
}
