//name: Aditya Tikhe  date:   4/3
// resource classes and interfaces
// for use with Graphs0: Intro
//              Graphs1: Warshall
//              Graphs2: Floyd
/*
 * 
 * What I learned: I learned the basics of the adjacency matrix and how it works. This lab was a good stepping stone into the 
 * 					warshall and floyd methods. 
 * 
 * How I feel about this lab: This lab was pretty simple, and a good introduction.
 * 								Reviewed interfaces. 
 * 
 * Creds: none
 * 
 * Warshall:
 *  had some difficulty getting it done.
 *  got some help from internet on the algorithm and understanding it + watched videos to understnad
 *  hardest method was allPairsReachability() because I had to first understand it.
 *  Had some trouble reading files...
 * 	really enjoyed doing this lab because it combined hashmaps and other interesting concepts
 * 
 * Floyd:
 * 	not too bad at all because the floyd algorithm was very similar to warshall's
 */
import java.util.*;
import java.io.*;

interface AdjacencyMatrix
{
   public void addEdge(int source, int target);
   public void removeEdge(int source, int target);
   public boolean isEdge(int from, int to);
   public void displayGrid();
   public int edgeCount();
   public List<Integer> getNeighbors(int source);
}

interface Warshall      //User-friendly functionality
{
   public boolean isEdge(String from, String to);
   public Map<String, Integer> getVertices();     
   public void readNames(String fileName) throws FileNotFoundException;
   public void readGrid(String fileName) throws FileNotFoundException;
   public void displayVertices();
   public void allPairsReachability(); // Warshall's Algorithm
   public ArrayList<String> getReachables(String city);
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted(); 
}

