class Bank {
  // Perform a transaction:
  public void doTransaction(Transaction transaction) {
    switch(transaction.getTransactionType()) {
      case Transaction.CREDIT:
        synchronized(transaction.getAccount()) {
//        System.out.println("Start credit of " +
//                 transaction.getAccount() + " amount: " +
//                 transaction.getAmount());

        // Get current balance:
        int balance = transaction.getAccount().getBalance();

        // Credits require require a lot of checks:
        try {
          Thread.sleep(100);		
        } catch(InterruptedException e) {
          System.out.println(e);
        }
        balance += transaction.getAmount();             // Increment the balance.
        transaction.getAccount().setBalance(balance);   // Restore account balance.

        
//        System.out.println("  End credit of " +
//                  transaction.getAccount() + " amount: " +
//                  transaction.getAmount());
                  

        }
        break;
      case Transaction.DEBIT:
        synchronized(transaction.getAccount()) {
//        System.out.println("Start debit  of " +
//                 transaction.getAccount() + " amount: " +
//                 transaction.getAmount());


        // Get current balance
        int balance = transaction.getAccount().getBalance();

        // Debits require even more checks...
        try {
          Thread.sleep(150);
        } catch(InterruptedException e) {
          System.out.println(e);
        }
        balance -= transaction.getAmount();             // Increment the balance.
        transaction.getAccount().setBalance(balance);   // Restore account balance.

        
//        System.out.println("  End debit of " +
//                  transaction.getAccount() + " amount: " +
//                  transaction.getAmount());
                  

        }
        break;
      default:                                          // We should never get here.
        assert false : "Invalid transaction";
    }
  }
}
