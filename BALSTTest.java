//////////////////////////////////////////////////////////////////////////////
//
// Title: Binary Search Tree (RED BLACK TREE )
// Course: CS 400, Fall, and 2019
//
// Author: Mohammed Sheriff Issaka
// Email: issaka@wisc.edu
// Lecturer's Name: Deb
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////////////////////////////////////////////////////


import static org.junit.Assert.fail;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.BeforeAll;


// @SuppressWarnings("rawtypes")
public class BALSTTest {

  BALST<String, String> balst1;
  BALST<Integer, String> balst2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    balst1 = createInstance();
    balst2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    balst1 = null;
    balst2 = null;
  }

  protected BALST<String, String> createInstance() {
    return new BALST<String, String>();
  }

  protected BALST<Integer, String> createInstance2() {
    return new BALST<Integer, String>();
  }

  /**
   * Insert three values in sorted order and then check the root, left, and right keys to see if
   * rebalancing occurred.
   */
  @Test
  void testBALST_001_insert_sorted_order_simple() {
    try {
      balst2.insert(10, "10");
      if (!balst2.getKeyAtRoot().equals(10))
        fail("avl insert at root does not work");

      balst2.insert(20, "20");
      if (!balst2.getKeyOfRightChildOf(10).equals(20))
        fail("avl insert to right child of root does not work");

      balst2.insert(30, "30");
      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * Insert three values in reverse sorted order and then check the root, left, and right keys to
   * see if rebalancing occurred in the other direction.
   */
  @Test
  void testBALST_002_insert_reversed_sorted_order_simple() {

    try {
      balst2.insert(30, "10");
      if (!balst2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      balst2.insert(20, "20");
      if (!balst2.getKeyOfLeftChildOf(30).equals(20))
        fail("avl insert to right child of root does not work");

      balst2.insert(10, "30");
      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }

  }

  /**
   * Insert three values so that a right-left rotation is needed to fix the balance.
   * 
   * Example: 10-30-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testBALST_003_insert_smallest_largest_middle_order_simple() {
    try {
      balst2.insert(20, "10");
      if (!balst2.getKeyAtRoot().equals(20))
        fail("avl insert at root does not work");
      balst2.insert(30, "20");
      balst2.insert(25, "30");
      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(25))
        fail("avl rotate does not work");
      if (!balst2.getKeyAtRoot().equals(25))
        fail("avl insert at root does not work");

      if (!balst2.getKeyOfLeftChildOf(25).equals(20))
        fail("avl insert to right child of root does not work");

      if (!balst2.getKeyOfRightChildOf(25).equals(30))
        fail("avl insert to right child of root does not work");

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }


  }

  /**
   * Insert three values so that a left-right rotation is needed to fix the balance.
   * 
   * Example: 30-10-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testBALST_004_insert_largest_smallest_middle_order_simple() {

    try {
      balst2.insert(30, "10");
      if (!balst2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");
      balst2.insert(10, "20");
      balst2.insert(20, "30");
      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");
      if (!balst2.getKeyAtRoot().equals(20))
        fail("avl insert at root does not work");

      if (!balst2.getKeyOfLeftChildOf(20).equals(10))
        fail("avl insert to right child of root does not work");

      if (!balst2.getKeyOfRightChildOf(20).equals(30))
        fail("avl insert to right child of root does not work");

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }


  }


  /**
   * testing if the program successfully makes and right and a left rotation
   */
  @Test
  void testBALST_005_right_and_left_rotations() {

    try {
      // inserting three keys that should cause a right rotation
      balst2.insert(10, "10");
      balst2.insert(5, "20");
      balst2.insert(3, "30");
      // checking if the left key is 3
      if (!balst2.getKeyOfLeftChildOf(5).equals(3)) {
        fail("left key of 5 is wrong");
      } // checking if the right key is 10
      if (!balst2.getKeyOfRightChildOf(5).equals(10)) {
        fail("rightr key of 10 is wrong");
      }
      // catching exceptions if any
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }

  }

  /**
   * testing the height of the tree after multiple insertions
   */
  @Test
  void testBALST_006_height() {
    try {
      // inserting some values
      balst2.insert(1, "1");
      balst2.insert(2, "2");
      balst2.insert(3, "3");
      balst2.insert(4, "4");
      balst2.insert(5, "5");
      balst2.insert(6, "6");
      balst2.insert(7, "7");
      balst2.insert(8, "8");
      balst2.insert(9, "9");
      balst2.insert(0, "0");
      // checking height is 4
      if (balst2.getHeight() != 4) {
        fail("wrong height");
      }
      // catching exceptions that may arise
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * testing if the get method works correctly
   */
  @Test
  void testBALST_007_get() {
    try {
      // inserting values
      balst2.insert(1, "1");
      balst2.insert(2, "2");
      balst2.insert(3, "3");
      balst2.insert(4, "4");
      balst2.insert(5, "5");
      balst2.insert(6, "6");
      balst2.insert(7, "7");
      balst2.insert(8, "8");
      balst2.insert(9, "9");
      balst2.insert(0, "0");
      // checking if right value is got
      if (!balst2.get(8).equals("8")) {
        fail("wrong get method");
      }
      // catching exceptions
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * checking the left and right keys after multiple insertions
   */
  @Test
  void testBALST_008_check_left_and_right_after_multi_insert() {
    try {
      // inserting nodes
      balst2.insert(1, "1");
      balst2.insert(2, "2");
      balst2.insert(3, "3");
      balst2.insert(4, "4");
      balst2.insert(5, "5");
      balst2.insert(6, "6");
      balst2.insert(7, "7");
      balst2.insert(8, "8");
      balst2.insert(9, "9");
      balst2.insert(0, "0");
      // checking left and right children for multiple cases
      if (!balst2.getKeyOfLeftChildOf(1).equals(0)) {
        fail("left child is error");
      }
      if (!balst2.getKeyOfLeftChildOf(8).equals(7)) {
        fail("left child is error");
      }
      if (!balst2.getKeyOfRightChildOf(8).equals(9)) {
        fail("right child is error");
      }
      if (!balst2.getKeyOfRightChildOf(2).equals(3)) {
        fail("right child is error");
      }
      // catching exceptions if any
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * checking if the values are returned in order
   */
  @Test
  void testBALST_009_check_in_order_traversal() {
    try {
      // inserting nodes
      balst2.insert(1, "1");
      balst2.insert(2, "2");
      balst2.insert(3, "3");
      balst2.insert(4, "4");
      balst2.insert(5, "5");
      balst2.insert(6, "6");
      balst2.insert(7, "7");
      balst2.insert(8, "8");
      balst2.insert(9, "9");
      // comparing expected output to output
      for (int i = 1; i < 10; i++) {
        if (Integer.parseInt(balst2.get(i)) != i) {
          fail("list isn't in order");
        }
      }
      // catching exceptions that may arise
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * checking if remove works
   */
  @Test
  void testBALST_010_try_remove_root() {

    try {// inserting nodes
      balst1.insert("20", "20");
      balst1.insert("30", "30");
      balst1.insert("40", "40");
      // removing one
      balst1.remove("40");
      // check if it exists
      if (balst1.contains("40")) {
        fail("didn't successfully delete key");
      }
      // catching exceptions when they arise
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * checking rotate left and right simultaneously
   */
  @Test
  void testBALST_011_rotate_left_right() {
    try {
      // inserting nodes
      balst2.insert(73, "12");
      balst2.insert(64, "34");
      balst2.insert(72, "56");
      // getting and comparing their side children
      if (!balst2.getKeyOfLeftChildOf(72).equals(64)
          && !balst2.getKeyOfRightChildOf(72).equals(73)) {
        fail("left right rotation failed");
      }


    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * checking rotate left and right simultaneously
   */
  @Test
  void testBALST_012_rotate_right_left() {
    try {
      // inserting values
      balst2.insert(50, "12");
      balst2.insert(90, "34");
      balst2.insert(70, "56");
      // getting and comparing their side children
      if (!balst2.getKeyOfLeftChildOf(70).equals(50)
          && !balst2.getKeyOfRightChildOf(70).equals(90)) {
        fail("left right rotation failed");
      }

      // catching exceptions if any
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }
}