public class P2_AdityaTikhe_AdjMat implements AdjacencyMatrix ,Warshall,Floyd
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name --> index. Used in
                                                    // graph1
   
   public P2_AdityaTikhe_AdjMat (int size)
   {
	   grid = new int [size][size];
	   vertices = new TreeMap<String, Integer>();
   }
     
   // Enter your code here: 
   // Define methods declared in AdjacencyMatrix so that AdjMat_0_Driver.java
   // works properly
   public void addEdge(int source, int target)
   {
	   grid[source][target] = 1;
   }
   public void removeEdge(int source, int target)
   {
	   grid[source][target] = 0;
   }
   public boolean isEdge(int from, int to)
   {
	   if(grid[from][to] == 0)
		   return false;
	   else
		   return true;
   }
   public void displayGrid()
   {
	   for(int row  = 0; row < grid.length; row++)
	   {
		   for(int col = 0; col < grid[0].length; col++)
		   {
			   System.out.print(grid[row][col] + "  ");
		   }
		   System.out.println();
	   }
   }
   public int edgeCount()
   {
	   int c = 0;
	   for(int row  = 0; row < grid.length; row++)
	   {
		   for(int col = 0; col < grid[0].length; col++)
		   {
			   if((grid[row][col] != 0) && (grid[row][col] != 9999))
				   	c++;
		   }
	   }
	   return c;
   }
   public List<Integer> getNeighbors(int source)
   {
	   List<Integer> list = new ArrayList <Integer>();
	   for(int col = 0; col < grid.length; col++)
		   if(grid[source][col] == 1)
			   list.add(col);
	   return list;
   }
   
   //Warshall 
   public boolean isEdge(String from, String to)
   {
	   int r = vertices.get(from);
	   int c = vertices.get(to);
	   if(grid[r][c]==1)
		   return true;
	   return false;
   }
   public Map<String, Integer> getVertices()
   {
	   return vertices;
   }
   public void readNames(String fileName) throws FileNotFoundException
   {
	   Scanner file = new Scanner(new File("/Users/Batman/Documents/workspace/APCS AB Eclipse/src/"  + fileName));
	   int size =  file.nextInt();
	   file.nextLine();
	   //System.out.println(size);
	   for(int i = 0; i < size; i++)
	   {
		   String city = file.nextLine().trim();
		   vertices.put(city, i);
	   }
   }
   public void readGrid(String fileName) throws FileNotFoundException
   {
	   Scanner file = new Scanner(new File("/Users/Batman/Documents/workspace/APCS AB Eclipse/src/"  + fileName));
	   int size =  file.nextInt();
	   grid = new int[size][size]; 
	   for(int r = 0; r < size; r++)
	   {
		   for(int c = 0; c < size; c++)
		   {
			   grid[r][c] = file.nextInt();
		   }
	   }
   }
   public void displayVertices()
   {
	   for(String city : vertices.keySet())
	   {
			   System.out.println(vertices.get(city) + " " + city);
	   }
   }
   public void allPairsReachability()
   {
	   for(int a = 0; a< grid.length; a++)
	   {
		   for(int b = 0; b< grid.length; b++)
		   {
			   for(int c = 0; c< grid.length; c++)
			   {
				   if(grid[b][a] + grid[a][c] == 2)
					   grid[b][c] = 1;
			   }
		   }
	   }
   }

   public ArrayList <String>  getReachables(String city) 
   {
	ArrayList <String> a = new ArrayList ();
	int r = vertices.get(city);
	for(int i = 0; i < grid.length; i++)
	{
		if(grid[r][i] == 1)
			a.add(getKey(i));
	}
	return a;
   }
   
   public String getKey(int v) //gets key from value for extension
   {
	   for(String s: vertices.keySet())
	   {
		   if(vertices.get(s).equals(v))
			   return s;
	   }
	   return null;
   }
   
   //floyd
   public int getCost(int from, int to)
   {
	   return grid[from][to];
   }
   public int getCost(String from, String to)
   {
	   int r = vertices.get(from);
	   int c = vertices.get(to);
	   return getCost(r,c);	   
   }
   public void allPairsWeighted() //floyds algorithm
   {
	   for(int a = 0; a< grid.length; a++)
	   {
		   for(int b = 0; b< grid.length; b++)
		   {
			   for(int c = 0; c< grid.length; c++)
			   {
				   if(grid[b][a] + grid[a][c] < grid[b][c])
					   grid[b][c] = grid[b][a] + grid[a][c];
			   }
		   }
	   }
   }
   
   
}
/*
 * floyds output:
 * Floyd's Algorithm! Enter file of names: cities
Enter file of the matrix: citymatrixweighted

Adjacency Matrix
0  9999  9999  9999  9999  9999  9999  8  
9999  0  9999  5  9999  9999  9999  9999  
9999  9999  0  9999  9999  5  9999  3  
9999  9999  9999  0  9999  10  9999  3  
2  9999  9999  9999  0  9999  9999  9999  
9999  4  9999  10  9999  0  9999  9999  
9999  9999  9999  9999  9999  2  0  9999  
8  9999  9999  9999  3  9999  9999  0  

Number of Edges: 12

0 Pendleton
1 Pensacola
2 Peoria
3 Phoenix
4 Pierre
5 Pittsburgh
6 Princeton
7 Pueblo
Cost Matrix
0  9999  9999  9999  11  9999  9999  8  
13  0  9999  5  11  15  9999  8  
8  9  0  14  6  5  9999  3  
8  14  9999  0  6  10  9999  3  
2  9999  9999  9999  0  9999  9999  10  
17  4  9999  9  15  0  9999  12  
19  6  9999  11  17  2  0  14  
5  9999  9999  9999  3  9999  9999  0  

Number of Edges: 33

What is the cost?  Enter start city (-1 to exit): Princeton
                Enter end city: Pueblo
14

What is the cost?  Enter start city (-1 to exit): Pierre
                Enter end city: Pittsburgh
9999

What is the cost?  Enter start city (-1 to exit): -1
 */

