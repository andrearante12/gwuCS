/*--------------------------------------------------------------------------
GWU - CS1112 Data Structures and Algorithms - Fall 2023

LinkedList based implementation of a MusicCatalog (list)

authors: <Andre>, Charles Peeke
--------------------------------------------------------------------------*/
public class LinkedList implements MusicCatalog {

    // For a linked-list based list, the head pointer
    private CatalogItem head;
    // The counter to track the number of elements in the list 
    private int count;

    // Parameterless Constructor
    public LinkedList() {
        count = 0;
        head = null;
    }

    /*
	 * Adds a song item to the end of a linked list. Accounts for edge case
	 * of empty list (head == null). Otherwise, transverse LinkedList to find
	 * the tail end
	 * @param song an item to append to the end of the linked list
	*/
    public void add(Song song) {
		count++;

        // If empty list, set head = new item
		if (head == null) { 
			head = new CatalogItem(song);
			return;
		}

		// Else transverse entire linked list to find tail
		CatalogItem current = head;
		while (current.next != null) {
			// Move on to next node, if exists
			current = current.next;
		}
		
		// Set tail.next = new item
		current.next = new CatalogItem(song);
    }

    /*
	 * Removes from the head of a linked list by adjusting the head pointer to
	 * point towards item #2, and then letting Java garbage collecter pick up the old
	 * head
	 * @return the song that we removed from the LinkedList
	*/
    public Song remove() {
        // Check for empty list edge case
		if (head == null) { return null; }

		// If non empty, remove first element by changing head to point at 2nd item
		CatalogItem first = head;
		head = head.next;
		count--;
		
        return first.getSong();
    }

    /*
	 * Removes a specific song from the linkedlist. We do this by transversing through
	 * the list 
	*/
    public Song remove(Song song) {
		// Check for empty list
		if (head == null) { return null; }

		count--;

		// Also need a trailing pointer to the previous node
		CatalogItem previous = null;
		CatalogItem current = head; 

		// Transverse to end of list
		while(!current.equals(song)) {
			if(current.getSong().equals(song)) {
				// Edge case where song is head
				if (previous == null) {
					head = current.next;
				} else {
					previous.next = current.next;
				}
				break;
			}	

			// Advance both pointers
			previous = current;
			current = current.next;

		}

        return current.getSong();
    }
    
    /*
	 * Clears the list by replacing the head reference with null, allowing
	 * for the Java garbage collecter to pick up the rest of the items
	 * Also reset the count variable
	*/
    public void clear() {
		count = 0;
        head = null;
    }
    
    /* 
	 * Determines if the list is empty by checking if the head variable is a null reference
	 * @return true if LinkedList is empty
	*/
    public boolean isEmpty() {
        return head == null;
    }
    
    /*
	 * Transverses the entire linkedlist and keep a counter of the number of nodes that 
	 * you visit. Return the count.
	 * @return an int that represents the number of nodes in the array
	*/
	public int count() {

		// Edge case for empty list
		if (head == null) return 0;

		// Iterate through list, counting the number of items
		int counter = 1;
		CatalogItem current = head;


		while(current.next != null) {
			current = current.next;
			counter++;
		}

		return counter;
	}
    
    /*
	 * Transverse the entire linkedlist, keeping a running counter that allows
	 * us to compare counter and int i and return the element at index i
	 * @param i an index in the linked list to return the element at
	 * @return Song the element at index i
	*/
	public Song get(int i) {
		int counter = 0;
		CatalogItem current = head;
		
		if (head == null) return null;

		if (i == 0) return head.getSong();
	
		// If we go past end of linked list counter > i, return null
		while(counter < i) {
			if (counter == i) return current.getSong();
			counter++;
			current = current.next;
		}
		return null;
	}

    /*
	 * Existential check for if a song exists in the LinkedList
	 * @param song a song to check if it exists in the LinkedList
	 * @return boolean true if the song exists in the LinkedList
	*/
    public boolean contains(Song song) {
		CatalogItem current = head;
	
		while(current.next != null) {
			if (current.getSong().equals(song)) return true;
			current = current.next;
		}
		return false;
    }

    //--------------------------------------------------------------------
    // Utilities
    //--------------------------------------------------------------------

    /// TODO Any private helper functions go here.  They must be documented


    /// Returns a truth value indicating whether the catalog's structural
    /// integrity remains valid.  If the integrity is no longer valid,
    /// then the catalog should be invalidated and usage should not be 
    /// trusted
    /// @return true if the catalog integrity is valid; otherwise, false
    public boolean isIntegrityValid() {
        if(count < 0) {
            return false;
        }
        if(count == 0 && head == null) {
            return true;
        }
        if(count == 1 && head != null && head.next == null) {
            return true;
        }

        int n = 1;
        CatalogItem it = head;
        while(it.next != null) {
            it = it.next;
            n++;
        }

        if(n != count) {
            return false;
        }

        return true;
    }

    /// Returns a string that contains information about the list and the 
    /// contents of the list.  This is mostly useful for visual debugging 
    /// @return a string containing information about the contents of the 
    ///         catalog
    public String toString() {
        String s = "";
        s = "LinkedList::count=" + count(); 
        s += ", isEmpty=" + isEmpty(); 
        s += ", ["; 
        CatalogItem it = head;
        while(it != null) {
            if(it != head) {
                s += ", ";
            }
            s += it.getSong().getTitle();
            s += " | ";
            s += it.getSong().getYear();
            it = it.next;
        }
        s += "]";

        return s; 
    }

    /// Returns the earliest and most recent years of all the songs in the
    /// catalog and then clears the catalog of all songs
    /// @return an array of the years of the earliest and most recent songs
    public int[] publish() {
        int[] years = new int[2];
        int oldYear = Integer.MAX_VALUE;
        int newYear = Integer.MIN_VALUE;

        CatalogItem it = head;
        while(it != null) {
            int curYear = it.getSong().getYear();
            if (curYear < oldYear) {
                oldYear = curYear;
                years[0] = oldYear;
            }
            if (curYear > newYear) {
                newYear = curYear;
                years[1] = newYear;
            }
            it = it.next;
        }
        clear();
        return years;
    }

}
