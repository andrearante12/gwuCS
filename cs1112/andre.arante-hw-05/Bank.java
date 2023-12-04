/*--------------------------------------------------------------------------
GWU CSCI 1112 Fall 2023
author: <Andre Arante>, Charles Peeke 


Overall banking model that contains multiple accounts to manage

--------------------------------------------------------------------------*/

public class Bank {
    // Declare instance variables
    public String bankName;
    private Account[] accounts;
    private Queue transactions;
    private int count;
    
    // Constructor that initializes a bank account with a given name
	public Bank(String name) {
		this.bankName = name;
        this.transactions = new Queue();
        this.count = 0;
        accounts = new Account[2];
	}

    // Creates an account number for a bank
    public int createAccount(String name) {
        Account a = new Account(name);

        // If we have more accounts that space (eg len(accounts)) double the space in accounts
        if (this.count+1 > accounts.length-1) {
            reallocateAccounts();
        }

        // Append a to the end of accounts
        accounts[this.count] = a;
        this.count++;
        return a.getAccountNumber();
    }

    // Removes an account from the account list by iterating through all the accounts in accounts
    // and deletes the account if we find it.
    // @param accountNumber the numerical identifier of the account we want to delete
    // @return double representing the remaining balance, -1 if no account was found
    public double closeAccount(int accountNumber) {
        // iterate through the list of accounts
        
        int k;

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                double remainingBalance = accounts[i].getBalance();

                // We have to shift the following values in order to avoid have "null gaps" in the
                // accounts array
                for (int j = i+1; j < this.count; j++) {
                    this.accounts[j-1] = this.accounts[j];
                }

                // this.accounts[this.count-1] = null; 

                this.count--;
                return remainingBalance;
            }
        }

        return -1;
    }

    // Searches for an account with the given account number, and returns balance if we find it
    // @param accountNumber the numerical identifier of the account 
    // @return double representing the remaining balance, -1 if no account was found
    public double getBalance(int accountNumber) {
        // iterate through the list of accounts
        for (int i = 0; i < this.count; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i].getBalance();
            }
        }

        return -1;

    }

    // Deposits an ammount into an account
    // @param accountNumber the numerical identifier of the account
    // @param amount the amount we want to withdraw from the account
    public void deposit(int accountNumber, double amount) {
        // iterate through the list of accounts
        for (int i = 0; i < this.count; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                accounts[i].deposit(amount);
            }
        }
    }

    // Finds an account, withdraw the given amount from the account
    // @param accountNumber the numerical identifier of the account
    // @param amount the amount we want to withdraw from the account
    public void withdraw(int accountNumber, double amount) {
        // iterate through the list of accounts
        for (int i = 0; i < this.count; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                accounts[i].withdraw(amount);
            }
        }

    }

    // ----------------------------------------
    // Transactions Methods
    // Adds a Transaction to the list of the banks total transactions. Note, does not 
    // actually invoke the transaction
    // @param t an object of type Transaction to be envoked when it reaches the front
    // of the queue
    public void addTransaction(Transaction t) {
       this.transactions.enqueue(t); 
    }

    // Process the transaction at the state of the queue
    public void processTransaction() {
        // Dequeues the first element in the queue
        Transaction t = this.transactions.dequeue();
        System.out.println(t.toString());

        // If operation 0 (opening or closing) existential verifications must be made
        if (t.getOperation() == 0) {
            boolean deleted = false;
            // Transverse the list of accounts
            for (int i = 0; i < this.count; i++) {
                // If we find an account, delete
                if (accounts[i].getAccountNumber() == t.getAccountNumber()) {
                    closeAccount(t.getAccountNumber());
                    deleted = true;
                    break;
                }
            }
            // If we don't find an account, create a new account
            if (!deleted) {
                int num = t.getAccountNumber();
                // Convert number to ascii letter, then create an account
                char c = (char) num;
                createAccount(String.valueOf(c));
            }
        }
    }

    // If we run out of space in the the accounts[] array, we need to call this method
    // and double the size of the accounts[] array
    private void reallocateAccounts () {
        int k = 0;
        Account[] arr = new Account[this.accounts.length*2];
        for (int i = 0; i < this.accounts.length; i++) {
            if (this.accounts[i] != null) {
                arr[k++] = accounts[i];
            }
        }
        this.accounts = arr;
    }

    // ----------------------------------------
    // Defines the public access methods for the bank
    public String getBankName() {
        return this.bankName;
    }

    public int getNumberOfAccounts() {
        return this.count;
    }

    public int getNumberOfTransactions() {
        return this.transactions.size();
    }

    // Overwrites the base toString() method
    @Override
    public String toString() {
        String s = "Bank Name: " + bankName + " \n";
        s += "Number of Accounts: " + count + " | ";
        s += "Number of Pending Transactions: " + transactions.size() + "\n";
        s += "Accounts: \n";
        for (int i = 0; i < count; i++) {
            s += accounts[i].toString() + " \n";
        }
        return s;
    }
}
