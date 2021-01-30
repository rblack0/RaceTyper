import java.util.*;
import java.lang.*;

public class Main
{
  public static void main(String[] args) 
  {
    Scanner sc = new Scanner(System.in);
    Menu menu = new Menu(sc);
    
    menu.menuGateway();
    System.out.println("See you next time! :D\n");
  }
}


