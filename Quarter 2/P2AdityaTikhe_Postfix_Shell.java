/***********************************************************************
Name: Aditya Tikhe     
Period: 2
Date: 12/10/18    
What I Learned: I gained a better grasp over infix to postfix expressions while
                working with the stack data structure. Also, I deepened my knowledge
                for problem solving. Though the lab gave the basic logic for this lab, 
                the challenge was implenting the logic while simultaneously manipulating
                different data types like int and char.  
How I feel about this lab:
               - This lab was enjoyable but challenging. This is so because it was a bit difficult
                  understanding how to do the eval method but once I took a break and thought about it
                  it was easy to grasp. All in all, it was very much worth my time to learn because 
                  I learned how to evaluate postfix while working with stack.
                          
Credit (person who helped me): N/A
Student(s) whom I helped (to what extent): N/A
************************************************************************/    
import java.util.*;
public class P2AdityaTikhe_Postfix_Shell
{
   public static final String operands = "+-*/";
   public static final String letters = "abcdefghijklmnopqrstuvwxyz";
   public static void main(String[] args)
   {
      System.out.println("Enter a valid postfix expression (single digits only),");
      System.out.println("such as 35*1+");
      Scanner keyboard = new Scanner(System.in);
      String s = keyboard.next();  
      
      while(!s.equals("end")) 
      { 
         System.out.println(s + "  --->  " + eval(s) + "\n");
      // //        System.out.println((s = "354*+7*") + " = " + eval(s) + "\n");
      // //          System.out.println((s = "82-") + " = " + eval(s) + "\n");
      // //          System.out.println((s = "82/") + " = " + eval(s) + "\n");
         s = keyboard.next();
      }
      
      
   }
   //pre: each "number" to be evaluated is single digit, and the input is a valid postfix expression
   //post: returns an integer value for the expression given
   public static int eval(String x)
   {
      for (int k = 0; k < x.length(); k++) //checks if invalid input
      {
         for(int inner = 0; inner < letters.length(); inner++)
         {
            if(x.charAt(k) == letters.charAt(inner))
            {
               System.out.println("use only numbers not letters!");
               return 0;
            }
         }
      }
      Stack<Integer> stack = new Stack <Integer> ();
      int temp = 0;
      int temp1;
      int temp2;
      char cTemp;
      int finalAnswer;
      for(int i = 0; i < x.length(); i++)
      {
         cTemp = x.charAt(i); //converted to char so it can be inputted into isOperator()
         if(!(isOperator(cTemp)))
         {
            temp = Integer.valueOf(x.substring(i,i+1));
            stack.push(temp); //pushes numbers into stack
         }
         
         if(isOperator(cTemp) && stack.size() >= 2) //checks if it can be evaluated
         {
            temp1 = stack.pop();
            temp2 = stack.pop();
            stack.push(eval(temp2, temp1, cTemp)); 
         }
            
      }
   
      finalAnswer = stack.pop();
      return finalAnswer;
      
   }
   
   
   //pre: given inputs of int, int, char
   //post: returns an evaluated integer that underwent the appropriate operation using the two integer inputs 
   public static int eval(int a, int b, char ch)
   {
      if(ch == '+')
         return a + b;
      if(ch == '-')
         return a-b;
      if(ch == '*')
         return a*b;
      if(ch == '/')
         return a/b;
      return 0;
   }
   //pre: input is a single char.
   //post: returns whether or not a char is an operand or not
   public static boolean isOperator(char ch)
   {
      String ch1 = Character.toString(ch);//converts to string 
      if(operands.contains(ch1))
         return true;
      return false;   
   }
}
