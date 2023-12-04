/*
   Concrete implementation of the hash table data structure using a 
   balanced binary tree
*/

// used only in balancing utilities
import java.util.List;       
import java.util.ArrayList;

public class TreeMap implements Map {
  
    // A node that acts as a reference to the root of all nodes in the
    // tree map
    private TreeNode root;
    // A flag that is raised when we need to rebalance the TreeMap
    private boolean rebalance;

    public TreeMap( boolean rebalance ) {
        this.root = null;
        this.rebalance = rebalance;
    }

    // If the key does not exist in the map, inserts the key-value pair into the map
    // Else, locates the existing key-value pair and upadates that value
    // associated with key
    // @param key a unique identifier for the node
    // @param value a value to be associated with key
    // @param profile an integer array for tracking number of operations
    public void put( String key, String value, int[] profile ) {
      // Update value if key exists
      TreeNode node = search(key, this.root);
      if (node != null) {
        node.updateValue(value);
      }
      // Otherwise insert into TreeMap
      else {
        TreeNode newNode = new TreeNode(key, value, dive(this.root));  
      }
    }

    // Returns a reference to the bottom left most node in the tree, obtains this
    // reference by mindlessly moving down until there is no where left to go
    // @param node the current node we are looking at
    private TreeNode dive(TreeNode node) {
      // Edge case, empty tree
      if (node == null) return null;
      // If there is an empty slot at the left spot, return node
      if (node.left == null) return node;
      return dive(node.left);
    }

    // Recursively searches the tree for a given key
    // @param key to search for
    // @param node the current node we are looking at
    // @return node if it exists, null otherwise
    private TreeNode search(String key, TreeNode node) {
      // If node exists, check if is the node we are looking for
      if (node != null) {
        if (node.getKey().equals(key)) { return node; }
        // Otherwise recursively search the other two paths
        else {
          search(key, node.left);
          search(key, node.right);
        }
      }
      return null;
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
    public void clear(){
        // TODO: write this method

    }
  
  
    //----------------------------------------------------------------------
    // Utilities
    //----------------------------------------------------------------------
    /// The entry point for balancing the entire tree to maintain optimal 
    /// insert and search efficiency
    private void balance() {
        root = balance(root);
    }

    /// Rebalance a given subtree given a local root node
    /// Note: This algorithm focuses on correctness and is not the most 
    /// efficient algorithm available. Please look up different algorithms 
    /// that solve the balancing problem.
    /// @param root the root of the subtree to balance
    /// @return the new root of the subtree after balancing
    private TreeNode balance(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        //Sorts tree from given root
        populate(root, nodes);
        //Return null if root has no children
        if(nodes.size() == 0) return null;
  
        return balance(nodes, root, 0, nodes.size() - 1);
    }
    
 
    /// Recursive helper in the balancing operation to support balance.
    /// @param nodes a list of nodes
    /// @param parent the parent node of this subtree
    /// @param start the start index within the list
    /// @param end the end index within the list
    /// @return the local root after balancing is performed on the subtree
    private TreeNode balance(List<TreeNode> nodes, TreeNode parent, int start, int end) {
        int mid = (start + end) / 2;
        TreeNode node = nodes.get(mid);
        node.parent = parent;
        if(start == end){
            node.left = null;
            node.right = null;
            return node;
        }
        //Recursively balance tree on left and right children using
        //middle node as root
        if(!(mid - 1 < start)) {
            node.left = balance(nodes, node, start, mid - 1);
        } else {
            node.left = null;
        }
  
        if(!(mid + 1 > end)) {
            node.right = balance(nodes, node, mid + 1, end);
        } else {
            node.right = null;
        }
  
        return node;
    }
 
    /// Recursive helper in the balancing operation to put listitems into
    /// the tree
    /// @param the root of the subtree to balance
    /// @param the list of nodes to balance
    private void populate(TreeNode node, List<TreeNode> list) {
        if(node == null) return;
        populate(node.left, list);
        if( !node.delete ) {
            list.add(node);
        }
        populate(node.right, list);
    }
}
