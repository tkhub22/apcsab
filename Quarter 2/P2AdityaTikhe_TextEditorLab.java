/*
//  Name: Aditya Tikhe
//  Date: 12/10/18
//  Period: 2
//  What I Learned:
   - practiced the different stack functions
   - manipulation of stack, and using in par with other data structures such as string. 

//  How I feel about this lab:
      - I honestly over thought this lab, and was confused on how to do it as efficiently as possible. This mindset
         drove me to create unecessary Stack variables. After sleeping on this lab Friday night, and waking up early on Saturday
         to complete it, I realized it was quite simple. I thoroughly enjoyed the lab after that turning point and it made
         me think of an efficient algorithm while appreciating the Stack data structure more.
//
*/
import java.util.*;
public class P2AdityaTikhe_TextEditorLab
{
   //prompts user to input values and stops when user inputs the word "end"
   public static void main(String[] args)
   {
      System.out.println("Enter text. \nType '-' to delete the character before \nor $ to delete everything upto that point...");
      Scanner console = new Scanner(System.in);
      String s = console.nextLine();
      
      while(!s.equals("end"))
      {
         System.out.println("You entered: " + s);
         System.out.println("Fixed: " + fix(s)); 
         System.out.println("\nAgain? type: 'end' to quit");
         s = console.nextLine();   
      }
   }
   
   //pre: string input size is >0
   //post: returns a string that deletes characters accordingly
   public static String fix(String input)
   {
      Stack <String> stack = new Stack <String> ();
    
      for(int i = 0; i < input.length(); i++) //string to stack
      {
         stack.push(input.substring(i,i+1));
      }
            
      String ss = "";  
      while(!stack.isEmpty()) //goes through each character
      {
         if(stack.peek().equals("$"))
         {
            purge(stack);
         }
         
         else if(stack.peek().equals("-"))
         {
            try//if '-' is the first character because if it deletes two characters an error will be thrown
            {
               deleteTwo(stack); 
            }
            catch(Exception e) {
               purge(stack); 
            }
         } 
         else
         {
            ss+= ""+stack.peek();
            stack.pop();
         }
      }
      
            
       //below to reverse a string because stack goes from last to first so the ss variable is modified correctly but backwards.
      String reverse = "";
      for(int i = ss.length() - 1; i >= 0; i--)
      {
         reverse = reverse + ss.charAt(i);
      }   
      return reverse;
   }
   //pre: takes a stack.
   //post: returns an empty stack
   public static void purge (Stack<String> stack) //empties stack
   {
      while(!stack.isEmpty())
      {
         stack.pop();
      }
   }
   
   //pre: takes a stack
   //post: deletes the '-' character and the one before it as well.
   public static void deleteTwo(Stack<String> stack) //deletes - and previous letter
   {
      stack.pop();
      stack.pop();
   }
}
