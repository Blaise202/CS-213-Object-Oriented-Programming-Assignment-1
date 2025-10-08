public class BankAccount{

  public enum AccountType{
    CURRENT,
    SAVINGS
  }

  public AccountType acctType;
  public String acctId;
  public double balance;
  public int numWithdrawals;
  public boolean inTheRed;
  public double minBalance;

  public double CURRENT_ACCT_MIN_BALANCE;
  public double SAVINGS_ACCT_MIN_BALANCE;

  public double interestRate;
  public double maintenanceFee;
  public int withdrawalLimit = -1;

  public double SAVINGS_ACCT_INTEREST_RATE;
  public double CURRENT_ACCT_MAINTENANCE_FEE;
  public double SAVINGS_WITHDRAWAL_LIMIT;


  public static void main(String[] args){

  }
}