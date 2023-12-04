/*--------------------------------------------------------------------------
GWU CSCI 1112 Fall 2023
author: James Taylor

A node class specifically for binary tree structures.

Note that this class could be generalized as a basic Node class that is 
suitable to represent data for any graph type data structure. 

--------------------------------------------------------------------------*/

public class TreeNode {
  
    private final KVP data;  // The key value pair associated with this node
    public TreeNode parent;  // Reference to any parent node
    public TreeNode left, right;  // References to any child nodes
    public boolean delete;

 
    /// Parameterized constructor forces users of the class to provide both
    /// key and value on creation.
    /// @param key the unique identifier for this node.  immutable
    /// @param value the value associated with the provided key
    /// @param parent the parent node of the node to insert.  If root,
    ///               parent is null.
    public TreeNode(String key, String value, TreeNode parent) {
        data = new KVP(key, value);
        this.parent = parent;
        left = null;
        right = null;
        delete = false;
    }



    /// Accessor to retrieve the key from the key-value pair  
    /// @return the unique key stored in this node
    public String getKey() {
        return data.key;
    }
  
    /// Accessor to retrieve the value from the key-value pair
    /// @return the value stored in this node 
    public String getValue() {
        return data.asString();
    }
	
	/// Updating the value in a key-value pair
	public void updateValue(String value) {
		data.setValue(value);
	}

    public void neighbors(TreeNode parent, TreeNode left, TreeNode right) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        if( left != null ) {
            left.parent = this;
        }
        if( right != null ) {
            right.parent = this;
        }
    }
}
