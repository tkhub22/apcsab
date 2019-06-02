import java.util.*;

public class HashMapPracticeREEEE {

	public static void main(String[] args) {
		Map<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();

		ArrayList<String> people = new ArrayList<String>();
		people.add("Anfinyy");
		people.add("Kevin");

		ArrayList<String> animals = new ArrayList<String>();
		animals.add("Zebra");
		animals.add("Camel");

		map.put(1, people);
		map.put(0, animals);

		System.out.println(map);

		System.out.println("");

		System.out.println(map.keySet());

		// 3/22
		Map<Integer, Integer> mm = new TreeMap<Integer, Integer>();
		mm.put(1, 1);
		mm.put(2, 2);
		mm.put(3, 3);
		mm.put(4, 4);

		System.out.println("\n\n" + mm);

		Map<Integer, Integer> newMM = ((TreeMap<Integer, Integer>) mm).descendingMap(); // how to reverse the map
		System.out.println(newMM);

		
		//reversing the map
		Map<Integer, Integer> r = new TreeMap();
		for (int i = 0; i < 5; i++) {
			//ArrayList <Integer> a = new ArrayList <Integer>();
			//a.add(i + 100);
			//a.add(i + 1000);
			//a.add(i + 10000);
			r.put(i, i + 100);
		}
		r.put(7, 102);
		r.put(8, 101);
		r.put(9, 102);
		System.out.println("\n\n" + r);

		System.out.println("to reverse this map...\n");

		Map< Integer, ArrayList<Integer>> reverse = new TreeMap();
		for(Integer key : r.keySet()) //reverse
		{
			Integer value = r.get(key);
			if(!reverse.containsKey(value))
				reverse.put(value, new ArrayList<Integer>());
			reverse.get(value).add(key);
		}
		
		
		System.out.println(reverse);
		
		String [] s = new String [] {"I", "like", "ice cream", "like"};
		System.out.println(wordCount(s));
		
		
		System.out.println(wordMultiple(s));
		
		
		List <Integer> s1 = new ArrayList <Integer> ();
		s1.add(3);
		s1.add(7);
		s1.add(3);
		s1.add(-1);
		s1.add(2);
		s1.add(3);
		s1.add(7);
		s1.add(2);
		s1.add(15);
		s1.add(15);
		
		List <Integer> s2 = new ArrayList <Integer>();
		s2.add(-5);
		s2.add(15);
		s2.add(2);
		s2.add(-1);
		s2.add(7);
		s2.add(15);
		s2.add(36);
		
		System.out.println(s1 +"\n"+ s2);
		System.out.println(countCommon(s1,s2));

	}
	
	public static Map<String, Integer> wordCount(String [] strings)
	{
		Map <String,Integer> map = new TreeMap();
		for(String s : strings){
			if(!map.containsKey(s))
				map.put(s, 1);
			else
				map.put(s, map.get(s) + 1);
		}
		
		return map;
	}
	
	public static Map<String, Boolean> wordMultiple (String [] strings)
	{
		Map <String,Boolean> map = new TreeMap();
		for(int i = 0; i < strings.length; i ++)
		{
			ArrayList <String> a = new ArrayList <String> ();
			for(int j = 0; j < strings.length; j++)
			{
				if(strings[i].equals(strings[j]))
						a.add(strings[i]);
			}
			if(a.size() >= 2)
				map.put(strings[i], true);
			else
				map.put(strings[i], false);
		}
		return map;
	}
	
	public static int countCommon(List <Integer> s1, List <Integer> s2)
	{
		Set<Integer> s3 = new HashSet();
		for(Integer x: s2)
		{
			if(s1.contains(x))
				s3.add(x);
		}
		
		return s3.size();
	}

}
