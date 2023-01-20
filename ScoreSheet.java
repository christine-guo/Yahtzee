/**
  * ScoreSheet Class
  * @author Christine Guo
  */

import java.util.ArrayList;

public class ScoreSheet extends ArrayList<ScoreRow>
{
  /**
    * Constructor
    */
  public ScoreSheet()
  {
    add(new NumberRow("Ones", 1));
    add(new NumberRow("Twos", 2));
    add(new NumberRow("Threes", 3));
    add(new NumberRow("Fours", 4));
    add(new NumberRow("Fives", 5));
    add(new NumberRow("Sixes", 6));
    add(new NofAKindRow("Three of a Kind", 3));
    add(new NofAKindRow("Four of a Kind", 4));
    add(new FullHouseRow("Full House"));
    add(new StraightRow("Small Straight", 4));
    add(new StraightRow("Large Straight", 5));
    add(new YahtzeeRow("Yahtzee"));
    add(new ChanceRow("Chance"));
  }

/**
    * method to calculate the total number of points in the score sheet
    * @return score total
    */
  public int getTotal()
  {
    int total = 0;
    // loop through each row in the score sheet
    for(ScoreRow row : this)
    {
      // sum all points that are not -1
      if(row.getScore() != -1)
        total += row.getScore();
    }
    return total;
  }

  /**
    * method to determine if all score categories have been used
    * @return true: if all categories are used; false: if not all categories are used
    */
  public boolean isFull()
  {
    boolean full = true;
    // loop through each row in the score sheet
    for(ScoreRow score : this)
    {
      // it's not full if one row is still equal to -1
      if(score.getScore() == -1)
        full = false;
    }
    return full;
  }

  public String toString()
  {
    String str = "";
    // loop through each row in the score sheet
    for(int i = 1; i <= size(); i++)
    {
      str += i + ".  " + get(i-1) + "\n";
    }
    str += "Total = " + getTotal();
    return str;
  }
}