/*
 * Warshall Output:
Warshall's Algorithm! Enter file of names: cities
Enter file of the matrix: citymatrix
Adjacency Matrix
0  0  0  0  0  0  0  1  
0  0  0  1  0  0  0  0  
0  0  0  0  0  1  0  1  
0  0  0  0  0  1  0  1  
1  0  0  0  0  0  0  0  
0  1  0  1  0  0  0  0  
0  0  0  0  0  1  1  0  
1  0  0  0  1  0  0  0  
Number of Edges: 13

0 Pendleton
1 Pensacola
2 Peoria
3 Phoenix
4 Pierre
5 Pittsburgh
6 Princeton
7 Pueblo

Reachability Matrix
1  0  0  0  1  0  0  1  
1  1  0  1  1  1  0  1  
1  1  0  1  1  1  0  1  
1  1  0  1  1  1  0  1  
1  0  0  0  1  0  0  1  
1  1  0  1  1  1  0  1  
1  1  0  1  1  1  1  1  
1  0  0  0  1  0  0  1  
Number of Edges: 40

Is it reachable?  Enter name of start city (-1 to exit): Princeton
                Enter name of end city: Pueblo
true

Is it reachable?  Enter name of start city (-1 to exit): Pierre
                Enter name of end city: Pittsburgh
false

Is it reachable?  Enter name of start city (-1 to exit): -1

================== EXTENSION ==================
List of every city's reachable cities: 
Pendleton--> [Pendleton, Pierre, Pueblo]
Pensacola--> [Pendleton, Pensacola, Phoenix, Pierre, Pittsburgh, Pueblo]
Peoria--> [Pendleton, Pensacola, Phoenix, Pierre, Pittsburgh, Pueblo]
Phoenix--> [Pendleton, Pensacola, Phoenix, Pierre, Pittsburgh, Pueblo]
Pierre--> [Pendleton, Pierre, Pueblo]
Pittsburgh--> [Pendleton, Pensacola, Phoenix, Pierre, Pittsburgh, Pueblo]
Princeton--> [Pendleton, Pensacola, Phoenix, Pierre, Pittsburgh, Princeton, Pueblo]
Pueblo--> [Pendleton, Pierre, Pueblo]
 */

/*
 *  Intro output:
  Enter size of adjacency matrix: 5
Adjacency Matrix
0  0  0  0  0  
0  0  0  0  0  
0  0  0  0  0  
0  0  0  0  0  
0  0  0  0  0  
Add edges, source<space>target<enter>.  Enter -1 to end.
0
0
1  0  0  0  0  
0  0  0  0  0  
0  0  0  0  0  
0  0  0  0  0  
0  0  0  0  0  
1
2
1  0  0  0  0  
0  0  1  0  0  
0  0  0  0  0  
0  0  0  0  0  
0  0  0  0  0  
3
4
1  0  0  0  0  
0  0  1  0  0  
0  0  0  0  0  
0  0  0  0  1  
0  0  0  0  0  
4
2
1  0  0  0  0  
0  0  1  0  0  
0  0  0  0  0  
0  0  0  0  1  
0  0  1  0  0  
1
3
1  0  0  0  0  
0  0  1  1  0  
0  0  0  0  0  
0  0  0  0  1  
0  0  1  0  0  
2
2
1  0  0  0  0  
0  0  1  1  0  
0  0  1  0  0  
0  0  0  0  1  
0  0  1  0  0  
3
3
1  0  0  0  0  
0  0  1  1  0  
0  0  1  0  0  
0  0  0  1  1  
0  0  1  0  0  
4
4
1  0  0  0  0  
0  0  1  1  0  
0  0  1  0  0  
0  0  0  1  1  
0  0  1  0  1  
1
3
1  0  0  0  0  
0  0  1  1  0  
0  0  1  0  0  
0  0  0  1  1  
0  0  1  0  1  
0
3
1  0  0  1  0  
0  0  1  1  0  
0  0  1  0  0  
0  0  0  1  1  
0  0  1  0  1  
0
2
1  0  1  1  0  
0  0  1  1  0  
0  0  1  0  0  
0  0  0  1  1  
0  0  1  0  1  
-1
1  0  1  1  0  
0  0  1  1  0  
0  0  1  0  0  
0  0  0  1  1  
0  0  1  0  1  
Remove an edge? Y/NY
Remove which edge?  0
0
0  0  1  1  0  
0  0  1  1  0  
0  0  1  0  0  
0  0  0  1  1  
0  0  1  0  1  
Remove which edge?  4
4
0  0  1  1  0  
0  0  1  1  0  
0  0  1  0  0  
0  0  0  1  1  
0  0  1  0  0  
Remove which edge?  -1
Number of edges: 8
The neighbors of each vertex: 
0: [2, 3]
1: [2, 3]
2: [2]
3: [3, 4]
4: [2]
 */


