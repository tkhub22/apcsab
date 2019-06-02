//Name:   Aditya Tikhe  P:2
//Date: 3/21/19
//What I learned: I've learned about how to work with the HashMaps data structure and how to manipulate it.
//How I feel about this lab: I enjoyed this lab because it was relatively simple, but helped me understand
//							how to make hash tables and how to traverse them efficiently. 
//							Furthermore, I had a bit of difficulty reversing it, but once I found out how to 
//							do it I appreciated it and understood that this is a necessary skill.
//I am wondering: I wonder if there is a more efficient way or built in way to reverse the K and V of a hash table.
 import java.util.*;
 public class P2_AdityaTikhe_ActingSchool_shell
 {
    public static void main(String[] args)
    {
       Map<String, String> sGrades = new HashMap<String, String>();     //HashMap
    
       sGrades.put("Jack Nicholson", "A-");
       sGrades.put("Humphrey Bogart", "A+");
       sGrades.put("Audrey Hepburn", "A");
       sGrades.put("Meryl Streep", "A-");
       sGrades.put("Jimmy Stewart", "A");
    
    	//display initial data
       for(String key : sGrades.keySet())
       {
    	   System.out.println(key + " (" + sGrades.get(key) + ")");
       }
            
    	//reverse the map    //TreeMap
    	Map <String, ArrayList<String>> reverse = new TreeMap <String, ArrayList<String>>(); //creates new hashmap
    	for(String name: sGrades.keySet()) //loops through og
    	{
    		String grade = sGrades.get(name); //grade
    		if(!(reverse.containsKey(grade))) //to make sure there are no duplicate grades
    		{
    			reverse.put(grade, new ArrayList<String>()); //makes a <K,V> for each grade
    		}
    		reverse.get(grade).add(name); //add the name to the correct grade
    	}
       
       System.out.println();
    	
       //display the reversed map
    	for(String key : reverse.keySet())
        {
     	   System.out.println(key  + " "+ reverse.get(key));
        }
    }
 }
	
	/**********************
  Audrey Hepburn (A)
  Humphrey Bogart (A+)
  Jack Nicholson (A-)
  Jimmy Stewart (A)
  Meryl Streep (A-)
  
  A: [Audrey Hepburn, Jimmy Stewart]
  A+: [Humphrey Bogart]
  A-: [Jack Nicholson, Meryl Streep]
  
  *********************/
