/*--------------------------------------------------------------------------
GWU CSCI 1112 Fall 2023
author: <Andre Arante>, Charles Peeke 


A base stackelement to encapsulate the values we want to place on the stack

--------------------------------------------------------------------------*/

public class StackElement {
    // Declare the private instance variables
    public String value;
    public StackElement next;

    // Initializes a basic StackElement with no connections and a value value
    public StackElement(String value) {
        this.value = value;
        this.next = null;
    }

    /// TODO:
    @Override
    public String toString() {
        return "StackElement [value=" + value + ", next=" + next + "]";
    }
}
