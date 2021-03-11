//////////////////////////////////////////////////////////////////////////////
//
// Title: Binary Search Tree (RED BLACK TREE)
// Course: CS 400, Fall, and 2019
//
// Author: Sheriff Issaka
// Email: issaka@wisc.edu
// Lecturer's Name: Deb
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////////////////////////////////////////////////////


import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Class to implement a BalanceSearchTree. This is a Red-Black Tree.
 * 
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class BALST<K extends Comparable<K>, V> implements BALSTADT<K, V> {

  /**
   * @author Sheriff
   *
   * @param <K> Key
   * @param <V> value
   */
  class BSTNode<K, V> {
    // variables to be assigned with each instance of a BSTNode
    // key to be assigned
    K key;
    // associated value of key
    V value;
    // node at left of tree
    BSTNode<K, V> left;
    // node at right of tree
    BSTNode<K, V> right;
    // parent of node
    BSTNode<K, V> parent;
    // assigned to all left values
    boolean isLeftChild;
    // assigning color of node
    boolean colorIsBlack;


    /**
     * @param key
     * @param value
     * @param leftChild
     * @param rightChild
     */
    BSTNode(K key, V value, BSTNode<K, V> leftChild, BSTNode<K, V> rightChild) {
      // assigning parameter values to the class variables
      this.key = key;
      this.value = value;
      this.left = leftChild;
      this.right = rightChild;
    }

    /**
     * @param key
     * @param value
     */
    BSTNode(K key, V value) {
      // assigning parameter values with a call of just two values
      this.key = key;
      this.value = value;
      left = right = parent = null;
      colorIsBlack = false;
      isLeftChild = false;
    }

  }

  // list to store values
  private List<K> list;
  // storing root
  private BSTNode<K, V> root;
  // storing number of keys
  private int numKeys = 0;


  public BALST() {}


  /**
   * Returns the key that is in the root node of this BST. If root is null, returns null.
   * 
   * @return key found at root node, or null
   */
  @Override
  public K getKeyAtRoot() {
    // checking if root is null
    if (root == null) {
      // null is returned
      return null;
    }
    // returning the key of the root
    return root.key;
  }

  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the key that is in the left child. If the left child of the found node is null, returns
   * null.
   * 
   * @param key A key to search for
   * @return The key that is in the left child of the found key
   * 
   * @throws IllegalNullKeyException if key argument is null
   * @throws KeyNotFoundException    if key is not found in this BST
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // checking if key is null
    if (key == null) {
      // throws exception if it is
      throw new IllegalNullKeyException();
      // checks if key is contained
    } else if (contains(key)) {
      // return the left child of the given key
      return getKeyOfLeftChildOf(root, key);
    }
    // if not found, throw exception
    throw new KeyNotFoundException();
  }

  private K getKeyOfLeftChildOf(BSTNode<K, V> node, K key) throws KeyNotFoundException {
    // ???????????????????????????????????????
    // ??????????????????????????????????????
    // should i check if node is null or node.left
    // checking if node is null
    if (node.left == null)
      // return null
      return null;
    // traversing the tree
    if (key.compareTo(node.key) > 0) {
      return getKeyOfLeftChildOf(node.right, key);
    } else if (key.compareTo(node.key) < 0) {
      return getKeyOfLeftChildOf(node.left, key);
    } else {
      // returning the found node
      return node.left.key;
    }

  }


  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the key that is in the right child. If the right child of the found node is null,
   * returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the right child of the found key
   * 
   * @throws IllegalNullKeyException if key is null
   * @throws KeyNotFoundException    if key is not found in this BST
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // checking if key is null
    if (key == null) {
      throw new IllegalNullKeyException();
    } else if (contains(key)) {
      // return the right child
      return getKeyOfRightChildOf(root, key);
    }
    throw new KeyNotFoundException();
  }


  private K getKeyOfRightChildOf(BSTNode<K, V> node, K key) throws KeyNotFoundException {
    // checking node is null
    if (node == null)
      return null;
    // traversing through tree
    if (key.compareTo(node.key) > 0) {
      return getKeyOfRightChildOf(node.right, key);
    } else if (key.compareTo(node.key) < 0) {
      return getKeyOfRightChildOf(node.left, key);
    } else {
      // returning right node
      return node.right.key;
    }
  }

  /**
   * Returns the height of this BST. H is defined as the number of levels in the tree.
   * 
   * If root is null, return 0 If root is a leaf, return 1 Else return 1 + max( height(root.left),
   * height(root.right) )
   * 
   * Examples: A BST with no keys, has a height of zero (0). A BST with one key, has a height of one
   * (1). A BST with two keys, has a height of two (2). A BST with three keys, can be balanced with
   * a height of two(2) or it may be linear with a height of three (3) ... and so on for tree with
   * other heights
   * 
   * @return the number of levels that contain keys in this BINARY SEARCH TREE
   */
  @Override
  public int getHeight() {
    // checking if root is null
    if (root == null) {
      return 0;
    }
    // calling helper method to return height
    return getHeight(root);
  }

  /**
   * @param node
   * @return height
   */
  private int getHeight(BSTNode<K, V> node) {
    // checking if node is null
    if (node == null) {
      return 0;
    }
    // getting height of both sides of the tree and saving in int
    int rightHeight = getHeight(node.right) + 1;
    int leftHeight = getHeight(node.left) + 1;
    // finding the max height
    if (rightHeight > leftHeight) {
      // return right if its greater
      return rightHeight;
    }
    // else return left
    return leftHeight;
  }


  /**
   * Returns the keys of the data structure in pre-order traversal order. In the case of binary
   * search trees, the order is: V L R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in pre-order
   */
  @Override
  public List<K> getInOrderTraversal() {
    // initializing list to store values
    list = new LinkedList<K>();
    // calling private helper method
    getInOrderTraversal(root);
    // returning initialized list
    return list;
  }

  /**
   * @param node
   */
  private void getInOrderTraversal(BSTNode<K, V> node) {
    // checking the value of node
    if (node == null) {
      return;
    }
    // traversing through left
    getInOrderTraversal(node.left);
    // adding values to the list
    list.add(node.key);
    // traversing through right
    getInOrderTraversal(node.right);

  }


  /**
   * Returns the keys of the data structure in pre-order traversal order. In the case of binary
   * search trees, the order is: V L R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in pre-order
   */
  @Override
  public List<K> getPreOrderTraversal() {
    // making list
    list = new LinkedList<K>();
    // calling private helper
    getPreOrderTraversal(root);
    // returning list
    return list;
  }

  private void getPreOrderTraversal(BSTNode<K, V> node) {
    // checking node
    if (node == null) {
      return;
    }
    // adding to list
    list.add(node.key);
    // traversing through tree
    getPreOrderTraversal(node.left);
    getPreOrderTraversal(node.right);
  }

  /**
   * Returns the keys of the data structure in post-order traversal order. In the case of binary
   * search trees, the order is: L R V
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in post-order
   */
  @Override
  public List<K> getPostOrderTraversal() {
    // creating list
    list = new LinkedList<K>();
    // calling helper method
    getPostOrderTraversal(root);
    // returning list
    return list;
  }

  /**
   * private helper method to return post order list
   * 
   * @param node
   */
  private void getPostOrderTraversal(BSTNode<K, V> node) {
    // checking value of node
    if (node == null) {
      return;
    }
    // traversing through tree
    getPostOrderTraversal(node.left);
    getPostOrderTraversal(node.right);
    // before adding to list
    list.add(node.key);

  }

  /**
   * Returns the keys of the data structure in level-order traversal order.
   * 
   * The root is first in the list, then the keys found in the next level down, and so on.
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in level-order
   */
  @Override
  public List<K> getLevelOrderTraversal() {
    // list to be filled
    list = new LinkedList<K>();
    // iterating through tree given its height
    for (int i = 1; i < getHeight() + 1; i++) {
      // calling private helper method to add
      getLevelOrderTraversal(root, i);
    }
    // returning the complete list
    return list;
  }


  /**
   * private helper method to return level order list
   * 
   * @param node
   * @param i
   */
  private void getLevelOrderTraversal(BSTNode<K, V> node, int i) {
    // checking node
    if (node == null) {
      return;
    }
    if (i == 1) {
      // adding to list
      list.add(node.key);
    } else if (i > 1) {
      // traversing tree
      getLevelOrderTraversal(node.left, i - 1);
      getLevelOrderTraversal(node.right, i - 1);
    }
  }

  /**
   * Add the key,value pair to the data structure and increase the number of keys. If key is null,
   * throw IllegalNullKeyException; If key is already in data structure, throw
   * DuplicateKeyException(); Do not increase the num of keys in the structure, if key,value pair is
   * not added.
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    // checking if key is null
    if (key == null) {
      // throw exception
      throw new IllegalNullKeyException();
      // if root is null
    } else if (root == null) {
      // then make a new node (tree)
      root = new BSTNode<K, V>(key, value);
      // increase the number of keys
      numKeys++;
      // assign root to black
      root.colorIsBlack = true;
      // exit
      return;
      // if key already exists
    } else if (contains(key)) {
      // throw duplicate exception
      throw new DuplicateKeyException();
    } else {
      // call a private helper method to insert
      BSTNode<K, V> current = new BSTNode<K, V>(key, value);
      myInsert(root, current);
    }
  }

  /**
   * this method is responsible for inserting keys
   * 
   * @param parent
   * @param current
   */
  private void myInsert(BSTNode<K, V> parent, BSTNode<K, V> current) {
    // checking if the node should go to the right side
    if ((current.key).compareTo(parent.key) > 0) {
      // if the right location is found
      if (parent.right == null) {
        // assign the right to current
        parent.right = current;
        // assign the path of the child to the parent
        current.parent = parent;
        // color is black
        current.isLeftChild = false;
        // increment the number of keys
        numKeys++;
      } else {
        // make a recursive call to self
        myInsert(parent.right, current);
      }
      // checking if the node should go to the left side
    } else if ((current.key).compareTo(parent.key) < 0) {
      // if the right location is found
      if (parent.left == null) {
        // assign the left to current
        parent.left = current;
        // assign the path of the child to the parent
        current.parent = parent;
        // color is black
        current.isLeftChild = true;
        // increment keys
        numKeys++;
      } else {
        // make a recursive call to self
        myInsert(parent.left, current);
      }
    }
    // check color each time to ensure no errors in coloring
    checkColor(current);
  }

  /**
   * checks the color of leaves for violations
   * 
   * @param current
   */
  private void checkColor(BSTNode<K, V> current) {
    // checking that current node isn't null
    if (current != null) {
      // base case when we reach the root
      if (current == root) {
        return;
        // if there are two consecutive red notes
      } else if (!current.colorIsBlack && !current.parent.colorIsBlack) {
        // call method to fix the found violation
        fixViolation(current);
      }
      // check color again
      checkColor(current.parent);
    }
  }

  /**
   * fixes violations
   * 
   * @param current
   */
  private void fixViolation(BSTNode<K, V> current) {
    // if the parent is a left child
    if (current.parent.isLeftChild) {
      // if the aunt is right or null
      if (current.parent.parent.right == null || current.parent.parent.right.colorIsBlack) {
        // then there needs to be a rotation to fix the tree
        rotate(current);
        // if the aunt is red
      } else if (!current.parent.parent.right.colorIsBlack) {
        // re-coloring
        current.parent.parent.right.colorIsBlack = true;
        current.parent.parent.colorIsBlack = false;
        current.parent.colorIsBlack = true;
        // fixing the current of the root if it changed to red
        if (current.parent.parent.parent == null) {
          current.parent.parent.colorIsBlack = true;
        }
      }
      // if it's a right child and its aunt is black or null
    } else if (current.parent.parent.left == null || current.parent.parent.left.colorIsBlack) {
      // there needs to be a rotation o fix the error
      rotate(current);
      // if the aunt is red
    } else if (current.parent.parent.left != null) {
      // then re-coloring the tree
      current.parent.parent.left.colorIsBlack = true;
      current.parent.parent.colorIsBlack = false;
      current.parent.colorIsBlack = true;
      // fixing the color of the root if it happened to change to red
      if (current.parent.parent.parent == null) {
        current.parent.parent.colorIsBlack = true;
      }
    }
  }

  /**
   * this method rotates the tree to fix violations
   * 
   * @param current
   */
  private void rotate(BSTNode<K, V> current) {
    // is it's a left node
    if (current.isLeftChild) {
      // and it's parent is also left
      if (current.parent.isLeftChild) {
        // then do a right rotation
        rotateRight(current.parent.parent);
        // re-color tree
        current.colorIsBlack = false;
        current.parent.colorIsBlack = true;
        if (current.parent.right != null) {
          current.parent.right.colorIsBlack = false;
        }
      } else {
        // if it's a right left error, make a right left rotate
        rotateRightLeft(current.parent.parent);
        // recolor tree
        current.colorIsBlack = true;
        current.right.colorIsBlack = false;
        current.left.colorIsBlack = false;

      }
      // if it's parent is left and child is right
    } else if (current.parent.isLeftChild) {
      // rotate left and right to fix error
      rotateLeftRight(current.parent.parent);
      // re-color tree
      current.colorIsBlack = true;
      current.right.colorIsBlack = false;
      current.left.colorIsBlack = false;

    } else {
      // if the error is in the right side of the right sub tree, rotate left
      rotateLeft(current.parent.parent);
      // recolor tree
      current.colorIsBlack = false;
      current.parent.colorIsBlack = true;
      if (current.parent.left != null) {
        current.parent.left.colorIsBlack = false;
      }
    }
  }

  /**
   * rotate right method
   * 
   * @param grandParent
   */
  public void rotateRight(BSTNode<K, V> grandParent) {
    // store left of grand parent
    BSTNode<K, V> store = grandParent.left;
    // assign the right of the the stored value to the left of the grand parent
    grandParent.left = store.right;
    if (grandParent.left != null) {
      // getting the left of grandparent to itself
      grandParent.left.parent = grandParent;
      // its now a right sub tree
      grandParent.left.isLeftChild = false;
    }
    // if grandparent is the root
    if (grandParent.parent == null) {
      // the root now becomes the stored value
      root = store;
      store.parent = null;
    } else {
      store.parent = grandParent.parent;
      // taking stored value now takes over grand parent
      if (grandParent.isLeftChild) {
        // it is a left child now
        store.isLeftChild = true;
        // assigning it back to its parent
        store.parent.left = store;
      } else {
        // if its a right child now
        store.isLeftChild = false;
        store.parent.right = store;
      }
    }
    // making the stores right the grandparent
    store.right = grandParent;
    // is isn't a left child now
    grandParent.isLeftChild = false;
    // assigning back to itself
    grandParent.parent = store;

  }

  /**
   * rotating both right and left
   * 
   * @param grandParent
   */
  public void rotateRightLeft(BSTNode<K, V> grandParent) {
    // rotating right
    rotateRight(grandParent.right);
    // rotating left
    rotateLeft(grandParent);
  }

  /**
   * rotating left and right
   * 
   * @param grandParent
   */
  public void rotateLeftRight(BSTNode<K, V> grandParent) {
    // rotating left
    rotateLeft(grandParent.left);
    // rotating right
    rotateRight(grandParent);
  }

  /**
   * rotating left
   * 
   * @param grandParent
   */
  public void rotateLeft(BSTNode<K, V> grandParent) {
    // making a temporary node
    BSTNode<K, V> store = grandParent.right;
    // making the right of the grandparent to the left of the store
    grandParent.right = store.left;
    if (grandParent.right != null) {
      // making reassignments
      grandParent.right.parent = grandParent;
      grandParent.right.isLeftChild = false;
    }
    // if the great grandparent is a root
    if (grandParent.parent == null) {
      root = store;
      store.parent = null;
    } else {
      store.parent = grandParent.parent;
      // because store is replacing grandparent completely
      if (grandParent.isLeftChild) {
        store.isLeftChild = true;
        store.parent.left = store;
      } else {
        store.isLeftChild = false;
        store.parent.right = store;
      }
    }
    // making the left of the temporary node the grandparent
    store.left = grandParent;
    // grandparent is now a left node
    grandParent.isLeftChild = true;
    // assigning the link of the grandparent's parent back to store
    grandParent.parent = store;

  }

  /**
   * calculating the in order successor
   * 
   * @param node
   * @param key
   * @return
   */
  private K successor(BSTNode<K, V> node, K key) {
    // making a temporary node
    K temp = node.key;
    // while there is a next node
    while (node.left != null) {
      // temp should refer o the left of the given node
      temp = node.left.key;
      // node now becomes it's own left
      node = node.left;
    }
    // return the right key as successor
    return temp;
  }

  /**
   * If key is found, remove the key,value pair from the data structure and decrease num keys. If
   * key is not found, do not decrease the number of keys in the data structure. If key is null,
   * throw IllegalNullKeyException If key is not found, throw KeyNotFoundException().
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // key if is null
    if (key == null) {
      // throw exception
      throw new IllegalNullKeyException();
      // checking if key already exists
    } else if (contains(key)) {
      // assign the root to the removed tree
      root = remove(root, key);
      // decrease number of keys
      numKeys--;
      // return value to show removed successfully
      return true;
    }
    // throw exception if key exists
    throw new KeyNotFoundException();
  }

  /**
   * private helper method to remove nodes
   * 
   * @param node
   * @param key
   * @return
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   */
  private BSTNode<K, V> remove(BSTNode<K, V> node, K key)
      throws IllegalNullKeyException, KeyNotFoundException {
    // if key not found
    if (node == null) {
      // throw exception
      throw new KeyNotFoundException();
    }
    // traversing tree
    if (key.compareTo(node.key) < 0) {
      node.left = remove(node.left, key);
    } else if (key.compareTo(node.key) > 0) {
      node.right = remove(node.right, key);
    } else {
      if (node.left == null) {
        return node.right;
      } else if (node.right == null) {
        return node.left;
      }
      // finding successor to replace the removed key
      node.key = successor(node.right, node.key);
      // making recursive call to find right node
      node.right = remove(node.right, node.key);
    }
    // return the node
    return node;
  }

  /**
   * Returns the value associated with the specified key
   *
   * Does not remove key or decrease number of keys If key is null, throw IllegalNullKeyException If
   * key is not found, throw KeyNotFoundException().
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // throw exception is key is null
    if (key == null) {
      throw new IllegalNullKeyException();
    } else if (contains(key)) {
      // if key exists then search for value
      if (get(root, key) != null) {
        return get(root, key);
      }
    }
    // throw exception is key isn't present
    throw new KeyNotFoundException();

  }


  /**
   * helper method to get value
   * 
   * @param node
   * @param key
   * @return
   * @throws KeyNotFoundException
   */
  private V get(BSTNode<K, V> node, K key) throws KeyNotFoundException {
    // throw exception if key is null
    if (node == null)
      throw new KeyNotFoundException();
    // traversing tree
    if (key.compareTo(node.key) > 0) {
      return get(node.right, key);
    } else if (key.compareTo(node.key) < 0) {
      return get(node.left, key);
    } else {
      // returning the desired value
      return node.value;
    }
  }

  /**
   * Returns true if the key is in the data structure If key is null, throw IllegalNullKeyException
   * Returns false if key is not null and is not present
   */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    // checking when key is null
    if (key == null) {
      // throw an exception
      throw new IllegalNullKeyException();
    } else {
      // search for key
      return contains(root, key);
    }
  }

  private boolean contains(BSTNode<K, V> node, K key) {
    // checking if node is null
    if (node == null)
      // return false to show key isn't present
      return false;
    // traverse tree
    if (key.compareTo(node.key) > 0) {
      return contains(node.right, key);
    } else if (key.compareTo(node.key) < 0) {
      return contains(node.left, key);
    } else {
      // return true when key is found
      return true;
    }
  }


  /**
   * Returns the number of key,value pairs in the data structure
   */
  @Override
  public int numKeys() {
    // Returns the number of key,value pairs in the data structure
    return numKeys;
  }

  /**
   * Prints the tree.
   */
  @Override
  public void print() {
    // calls private helper method
    print(root, 0);
  }

  /**
   * private helper method to print tree
   * 
   * @param node
   * @param depth
   */
  private void print(BSTNode<K, V> node, int depth) {
    // base case when node is null
    if (node == null) {
      return;
    }
    // making recursive calls to print the individual values in tree
    print(node.right, depth + 1);
    for (int i = 0; i < depth - 1; i++) {
      System.out.print("|     ");
    }
    if (depth > 0)
      System.out.print("|----");
    System.out.println(node.key + " ");// + node.colorIsBlack);
    print(node.left, depth + 1);

  }


  public static void main(String[] args) {
    BALST<Integer, String> b = new BALST<>();
    try {
      b.insert(64, "34");
      b.insert(10, "df");
      b.insert(70, "34");
      b.insert(7, "56");
      b.insert(90, "12");
      b.insert(12, "90");
      b.insert(0, "12");
      b.insert(11, "34");
      b.insert(9, "43");
      b.insert(3, "34");

      b.print();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("root is " + b.getKeyAtRoot());
      b.get(64);
      // b.print();
      System.out.println("InOr: " + b.getInOrderTraversal());

      System.out.println("Post: " + b.getPostOrderTraversal());

      System.out.println("PreO: " + b.getPreOrderTraversal());

      System.out.println("LO:   " + b.getLevelOrderTraversal());


    } catch (IllegalNullKeyException e) {
      System.out.println("ill");
    } catch (DuplicateKeyException e) {
      System.out.println("dup");
    } catch (KeyNotFoundException e) {
      System.out.println("no key");
    }
  }

}
