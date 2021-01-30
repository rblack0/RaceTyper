import java.util.*;
import java.io.*;


public class ParagraphManager
{
  private ArrayList<Paragraph> blockLoader;
  public static Scanner sc;
  private String filename;

  public ParagraphManager(Scanner scanner)
  {
    blockLoader = new ArrayList<>();
    sc = scanner;
  }

  // Get the size of the paragraph to check whether or not it exists
  public int getSize()
  {
    return blockLoader.size();
  }
  
  // The paragraph editor menu :D
  public void paragraphMenu()
  { 
    while (true)
    {
      createHeader("0: See saved paragraphs\n1: Make a new paragraph.\n2: Make a new paragraph from a file\n3: Add to an existing paragraph.\n4: Exit");

      String input = sc.next();
      switch (input)
      {
        case "0":
          createHeader("Paragraph Editor\nHere's what you've saved so far:");
          titleList();
          break;
        case "1":
          enterParagraph();
          break;
        case "2":
          readFile();
          break;
        case "3":
          addToParagraph();
          break;
        case "4":
          return;
          
        default:
          System.out.println("Please enter a valid input");
      }
      System.out.println();
    }
  }

  public Paragraph get(int i)
  {
    return blockLoader.get(i);
  }

   public void enterParagraph()
  {
    String input = "";
    boolean flag = false;
    
    Paragraph paragraph = new Paragraph();

    System.out.println("NOTE: Enter @@ when you finish typing your paragraph!");
    System.out.println("Please enter your paragraph ONE sentence at a time:\n");

    while (true) 
    {
      input = sc.nextLine();
      if (input.isEmpty())
        continue;
      if (input.equals("@@"))
      {
        break;
      }
      else
      {
        flag = true;
        paragraph.add(input);
      }
    } 

    if (true == flag)
    {
      System.out.println("Please enter a title for your paragraph:");
     
      String title = sc.nextLine();

      paragraph.setTitle(title);

      blockLoader.add(paragraph);
    }   
    
 }

 public void addToParagraph()
 {
   System.out.println("What paragraph would you like to edit?");
   this.titleList();
   
   int input = Integer.parseInt(sc.next());
   sc.skip("\n");

   System.out.println("Enter in a sentence to add; @@ to leave");
   while (true)
   {
     String newSentence = sc.nextLine();
     
     if (newSentence.isEmpty())
     {
       System.out.println("Empty input");
       continue;
     }

     if (newSentence.equals("@@"))
     {
       createHeader(blockLoader.get(input).toString());
       System.out.println("Is this okay? (Y/N)");
       
       String answer = sc.nextLine();
       if (answer.equals("Y"))
        break;
     }
     else
     {
       blockLoader.get(input).add(newSentence); 
     }
          
   }
 }
 
  // Function to output a list of all titles along with the index asssociated with that specific paragraph.
  public void titleList()
  {
    for (int i = 0; i < blockLoader.size(); i++) 
    {
      System.out.println(i + " " + "|" + " " + blockLoader.get(i).getTitle());
    }
    System.out.println();
  }

    // // Function that creates our beautiful header.
  public void createHeader(String header)
  {
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-==-=-=-=-=-=-=-=-=-");
    System.out.println(header);
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-==-=-=-=-=-=-=-=-=-\n");
  }
// Take in input from a text file with paragraph in it as specified by the user
  public void readFile() 
  {
    Paragraph newParagraph = new Paragraph();

    System.out.println("Please enter the name of the file that you would like to use as input.");
    
    filename = sc.next();
    
    // Takes file specified by the user and turns it into a paragraph and saves it to the program
    
    try (Scanner inputscanner = new Scanner(new File(filename)))
    {
      while (inputscanner.hasNextLine())
      {
        String line = inputscanner.nextLine();
        newParagraph.add(line); 
      }
      createHeader(newParagraph.toString());    
      inputscanner.close();

      System.out.println("Now what name would you like this paragraph to be saved under?");

      String inputTitle = "";
      while (true)
      {
        inputTitle = sc.nextLine();
        if (inputTitle.isEmpty())
        {
          continue;
        }
        break;
      }
      
      newParagraph.setTitle(inputTitle);
      blockLoader.add(newParagraph);
      
    }
    catch (Exception e)
    {
      System.out.println(e);
    }   
    
  }

  // public String stringSanitizer(String s)
  // {
  //    
  // }

}