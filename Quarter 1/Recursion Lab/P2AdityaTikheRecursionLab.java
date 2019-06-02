/***********************************************************************************************************************************************
 * Name: Aditya Tikhe               
 * Period: 2
 * Name of the Lab: Recursion LAB
 * Purpose of the Program: The purpose of this program is to call various methods to manipulate data such as reversing
                           a number through recursion
 * Due Date: 10/12/18
 * Date Submitted: 10/11/18
 * What I learned: I learned how to use recursive methods and brushed up on my knowledge of recursion from CS A.
 * How I feel about this lab: I like this lab a lot though it was a bit challenging due to my lack of experience 
                              with recursion. 
 * What I wonder: I wonder how I can get better at recursion.
 * Sources: I got much help from youtube and websites to relearn recursion. 
 * Student(s) who helped me (to what extent):  N/A
 * Student(s) whom I helped (to what extent):  N/A
 *************************************************************************************************************************************************/

import java.util.Scanner;   
public class P2AdityaTikheRecursionLab
{
   //Pre: c is a lower case letter - Post: all lower case letters a-char c are printed 
   public static void letters(char c)
   {   
      if(c == 'a')
         System.out.print(c);
      else
      {
         int temp = c-1;  
         letters((char)temp); 
         System.out.print(c + "");
      }
   }
   
   
	//Pre: none - Post: returns number of times two can go into x
   public static int twos(int x)
   {
      if(x -2 >= 0) 
         return 1 + twos(x-2);
      return 0;    
   }
   
   
	//Pre: none - Post: returns if x is a power of 3
   public static boolean powerof3(int x)
   {
      if(x==1) //3^0 = 1
         return true;
      if(x>=3) 
      {
         return (x%3)==0 && powerof3(x/3); //return true until x/3 mod 3 isnt 0 then it returns false
      }
      return false;
   }
   
   
	//Pre: none - Post: returns String of x reversed 
   public static String reverse(long x)
   {
      if((x/10)== 0) //if one digit
         return "" + x;
      if(x < 0) //checks if negative
         return "" + (x%10) + reverse(x/-10); 
      if(x >0)
         return "" + (x%10) + reverse(x/10);  // gets last then calls reverse with shorter 
      return "";
   }
   
   
   
	//Pre: x > 0 - Post: Prints x in base 5
   public static void base5(int x) 
   {
      if(x>=5)
      {
         base5(x/5); 
         System.out.print(x%5); 
      }
      else
      {
         System.out.print(x);
      }
   }
   
   
	// Pre: x > 0 - Post: Prints x with commas
   public static void printWithCommas(long x) //doesn't work for 6 digit numbers
   {
      if(x > 999)
      {
         printWithCommas(x /1000); //gets number that is 3 digits shorter
         if((x%1000)!=0)
            System.out.print("," + (x%1000)); //this if statement was added because 1000%1000 is 0 so it would only print one 0
         else
            System.out.print(",000");
      }
      else
      {
         System.out.print(x);
      
      }
   }
	
	
   public static void main(String []args)
   {
      Scanner scan = new Scanner (System.in);
      int choice;
      do
      {
         System.out.println("\n\n1)Letters"+
                           "\n2)Twos"+
                           "\n3)Power Of 3"+
                           "\n4)Reverse"+
                           "\n5)Base 5"+
                           "\n6)Print With Commas"+
                           "\n7)Exit");
         choice = scan.nextInt();
         if (choice == 1)
         {
            System.out.println("Enter a letter");
            char charA = scan.next().charAt(0);
            if (charA < 'a' || charA > 'z')
               System.out.println("That letter not valid");
            else
               letters(charA);
         }
         else if (choice == 2)
         {
            System.out.println("Enter a number");
            System.out.println(twos(scan.nextInt()));
         }
         else if (choice == 3)
         {
            System.out.println("Enter a number");
            System.out.println(powerof3(scan.nextInt()));
         }
         else if (choice == 4)
         {
            System.out.println("Enter a number");
            System.out.println(reverse(scan.nextLong()));
         }
         else if (choice == 5)
         {
            System.out.println("Enter a number");
            int number = scan.nextInt();
            if (number > 0)
               base5(number);
            else
               System.out.println("That number is not valid");
         }
         else if (choice == 6)
         {
            System.out.println("Enter a number");
            int number = scan.nextInt();
            if (number > 0)
               printWithCommas(number);
            else
               System.out.println("That number is not valid");
         }
      }while(choice != 7);
   }
}
