public class TestBankAccount {
  public static void main(String[] args) {

    // Create a current account
    BankAccount currentAcc = new BankAccount(BankAccount.AccountType.CURRENT, "C001", 200);
    System.out.println("Created Current Account: " + currentAcc.getAccountID());
    System.out.println("Balance: " + currentAcc.getBalance());

    // Create a savings account
    BankAccount savingsAcc = new BankAccount(BankAccount.AccountType.SAVINGS, "S001", 100);
    System.out.println("\nCreated Savings Account: " + savingsAcc.getAccountID());
    System.out.println("Balance: " + savingsAcc.getBalance());

    // Deposit
    savingsAcc.deposit(50);
    System.out.println("\nAfter deposit, savings balance: " + savingsAcc.getBalance());

    // Withdraw
    savingsAcc.withdraw(30);
    System.out.println("After withdrawal, savings balance: " + savingsAcc.getBalance());

    // Transfer from current to savings
    currentAcc.transfer(true, savingsAcc, 50);
    System.out.println("\nAfter transfer:");
    System.out.println("Current Account Balance: " + currentAcc.getBalance());
    System.out.println("Savings Account Balance: " + savingsAcc.getBalance());

    // Perform monthly maintenance
    System.out.println("\nPerforming monthly maintenance for savings:");
    savingsAcc.performMonthlyMaintenance();

    System.out.println("\nPerforming monthly maintenance for current:");
    currentAcc.performMonthlyMaintenance();
  }
}
