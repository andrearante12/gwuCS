/*--------------------------------------------------------------------------
GWU CSCI 1112 Fall 2023
author: <Andre Arante>, Charles Peeke 

A basic Queue implementation to store banking data

--------------------------------------------------------------------------*/

public class QueueElement {
    // Declare our public instance variables
    public Transaction value;
    public QueueElement next;

    // Initialize a queue element with no connections and value value
    public QueueElement(Transaction value) {
        this.value = value;
        this.next = null;
    }

    // Overwrites the base toString function to print out a queue element value
    @Override
    public String toString() {
        return "QueueElement [value=" + value + ", next=" + next + "]";
    }
}
