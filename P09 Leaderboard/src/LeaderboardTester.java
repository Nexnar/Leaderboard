//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Leaderboard
// Course: CS 300 Fall 2024
//
// Author: Tristin Yun
// Email: tyun7@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: Hobbes LeGault; helped through their responses on Piazza @2279
// (for remove implementation) and @2280 (for iterator implementation)
// Online Sources: None other than aforementioned Piazza
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class contains methods to test the Leaderboard class
 */
public class LeaderboardTester {

  /////////////////////////////////////////// COMPARE TO ///////////////////////////////////////////

  /**
   * A method that outputs whether the various "compare to" tester methods all pass
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testPlayerCompareTo() {
    boolean test1 = testCompareToDiffScore();
    boolean test2 = testCompareToSameScoreDiffName();
    boolean test3 = testCompareToEqual();
    if (!test1)
      System.out.print("diffScore FAIL ");
    if (!test2)
      System.out.print("diffName FAIL ");
    if (!test3)
      System.out.print("equals FAIL ");
    return test1 && test2 && test3;
  }

  /**
   * Tester method that compares players with different scores and verify the results
   * 
   * @return true if all compareTo is valid for this test, false otherwise
   */
  private static boolean testCompareToDiffScore() {
    Player p1 = new Player("John", 20);
    Player p2 = new Player("Adam", 10);

    int compareVal = p1.compareTo(p2); // the difference should be 10, which is > 0
    if (compareVal <= 0)
      return false;

    return true;
  }

  /**
   * Tester method that compares players with the same score but different names and verify the
   * results
   * 
   * @return true if all compareTo is valid for this test, false otherwise
   */
  private static boolean testCompareToSameScoreDiffName() {
    Player p1 = new Player("John", 10);
    Player p2 = new Player("Adam", 10);

    int compareVal = p1.compareTo(p2);
    if (compareVal <= 0) // John is lexigraphically greater than Adam
      return false;

    return true;
  }

  /**
   * Tester method that compares players with the same score and name and verify the results
   * 
   * @return true if all compareTo is valid for this test, false otherwise
   */
  private static boolean testCompareToEqual() {
    Player p1 = new Player("Adam", 10);
    Player p2 = new Player("Adam", 10);

    int compareVal = p1.compareTo(p2);
    if (compareVal != 0)
      return false;

    return true;
  }

  ///////////////////////////////////////// LOOKUP: NAME /////////////////////////////////////////

  /**
   * A method that outputs whether the various "lookup" tester methods all pass
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testNameLookup() {
    boolean test1 = testLookupRoot();
    boolean test2 = testLookupLeft();
    boolean test3 = testLookupRight();
    boolean test4 = testLookupNotPresent();
    if (!test1)
      System.out.print("lookupRoot FAIL ");
    if (!test2)
      System.out.print("lookupLeft FAIL ");
    if (!test3)
      System.out.print("lookupRight FAIL ");
    if (!test4)
      System.out.print("lookupNotPresent FAIL");
    return test1 && test2 && test3 && test4;
  }

  // HINT: for these testers, add one Player at the root and then build the rest of the tree
  // manually
  // using the reference returned by getRoot(), so that you are not relying on the correctness of
  // the addPlayer() method.

  /**
   * Tester method that tests whether the lookup method can lookup the player at the root node
   * 
   * @return true if works as expected, false otherwise
   */
  private static boolean testLookupRoot() {
    Leaderboard l1 = new Leaderboard();
    Player p1 = new Player("Mike", 10);
    l1.addPlayer(p1);
    if (l1.lookup("Mike") != p1)
      return false;
    return true;
  }

  /**
   * Tester method that tests whether the lookup method can lookup the player in the left subtree
   * 
   * @return true if works as expected, false otherwise
   */
  private static boolean testLookupLeft() {
    Leaderboard l1 = new Leaderboard();
    Player p1 = new Player("Mike", 10);
    l1.addPlayer(p1);

    Player p2 = new Player("John", 10);
    Player p3 = new Player("Adam", 10);
    l1.getRoot().setLeft(new BSTNode<Player>(p2));
    l1.getRoot().setRight(new BSTNode<Player>(p3));
    // M
    // J A

    if (l1.lookup("John") != p2)
      return false;
    return true;
  }

