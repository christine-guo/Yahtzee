/**
  * YahtzeeRow class
  * @author Christine Guo
  */

public class YahtzeeRow extends FixedAmountRow
{
  /**
    * constructor
    * @param name name of row
    */
  public YahtzeeRow(String name)
  {
    // the score for the yahtzee row is 50
    super(name, 50);
  }


  /**
    * method to determine whether all five dice are the same
    * @param dice the dice
    * @return true: if the combination is valid; false: if the combination is not valid
    */
  public boolean isValid(Dice dice)
  {
    int val1, val2;
    // compare the value of one die to its adjacent die
    // make sure each die has the same value as another
    for(int i = 0; i < Dice.numDice -1; i++)
    {
      val1 = dice.getDie(i).getValue();
      val2 = dice.getDie(i + 1).getValue();
      if(val1 != val2)
        return false;
    }
    return true;
  }

}
