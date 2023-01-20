/**
  * ScoreRow abstract Class
  * @author Christine Guo
  */

abstract class ScoreRow
{
  // instance variables
  private String name;
  private int score;

  /**
    * constructor
    * @param name name of row
    */
  public ScoreRow(String name)
  {
    this.name = name;
    score = -1;
  }

  // accessors
  public String getName() {return name;}
  public int getScore() {return score;}

  // modifiers
  public void setName(String name) {this.name = name;}
  public void setScore(int score) {this.score = score;}

  public String toString()
  {
    String str = name + " --> ";
    if(score != -1)
      str += score;
    return str;
  }

  /**
    * method to sort the die based on their values
    * @param dice the dice
    * @return an int array that has a count of how much each value of die occurs in the dice
    */
  public static int[] countDice(Dice dice)
  {
    int[] count = new int[Die.numValues];
    // loop through all dice
    for(int i = 0; i < Dice.numDice; i++)
    {
      // increment every time the index of the array is equal to the value of dice - 1
      if(dice.getDie(i).getValue() == i + 1)
      count[i]++;
    }
    return count;
  }


  /**
    * method that determines the score for that row given a dice combination and changes the score field
    * @param dice the dice
    */
  public abstract void calculateScore(Dice dice);

  /**
    * method to determine whether the given dice combination is valid for this row
    * @param dice the dice
    * @return true: if the combination is valid; false: if the combination is not valid
    */
  public abstract boolean isValid(Dice dice);
}
