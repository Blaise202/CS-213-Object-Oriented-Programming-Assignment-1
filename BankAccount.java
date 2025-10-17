public class BankAccount {

  // Enumeration for account type
  public enum AccountType {
    CURRENT,
    SAVINGS
  }

  // Defined constants
  private static double CURRENT_ACCT_MIN_BALANCE = 100.0;
  private static double SAVINGS_ACCT_MIN_BALANCE = 50.0;
  private static double SAVINGS_ACCT_INTEREST_RATE = 0.05; // 5% annual interest
  private static double CURRENT_ACCT_MAINTENANCE_FEE = 10.0;
  private static int SAVINGS_WITHDRAWAL_LIMIT = 2;

  // Member variables
  private AccountType acctType;
  private String acctID;
  private double balance;
  private int numWithdrawals;
  private boolean inTheRed;
  private double minBalance;
  private double interestRate;
  private double maintenanceFee;
  private int withdrawalLimit;

  // Constructor 1: type and id only
  public BankAccount(AccountType type, String id) {
    this.acctType = type;
    this.acctID = id;
    this.balance = 0;
    this.numWithdrawals = 0;

    if (type == AccountType.CURRENT) {
      this.minBalance = CURRENT_ACCT_MIN_BALANCE;
      this.interestRate = 0;
      this.maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
      this.withdrawalLimit = -1; // no limit
    } else {
      this.minBalance = SAVINGS_ACCT_MIN_BALANCE;
      this.interestRate = SAVINGS_ACCT_INTEREST_RATE;
      this.maintenanceFee = 0;
      this.withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
    }

    this.inTheRed = (balance < minBalance);
  }

  // Constructor 2: type, id, and opening balance
  public BankAccount(AccountType type, String id, double openingBalance) {
    this(type, id);
    this.balance = openingBalance;
    this.inTheRed = (balance < minBalance);
  }

  // B. getBalance
  public double getBalance() {
    return balance;
  }

  // C. getAccountType
  public AccountType getAccountType() {
    return acctType;
  }

  // D. getAccountID
  public String getAccountID() {
    return acctID;
  }

  // E. getMinBalance
  public double getMinBalance() {
    return minBalance;
  }

  // F. withdraw
  public boolean withdraw(double amount) {
    if (withdrawalLimit != -1 && numWithdrawals >= withdrawalLimit) {
      System.out.println("Sorry, could not perform withdrawal: Withdrawal limit reached.");
      return false;
    }
    if (inTheRed) {
      System.out.println("Sorry, could not perform withdrawal: Account is in the red.");
      return false;
    }
    if (balance - amount < minBalance) {
      System.out.println("Sorry, could not perform withdrawal: Insufficient balance.");
      return false;
    }

    balance -= amount;
    numWithdrawals++;
    inTheRed = (balance < minBalance);
    return true;
  }

  // G. deposit
  public void deposit(double amount) {
    balance += amount;
    inTheRed = (balance < minBalance);
  }

  // H. performMonthlyMaintenance
  public void performMonthlyMaintenance() {
    double earnedInterest = (balance * (interestRate / 12)); // monthly interest
    balance += earnedInterest;
    balance -= maintenanceFee;

    inTheRed = (balance < minBalance);
    numWithdrawals = 0;

    System.out.println("Earned interest: " + earnedInterest);
    System.out.println("Maintenance fee: " + maintenanceFee);
    System.out.println("Updated balance: " + balance);

    if (inTheRed) {
      System.out.println("WARNING: This account is in the red!");
    }
  }

  // I. transfer
  public boolean transfer(boolean transferTo, BankAccount otherAccount, double amount) {
    if (transferTo) {
      if (this.withdraw(amount)) {
        otherAccount.deposit(amount);
        return true;
      } else {
        return false;
      }
    } else {
      if (otherAccount.withdraw(amount)) {
        this.deposit(amount);
        return true;
      } else {
        return false;
      }
    }
  }
}