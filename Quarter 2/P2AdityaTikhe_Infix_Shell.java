/***********************************************************************
Name: Aditya Tikhe     
Period: 2
Date: 12/13/18   
What I Learned: More practice working with changing infix to postfix expressions. Though the lab packet 
                gave the algorithm and we discussed how to do it in class, it was a whole new experience
                actually coding it. Also, while proofing and debugging my lab I had to do the conversion to 
                postfix manually to check my output was right.
                 
How I feel about this lab: This lab was particularly interesting to me, and I like coding this project. There were 
                           some setbacks and where the logic in the packet didn't make sense to me, so I utilized my 
                           own thinking. This led me to add some of my own code, though kind of inefficent, it made the 
                           algorithm made sense to me.
                                       
Credit (person who helped me): N/A because the packet gave the algorithm
Student(s) whom I helped (to what extent): N/A
************************************************************************/ 
import java.util.*;
public class P2AdityaTikhe_Infix_Shell
{
   public static void main(String[] args)
   {
      System.out.println("Enter an infix expression, single digits");
      System.out.println("such as 1+2*3 or (1+2)*3");
      Scanner keyboard = new Scanner(System.in);    //  (3*(4+5)-2)/5
      String s = keyboard.next();  
      while (!s.equals ("end"))
      {
         String  postfixExp = trans(s);
         System.out.println (s + " --> " + postfixExp  + " --> " + P2AdityaTikhe_Postfix_Shell.eval (postfixExp) + "\n");
         s = keyboard.next();  
      }
   }  // end of main
   
   
   //pre: user enters a valid infix expression
   //post: returns a postfix string
   public static String trans(String x)
   {
      Stack <Character> stack = new Stack();
      String postfixString = "";
      char leftParen = '(';
      char rightParen = ')'; 
      char index;
      for(int i = 0; i < x.length(); i++)
      {
         index = x.charAt(i); //each character
         
         if(!(P2AdityaTikhe_Postfix_Shell.isOperator(index))) //checks if number
         {
            postfixString += "" + index;
         }
         
         
         if(index == leftParen)
         {
            stack.push(index);
         }
         
         if(index == rightParen) 
         {
            while (!stack.isEmpty() && stack.peek() != leftParen) 
            {
               postfixString += stack.pop();
            }  
         }
         
         
         if(P2AdityaTikhe_Postfix_Shell.isOperator(index))
         {
            while(!stack.isEmpty() && (precedence(index) <= precedence(stack.peek()))) //flip
            {
               postfixString += stack.pop();
            }
            stack.push(index);
         }
      }
      while(!stack.isEmpty())
         postfixString += stack.pop();
      
      String newString = "";
      for(int j = 0; j < postfixString.length(); j++) //this loop takes out any parenthesis in String
      {
         if(postfixString.charAt(j) != '(' && postfixString.charAt(j) != ')')
            newString += "" + postfixString.charAt(j);
      }
      
      return newString;
   }  // end of trans
   
   //Pre: takes a char
   //Post: returns 0 if the precedence of the operator is lower else returns 1000
   public static int precedence (char operator) 
   {
      if(operator == '+' || operator == '-')
         return 0;
      else 
         return 1000;
   }
   
}  // end of Infix_Shell