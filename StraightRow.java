/**
  * StraightRow class
  * @author Christine Guo
  */

import java.util.Arrays;

public class StraightRow extends FixedAmountRow
{
  // instance variable
  private int numInARow;

  /**
    * constructor
    * @param name name of row
    * @param numInARow number of sequential dice needed
    */
  public StraightRow(String name, int numInARow)
  {
    // the score for a small straight is 30 and a large straight is 40 or (numInARow - 1)*10
    super(name, (numInARow - 1)*10);
    this.numInARow = numInARow;
  }

  /**
    * method to determine whether the given dice combination contains a straight
    * @param dice the dice
    * @return true: if the combination is valid; false: if the combination is not valid
    */
  public boolean isValid(Dice dice)
  {
    int count = 0;
    // create array
    int[] sortedDice = new int[Dice.numDice];
    // add the value of each die to the array
    for(int i = 0; i < sortedDice.length; i++)
    {
      sortedDice[i] = dice.getDie(i).getValue();
    }
    // sort array in ascending order
    Arrays.sort(sortedDice);
    // loop through the array and check for a straight
    for(int i = 0; i < sortedDice.length - 1; i++)
    {
      // increment count if next value in the array is equal to the current value + 1
      if(sortedDice[i + 1] == sortedDice[i] + 1)
        count++;
      // set count equal to 0 if the next value in the array is not even equal to current value (straight is broken)
      else if(sortedDice[i + 1] != sortedDice[i])
        count = 0;
      // there is a straight if the count meets the length requirement for the straight
      if(count == numInARow - 1)
        return true;
    }
    // there is NOT a straight if the count does NOT meet the length requirement for the straight
    return false;
  }
}
