/*--------------------------------------------------------------------------
GWU CSCI 1112 Fall 2023
author: <your name>, Charles Peeke 


Fundamental Class of the Banking System. Provides a constructor which all
objects of type Account will inherit from. Also provides base implementations
for each function

--------------------------------------------------------------------------*/
import java.text.DecimalFormat;

public class Account {
    // Instance variable declarations
    public String accountName;
    public int accountNumber;
    public double balance;

    // Initializes a bank account with 0 balance and the passed in name
	// @param name a string representing the name of the owner of the bank account
    public Account(String name) {
		this.accountName = name;
       	this.balance = 0; 
        this.assignAccountNumber();
    }

    // Calculates the account number by taking the ASCII sum of the accountName
    private void assignAccountNumber() {
    	// Convert to char array
		char[] name = this.accountName.toCharArray();

		int sum = 0;
		// Iterate through name, adding each ASCII value to sum 
		for (int i = 0; i < name.length; i++) {
			sum += (int) name[i];			
		}

		this.accountNumber = sum;
    }

    // Getters and setters
    public int getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    // Deposit and Withdraw Methods
    // Adds a passed in amount to the total account balance
	// @param amount a double to be deposited
    public void deposit(double amount) {
		this.balance += amount;
    }

    // Removes a passed in amount to the total account balance
	// @param amount a double to be withdrawn
    public void withdraw(double amount) {
		this.balance -= amount;
    }

    // Overrides the base toString method to allow for easy visuals
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        String s = "";
        s += "Account Name: " + this.accountName + " \t";
        s += "Account Number: " + this.accountNumber + " \t";
        s += "Balance: " + df.format(this.balance);
        return s;
    }
}
