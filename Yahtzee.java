/**
 * Class to play the game of Yahtzee
 * @author Christine Guo
 * The purpose of this lab is to create a one-player game of Yahtzee, which is a game of dice
 * My general approach to this lab was to first create the most fundamental parts of the game and then work my way to the specifics.
 * I also made sure to test my function before moving on to the next one.
 * From this lab, I have learned how to effectively use polymorphism and inheritence to make my code more efficient.
 * I encountered multiple problems while coding this lab.
 * I had trouble with creating rollDice() for this class. I needed the function to continue to call itself until the user's input was valid, but it couldn't roll the dice every time it did that.
 * So, I had to create a different function called rollDiceInput that specifically checks if the user's input is valid.
 * I also had some issue with the ScoreSheet class. Originally, I made the ArrayList an instance variable and I kept getting IndexOutOfBoundsException.
 * I realized that the class was supposed to have an Is-A rather than Has-A relationship, so I deleted the instance variable and directly added to the ArrayList.
 * I have neither given nor received any unauthorized aid.
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Yahtzee
{
  /**
   * Main method to test valid combinations or play the game
   * @param args command line arguments
   */
  public static void main(String[] args)
  {
    // test valid combinations
    ArrayList<Dice> diceList = generateAllDiceCombinations();
    testIsValid(diceList);
    System.out.println();
    System.out.println("___________________________________________");
    System.out.println();
    // play game
    playGame();
  }

  /**
   * Play the game of Yahtzee
   */
  public static void playGame()
  {
    System.out.println("Welcome to Yahtzee!");
    ScoreSheet sheet = new ScoreSheet();
    // continue the game until the score sheet is full
    while(!sheet.isFull())
    {
      // roll dice and get user input
      Dice dice = rollDice();
      // get user input to pick which row to use the dice
      pickRow(sheet, dice);
      // print result from each turn
      System.out.println(sheet);
    }
  }

  /**
   * Roll the dice up to 3 times with user interaction
   * @return the final rolled dice
   */
  public static Dice rollDice()
  {
    // keep track of number of rerolls
    int count = 0;
    String[] times = {"first", "second", "third"};
    boolean quitRolling = false;
    // roll all dice
    Dice dice = new Dice();
    dice.rollAllDice();
    // continue to roll unless user quits
    while(!quitRolling)
    {
      System.out.println("Your " + times[count] + " roll of dice is: " + dice);
      count++;
      if(count == 3)
        quitRolling = true;
      else
      {
        boolean[] result = rollDiceInput();
        if(result.length == 1)
          quitRolling = true;
        else
          dice.rollSomeDice(result);
      }
    }
    return dice;
  }

  /**
    * method to interpret user input after rolling dice
    * @return boolean array that uses true or false to determine which dice are rolled
    */
  public static boolean[] rollDiceInput()
  {
    boolean[] keepDiceArray;
    // Create a scanner object
    Scanner keyboard = new Scanner(System.in);
    // print results
    System.out.println("Enter the pattern xxxxx with 1 for keep and 0 for reroll");
    // return user input
    System.out.print("Enter q to stop rolling and pick your row in the score sheet: ");
    String answer = keyboard.nextLine();
    if(answer.equals("q"))
      keepDiceArray = new boolean[1];
    // answer can only be 5 digits (one for each die)
    // if it does not follow the above conditions, then re-ask for user input
    else if((answer.length() != Dice.numDice) || (!checkOnlyDigits(answer)))
    {
      System.out.println("This is not an acceptable answer");
      keepDiceArray = rollDiceInput();
    }
    // check if all digits are only 0 or 1
    else
    {
      keepDiceArray = new boolean[Dice.numDice];
      // convert answer to an int
      int intAnswer = Integer.parseInt(answer);
      // go through each digit in the array
      for(int i = keepDiceArray.length - 1; i >= 0; i--)
      {
        // get digit by getting the number's remainder
        int remainder = intAnswer % 10;
        intAnswer /= 10;
        // check if the digit is valid (must be 0 or 1)
        if((remainder != 0) && (remainder != 1))
        {
          System.out.println("This is not an acceptable answer");
          rollDiceInput();
        }
        // if digit is 0 or 1 then translate it as false or true (respectively) and add to boolean array
        else
        {
          if(remainder == 0)
            keepDiceArray[i] = false;
          else
            keepDiceArray[i] = true;
        }
      }
    }
    return keepDiceArray ;
  }

  /**
    * method checks if string only contains digits
    * @param str input string
    * @return true: if the string only contains digits; false: if the string doesn't contain only digits
    */
  public static boolean checkOnlyDigits(String str)
  {
    // convert str to an array of characters and go through each character
    for(char c : str.toCharArray())
    {
      // check if the character is a digit
      // if its not a digit, return false
      if(!Character.isDigit(c))
        return false;
    }
    return true;
  }

  /**
   * Get the user to pick the row where to insert the new score
   * @param s the score sheet to be updated
   * @param dice the dice combination to be used to add a new score
   */
  public static void pickRow(ScoreSheet s, Dice dice)
  {
    // Create a scanner object
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Your dice are: " + dice);
    // get user input
    System.out.print("Enter the row number for the score: ");
    String answer = keyboard.nextLine();
    // check if the answer only contains digits
    if(checkOnlyDigits(answer))
    {
      int intAnswer = Integer.parseInt(answer);
      // check if the number is between 1 and 13
      if((intAnswer < 1) || (intAnswer > 13))
      {
        System.out.println("The row should be a number between 1 and 13");
        pickRow(s, dice);
      }
      else
      {
        // get row
        ScoreRow row = s.get(intAnswer - 1);
        // check if row is already filled
        if(row.getScore() != -1)
        {
          System.out.println("The row '" + row.getName() + "' already has a score");
          pickRow(s, dice);
        }
        // check if the combination of dice should be valid for that row
        // if not valid, the method should ask with the user whether he/she wants to enter a 0 in that row
        else if(!row.isValid(dice))
        {
          System.out.println("The row '" + row.getName() + "' does not accept this combination of dice");
          System.out.print("Do you really want to enter a 0 for '" + row.getName() + "' (y/n)? ");
          answer = keyboard.nextLine();
          {
            // set score to 0
            if(answer.equals("y"))
            {
              row.setScore(0);
            }
            // re-ask question
            else if(answer.equals("n"))
            {
              pickRow(s, dice);
            }
            // not valid answer so re-ask question
            else
            {
              System.out.println("That's not a valid answer. Please try again.");
              pickRow(s, dice);
            }
          }
        }
        // if everything is valid, set the score
        else
        {
          row.calculateScore(dice);
        }
      }
    }
    else
    {
      System.out.println("The row should be a number between 1 and 13");
      pickRow(s, dice);
    }

  }

  /**
   * Generate all possible combinations of dice to be used in testing the
   * ScoreRow isValid method for different kinds of rows
   * @return An array list of all possible dice combinations
   */
  public static ArrayList<Dice> generateAllDiceCombinations()
  {
    ArrayList<Dice> diceCombinations = new ArrayList<Dice>();
    // Starting values for the dice
    int[] values = new int[Dice.numDice];
    for(int i = 0; i < Dice.numDice; i++)
      values[i] = 1;
    boolean done = false;
    while (!done)
    {
      // Create the dice using the set of values
      Dice dice = new Dice();
      for(int i = 0; i < Dice.numDice; i++)
        dice.getDie(i).setValue(values[i]);
      // Add this new Dice object to the array list of combinations
      diceCombinations.add(dice);
      int currentDie = 0;
      boolean foundDie = false;
      // Change dice one at a time, look for the die to change
      while (!foundDie)
      {
        // If the value of the current die has not reached the last face value
        if (values[currentDie] < Die.numValues)
        {
          // Increment the value of the die
          values[currentDie]++;
          foundDie = true;
        }
        else
        {
          // Move on to the next die, reset this die back to starting value 1
          values[currentDie] = 1;
          currentDie++;
          // If the current die is beyond the last die, we're done
          if (currentDie == Dice.numDice)
          {
            foundDie = true;
            done = true;
          }
        }
      }
    }
    return diceCombinations;
  }

  /**
    * method to tests the number of valid combinations for each row given all the possible combinations of dice
    * @param allCombinations array list with every combination of dice
    */
  public static void testIsValid(ArrayList<Dice> allCombinations)
  {
    System.out.println("These are all the possible combinations for each row:");
    ScoreSheet s = new ScoreSheet();
    // keep track of the number of valid combinations for each row in int array
    int[] counts = new int[13];
    // test each row
    for(int i = 0; i < 13; i++)
    {
      int counter = 0;
      ScoreRow row = s.get(i);
      for(Dice dice : allCombinations)
      {
        // check if the combination of dice is valid
        if(row.isValid(dice))
          counter++;
      }
      // save counter to the array
      counts[i] = counter;
    }
    // print results
    for(int i = 0; i < 13; i++)
    {
      int j = i + 1;
      ScoreRow row = s.get(i);
      System.out.println(j + ". " + row.getName() + " has " + counts[i] + " valid combinations");
    }
  }
}
