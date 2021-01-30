import java.io.*;
import java.util.*;

public class Menu 
{

  // Menu variables
  private ParagraphManager paraManager;
  public static Scanner sc;
  private int timeLimit = 20;

  // Constructor to initialize the scanner and the paraManager
  public Menu(Scanner scanner)
  {
    sc = scanner;
    paraManager = new ParagraphManager(scanner);
  } 
  
  public void menuGateway()
  {
    if (!splashOptions())
    {
      return;
    }
    startMenu();    
  }

  // Initial options menu after moving past the splash screen
  public void startMenu()
  {   
    while (true)
    {
      createHeader("1: Start a game.\n2: Paragraph editor.\n3: Exit");
      String choice = sc.next();
      switch (choice)
      {
        case "1":
          initGame();        
          break;
        case "2":
          paraManager.paragraphMenu();
          break;

        case "3":
          System.out.println("Thanks for playing!\n");
          System.exit(0);
        
        default: System.out.println("That's not a valid choice!");
      }
    }

    
  }   
  
  // Function that asks the user to choose a paragraph they wish to practice, displays titles,
  // and they choose the index assosciated with that specific paragraph. Starts the game.
  public void initGame() 
  {
    if (paraManager.getSize() <= 0)
    {
      System.out.println("No paragraphs in storage!\n");
      return;
    }

    System.out.println("Please choose a paragraph to practice:");
    paraManager.titleList();
    int pgraph_num = Integer.parseInt(sc.next());
    sc.skip("\n");
    System.out.println("Please choose a difficulty (1-5):");
    int difficulty = Integer.parseInt(sc.next());

    startGame(pgraph_num, difficulty);
  }

  // Initial start menu prompting the user if they would like to create a new paragaph or use
  // an existing one(implemented later).
  public boolean splashOptions() {
    Logo();
    createHeader("Welcome to RaceTyper! (Patent pending)\nCreated by John Pham, Jonathan Dieu, and Ryan Black");

    System.out.println("Continue (Y/N)?");
    String res = sc.next();
  
    return res.equals("Y");
  }

  // // Function that creates our beautiful header.
  public void createHeader(String header)
  {
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-==-=-=-=-=-=-=-=-=-");
    System.out.println(header);
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-==-=-=-=-=-=-=-=-=-\n\n");
  }

  // Functionality for the game
  // Times users and compares their input to what is being held in blockLoader
  public void startGame(int paragraph, int difficulty)
  {
    int count = 0;
    double overallTime = 0;
    long start = 0;
    long stop = 0;
    
    LinkedList<String> temp = paraManager.get(paragraph).getBlock();
    System.out.println("START!\n");
    for (String sentence : temp)
    {

      String userInput = "";
      System.out.println(sentence);
      while (true)
      {
        start = System.nanoTime();
        
        userInput = sc.nextLine();
        if (userInput.isEmpty())
        {
          continue;
        }
        else
        {
          stop = System.nanoTime();
          break;
        }

      }      
      
      double totalTime = (stop - start) / 1e9;
      overallTime += totalTime;

      System.out.println();

      if (totalTime >= timeLimit - difficulty || !userInput.equals(sentence))
        typingError(sentence, userInput, totalTime);
      else if (++count % 3 == 0)
        typingSuccess(overallTime);

      System.out.println();
    }

    // IMPLEMENT LATER
    // A report screen that will display some cool stats to the user
    // reportPrint(paragraph, difficulty, overallTime);
  }

  // Occurs when the user can't type a word correctly or on time
  public void typingError(String sentence, String userInput, double totalTime)
  {
    System.out.println("Your input: " + userInput + "\nYour time: " + totalTime + " seconds");
    System.out.println("Oops! Looks like you couldn't finish correctly or on time, let's try this again");
    System.out.println("Try typing this sentence again:\n" + sentence + "\nTake your time, you can do it!");

    while (true)
    {
      sc.nextLine();
      String input = sc.nextLine();
      if (!input.equals(sentence))
      {
        continue;
      }
      else
      {
        System.out.println("Nice work! :D");
        break;
      }

    }
  }

  // A small congratulatory message when the user types 3 sentences correctly
  //make a random to select from 
  public void typingSuccess(double overallTime)
  {
    System.out.println("LET'S GOOOOOOOOO!!!\nPOGCHAMP");

    System.out.println("You got a score of: " + (overallTime*1000));
  }

  // public static void countdown()
  // {
  //   System.out.println("Get ready...");
  //   {timer = new Timer(); timer.schedule(new DisplayCountdown(), 1000);} // Last parameter is the interval in which the coundown is displayed (frequency wise)

  //  class DisplayCountdown extends TimerTask {int seconds = timeLimit;} // 2 errors

// Printing out a report card 
  public void raceReport()
  {
    System.out.println("Congratulations on getting through the challenge!");
  }

// death
  public void Logo()
  {
      System.out.println("     ____                ______");
      System.out.printf("    / __ ");
      System.out.printf("\\");
      System.out.println("____ _________/_  __/_  ______  ___  _____");
      System.out.printf("   / /_/ / __ `/ ___/ _ ");
      System.out.printf("\\");
      System.out.printf("/ / / / / / __ ");
      System.out.printf("\\");
      System.out.printf("/ _ ");
      System.out.printf("\\");
      System.out.println("/ ___/");

      System.out.println("  / _, _/ /_/ / /__/  __/ / / /_/ / /_/ /  __/ /    ");
    System.out.printf(" /_/ |_|");
    System.out.printf("\\");
    System.out.printf("__,_/");
    System.out.printf("\\");
    System.out.printf("___/");
    System.out.printf("\\");
    System.out.printf("___/_/  ");
    System.out.printf("\\");
    System.out.printf("__, / .___/");
    System.out.printf("\\");
    System.out.println("___/_/     ");
    System.out.println("                           /____/_/\n");     
  }

}