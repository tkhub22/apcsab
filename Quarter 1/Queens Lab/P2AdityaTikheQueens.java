import java.util.Scanner;
public class P2AdityaTikheQueens
{

   private int open = 0; //available square
   private int taken = 1; //queen is placed as a 1
   private int [] [] board = 
   {
      {0,0,0,0},
      {0,0,0,0},
      {0,0,0,0},
      {0,0,0,0}
   };


   public String toString() //iterates through and prints grid
   {
      String s ="";
      for (int i = 0; i<board.length; i++)
      {
         for (int j = 0; j<board[i].length; j++)
         {
            s += board[i][j] + " ";
         }
         s += "\n";
      }
      return s;
   } // toString
   
   
   
   public static void solve (int x)
   {
      
   }
   
   
   
   public static void main (String [] args)
   {
      P2AdityaTikheQueens m = new P2AdityaTikheQueens();
      System.out.println("Queens Problem!! \nPurpose: Place N queens in positions around a NxN board \n so they don't attack each other.\n");
      
      System.out.println(m);
      //call solve method ....(4);
   }
   
}