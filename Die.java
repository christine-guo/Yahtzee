/**
  * Die Class
  * @author Christine Guo
  */

public class Die
{
  // instance variables
  private int value;
  public static final int numValues = 6;

  /**
    * constructor that initalizes the die to 0
    */
  public Die()
  {
    value = 0;
  }

  // accessor
  public int getValue() {return value;}
  // modifier
  public void setValue(int value) {this.value = value;}

  /**
    * method that rolls the die and gives it a random value between 1 and 6
    */
  public void rollDie() {value = (int)(Math.random()*6) + 1;}

  public String toString()
  {
    return String.valueOf(value);
  }
}
