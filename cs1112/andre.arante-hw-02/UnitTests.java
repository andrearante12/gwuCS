/*--------------------------------------------------------------------------
GWU CSCI 1112 Fall 2023
author: James Taylor

This is a set of unit tests that validate the Embedded set class which is 
intended to facilitate extracting a set of words from a multilayer embedding
in a cipher text.
--------------------------------------------------------------------------*/
class UnitTests {
    // This is a case where a global reference to a dictionary makes sense.
    // The dictionary is a read only database of sorts and we wish to have 
    // this reference available for any unit tests that need to use the 
    // whole dictionary.  We also wish to load it only once.
    static String[] dictionary;
    // For most cases, this would be a bad decision, but it is acceptable
    // here given the above behavior and requirements

    /// The main function acts as the test harness.  Any unit tests must be
    /// registered in this function.
    public static void main(String[] args) {
        // Get the dictionary.
        dictionary = WordTool.getDictionary ();

        // fail tracks whether any test has failed.
        boolean fail = false;

        // ---------------------------------------------------------------

        System.out.println("Block 1 Unit Tests");

        System.out.println("Unit Test 1.1 - Begin");
    	if( unitTest1_1() ) {
    	    System.out.println("Unit Test 1.1 - Succeeded");
    	} else {
    	    System.out.println("Unit Test 1.1 - Failed");
            fail = true;
    	}

        System.out.println("Unit Test 1.2 - Begin");
    	if( unitTest1_2() ) {
    	    System.out.println("Unit Test 1.2 - Succeeded");
    	} else {
    	    System.out.println("Unit Test 1.2 - Failed");
            fail = true;
    	}

        System.out.println("Unit Test 1.3 - Begin");
    	if( unitTest1_3() ) {
    	    System.out.println("Unit Test 1.3 - Succeeded");
    	} else {
    	    System.out.println("Unit Test 1.3 - Failed");
            fail = true;
    	}

        System.out.println("Unit Test 1.4 - Begin");
    	if( unitTest1_4() ) {
    	    System.out.println("Unit Test 1.4 - Succeeded");
    	} else {
    	    System.out.println("Unit Test 1.4 - Failed");
            fail = true;
    	}

        if( fail ) {
            System.out.println("Failed Block 1 Unit Testing");
        } else {
            System.out.println("Passed Block 1 Unit Testing");
        }

        // ---------------------------------------------------------------

        System.out.println("Block 2 Unit Tests");

        System.out.println("Unit Test 2.1 - Begin");
    	if( unitTest2_1() ) {
    	    System.out.println("Unit Test 2.1 - Succeeded");
    	} else {
    	    System.out.println("Unit Test 2.1 - Failed");
            fail = true;
    	}

        System.out.println("Unit Test 2.2 - Begin");
    	if( unitTest2_2() ) {
    	    System.out.println("Unit Test 2.2 - Succeeded");
    	} else {
    	    System.out.println("Unit Test 2.2 - Failed");
            fail = true;
    	}

        System.out.println("Unit Test 2.3 - Begin");
    	if( unitTest2_3() ) {
    	    System.out.println("Unit Test 2.3 - Succeeded");
    	} else {
    	    System.out.println("Unit Test 2.3 - Failed");
            fail = true;
    	}

        System.out.println("Unit Test 2.4 - Begin");
    	if( unitTest2_4() ) {
    	    System.out.println("Unit Test 2.4 - Succeeded");
    	} else {
    	    System.out.println("Unit Test 2.4 - Failed");
            fail = true;
    	}

        System.out.println("Unit Test 2.5 - Begin");
    	if( unitTest2_5() ) {
    	    System.out.println("Unit Test 2.5 - Succeeded");
    	} else {
    	    System.out.println("Unit Test 2.5 - Failed");
            fail = true;
    	}

        if( fail ) {
            System.out.println("Failed Block 2 Unit Testing");
        } else {
            System.out.println("Passed Block 2 Unit Testing");
        }

        // ---------------------------------------------------------------

        System.out.println("Block 3 Unit Tests");

        System.out.println("Unit Test 3.1 - Begin");
    	if( unitTest3_1() ) {
    	    System.out.println("Unit Test 3.1 - Succeeded");
    	} else {
    	    System.out.println("Unit Test 3.1 - Failed");
            fail = true;
    	}

        System.out.println("Unit Test 3.2 - Begin");
    	if( unitTest3_2() ) {
    	    System.out.println("Unit Test 3.2 - Succeeded");
    	} else {
    	    System.out.println("Unit Test 3.2 - Failed");
            fail = true;
    	}

        System.out.println("Unit Test 3.3 - Begin");
    	if( unitTest3_3() ) {
    	    System.out.println("Unit Test 3.3 - Succeeded");
    	} else {
    	    System.out.println("Unit Test 3.3 - Failed");
            fail = true;
    	}

        System.out.println("Unit Test 3.4 - Begin");
    	if( unitTest3_4() ) {
    	    System.out.println("Unit Test 3.4 - Succeeded");
    	} else {
    	    System.out.println("Unit Test 3.4 - Failed");
            fail = true;
    	}

        System.out.println("Unit Test 3.5 - Begin");
    	if( unitTest3_5() ) {
    	    System.out.println("Unit Test 3.5 - Succeeded");
    	} else {
    	    System.out.println("Unit Test 3.5 - Failed");
            fail = true;
    	}

        if( fail ) {
            System.out.println("Failed Block 3 Unit Testing");
        } else {
            System.out.println("Passed Block 3 Unit Testing");
        }

        // ---------------------------------------------------------------

    }

