
//lesson:  Graphs4: DFS_BFS
//uses AdjList
//DFS and BFS are much easier to understand now 
import java.util.*;
import java.io.*;
 
public class DFS_BFS_Driver
{  
   public static void main(String[] args)throws FileNotFoundException
   {
      System.out.println("Edge List Representation! ");
      P2_AdityaTikhe_AdjList g = new P2_AdityaTikhe_AdjList();
      g.addVertex("A");      //if it's not there, add it.
      g.addVertex("B");
      g.addEdge("A", "C"); // <-- warning!  Be sure to add all the Vertices first, 
                           // or else deal with it. 
      g.addVertex("C");
      g.addVertex("D");
      g.addEdge("B", "A");
      g.addEdge("C", "C");
      g.addEdge("C", "D");
      g.addEdge("D", "C");
      g.addEdge("D", "A");
      System.out.println(g.toString());      //let's look at it first
   	    
      System.out.println("\nDepth First Search");
      for (String name : g.getVertexMap().keySet() )
         System.out.println( name + " ---> " + g.depthFirstSearch(name) );
         
      System.out.println("\nBreadth First Search");
      for (String name : g.getVertexMap().keySet() )
         System.out.println( name + " ---> " + g.breadthFirstSearch(name) );
      
      //use the debugger to see the difference:
      //System.out.println();  
      //System.out.println( "D" + " ---> " + g.depthFirstSearch("D") );
      ///System.out.println( "D" + " ---> " + g.breadthFirstSearch("D") );
      
      /*  Extension  */   
      // System.out.println("\nDepth First Search (Recursive)");
      // for (String name : g.getVertexMap().keySet() )
      // System.out.println ( name + " ---> " + g.depthFirstRecur(name) );
   }   
}
/********************************
Edge List Representation! 
 A [C]
 B [A]
 C [C, D]
 D [C, A]
 
 Depth First Search
 A ---> [A, C, D]
 B ---> [B, A, C, D]
 C ---> [C, D, A]
 D ---> [D, A, C]
 
 Breadth First Search
 A ---> [A, C, D]
 B ---> [B, A, C, D]
 C ---> [C, D, A]
 D ---> [D, C, A]
 
******************************/
