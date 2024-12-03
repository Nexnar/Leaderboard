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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Acts as the Iterator for the Leaderboard class
 */
public class LeaderboardIterator implements Iterator<Player> {

  private Leaderboard lbCopy = new Leaderboard();

  /**
   * Constructor method that creates a copy of the leaderboard we want to iterate over (lbCopy)
   * 
   * @param leaderboard the leaderboard to be copied
   */
  public LeaderboardIterator(Leaderboard leaderboard) {
    // we first add the min score so we can use the while loop to add the remainder of the nodes
    lbCopy.addPlayer(leaderboard.getMinScore());
    while (lbCopy.size() != leaderboard.size()) {
      // iterates until lbCopy contains all players of the leaderboard we are copying
      Player current = lbCopy.getMaxScore();
      lbCopy.addPlayer(leaderboard.next(current));
    }
  }

  /**
   * Determines if there is something still to iterate over by checking that the leaderboard size is
   * greater than 0
   * 
   * @return true if there is still something to iterate, false otherwise
   */
  @Override
  public boolean hasNext() {
    return this.lbCopy.size() > 0;
  }

  /**
   * Returns the next player of the iteration
   * 
   * @throws NoSuchElementException if there is nothing left to iterate over
   * @return the pLayer of iteration
   */
  @Override
  public Player next() {
    if (!hasNext())
      throw new NoSuchElementException("No item to iterate");

    Player next = this.lbCopy.getMinScore();
    this.lbCopy.removePlayer(next);

    return next;
  }

}
