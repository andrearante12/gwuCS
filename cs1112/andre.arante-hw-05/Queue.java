/*--------------------------------------------------------------------------
GWU CSCI 1112 Fall 2023
author: <Andre Arante>, Charles Peeke 


A basic implementation of a queue to store banking information

--------------------------------------------------------------------------*/

public class Queue {
    // Declare private instance variables
    private QueueElement front;
    private QueueElement back;
    private int count;

    // Initializes an empty queue
	public Queue() {
		this.front = this.back = null;
		this.count = 0;
	}

    // Adds a transaction to the tail of the queue
	// @param t an object of type Transaction that will be appended to the queue
    public void enqueue(Transaction t) {

		QueueElement qe = new QueueElement(t);		

		// Edge case - empty queue
		if (this.isEmpty()) {
			this.front = this.back = qe;
			this.count++;
		}

        // Edge case - one element in queue
        else if (this.count == 1) {
            this.front.next = qe;
            this.back = qe;
            this.count++;
        }

        else {    
		    // Else adjust tail pointer
            QueueElement it = this.front.next;
            while (it.next != null) {
                it = it.next;
            }
            it.next = qe;
            this.back = qe;
		    this.count++;
        }
    }

    // Removes a transaction fron the head of the queue. Return null if empty
	// @return Transaction that was dequeued
    public Transaction dequeue() {
        // Edge case that the queue is empty
		if (this.front == null) {
			return null;
		}

		// Adjust pointers
		else {
			QueueElement it = this.front;
			this.front = this.front.next;
			this.count--;
			return it.value;
		}
    }

    // Returns true if queue is empty, false otherwise
    public boolean isEmpty() {
        return count == 0;
    }

    // Returns the tracking variable that stores how big the list is
    public int size() {
        return count;
    }

    // Overides the base toString method to print out elements in the queue
    @Override
    public String toString() {
        String s = "";
        QueueElement current = this.front;
        while (current != null) {
            s += current.value.toString() + ";";
            current = current.next;
        }
        return s;
    }

}
