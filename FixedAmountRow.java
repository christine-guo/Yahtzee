/**
  * FixedAmountRow abstract class
  * @author Christine Guo
  */

abstract class FixedAmountRow extends ScoreRow
{
  // instance variable
  private int scoreValue;

  /**
    * constructor
    * @param name name of row
    * @param scoreValue fixed score for row
    */
  public FixedAmountRow(String name, int scoreValue)
  {
    super(name);
    this.scoreValue = scoreValue;
  }

  /**
    * method that sets the fixed score for the row's score
    * @param dice the dice
    */
  public void calculateScore(Dice dice)
  {
    setScore(scoreValue);
  }
}
