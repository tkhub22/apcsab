
//*********************************************************************************************************************************
//Name: Aditya Tikhe  
//Period: 2                                                
//Date: 3/26/19
//What I learned:  I learned how to work with the HashMaps data structure and how to reverse it.I also learned something new about
//	adding things to a file.
//How I feel about this lab: This lab was very nice and introductory for HashMaps. I got some good practice with working with HashMap methods.
//What I wonder: why do enhanced for loops work so much nicer than Iterators for going through sets?
//***********************************************************************************************************************************

import java.io.*;
import java.util.*;

public class P2_AdityaTikhe_Dictionary_shell {
	private static Map<String, Set<String>> eng2spn = new TreeMap<String, Set<String>>();

	public static void main(String[] args) throws Exception {
		try {
			System.setOut(new PrintStream(new FileOutputStream(
					"/Users/Batman/Documents/workspace/APCS AB Eclipse/src/dictionaryOutput.txt")));

			/// System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
		}

		catch (Exception e) {
			System.out.println(e);
		}
		
		//commented out for extension...
		/// Map<String, Set<String>> eng2spn = new TreeMap<String,
		/// Set<String>>();
		/*
		 * Scanner infile = new Scanner(new File(
		 * "/Users/Batman/Documents/workspace/APCS AB Eclipse/src/spanglish.txt"
		 * ));
		 * 
		 * while(infile.hasNext()) { add(eng2spn, infile.next(), infile.next());
		 * }
		 * 
		 * 
		 * infile.close();
		 */
		input();

		System.out.println("ENGLISH TO SPANISH");
		display(eng2spn);

		Map<String, Set<String>> spn2eng = reverse(eng2spn);
		System.out.println("\n\nSPANISH TO ENGLISH");
		display(spn2eng);

	}

	public Map getEnglishDict() {
		return eng2spn;
	}

	public static void input() throws FileNotFoundException {
		Scanner infile = new Scanner(new File("/Users/Batman/Documents/workspace/APCS AB Eclipse/src/spanglish.txt"));

		while (infile.hasNext()) {
			add(eng2spn, infile.next(), infile.next());
		}
		infile.close();
	}

	// Note: must explain how your method works
	// Postcondition: display the contents of a dictionary
	public static void display(Map<String, Set<String>> m) {
		for (String key : m.keySet()) // loops through the map
		{
			System.out.println(key + ": " + m.get(key)); // prints the key and
															// the value (list)
		}
	}

	// Note: must explain how your method works
	// postcondition: insert a new pair to the English to Spanish Dictionary
	public static void add(Map<String, Set<String>> engToSpnDictionary, String word, String translation) {
		if (!engToSpnDictionary.containsKey(word)) // check if there is not an english word for it already
		{
			Set<String> set = new TreeSet<String>(); // creates a tree set to put in of that word
			set.add(translation);
			engToSpnDictionary.put(word, set);
		} else {
			Set<String> set = engToSpnDictionary.get(word); // uses the original set and adds it to it
			set.add(translation);
			engToSpnDictionary.put(word, set);
		}

	}

	// Note: must explain how your method works
	// postcondition: returns a Spanish to English dictionary
	public static Map<String, Set<String>> reverse(Map<String, Set<String>> engToSpnDictionary) {
		Map<String, Set<String>> reverse = new TreeMap<String, Set<String>>(); // new Spanish to English dictiontary + tree set to alphabetize

		for (String english : engToSpnDictionary.keySet()) // for every English word in english dictionary
		{
			Set<String> spanish = engToSpnDictionary.get(english);
			for (String spanishItr : spanish) // looping through the spanish words set
			{
				if (!reverse.containsKey(spanishItr)) // no duplicate keys
				{
					reverse.put(spanishItr, new HashSet<String>()); //creates a <K,V> for the spanish word
				}
				reverse.get(spanishItr).add(english); //adds English word to set
			}
		}
		return reverse;
	}
}
/********************
 * INPUT: holiday fiesta holiday vacaciones party fiesta celebration fiesta
 * <etc.>
 *********************************** 
 * OUTPUT: ENGLISH TO SPANISH banana [banana] celebration [fiesta] computer
 * [computadora, ordenador] double [doblar, doble, duplicar] father [padre]
 * feast [fiesta] good [bueno] hand [mano] hello [hola] holiday [fiesta,
 * vacaciones] party [fiesta] plaza [plaza] priest [padre] program [programa,
 * programar] sleep [dormir] son [hijo] sun [sol] vacation [vacaciones]
 * 
 * SPANISH TO ENGLISH banana [banana] bueno [good] computadora [computer] doblar
 * [double] doble [double] dormir [sleep] duplicar [double] fiesta [celebration,
 * feast, holiday, party] hijo [son] hola [hello] mano [hand] ordenador
 * [computer] padre [father, priest] plaza [plaza] programa [program] programar
 * [program] sol [sun] vacaciones [holiday, vacation]
 * 
 **********************/
