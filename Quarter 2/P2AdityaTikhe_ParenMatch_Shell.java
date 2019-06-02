/***********************************************************************
Name: Aditya Tikhe
Period:2
Date: 12/11/18   
What I Learned: I learned how to manipulate the stack data structure, and how
                 last in first out (LIFO) works.
           
How I feel about this lab: This lab made me think, and think of a creative approach instead of 
                           brute forcing a solution by checking every case.
               
                         
Credit (person who helped me): N/A
Student(s) whom I helped (to what extent): /a
************************************************************************/ 
import java.util.*;
public class P2AdityaTikhe_ParenMatch_Shell
{
   public static final String left  = "([{<";
   public static final String right = ")]}>";
   public static final String letters = "abcdefghijklmnopqrstuvwxyz";
   public static final String operands = "+-*/";
   public static final String numbers = "1234567890";
   public static void main(String[] args)
   {
   
      System.out.println("Enter an expression with grouping symbols,");
      System.out.println("such as (2+3)-[5*(6+1)]IndexMals");
      Scanner keyboard = new Scanner(System.in);
      String s = keyboard.next();  
      while(!s.equals("end"))
      {
         boolean flag = check(s);
         if(flag)
            System.out.println(s + " is good.");
         else
            System.out.println("No, no, no.  Bad.  " + s);
         System.out.println();
         s = keyboard.next();
      }
   }
   
   // precondition: user inputs a string
   // postcondition: returns true if it is a valid expression and false if it isn't
   public static boolean check(String s)
   {   
      
      //convert string to stack
      Stack <String> stack = new Stack <String> ();
      for(int i = 0; i < s.length(); i++)
      {
         stack.push(s.substring(i,i+1));
      }
      
      //checks if valid expression
      if(stack.size() <3 )
      {
         System.out.println("enter a valid expression, ex: 1+1");
         return false;
      }
      
      //checks if same number of parenthesis on either side or if a right bracket comes before left bracket.
      int counter = 0;
      int oCounter = 0;
      while(!stack.isEmpty())
      {
         if(right.contains(stack.peek()))
         {
            counter--;
         }
         if(left.contains(stack.peek()))
         {
            counter++;
         }
         if(counter >0)
         {
            return false;
         }
         if(letters.contains(stack.peek()))
            return false;
         if(numbers.contains(stack.peek()))
            oCounter--;
         if(operands.contains(stack.peek()))
            oCounter++;
         if(oCounter > 0)
            return false;
         stack.pop();
      }

      if(counter !=0) 
      {
         return false;
      }  
     
      System.out.println("\n" + stack + "\n");
      
      return true;
   }
}
