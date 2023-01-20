/**
  * NofAKindRow class
  * @author Christine Guo
  */

public class NofAKindRow extends SumAllRow
{
  // instance variable
  private int numOfKind;

  /**
    * constructor
    * @param name name of row
    * @param numOfKind number of dice that must be the same
    */
  public NofAKindRow(String name, int numOfKind)
  {
    super(name);
    this.numOfKind = numOfKind;
  }

  /**
    * method to determine whether the given dice combination is valid for this row (there must be at least numOfKind dice that are the same)
    * @param dice the dice
    * @return true: if the combination is valid; false: if the combination is not valid
    */
  public boolean isValid(Dice dice)
  {
    // count the number of times each value of die occurs
    int[] count = countDice(dice);
    for(int num : count)
    {
      // if there are numOfKind instances of a value, there is a N of a kind
      if(num == numOfKind)
        return true;
    }
    return false;
  }
}
