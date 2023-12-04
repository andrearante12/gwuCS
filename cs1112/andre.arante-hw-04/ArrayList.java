/*--------------------------------------------------------------------------
GWU - CS1112 Data Structures and Algorithms - Fall 2023

ArrayList based implementation of a MusicCatalog (List)

authors: <Andre Arante>, Charles Peeke
--------------------------------------------------------------------------*/
public class ArrayList implements MusicCatalog {
    // For an array-based list, the array itself
    private CatalogItem[] data;
    // The counter to track the number of elements in the list
    private int count;

    // Parameterless Constructor
    public ArrayList() {
        count = 0;
        data = new CatalogItem[2];
    }
   
    /**
	 *	Appends an song to the end of an array-based implementation of Linked List (ArrayList)
	 *	@param song Song item to be appended to the end of the list
	**/
    public void add(Song song) {
        // Check if we need to allocate more space in memory
		if (count >= data.length) {
			// Allocate 2x the space of original 
			int n = count*2;
			CatalogItem[] newData = new CatalogItem[n];

			// Shallow copy of data
			for (int i = 0; i < data.length; i++) {
				newData[i] = data[i];	
			}
			// Encapsulated song in a catalog item 
			CatalogItem c = new CatalogItem(song);
			newData[count++] = c;
			data = newData;	
		} else {
			data[count++] = new CatalogItem(song);
		}
    }

    /*
	 * Removes from start of linked list 
	 * 
	*/
    public Song remove() {
        // Create a new array of same size of data
		CatalogItem[] newData = new CatalogItem[count];

		// Copy all items from original over to the new array, starting from
		// 	index 1 to omit the first element
		for (int i = 1; i < count; i++) {
			newData[i-1] = data[i];
		} 

		// 
		CatalogItem c = data[0];
		data = newData;
		count--;
		return c.getSong();

    }

    /*
	 * Removes the first element in the array by creating a copy of original array
	 * From the subarray  [1, len(data)-1]
	 * @param song a song element to be encapsulated in CatalogItem and appened to array
	*/
    public Song remove(Song song) {
        // Transverse through the list, and stop when we find the item
		boolean found = false;
		int i = 0;

		while (!found) {
			if (data[i].getSong().equals(song)) { 
				found = !found;
				break;
			}	
			i++;
		}

		CatalogItem[] newData = new CatalogItem[count];
		// Iterate through list
		for (int j = 0; j < count; j++) {
			// Skip over the index of the item we wanna delete i
			if (j != i) {
				// If we have passed index i, we gotta copy elements from j-1 from data 
				if (j > i) { newData[j] = data[j-1]; }
				else newData[j] = data[j];
			}	
		}
	
		CatalogItem c = data[i];
		data = newData;

		count--;

        return c.getSong();
    }
    
    /*
	 * Reset the array by overwritting data with an empty array of size 2
	 * Also reset the count to 0 and garbage collect all element
	*/
    public void clear() {
        data = new CatalogItem[2];
		count = 0;
    }
    
    /*
	 * Returns a boolean value that represents whether or not the array is empty by transversing
	 * the entire array, and returning false if we find a non null value
	 * @return boolean true if array is empty, false otherwise
	*/
    public boolean isEmpty() {
		for (int i = 0; i < count; i++) {
			if (data[i] != null) return false;
		}
		return true;
    }
    
    /*
	 * Returns the size of the array which is stored as a local, private variable
	 * @return count represents the size of the array
	*/
    public int count() {
        return count;
    }
    
    /*
	 * Retrieves a song at a given index i. We must confirm that i is within
	 * the bounds of the array
	 * @param i the index i that we need to return the element i from
	*/
    public Song get(int i) {
		if (i < 0 || i >= count) return null;

		
        return data[i].getSong();
    }

    /*
	 * Retrieves a song by transversing through the array, and return true 
	 * if we find it
	 * @param song a song item to search for in the array
	 * @return boolean true if item exists in the array, false otherwise
	*/
    public boolean contains(Song song) {
		// Transverse the array	
		for (int i = 0; i < count; i++) {
			if (data[i].getSong().equals(song)) { return true; }
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
        if(data == null) {
            return false;
        }
        if(count > data.length) {
            return false;
        }
        for(int i = 0; i < count; i++) {
            if(data[i] == null) {
                return false;
            }
        }

        return true;
    }

    /// Returns a string that contains information about the list and the 
    /// contents of the list.  This is mostly useful for visual debugging 
    /// @return a string containing information about the contents of the 
    ///         catalog
    public String toString() {
        String s = "";
        s = "ArrayList::allocated=" + data.length;
        s += ", count=" + count(); 
        s += ", isEmpty=" + isEmpty(); 
        s += ", ["; 
        for(int i = 0; i < count; i++) {
            if(i > 0) {
                s += ", ";
            }
            s += data[i].getSong().getTitle();
            s += " | ";
            s += data[i].getSong().getYear();
        }
        s += "]";
        return s;
    }

    /// Returns the earliest and most recent years of all the songs in the
    /// catalog and then clears the catalog of all songs
    /// @return an array of the years of the earliest and most recent songs
    public int[] publish() {
        int oldYear = Integer.MAX_VALUE;
        int newYear = Integer.MIN_VALUE;

        for(int i = 0; i < count; i++) {
            int curYear = data[i].getSong().getYear();
            if (curYear < oldYear) oldYear = curYear;
            if (curYear > newYear) newYear = curYear;
        }
        clear();
        return new int[] { oldYear, newYear };
    }

}