  /**
   * Tester method that tests whether the lookup method can lookup the player in the right subtree
   * 
   * @return true if works as expected, false otherwise
   */
  private static boolean testLookupRight() {
    Leaderboard l1 = new Leaderboard();
    Player p1 = new Player("Mike", 10);
    l1.addPlayer(p1);

    Player p2 = new Player("John", 10);
    Player p3 = new Player("Adam", 10);
    l1.getRoot().setLeft(new BSTNode<Player>(p2));
    l1.getRoot().setRight(new BSTNode<Player>(p3));
    // M
    // J A

    if (l1.lookup("Adam") != p3)
      return false;
    return true;
  }

  /**
   * Tester method that tests whether the lookup method will return null when finding a player that
   * does not exist
   * 
   * @return true if works as expected, false otherwise
   */
  private static boolean testLookupNotPresent() {
    // TODO: look up the name of a player who is not present in the tree (NOT on an empty tree!)
    Leaderboard l1 = new Leaderboard();
    Player p1 = new Player("Mike", 10);
    l1.addPlayer(p1);

    Player p2 = new Player("John", 10);
    Player p3 = new Player("Adam", 10);
    l1.getRoot().setLeft(new BSTNode<Player>(p2));
    l1.getRoot().setRight(new BSTNode<Player>(p3));
    // M
    // J A

    if (l1.lookup("Gordon") != null)
      return false;
    return true;
  }

  //////////////////////////////////////////// ADD ////////////////////////////////////////////

  /**
   * A method that outputs whether the various "add" tester methods all pass
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAdd() {
    boolean test1 = testAddPlayerEmpty();
    boolean test2 = testAddPlayer();
    boolean test3 = testAddPlayerDuplicate();
    if (!test1)
      System.out.print("addEmpty FAIL ");
    if (!test2)
      System.out.print("addPlayer FAIL ");
    if (!test3)
      System.out.print("addDuplicate FAIL ");
    return test1 && test2 && test3;
  }

  /**
   * Tester method that tests whether the addPlayer method can add the root node to an empty
   * leaderboard
   * 
   * @return true if works as expected, false otherwise
   */
  private static boolean testAddPlayerEmpty() {
    Leaderboard l1 = new Leaderboard();
    Player p1 = new Player("Adam", 5);

    if (!l1.addPlayer(p1))
      return false;

    if (l1.getRoot().getData() != p1)
      return false;
    if (l1.count() != 1)
      return false;

    return true;
  }

  /**
   * Tester method that verifies that addPlayer() correctly adds a player to a non-empty BST // Each
   * time you add a player, make sure that: // (1) the method returns true // (2) the size and count
   * have increased correctly // (3) the output of prettyPrint() is the tree that you expect (you
   * may do this one visually)
   * 
   * @return true if works as expected, false otherwise
   */
  private static boolean testAddPlayer() {

    Leaderboard l1 = new Leaderboard();
    Player p1 = new Player("Adam", 5);
    Player p2 = new Player("George", 5);
    Player p3 = new Player("Sam", 5);
    Player p4 = new Player("Abraham", 5);
    Player[] playerList = new Player[] {p1, p2, p3, p4};

    for (int i = 0; i < playerList.length; i++) {

      if (!l1.addPlayer(playerList[i]))
        return false;

      if (l1.size() != i + 1) // size should be i + 1
        return false;

      if (l1.count() != l1.size()) // size should be i + 1
        return false;

      System.out.println(l1.prettyPrint());
    }

    if (l1.countHelper(l1.getRoot().getLeft()) != 1)
      return false; // the left side should have one node
    if (l1.countHelper(l1.getRoot().getRight()) != 2)
      return false; // the right side should have two nodes

    return true;
  }

