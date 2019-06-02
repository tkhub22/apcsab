//Name:   Aditya Tikhe
//Date: 3/16/19
//What I learned: I learned how Sets work and how to add to them. Also, TreeSets are automatically ordered.
//How I feel about this lab: I like this lab because it was straightforward and encompassed many useful 
				//			Java skills such as working with files.
//What I wonder:  What are some more uses for Sets apart from not having duplicates?
//Question:  If Java didn't implement Sets, how would you solve this programming problem?  Be creative!
			//A: I would make a string of all the alphabets and its upper and lower case versions and then compare each char of the line
				// to the string, and if it is a upper/lower letter than you add it to the "set", then if a duplicate appears it would check
				// the "set" if its already there, if it is then it skips over it.
//credits: online for opening file and reading contents + reviewing how iterator works.
//
 import java.util.*;
 import java.io.*;
  public class P2_AdityaTikhe_SetsOfLetters_shell
 {
     public static void main(String[] args) throws FileNotFoundException
    {
    	 
    	 try{
    	 File file = new File("/Users/Batman/Documents/workspace/APCS AB Eclipse/src/declerationCS.txt");
    	 FileReader fileReader = new FileReader(file);
    	 BufferedReader bufferedReader = new BufferedReader(fileReader);
    	 String line;
    	 Set <Character> commonLower= null;
    	 Set <Character> commonUpper= null;
    	 Set <Character> commonPunc = null;
			while ((line = bufferedReader.readLine()) != null) 
			{
				System.out.println(line );
				char [] array = line.toCharArray();
				Set <Character> lower = new TreeSet <Character>();
				Set <Character> upper = new TreeSet <Character>();
				Set <Character> punc = new TreeSet <Character>();
				
				for(int i = 0; i < array.length; i++)
				{
					char c = (char)array[i];
					if(Character.isLetter(c))
					{
						if(Character.isLowerCase(c))
							lower.add(array[i]);
						else
							upper.add(array[i]);
					}
					else
						punc.add(array[i]);
				}
				System.out.println("Lower Case: " + lower + "\nUpper Case: " + upper + "\nOther: " + punc + "\n\n");
				commonLower = common(lower, commonLower);
				commonUpper = common(upper, commonUpper);
				commonPunc = common(punc,commonPunc);
			}
			
			System.out.println("\nCommon Lower Case: " + commonLower);
			System.out.println("Common Upper Case: " + commonUpper);
			System.out.println("Common Other: " + commonPunc);
			fileReader.close();
    	 }
    	 catch (Exception e) {
 			e.printStackTrace();
    	 }
         // Need to check the common characters in each group:  lower, upper, punctuation marks.
         // You need to use an iterator to iterate over each set.  You might need to use the contains
         // and remove methods of Set.
    }
     //pre:
     //post: returns the commons removing values if they are not present in the line set
     public static Set <Character> common (Set<Character> line, Set <Character> commons)
     {
    	 if(commons == null) //checks if commons is null (basically for the first line)
    		 return line;
    	 Iterator <Character>i = commons.iterator();
    	 while(i.hasNext())
    	 {
    		 char c = (char)i.next();
    		 if(!(line.contains(c)))
    		 {
    			 i.remove();
    		 }
    	 }
    	 return commons;
     }
 }