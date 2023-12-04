/*--------------------------------------------------------------------------
GWU CSCI 1112 Fall 2023
author: James Taylor

A generalized/abstract map interface which defines the methods needed to
satisfy the map model

Note that descriptions are intentionally omitted here to force students to
perform the documentation exercise for both binary tree and hash table 
classes

--------------------------------------------------------------------------*/

public interface Map {

    public void put(String key, String value, int[] profile);

    public String get(String key, int[] profile);

    public boolean delete(String key, int[] profile);

    public void clear();
}