    //========================= Unit Tests ========================= 

    //-------------------------- Block 1 ---------------------------
    // Validates that append fails correctly when a null reference is 
    // passed as the word
    public static boolean unitTest1_1() {

        String[] result = EmbeddedSet.append(null, new String[0]);
        if( result == null ) { 
            return true;
        }
	    return false;
    }

    // Validates that append fails correctly when a null reference is 
    // passed as the list of words
    public static boolean unitTest1_2() {

        String[] result = EmbeddedSet.append(new String("").toCharArray() , null);
        if( result == null ) { 
            return true;
        }
	    return false;
    }

    // Validates that append correctly handles appending to an empty list 
    public static boolean unitTest1_3() {

        String[] inArray = new String[0];
        String str = new String("Hello");

        String[] result = EmbeddedSet.append(str.toCharArray(), inArray);
        if( result == inArray ) { 
            return false;
        }
        if( result.length != 1 ) {
            return false;
        }
        if( result[0].compareTo(str) != 0) {
            return false;
        }
	    return true;
    }

    // Validates that append correctly handles appending to a non-empty list
    public static boolean unitTest1_4() {

        String[] inArray = {"Hello"};
        String str = new String("World");

        String[] result = EmbeddedSet.append(str.toCharArray(), inArray);
        if( result == inArray ) { 
            return false;
        }
        if( result.length != 2 ) {
            return false;
        }
        if( result[0].compareTo(inArray[0]) != 0) {
            return false;
        }
        if( result[1].compareTo(str) != 0) {
            return false;
        }
	    return true;
    }

    //-------------------------- Block 2 ---------------------------

    // Validates that wordInCipher fails gracefully when word is null
    public static boolean unitTest2_1() {
        String cipher = Utilities.clean(new String("World"));

        int result = EmbeddedSet.wordInCipher(null, cipher.toCharArray());
        //System.out.println(result);
        if( result == -1 ) { 
            return true;
        }
	    return false;
    }

    // Validates that wordInCipher fails gracefully when cipher is null
    public static boolean unitTest2_2() {
        String word = Utilities.clean(new String("Hello"));

        int result = EmbeddedSet.wordInCipher(word.toCharArray(), null);
        //System.out.println(result);
        if( result == -1 ) { 
            return true;
        }
	    return false;
    }

