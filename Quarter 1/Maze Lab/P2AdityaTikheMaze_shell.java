import java.util.Scanner;
public class P2AdityaTikheMaze_shell
{
   private final int VISITED = 3;
   private final int PATH = 7;
   private final int NOT_YET = 1; //possible space
   private final int BLOCKED = 0; //blocked
   private int counter = 0; //blocked

   
   private int [] [] grid2 = 
   {
      {1,1,1,0,1,1,0,0,0,1,1,1,1},
      {1,0,1,1,1,0,1,1,1,1,0,0,1},
      {0,0,0,0,1,0,1,0,1,0,1,0,0},
      {1,1,1,0,1,1,1,0,1,0,0,1,1},
      {1,0,1,0,0,0,0,1,1,1,0,0,1},
      {1,0,1,1,1,1,1,1,0,1,1,1,0},
      {1,0,0,0,0,0,0,0,0,0,0,0,0},
      {1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    
    
// Use a smaller maze first 
  /* private int [] [] grid2 = 
   {
      {1,1,1,1},
      {1,0,0,1},
      {1,1,0,1},
      {0,0,0,1}
   };*/


   // Assumption: the exit is at the bottom right corner of the grid
   //
   public boolean findAnExit (int x, int y) 
   {
      String path = "";
      return findAnExitHelper(x, y);  
   }  // FindAnExit


   public boolean findAnExitHelper (int x, int y) //used to findAnExitHelpermethod
   {  
      int lengthX = grid2.length; //length of array         
      int lengthY = grid2[0].length; //length of array      
      boolean temp = false;
      
      if (works (x, y)) 
      {
      
         grid2[x][y] = 3;  // cell has been tried
      
         if (x == lengthX-1 && y == lengthY-1)
            temp = true;  // maze is solved
         else {
            temp = findAnExitHelper(x+1, y);  // south
            if (!temp)
               temp = findAnExitHelper(x, y+1);  // east
            if (!temp)
               temp = findAnExitHelper(x-1, y);  // north
            if (!temp)
               temp = findAnExitHelper(x, y-1);  // west
         }
         if (temp)  // final path
            grid2[x][y] = 7;
      }   
      return temp;
   } // findAnExitHelper
   
   
   private boolean works (int x, int y) //method that checks if it blocked/tried/in bounds
   {
      int lengthX = grid2.length; //length of array         
      int lengthY = grid2[0].length; //length of array      
   
      boolean temp = false;
      if (x >= 0 && x < lengthX && y >= 0 && y < lengthY)
      {
         if (grid2[x][y] == 1)
         {
            temp = true;
         }
      }
      return temp;
   }  //works

   
   public String toString() //iterates through and prints grid
   {
      String s ="";
      for (int i = 0; i<grid2.length; i++)
      {
         for (int j = 0; j<grid2[i].length; j++)
         {
            s += grid2[i][j] + " ";
         }
         s += "\n";
      }
      return s;
   } // toString
   
   
   
   public static void main (String [] args)
   {
      // Assume that the exit of the maze is at the lower right hand corner of 
      // the grid
      P2AdityaTikheMaze_shell m = new P2AdityaTikheMaze_shell();
      
      // display the maze  
      System.out.println (m);
      Scanner input = new Scanner (System.in);
      
      System.out.println ("Start location coordinates (separated by a space): ");
      int startX = input.nextInt();
      int startY = input.nextInt();
        
      while (!m.findAnExit(startX, startY))
      {
         System.out.println ("Still trapped inside!");
         System.out.println (m);
         
         System.out.println ("Start location coordinates (separated by a space): ");
         startX = input.nextInt();
         startY = input.nextInt();
      }
         
      System.out.println ("Successfully exit the maze!!!");
              
      // display the path (indicated by 7) that leads to the exit of the maze
      // also display locations tried
      System.out.println (m);
   } // main 
} // Maze


/********************************  Sample Runs
*********************************  Run #1
      ----jGRASP exec: java Maze
 
 1 1 1 0 1 1 0 0 0 1 1 1 1 
 1 0 1 1 1 0 1 1 1 1 0 0 1 
 0 0 0 0 1 0 1 0 1 0 1 0 0 
 1 1 1 0 1 1 1 0 1 0 0 1 1 
 1 0 1 0 0 0 0 1 1 1 0 0 1 
 1 0 1 1 1 1 1 1 0 1 1 1 0 
 1 0 0 0 0 0 0 0 0 0 0 0 0 
 1 1 1 1 1 1 1 1 1 1 1 1 1 
 
 Start location coordinates (separated by a space): 
 0 0
 [0,0][0,1][0,2][1,2][1,3][1,4][2,4][3,4][3,5][3,6][2,6][1,6][1,7][1,8][2,8][3,8][4,8][4,7][5,7][5,6][5,5][5,4][5,3][5,2][4,2][3,2][3,1][3,0][4,0][5,0][6,0][7,0][7,1][7,2][7,3][7,4][7,5][7,6][7,7][7,8][7,9][7,10][7,11][7,12]
 Successfully exit the maze!!!
 
 7 7 7 0 1 1 0 0 0 1 1 1 1 
 3 0 7 7 7 0 7 7 7 1 0 0 1 
 0 0 0 0 7 0 7 0 7 0 1 0 0 
 7 7 7 0 7 7 7 0 7 0 0 1 1 
 7 0 7 0 0 0 0 7 7 1 0 0 1 
 7 0 7 7 7 7 7 7 0 1 1 1 0 
 7 0 0 0 0 0 0 0 0 0 0 0 0 
 7 7 7 7 7 7 7 7 7 7 7 7 7 
 
 
  ----jGRASP: operation complete.
 *********************************  Run #2
  ----jGRASP exec: java Maze
 
 1 1 1 0 1 1 0 0 0 1 1 1 1 
 1 0 1 1 1 0 1 1 1 1 0 0 1 
 0 0 0 0 1 0 1 0 1 0 1 0 0 
 1 1 1 0 1 1 1 0 1 0 0 1 1 
 1 0 1 0 0 0 0 1 1 1 0 0 1 
 1 0 1 1 1 1 1 1 0 1 1 1 0 
 1 0 0 0 0 0 0 0 0 0 0 0 0 
 1 1 1 1 1 1 1 1 1 1 1 1 1 
 
 Start location coordinates (separated by a space): 
 3 10
 Still trapped inside!
 
 1 1 1 0 1 1 0 0 0 1 1 1 1 
 1 0 1 1 1 0 1 1 1 1 0 0 1 
 0 0 0 0 1 0 1 0 1 0 1 0 0 
 1 1 1 0 1 1 1 0 1 0 0 1 1 
 1 0 1 0 0 0 0 1 1 1 0 0 1 
 1 0 1 1 1 1 1 1 0 1 1 1 0 
 1 0 0 0 0 0 0 0 0 0 0 0 0 
 1 1 1 1 1 1 1 1 1 1 1 1 1 
 
 Start location coordinates (separated by a space): 
 7 0
 [7,0][7,1][7,2][7,3][7,4][7,5][7,6][7,7][7,8][7,9][7,10][7,11][7,12]
 Successfully exit the maze!!!
 
 3 3 3 0 3 3 0 0 0 3 3 3 3 
 3 0 3 3 3 0 3 3 3 3 0 0 3 
 0 0 0 0 3 0 3 0 3 0 1 0 0 
 3 3 3 0 3 3 3 0 3 0 0 1 1 
 3 0 3 0 0 0 0 3 3 3 0 0 1 
 3 0 3 3 3 3 3 3 0 3 3 3 0 
 3 0 0 0 0 0 0 0 0 0 0 0 0 
 7 7 7 7 7 7 7 7 7 7 7 7 7 
 
 
  ----jGRASP: operation complete.
 *********************************  Run #3
  ----jGRASP exec: java Maze
 
 1 1 1 0 1 1 0 0 0 1 1 1 1 
 1 0 1 1 1 0 1 1 1 1 0 0 1 
 0 0 0 0 1 0 1 0 1 0 1 0 0 
 1 1 1 0 1 1 1 0 1 0 0 1 1 
 1 0 1 0 0 0 0 1 1 1 0 0 1 
 1 0 1 1 1 1 1 1 0 1 1 1 0 
 1 0 0 0 0 0 0 0 0 0 0 0 0 
 1 1 1 1 1 1 1 1 1 1 1 1 1 
 
 Start location coordinates (separated by a space): 
 2 10
 Still trapped inside!
 
 1 1 1 0 1 1 0 0 0 1 1 1 1 
 1 0 1 1 1 0 1 1 1 1 0 0 1 
 0 0 0 0 1 0 1 0 1 0 3 0 0 
 1 1 1 0 1 1 1 0 1 0 0 1 1 
 1 0 1 0 0 0 0 1 1 1 0 0 1 
 1 0 1 1 1 1 1 1 0 1 1 1 0 
 1 0 0 0 0 0 0 0 0 0 0 0 0 
 1 1 1 1 1 1 1 1 1 1 1 1 1 
 
 Start location coordinates (separated by a space): 
 0 12
 [0,12][0,11][0,10][0,9][1,9][1,8][2,8][3,8][4,8][4,7][5,7][5,6][5,5][5,4][5,3][5,2][4,2][3,2][3,1][3,0][4,0][5,0][6,0][7,0][7,1][7,2][7,3][7,4][7,5][7,6][7,7][7,8][7,9][7,10][7,11][7,12]
 Successfully exit the maze!!!
 
 1 1 1 0 1 1 0 0 0 7 7 7 7 
 1 0 1 1 1 0 1 1 7 7 0 0 3 
 0 0 0 0 1 0 1 0 7 0 3 0 0 
 7 7 7 0 1 1 1 0 7 0 0 1 1 
 7 0 7 0 0 0 0 7 7 1 0 0 1 
 7 0 7 7 7 7 7 7 0 1 1 1 0 
 7 0 0 0 0 0 0 0 0 0 0 0 0 
 7 7 7 7 7 7 7 7 7 7 7 7 7 
 
 
  ----jGRASP: operation complete.
 

*********************************/
