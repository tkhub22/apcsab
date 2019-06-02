/***************************************************************************************************************************
Name: Aditya Tikhe
Period: 2
Due date: 10/21/2018		
Date submitted: 10/21/2018
Purpose: click on a square to solve the maze for you.
What I learned: 
- I learned how to recursively backtrack and find the solution to a maze by exploring all the options through an algorithm.
- I revisited GUI after a long time and based this program off the Battleship GUI from CS A. (therefore some unecessary variables and weird names for variables)
How I feel about this lab: 
- I like this lab because it gave me a practical application for backtracking. It also made me understand recursion more,
   as I had to draw out how my algorithm would play out.
CREDIT: I refrenced the Battleship and other GUI programs to get a better understanding of GUI. Also, picked up tips and tricks
         from other students in my class to better understand how backtracking works.
*************************************************************************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class P2AdityaTikheMazePanel extends JPanel
{
   private JButton[][] board; //what users see
   private int hits, torpedoes;
   private JLabel label;
   private JButton reset; //actually the solve button
   private JButton quit; 
   public int count; 
   /*private int [] [] matrix = 
   {
      {1,1,1,1},
      {1,0,0,1},
      {1,1,0,1},
      {0,0,0,1}
   };*/
   
   private int [] [] matrix = 
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
   
   public P2AdityaTikheMazePanel()
   {
      setLayout(new BorderLayout());
      hits = 0;
      torpedoes = 1; //tries to click a square
      count = 0;
      
      JPanel north = new JPanel();
      north.setLayout(new FlowLayout());
      add(north, BorderLayout.NORTH);
      label = new JLabel("Click a white square. Black squares are walls. Click \"Solve Maze!!\"");
      north.add(label);
   
      JPanel center = new JPanel(); //sets up board
      center.setLayout(new GridLayout(matrix.length,matrix[0].length));
      add(center, BorderLayout.CENTER);
      board = new JButton[matrix.length][matrix[0].length];
      
       
      for(int r = 0; r < matrix.length; r++) //sets up squares
      {
         for(int c = 0; c < matrix[0].length; c++)  
         {
            board[r][c] = new JButton();
            board[r][c].setBackground(Color.BLACK);
            if((matrix[r][c]) == 1)
            {
               board[r][c].setBackground(Color.WHITE);
            }
            if((matrix[r][c]) == 7)
            {
               board[r][c].setBackground(Color.GREEN);
            }
            board[r][c].addActionListener(new Handler1(r, c) );
            center.add(board[r][c]);
         }
      }
      
   
      reset = new JButton("Solve Maze!!");
      reset.addActionListener( new Handler2() );
      reset.setEnabled(true);
      add(reset, BorderLayout.EAST);
      
      quit = new JButton("Quit"); //quit button
      quit.addActionListener( new Handler3() );
      quit.setEnabled(true);
      add(quit, BorderLayout.SOUTH);
      
      
   }
   
   
   private class Handler1 implements ActionListener
   {
      private int myRow, myCol;
      public Handler1(int r, int c)
      {
         myRow = r;
         myCol = c;
      }  
      
      public void actionPerformed(ActionEvent e) //what happens when you click a square
      {
         if(torpedoes > 0)
         {
            torpedoes--;
         
            if(matrix[myRow][myCol] == 1)
            {
               label.setText("You have 1 shot lets find the end!"); 
               board[myRow][myCol].setBackground(Color.red);
               board[myRow][myCol].setEnabled(true);
               count++;
               if(findAnExitHelper(myRow, myCol))
               
               {
                  label.setText("starting here");
                  reset.setVisible(true);
                  reset.setEnabled(true);
                  for(int r = 0; r < matrix.length; r++)
                  {
                     for(int c = 0; c < matrix[0].length; c++)
                     {
                        board[r][c].setEnabled(false);
                     }
                  }
               }
            	 
            }
            else
            {
               label.setText("You have cliked a wall! Try again"); //what happens if you click a wall
               board[myRow][myCol].setEnabled(true);
               board[myRow][myCol].setEnabled(false);
            
            
            }
         }
      
      }   
   }
      
   private class Handler2 implements ActionListener ///what happens when you click a square and then click solve maze
   {
      public void actionPerformed(ActionEvent e)
      {
         for(int r = 0; r < matrix.length; r++)
         {
            for(int c = 0; c < matrix[0].length; c++)
            {
               board[r][c].setBackground(Color.BLACK);
               if((matrix[r][c]) == 1)
               {
                  board[r][c].setBackground(Color.WHITE);
               }
               else if((matrix[r][c]) == 7)
               {
                  board[r][c].setBackground(Color.GREEN);
               }

               board[r][c].setEnabled(true);
            }
         }
       
         hits = 0;
         label.setText("Maze");
         torpedoes = 1;
         count = 0;
      }  
   }
   
   private class Handler3 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
          System.exit(0);        //closes program
      }
   }
   
   
   
   public boolean findAnExit (int x, int y) 
   {
      String path = "";
      return findAnExitHelper(x, y); 
   }  // FindAnExit
   


   
   public boolean findAnExitHelper (int x, int y) 
   {  
      int lengthX = matrix.length; //length of array         
      int lengthY = matrix[0].length; //length of array      
      boolean temp = false;
      
      if (works (x, y)) //calls if satisifies works
      {
      
         matrix[x][y] = 3;  // cell has been tried
      
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
            matrix[x][y] = 7;
      }   
      return temp;
   } // findAnExitHelper
   
   
   private boolean works (int x, int y) //method that checks if it blocked/tried/in bounds
   {
      int lengthX = matrix.length; //length of array         
      int lengthY = matrix[0].length; //length of array      
   
      boolean temp = false;
      if (x >= 0 && x < lengthX && y >= 0 && y < lengthY)
      {
         if (matrix[x][y] == 1)
         {
            temp = true;
         }
      }
      return temp;
   }  //works
   


   public static void main(String[] args)
   {
      boolean USE_CROSS_PLATFORM_UI = true;
      if(USE_CROSS_PLATFORM_UI){
         try{UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());}
         catch (Exception e) {e.printStackTrace();}
      }
   
      JFrame frame = new JFrame("Maze!!");
      frame.setSize(500, 500);
      frame.setLocation(200, 100);
      frame.setContentPane(new P2AdityaTikheMazePanel());
      frame.setVisible(true);
      frame.getContentPane().setBackground(Color.YELLOW);
   } //main
}
