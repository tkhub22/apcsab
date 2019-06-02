/***************************************************************************************************************************
Name: Aditya Tikhe
Period: 2
Due date: 10/24/2017		
Date submitted: 10/23/2017
What I learned:
- I learned how to practice recursion and backtracing. 
- Also learned how to read files and other useful features.
How I feel about this lab: 
- I thoroughly enjoyed the lab as it allowed me to understand recursion better. Also, it incorporated other Java skills
  such as reading files and working with scanners. By noticing certain things like the "throws exception", it showed 
  me practical applications of what I learned earlier in the year. This lab was a perfect level of difficulty as it 
  allowed me to perfect my craft while brushing up on old topics.
CREDIT: the internet on how to read files, and the principles/comments of the maze lab.
 *************************************************************************************************************************/
   
import java.util.Scanner;
import java.io.*;
public class P2AdityaTikheAreaFill_shell
{
   public static char[][] grid = null;
   
   public static void main(String[] args) throws FileNotFoundException
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Filename: ");
      String filename = sc.next();
      grid = read(filename);
      display(grid);
      System.out.print("\nEnter ROW COL to fill from: ");
      int row = sc.nextInt();
      int col = sc.nextInt(); 
      fill(grid, row, col, grid[row][col]);
      display(grid);
      sc.close();
   }
      
      
   //pre: file exists and is in the same folder as area fill lab
   //post: reads a file inputted from the user
   // "C:\\Users\\Aditya\\Desktop\\APCS AB\\Quarter 1\\Area Fill Lab\\area.txt" <== how to get file from my computer
   public static char[][] read(String filename)throws FileNotFoundException //access file
   {
      File file = new File (filename);
      
      Scanner infile = new Scanner(file);
      char [][] g = new char[infile.nextInt()][infile.nextInt()];
      infile.nextLine();
      int count = 0;
      while(infile.hasNextLine()) //while there is a next line to read in the file
         g[count++] = infile.nextLine().toCharArray();
         
      return g;
      
   }
      
      
   //pre: file exists
   //post: //display map print content of each element
   public static void display(char[][] g) 
   {  
           
      for(int r = 0; r < g.length; r ++)
         {
            for(int c = 0; c < g[r].length; c++) //loops through double array and prints 
            {
               System.out.print(g[r][c] + " ");
            }
           System.out.println(); //new line 
         }
   }
      
      
   //pre: g exists and read properly
   //post: changes the elements adjacent, alike elements to stars, recursively
   public static void fill(char[][] g, int r, int c, char ch) //recursive method   
   {
      if(r < g.length && c < g[0].length && r>=0 && c >=0 && g[r][c] == ch) //conditions to change elements around
      {
         //checks and changes North South East West of coordinates
         g[r][c] = '*';
         fill(g,r,c+1,ch);
         fill(g,r,c-1,ch);
         fill(g,r+1,c,ch);
         fill(g,r-1,c,ch);
      }       
   }
}