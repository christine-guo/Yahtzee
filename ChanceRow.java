/**
  * ChanceRow class
  * @author Christine Guo
  */

public class ChanceRow extends SumAllRow
{
  /**
    * constructor
    * @param name name of row
    */
  public ChanceRow(String name)
  {
    super(name);
  }

  /**
    * method that returns true because the chance row can have any combination of die
    * @param dice the dice
    * @return true 
    */
  public boolean isValid(Dice dice)
  {
    return true;
  }
}
