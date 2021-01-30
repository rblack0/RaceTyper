import java.util.*;

public class ParagraphManager
{
  private ArrayList<Paragraph> blockLoader;
  private Scanner sc;

  public ParagraphManager(Scanner scanner)
  {
    blockLoader = new ArrayList<>();
    sc = scanner;
  }

  // The paragraph editor menu :D
  public void paragraphMenu()
  {
    createHeader("Paragraph Editor\nHere's what you've saved so far:");
    titleList(); 
    createHeader("1: Make a new paragraph.\n2: Add to an existing paragraph.\n3: Exit");
  }

  public Paragraph get(int i)
  {
    return blockLoader.get(i);
  }

   public void enterParagraph()
  {
    String input;
    Paragraph paragraph = new Paragraph();

    System.out.println("Please enter a title for your paragraph:");
    paragraph.setTitle(sc.nextLine());

    System.out.println("NOTE: Enter @@ when you finish typing your paragraph!");
    System.out.println("Please enter your paragraph ONE sentence at a time:");

    input = sc.nextLine();
    while (input != "@@") 
    {
        input = sc.next();
        paragraph.add(input);
    }
  
    blockLoader.add(paragraph);
 }

 public void addToParagraph(int paragraph)
 {
   while (true)
   {
     System.out.println("Enter in a sentence to add; @@ to leave");
     String input = sc.nextLine();

     if (input.isEmpty())
     {
       continue;
     }
     if (input.equals("@@"))
     {
       return;
     }
     blockLoader.get(paragraph).add(input);     
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
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-==-=-=-=-=-=-=-=-=-\n\n");
  }

}