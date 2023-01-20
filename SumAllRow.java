/**
  * SumAllRow abstract class
  * @author Christine Guo
  */

abstract class SumAllRow extends ScoreRow
{
  /**
    * constructor
    * @param name name of row
    */
  public SumAllRow(String name)
  {
    super(name);
  }

  /**
    * method that determines the score for that row by adding all dice
    * @param dice the dice
    */
  public void calculateScore(Dice dice)
  {
    int score = 0;
    for(int i = 0; i < Dice.numDice; i++)
    {
      // add every value of dice
      score += dice.getDie(i).getValue();
    }
    setScore(score);
  }
}
