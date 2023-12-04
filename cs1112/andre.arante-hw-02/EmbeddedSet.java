/*--------------------------------------------------------------------------
GWU CSCI 1112 Fall 2023
author: <your name>

This class encapsulates the logic needed to support deciphering messages
layered in a cipher
--------------------------------------------------------------------------*/

class EmbeddedSet {

    /// appends (add to the end) a word to a set of words.  
    /// @param word the word to append to the list of words
    /// @param words the list of words to be appended to
    /// @return a reference to the newly appended list of words if
    ///         successful; otherwise, returns null on failure
    public static String[] append(char[] word, String[] words) {

		// Check if word or words is null
		if (word == null) { return null; }
		if (words == null) { return null; }

        // Allocate space for a new array of size words + 1
		String[] newWords = new String[words.length+1];

		// Create a deep copy of original words array
		for (int i = 0; i < words.length; i++) {
			// Each letter will have to be manually copied to create a 
			// 		deep copy
			String temp = new String(words[i].toCharArray());
			newWords[i] = temp;
		}

		// Convert word to type String and copy word to newWords last index
		String s = new String(word);
		newWords[newWords.length-1] = s;	

        return newWords;
    }

    /// Scans cipher for a matching word.  If the word is found, returns 
    /// the position of the beginning of the word in cipher; otherwise, 
    /// returns -1
    /// @param word a character array containing the sequence to search for
    /// @param cipher a character array containing the sequence to search in
    /// @return the posiiton of the word in the cipher if it exists; 
    ///         otherwise, returns -1 to indicate failure
    public static int wordInCipher(char[] word, char[] cipher) {
		// Why do we always have to check for invalid stuff :c
		if (word == null || cipher == null) { return -1; }
			
		String wordCleaned = clean(new String(word));
		word = wordCleaned.toCharArray();

		String cipherCleaned = clean(new String(cipher));
		cipher = cipherCleaned.toCharArray();

		// Trivial Case when word = cipher
		// Important because if cipher.length = word.length for loop below
		// doesn't run
		if (word.length == cipher.length) {
			for (int i = 0; i < word.length; i++) {
				if (word[i] != cipher[i]) { return -1; }
			}
			return 0;
		}

		// Loop through cipher letter by letter
		// Each time look at word.len number of characters and search for 
		//		a match
		for (int i = 0; i < cipher.length - word.length + 1; i++) {
			int k = 0;
			for (int j = 0; j < word.length; j++) {
				// Check if any letters mismatch
				// If they do, break and move onto the next set of words
				if (word[j] != cipher[i + j]) {
					break;
				}
				k++;
				if (k == word.length) {return i;}
			}				
		}	
		return -1;
    }

    /// extracts the set of words in the order in which they are found from
    /// the cipher given a dictionary of words.  Scanning is always 
    /// performed left to right.  The cipher is reprocessed after an 
    /// extraction for additional words that may form.
    /// @param cipher the stream of characters in which to search 
    /// @param dictionary the set of words to search for in the cipher
    /// @return the set of words in the order in which they are found in the
    ///         cipher.  The set must exist, but it may be empty.  Never 
    ///         returns null.
    public static String[] extractWords( char[] cipher, String[] dictionary ) {
		if (cipher == null || dictionary == null) return new String[0];

		String cleanedCipher = clean(new String(cipher));
		cipher = cleanedCipher.toCharArray();

		String[] wordsFound = new String[0];

		wordsFound = simplify(cipher, dictionary, wordsFound);
		
        return wordsFound;  
    }


	public static String[] simplify(char[] cipher, String[] dictionary, String[] wordsFound) {
		// Loop through the dictionary, check each cipher for each word 
		for (int i = 0; i < dictionary.length; i++) {
			int start = wordInCipher(dictionary[i].toCharArray(), cipher);
			int length = dictionary[i].toCharArray().length;

			// Block below only runs if word is found
			if (start != -1) {
				// Append found word to wordsFound
				wordsFound = append(dictionary[i].toCharArray(), wordsFound);

				// Extract found word from cipher
				char[] newCipher = extractWord(cipher, start, length);

				// Recursive Case
				//	Call simplify again but using the new cipher
				wordsFound = simplify(newCipher, dictionary, wordsFound);
			}
		}
		return wordsFound;
	}

	// "extracts" word by creating a deep copy of cipher but skips over indicies
	//		in range [start, start+len]
	public static char[] extractWord(char[] cipher, int start, int len) {
    	char[] deepCopy = new char[cipher.length - len];
		// Create a counter j that represents where we are in the new copy
		// Needed because i will count ALL letters, whereas j counts only the letters
		//	we are copying
        int j = 0;
        for (int i = 0; i < cipher.length; i++) {
            if (i >= start && i < start + len) { continue; }
			deepCopy[j] = cipher[i];
			j++;
  		}  
  		return deepCopy;
	}	

	// Manually copied this over from utilities because for some reason import wasn't working
	public static String clean(String s)
    {
        return s.replaceAll("[^a-zA-Z]","").toUpperCase();
    }
}