    // Validates the case that when a word does not exist in the cipher, 
    // it is correctly not found 
    public static boolean unitTest2_3() {
        String cipher = "World";
        String word = "Hello";

        cipher = Utilities.clean(cipher);
        word = Utilities.clean(word);

        int result = EmbeddedSet.wordInCipher(word.toCharArray(), cipher.toCharArray());
        //System.out.println(result);
        if( result == -1 ) { 
            return true;
        }
	    return false;
    }

    // Validates the trivial case when the word and the cipher are the same
    public static boolean unitTest2_4() {
        String cipher = "Hello";
        String word = "Hello";

        cipher = Utilities.clean(cipher);
        word = Utilities.clean(word);

        int result = EmbeddedSet.wordInCipher(word.toCharArray(), cipher.toCharArray());
        //System.out.println(result);
        if( result == 0 ) { 
            return true;
        }
	    return false;
    }

    // Validates the case that when a word exists as embedded in the cipher,
    // it is correctly found 
    public static boolean unitTest2_5() {

        String cipher = "WoHellorld";
        String word = "Hello";

        cipher = Utilities.clean(cipher);
        word = Utilities.clean(word);

        int result = EmbeddedSet.wordInCipher(word.toCharArray(), cipher.toCharArray());
        //System.out.println(result);
        if( result == -1 ) { 
            return false;
        }
	    return true;
    }

    //-------------------------- Block 3 ---------------------------

    // Validates the case that extractWords fails gracefully when the cipher
    // is null
    public static boolean unitTest3_1() {
        String[] dict = { "HELLO", "WORLD" };

        String[] result = EmbeddedSet.extractWords( null, dict );
        if( result == null  ) { 
            return false;
        }
        if( result.length != 0  ) { 
            return false;
        }

	    return true;
    }

    // Validates the case that extractWords fails gracefully when the 
    // dictionary is null
    public static boolean unitTest3_2() {
        String cipher = "WoHellorld";

        cipher = Utilities.clean(cipher);

        String[] result = EmbeddedSet.extractWords( cipher.toCharArray(), null );
        if( result == null  ) { 
            return false;
        }
        if( result.length != 0 ) { 
            return false;
        }

	    return true;
    }

    // Validates the case where the input is trivially the same
    public static boolean unitTest3_3() {
        String cipher = "Hello";
        String[] dict = { "HELLO" };

        cipher = Utilities.clean(cipher);

        String[] result = EmbeddedSet.extractWords( cipher.toCharArray(), dict );
        //System.out.println( java.util.Arrays.toString(result) );
        if( result == null  ) { 
            return false;
        }
        if( result.length != 1  ) { 
            return false;
        }
        if( result[0].compareTo(dict[0]) != 0 ) { 
            return false;
        }
	    return true;
    }

    // Validates the case where the embedded set contains multiple words 
    // from the dictionary
    public static boolean unitTest3_4() {
        String cipher = "WoHellorld";
        String[] dict = { "HELLO", "WORLD" };

        cipher = Utilities.clean(cipher);

        String[] result = EmbeddedSet.extractWords( cipher.toCharArray(), dict );
        //System.out.println( java.util.Arrays.toString(result) );
        if( result == null  ) { 
            return false;
        }
        if( result.length != 2  ) { 
            return false;
        }
        if( result[0].compareTo(dict[0]) != 0 ) { 
            return false;
        }
        if( result[1].compareTo(dict[1]) != 0 ) { 
            return false;
        }

	    return true;
    }

    // Validates that the process correctly restarts from the beginning of
    // the dictionary
    public static boolean unitTest3_5() {
        String cipher = "HelWorldlo";
        String[] dict = { "HELLO", "WORLD" };

        cipher = Utilities.clean(cipher);

        String[] result = EmbeddedSet.extractWords( cipher.toCharArray(), dict );
        //System.out.println( java.util.Arrays.toString(result) );
        if( result == null  ) { 
            return false;
        }
        if( result.length != 2  ) { 
            return false;
        }
        if( result[0].compareTo(dict[1]) != 0 ) { 
            return false;
        }
        if( result[1].compareTo(dict[0]) != 0 ) { 
            return false;
        }

	    return true;
    }

}
