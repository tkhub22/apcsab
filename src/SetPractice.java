   import java.util.*;
    public class SetPractice
   {
       public static void main(String[] args)
      {
         Set<String> s = new HashSet<String>();
         s.add("Mary");
         s.add("Joan");
         s.add("Mary");    //duplicate!
         s.add("Dennis");
         s.add("Bob");
         s.add("MaryAnn");
         s.add("Zoe");
         s.add("Maya"); //duplicate!
         s.add("Maya"); //duplicate!
         s.add("Maya"); //duplicate!
         s.add("Maya"); //duplicate!
         s.add("Maya"); //duplicate!
         s.add("Maya"); //duplicate!
         s.add("Maya"); //duplicate!
         s.add("Maya"); //duplicate!
         System.out.println("Size:  " + s.size());
         Iterator <String> it = s.iterator();
         while(it.hasNext())
            System.out.print((String) it.next() + " ");
         System.out.println();
         
         Set<String> t = new TreeSet<String>(s);//from HashSet to TreeSet
         it = t.iterator();
         while(it.hasNext())
            System.out.print( it.next() + " " );
         System.out.println("\n\n the hashset is generally faster than tree set! \n the treeSet puts it in order! \n"); 
         System.out.println(s);    //print any Collection--wow!
         System.out.println(t);
      }
   }
   