import java.util.*;

public class Paragraph
{
  private LinkedList<String> block;
  private String title;

  public Paragraph(String s)
  {
    block = new LinkedList<>();
    setTitle(s);
  }

  public Paragraph()
  {
    block = new LinkedList<String>();
    title = "RandomTitle";
  }

  public LinkedList<String> getBlock()
  {
    return block;
  }
  
  public int getBlockSize()
  {
    return block.size();
  }

  public void add(String sentence)
  {
    block.add(sentence);
  }

  // Set the title
  public void setTitle(String s) {
    title = s;
  }

  public String getTitle()
  {
    return title;
  }

  // Method for converting 
  public String toString()
  {
    StringBuilder bobTheBuilder = new StringBuilder(block.size());

    for (String sentence : block)
    {
      bobTheBuilder.append(sentence + "\n");
    }
    return bobTheBuilder.toString();
  }
}

