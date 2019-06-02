
/*Aditya Tikhe P:2
 * Extension for Dictionary
 * What I learned:
 * 	- worked more with hashtable methods
 * 
 * Credits: n/a
 * 
 * Note: I am at a college visit this weekend so I did not have much time doing this lab, I will
 * 		 work on the extension more next week for general knowledge.
 */
import java.util.*;
import java.io.*;

public class Dictionary_Driver {
	public static void main(String[] args) {
		
		System.out.println("note: additions to the list will not be saved to the actual dictionary textfile for the sake of completing the lab!\n\n");
		Scanner sc = new Scanner(System.in);
		int option = 0;
		while (option != 5) {
			System.out.println(
					"Do you want english to spanish or spanish to english \nenter 1 for english > spanish \nenter 2 for spanish > english \nenter 3 to add an English word with Spanish translation  \noption:");
			option = sc.nextInt();

			String input;

			if (option == 1) {
				System.out.println("What english word do you want to translate to spanish: ");
				input = sc.next();
				System.out.println("The translation for your English word '" + input + "' is: " + englishFind(input));
			} else if (option == 2) {
				System.out.println("What spanish word do you want to translate to english: ");
				input = sc.next();
				System.out.println("The translation for your Spanish word '" + input + "' is: " + spanishFind(input));
			}
			else if(option == 3)
			{
				System.out.println("What English word do you want to create a translation for: ");
				input = sc.next();
				System.out.println("What is the Spanish translation for this word: ");
				String spanishTran = sc.next();
				addEnglish(input, spanishTran);
				System.out.println("Your contribution has been added!\n Try searching for it!");
			}
			else {
				System.out.println("enter a 1 or 2. Try again!");
			}

			System.out.println("\n\nContinue? \nenter 4 to continue\nenter 5 to quit \noption: ");
			option = sc.nextInt();
			System.out.println();
		}

	}

	public static Set<String> englishFind(String s) {
		P2_AdityaTikhe_Dictionary_shell d = new P2_AdityaTikhe_Dictionary_shell();

		try {
			Scanner infile = new Scanner(
					new File("/Users/Batman/Documents/workspace/APCS AB Eclipse/src/dictionaryOutput.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			d.input();
		} catch (Exception e) {
			System.out.println(e);
		}

		Map<String, Set<String>> englishDict = d.getEnglishDict();

		// String spanishTranslation;
		for (String english : englishDict.keySet()) // traverses through english
													// dict
		{
			if (english.equals(s)) { //checks if word is there
				return englishDict.get(english);
			}
		}
		Set<String> blah = new HashSet<String>();
		blah.add("Sorry, cannot find the translation for this English word");
		return blah;
	}

	public static Set<String> spanishFind(String s) {
		P2_AdityaTikhe_Dictionary_shell d = new P2_AdityaTikhe_Dictionary_shell();

		try {
			Scanner infile = new Scanner(
					new File("/Users/Batman/Documents/workspace/APCS AB Eclipse/src/dictionaryOutput.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			d.input();
		} catch (Exception e) {
			System.out.println(e);
		}

		Map<String, Set<String>> spanishDict = d.reverse(d.getEnglishDict());

		for (String spanish : spanishDict.keySet()) // traverses through spanish
													// dict
		{
			if (spanish.equals(s)) { //checks if word is there
				return spanishDict.get(spanish);
			}
		}
		Set<String> blah = new HashSet<String>();
		blah.add("Sorry, cannot find the translation for this English word");
		return blah;
	}
	
	public static void addEnglish (String english, String spanish){
		P2_AdityaTikhe_Dictionary_shell d = new P2_AdityaTikhe_Dictionary_shell();

		try {
			Scanner infile = new Scanner(
					new File("/Users/Batman/Documents/workspace/APCS AB Eclipse/src/dictionaryOutput.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			d.input();
		} catch (Exception e) {
			System.out.println(e);
		}

		Map<String, Set<String>> englishDict = d.getEnglishDict();
		
		Set <String> set = new TreeSet <String>();
		set.add(spanish);
		englishDict.put(english, set);
	}	
}
