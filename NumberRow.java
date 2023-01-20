/**
  * NumberRow class
  * @author Christine Guo
  */

public class NumberRow extends ScoreRow
{
  // instance variable
  private int value;

  /**
    * constructor
    * @param name name of row
    * @param value value of die that is needed
    */
  public NumberRow(String name, int value)
  {
    super(name);
    this.value = value;
  }

  /**
    * method that determines the score by only adding values that are equal to the value of the row
    * @param dice the dice
    */
  public void calculateScore(Dice dice)
  {
    int score = 0;
    for(int i = 0; i < Dice.numDice; i++)
    {
      // only sum values that are equal to the value variable
      if(dice.getDie(i).getValue() == value)
        score += value;
    }
    setScore(score);
  }

  /**
    * method that always returns true for any combination of dice
    * @param dice the dice
    * @return true: if the combination is valid
    */
  public boolean isValid(Dice dice)
  {
    return true;
  }
}