  /**
   * Tester method that verifies that adding a duplicate player returns false, does not modify the
   * BST, and does not cause an exception
   * 
   * @return true if works as expected, false otherwise
   */
  private static boolean testAddPlayerDuplicate() {

    Leaderboard l1 = new Leaderboard();
    Player p1 = new Player("Adam", 5);
    Player p2 = new Player("George", 5);
    Player p3 = new Player("Sam", 5);
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);

    try { // this should not throw any exceptions
      if (l1.addPlayer(new Player("George", 5)) != false)
        return false;
    } catch (Exception e) {
      return false;
    }

    if (l1.size() != 3)
      return false;

    return true;
  }

  //////////////////////////////////////////// REMOVE ////////////////////////////////////////////

  /**
   * A method that outputs whether the various "remove" tester methods all pass
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemove() {
    boolean test1 = testRemoveLeaf();
    boolean test2 = testRemoveOneChild();
    boolean test3 = testRemoveTwoChildren();
    boolean test4 = testRemoveNotInTree();
    if (!test1)
      System.out.print("remove FAIL ");
    if (!test2)
      System.out.print("removeOneChild FAIL ");
    if (!test3)
      System.out.print("removeTwoChildren FAIL ");
    if (!test4)
      System.out.print("removeNotInTree FAIL ");
    return test1 && test2 && test3 && test4;
  }

  /**
   * Tester method that verifies that removePlayer() correctly removes a leaf node from the tree
   * 
   * @return true if works as expected, false otherwise
   */
  private static boolean testRemoveLeaf() {

    Leaderboard l1 = new Leaderboard();
    Player p1 = new Player("Bentley", 5);
    Player p2 = new Player("Aiden", 5);
    Player p3 = new Player("Cam", 5);
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);

    if (!l1.removePlayer(p3))
      return false;
    if (l1.size() != 2)
      return false;

    return true;
  }

  /**
   * Tester method that verifies that removePlayer() correctly removes a player with ONE child //
   * Each time you remove a player, make sure that: // (1) the method returns true // (2) the size
   * and count have decreased correctly // (3) the output of prettyPrint() is the tree that you
   * expect (you may do this one visually)
   * 
   * @return true if works as expected, false otherwise
   */
  private static boolean testRemoveOneChild() {

    Leaderboard l1 = new Leaderboard();
    Player p1 = new Player("Bentley", 5);
    Player p2 = new Player("Aiden", 5);
    Player p3 = new Player("Cam", 5);
    Player c1 = new Player("Baron", 5);
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);
    l1.addPlayer(c1);

    if (!l1.removePlayer(p2))
      return false;
    if (l1.size() != 3)
      return false;

    System.out.println(l1.prettyPrint());

    return true;
  }

  /**
   * Tester method that verifies that removePlayer() correctly removes a player with TWO children //
   * Each time you remove a player, make sure that: // (1) the method returns true // (2) the size
   * and count have decreased correctly // (3) the output of prettyPrint() is the tree that you
   * expect (you may do this one visually)
   * 
   * @return true if everything works as expected, false otherwise
   */
  private static boolean testRemoveTwoChildren() {

    Leaderboard l1 = new Leaderboard();
    Player p1 = new Player("Bentley", 5);
    Player p2 = new Player("Aiden", 5);
    Player p3 = new Player("Cam", 5);
    Player c1 = new Player("Cristiano", 5);
    Player c2 = new Player("Cristian", 5);
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);
    l1.addPlayer(c1);
    l1.addPlayer(c2);

    System.out.println(l1.prettyPrint());

    if (!l1.removePlayer(p3))
      return false;
    if (l1.size() != 4)
      return false;
    if (l1.lookup("Cristiano") == null)
      return false; // makes sure the entire subtree wasnt removed
    if (l1.lookup("Cristian") == null)
      return false;

    System.out.println(l1.prettyPrint());

    return true;
  }

  /**
   * Tester method that verifies that removing a player not in the tree returns false, does not
   * modify the BST, and does not cause an exception
   * 
   * @return true if everything works as expected, false otherwise
   */
  private static boolean testRemoveNotInTree() {

    Leaderboard l1 = new Leaderboard();
    Player p1 = new Player("Bentley", 5);
    Player p2 = new Player("Aiden", 5);
    Player p3 = new Player("Cam", 5);
    Player c1 = new Player("Cristiano", 5);
    Player c2 = new Player("Cristian", 5);
    Player c3 = new Player("Zane", 4);
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);
    l1.addPlayer(c1);
    l1.addPlayer(c2);
    l1.addPlayer(c3);

    if (l1.removePlayer(new Player("Cam", 4)))
      return false;
    if (l1.size() != 6) // size should be intact; still 6 players
      return false;

    System.out.println(l1.prettyPrint());

    return true;
  }

  //////////////////////////////////////////// GET NEXT ////////////////////////////////////////////

  /**
   * A method that outputs whether the various "get next" tester methods all pass
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetNext() {
    boolean test1 = testGetNextAfterRoot();
    boolean test2 = testGetNextAfterLeftSubtree();
    boolean test3 = testGetNextAfterRightSubtree();
    if (!test1)
      System.out.print("afterRoot FAIL ");
    if (!test2)
      System.out.print("afterLeft FAIL ");
    if (!test3)
      System.out.print("afterRight FAIL ");
    return test1 && test2 && test3;
  }

  /**
   * Tester method that verifies that next() returns the correct Player when passed the Player in
   * the root node
   * 
   * @return true if everything works as expected, false otherwise
   */
  private static boolean testGetNextAfterRoot() {

    Leaderboard l1 = new Leaderboard();
    Player p1 = new Player("Bentley", 5);
    Player p2 = new Player("Aiden", 5);
    Player p3 = new Player("Cam", 5);
    Player p4 = new Player("Brett", 5);
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);
    l1.addPlayer(p4);
    System.out.print(l1.prettyPrint());
    if (l1.next(l1.getRoot().getData()) != p4)
      return false;
    return true;
  }

  /**
   * Tester method that verifies that next() returns the correct Player when the correct value is in
   * the left subtree of the leaderboard
   * 
   * @return true if everything works as expected, false otherwise
   */
  private static boolean testGetNextAfterLeftSubtree() {

    Leaderboard l1 = new Leaderboard();
    Player p1 = new Player("Bentley", 5);
    Player p2 = new Player("Aiden", 5);
    Player p3 = new Player("Cam", 5);

    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);

    System.out.print(l1.prettyPrint());
    if (l1.next(p2) != p1)
      return false;
    return true;
  }

  /**
   * Tester method that verifies that next() returns the correct Player when the correct value is in
   * the right subtree of the leaderboard
   * 
   * @return true if everything works as expected, false otherwise
   */
  private static boolean testGetNextAfterRightSubtree() {

    Leaderboard l1 = new Leaderboard();
    Player p1 = new Player("Bentley", 5);
    Player p2 = new Player("Aiden", 5);
    Player p3 = new Player("Cam", 5);
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);
    System.out.print(l1.prettyPrint());
    if (l1.next(p3) != null)
      return false;
    return true;
  }

  //////////////////////////////////////////// MAIN ////////////////////////////////////////////

  /**
   * A method that outputs whether the all tester methods of all categories pass
   */
  public static void main(String[] args) {
    System.out.print("Player compareTo(): ");
    System.out.println(testPlayerCompareTo() ? "PASS" : "");

    System.out.print("Leaderboard lookup(): ");
    System.out.println(testNameLookup() ? "PASS" : "");

    System.out.print("Leaderboard add(): ");
    System.out.println(testAdd() ? "PASS" : "");

    System.out.print("Leaderboard remove(): ");
    System.out.println(testRemove() ? "PASS" : "");

    System.out.print("Leaderboard next(): ");
    System.out.println(testGetNext() ? "PASS" : "");
  }

}
