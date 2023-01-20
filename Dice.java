/**
  * Dice class
  * @author Christine Guo
  */

public class Dice
{
  // instance variables
  private Die[] dieArray;
  public static final int numDice = 5;

  /**
    * Constructor
    */
  public Dice()
  {
    dieArray = new Die[5];
    for(int i = 0; i < dieArray.length; i++)
    {
      dieArray[i] = new Die();
    }
  }

  /**
    * accessor for one die at certain index
    * @param index integer for the index in the array
    * @return int value of the die
    */
  public Die getDie(int index) {return dieArray[index];}

  /**
    * method to roll all five dice
    */
  public void rollAllDice()
  {
    for(int i = 0; i < dieArray.length; i++)
    {
      dieArray[i].rollDie();
    }
  }

  /**
    * method to roll some of the dice
    * @param whichDie boolean array that indicates which die should be kept (true) and which die should be rolled (false)
    */
  public void rollSomeDice(boolean[] whichDie)
  {
    for(int i = 0; i < dieArray.length; i++)
    {
      if(!whichDie[i])
        dieArray[i].rollDie();
    }
  }

  public String toString()
  {
    String str = "";
    for(Die die : dieArray)
    {
      str += die.toString() + " ";
    }
    return str;
  }
}
