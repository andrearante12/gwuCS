/*--------------------------------------------------------------------------
GWU CSCI 1112 Fall 2023
author: James Taylor

This class is a demo of a simple game using the rules provided by the 
EmbeddedSet class.  It allows a user to input a ciphered stream and the 
program will decipher all the words embedded in the ciphered input stream
based on the internal dictionary.  Words not in the dictionary cannot be 
identified
--------------------------------------------------------------------------*/

import java.util.Scanner;

class DeCipher {

    /// This is the entry point for the basic example program
    public static void main( String[] args ) {

        String[] dictionary = WordTool.getDictionary ();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cipher: ");
        String cipher = scanner.nextLine();

        cipher = Utilities.clean(cipher);

        String[] words = EmbeddedSet.extractWords(cipher.toCharArray(), dictionary);

        System.out.println("Found the following words in the cipher...");
        System.out.println( java.util.Arrays.toString(words) );
    }
}
