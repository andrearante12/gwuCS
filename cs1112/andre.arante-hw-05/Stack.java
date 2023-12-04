/*--------------------------------------------------------------------------
GWU CSCI 1112 Fall 2023
author: Andre Arante, Charles Peeke 


Custom Implementation of a stack datastructure

--------------------------------------------------------------------------*/

public class Stack {

	// Private Instance Variables
    private StackElement top;
    private int count;

	// Initializes an empty stack
    public Stack() {
		this.count = 0;
		this.top = null;
    }
    
    // Pushs a new String s to the the top of the stack
	// @param s a string to be pushed to stack
	public void push(String s) {
		StackElement se = new StackElement(s);

		// Edge case of empty stack, addition can be done in one step
		if (this.isEmpty()) {
			this.top = se;
			this.count++;
		} 

        else {
            // Place element on the top of the stack
            StackElement previous = this.top;
            this.top = se;
            se.next = previous;
		    this.count++;
        }

	}
    
    // Pops the top element off of the stack, deleting it and returning the value
	// @param none
	// @return the value that was popped off the stack
    public String pop() {
        // Edge case where stack is empty
		if (this.isEmpty()) return null;

		// Edge case where stack is only one element
		else if (this.count == 1) {
			StackElement it = this.top;
			this.top = null;
			this.count--;
			return it.value;
		}

		// Else delete element by removing all references to it and allowing Java
		// garbage collector to pick it up
		else {
			StackElement it = this.top;
			this.top = this.top.next;
			this.count--;
			return it.value;
		}

    }
    
    // Checks if list is empty or not
	// @return a boolean value true if list is empty
    public boolean isEmpty() {
        return this.count == 0;
    }

    // @param an integer that represents the number of nodes in the Stack
    public int size() {
        return this.count;
    }

    // Overrides the base toString function to print each node value in the Stack
    @Override
    public String toString() {
        String s = "";
        StackElement current = this.top;
        while (current != null) {
            s += current + " ";
            current = current.next;
        }
        return s;
    }
}


