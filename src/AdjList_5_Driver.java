//Aditya Tikhe
//lesson:  Graphs5: EdgeListCities
//uses AdjList
//hardest out of the 3 labs, but most worth while. 
//hardest method: graphFromEdgeListData
import java.util.*;
import java.io.*;
 
public class AdjList_5_Driver
{  
   public static void main(String[] args)throws FileNotFoundException
   {
      System.out.println("Edge List with Cities! ");
      Scanner kb = new Scanner(System.in);
      System.out.print("Enter file of cities and edges: "); 
   											             //cityEdgeList
      String fileOfCities = kb.next()+".txt";
      P2_AdityaTikhe_AdjList g = new P2_AdityaTikhe_AdjList();
      g.graphFromEdgeListData(fileOfCities);
     
      System.out.println("\nAdjacency List: ");
      System.out.println(g.toString());
   	
      System.out.println("Number of edges: " + g.edgeCount());
      
      System.out.print("\nIs this graph fully reachable? " + g.isFullyReachable() );
      
      while(true)
      {
         System.out.print("\nIs it reachable?  Enter start city (-1 to exit): ");
         String from = kb.next();
         if(from.equals("-1"))
            break;
         System.out.print("                    Enter end city: "); 
         String to = kb.next();  
         System.out.println( g.isReachable(from, to) );
      }
   }  
}
/**********************************
Edge List with Cities! 
Enter file of cities and edges: cityEdgeList

Adjacency List
Pendleton [Pueblo]
Pueblo [Pendleton, Pierre]
Pensacola [Phoenix]
Phoenix [Pittsburgh, Pueblo]
Peoria [Pittsburgh, Pueblo]
Pittsburgh [Pensacola, Phoenix]
Pierre [Pendleton]
Princeton [Pittsburgh, Princeton]

Number of edges: 13

Is this graph connected? false
Is it reachable?  Enter start city (-1 to exit): Pittsburgh
                    Enter end city: Pendleton
true

Is it reachable?  Enter start city (-1 to exit): -1

****************************/

