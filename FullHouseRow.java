/**
  * FullHouseRow class
  * @author Christine Guo
  */

public class FullHouseRow extends FixedAmountRow
{
  /**
    * constructor
    * @param name name of row
    */
  public FullHouseRow(String name)
  {
    // the score for a full house is 25
    super(name, 25);
  }


  /**
    * method to determine whether the given dice combination contains a three of a kind and a two of a kind
    * @param dice the dice
    * @return true: if the combination is valid; false: if the combination is not valid
    */
  public boolean isValid(Dice dice)
  {
    boolean check1 = false;
    boolean check2 = false;
    // count the number of times each value of die occurs
    int[] count = countDice(dice);
    for(int num : count)
    {
      if(num == 3)
        check1 = true;
      else if(num == 2)
        check2 = true;
    }
    if(check1 && check2)
      return true;
    else
      return false;
  }

}
