/*--------------------------------------------------------------------------
GWU CSCI 1112 Fall 2023
author: James Taylor

TODO - Summarize this class

--------------------------------------------------------------------------*/

public class HashMap implements Map {
  
    // TODO - Document all instance variables
    private final ListNode[] buckets;
  
    public HashMap(int length) {
        this.buckets = new ListNode[length];
    }
  
    /// TODO - Document this method
    public void put(String key, String value, int[] profile) {
        // TODO: write this method

    }
  
    /// TODO - Document this method
    public String get(String key, int[] profile) {
        // TODO: write this method

        return null;
    }

    /// TODO - Document this method
    public boolean delete(String key, int[] profile) {
        // TODO: write this method

        return false;
    }
  
    /// TODO - Document this method
    public void clear() {
        // TODO: write this method

    }

    //----------------------------------------------------------------------
    // Utilities
    //----------------------------------------------------------------------
    /// Hash function.  DO NOT MODIFY
    /// @param key string input to be hashed
    /// @return index location of where an object should be put in the table
    private int hash(String key) {
        return Math.abs(key.hashCode() % buckets.length);
    }
}
