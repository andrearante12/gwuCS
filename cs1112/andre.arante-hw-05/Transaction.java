/*--------------------------------------------------------------------------
GWU CSCI 1112 Fall 2023
author: <Andre Arante>, Charles Peeke 


// Interface that represents the interactions that the user can make with
the banking model

--------------------------------------------------------------------------*/

public class Transaction {
    // Declares the instance variables we need
    public int accountNumber;
    private int operation;
    private double amount;

    // Constructor that defines a transaction
	public Transaction(int acctNumber, int operation, String content) {
		this.accountNumber = acctNumber;
		
		// If operation [0, 2] content should be saved as a double
		if (operation >= 1 && operation <= 2) {
            this.operation = operation;
			this.amount = Double.parseDouble(content);
		} else if (operation == 0) {
            this.operation = operation;
        } else {
			this.operation = translateOldOperation(content);
		}

	}

    // Returns the account number
    public int getAccountNumber() {
        return this.accountNumber;
    }

    // returns an int that represents operation we need to conduct on the account
    public int getOperation() {
        return this.operation;
    }

    // returns a double representing the amount of money in the bank
    public double getAmount() {
        return this.amount;
    }

    // Utilizes a stack interface to translate from prefix to normal operation
    private int translateOldOperation(String content) {
        // Create a new stack object and add each item in content
		Stack s = new Stack();
		String[] str = content.split(" ");

        // Iterate through content
		for (int i = 0; i < str.length; i++) {
            // If we find a operation, pop the top two elements and preform operation
            if (isOperation(str[i])) {
                // Then push result
                double val1 = Double.parseDouble(s.pop());
                double val2 = Double.parseDouble(s.pop());

                if (str[i].equals("+")) {
                    s.push(Double.toString(val1+val2));
                } else if (str[i].equals("-")) {
                    s.push(Double.toString(val1-val2));
                } else if (str[i].equals("/")) {
                    s.push(Double.toString(val1/val2));
                } else {
                    s.push(Double.toString(val1*val2));
                }

            }
            // Otherwise push the element (which should be a double)
            else {
                s.push(str[i]);
            }

		}

        double sum = Double.parseDouble(s.pop()); 
        
        // If positive value deposit
        if (sum > 0) {
            this.amount = sum;
            return 1;
        }

        // Otherwise withdraw
        else {
            // Negative will be applied in the withdraw method
            this.amount = sum * -1;
            return 2;
        }
    }


    // Determines if a passed string is a valid operation, (+, -, /, *)
    // @param str a string the represents a potential operation
    // @return true if a valid operation, false otherwise
    private boolean isOperation(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    // Overrides the base toString method
    @Override
    public String toString() {
        String operationString = "";
        if (this.operation == 0) {
            operationString = "Create";
        }
        else if (this.operation == 1) {
            operationString = "Deposit";
        }
        else if (this.operation == 2) {
            operationString = "Withdraw";
        }
        else {
            operationString = "Error";
        }
        return "Transaction [accountNumber=" + accountNumber + ", operation=" + operationString + ", amount=" + amount + "]";
    }
}
