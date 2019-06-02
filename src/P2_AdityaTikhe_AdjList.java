
//name: Aditya Tikhe   due date: 4/27 - finished most of it during spring break
//resource classes and interfaces
//for use with Graphs3: EdgeList
//           Graphs4: DFS-BFS
//           Graphs5: EdgeListCities
/*
 * Thoughts: Enjoyed the lab because it was the same format as the AdjMap one so I could focus on learning the content.
 * After I understood the concepts and definitions the lab became straightforward. Understanding why each variable was used
 * became more clear so I learned more from this lab. 
 * 
 * What I wonder: How to do BFS and DFS recursively. 
 * 
 * What I want to work on: getting better at working with file reading
 */

//output at bottom

import java.io.*;
import java.util.*;

/********************* Graphs 3: EdgeList *******************************/
interface VertexInterface {
	public String toString(); // just return the name

	public String getName();

	public ArrayList<Vertex> getAdjacencies();

	public void addEdge(Vertex v);
}

/***********************************************************/
class Vertex implements VertexInterface {
	private final String name;
	private ArrayList<Vertex> adjacencies = new ArrayList<Vertex>();

	/* enter your code here */
	// constructor
	public Vertex(String n) {
		name = n;
		adjacencies = new ArrayList<Vertex>();
	}

	public String toString() // just return the name
	{
		return name;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Vertex> getAdjacencies() {
		return adjacencies;
	}

	public void addEdge(Vertex v) {
		adjacencies.add(v);
	}

	public boolean ee(Vertex o) { //check if equals
		if (this.name.equals(o.name))
			return true;
		else
			return false;
	}

}

interface AdjListInterface {
	public List<Vertex> getVertices();

	public Vertex getVertex(int i);

	public Vertex getVertex(String vertexName);

	public Map<String, Integer> getVertexMap();

	public void addVertex(String v);

	public void addEdge(String source, String target);

	public String toString();
}

/********************* Graphs 4: DFS and BFS ****************************/
interface DFS_BFS {
	public List<Vertex> depthFirstSearch(String name);

	public List<Vertex> depthFirstSearch(Vertex v);

	public List<Vertex> breadthFirstSearch(String name);

	public List<Vertex> breadthFirstSearch(Vertex v);
	// public List<Vertex> depthFirstRecur(String name);
	// public List<Vertex> depthFirstRecur(Vertex v);
	// public void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/**************** Graphs 5: EdgeList with Cities *********/
interface EdgeListWithCities {
	public void graphFromEdgeListData(String fileName) throws FileNotFoundException;

	public int edgeCount();

	public boolean isReachable(String source, String target);

	public boolean isFullyReachable();
}

/*******************************************************/
public class P2_AdityaTikhe_AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>(); // list of
																	// vertexes
	private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>(); // map
																				// name
																				// to
																				// index

	/* enter your code here */
	public List<Vertex> getVertices() {
		return vertices;
	}

	public Vertex getVertex(int i) {
		return vertices.get(i);
	}
	//returns vertex of string param
	public Vertex getVertex(String vertexName) {
		try {
			return getVertex(nameToIndex.get(vertexName));
		} catch (Exception e) {
			return null;
		}
	}

	public Map<String, Integer> getVertexMap() {
		return nameToIndex;
	}

	public void addVertex(String v) {
		Vertex val = new Vertex(v);

		for (Vertex vv : vertices) // to not add duplicates
		{
			if (vv.ee(val)) {
				return;
			}
		}

		vertices.add(val);
		nameToIndex.put(v, vertices.size() - 1);
	}

	public void addEdge(String source, String target) {
		// if source or target not there, add it
		if (getVertex(source) == null)
			addVertex(source);
		if (getVertex(target) == null)
			addVertex(target);

		(getVertex(source).getAdjacencies()).add(getVertex(target)); //adds edge
	}

	public String toString() {
		String s = "";
		for (Vertex v : vertices) {
			s += v.toString() + " " +v.getAdjacencies() + "\n";
		}
		return s;
	}

	public List<Vertex> depthFirstSearch(String name) // calls the vertex method
	{
		Vertex v = vertices.get(nameToIndex.get(name));
		return depthFirstSearch(v);
	}

	public List<Vertex> depthFirstSearch(Vertex v) // non recursive //algorithm from handout
	{
		ArrayList<Vertex> reachable = new ArrayList<Vertex>();
		Stack<Vertex> stack = new Stack<Vertex>(); //stack
		reachable.add(v);
		stack.push(v);
		while (!stack.isEmpty()) {
			Vertex next = stack.pop();
			for (Vertex d : next.getAdjacencies()) {
				if (!reachable.contains(d)) {
					reachable.add(d);
					stack.push(d);
				}
			}
		}
		return reachable;
	}
	//calls dfs(Vertex)
	public List<Vertex> breadthFirstSearch(String name) {
		Vertex v = vertices.get(nameToIndex.get(name));
		return breadthFirstSearch(v);
	}
	//algorithm from notes
	public List<Vertex> breadthFirstSearch(Vertex v) {
		ArrayList<Vertex> reachable = new ArrayList<Vertex>();
		Queue<Vertex> q = new LinkedList<Vertex>();
		reachable.add(v);
		q.add(v);
		while (!q.isEmpty()) {
			Vertex next = q.remove();
			for (Vertex d : next.getAdjacencies()) {
				if (!reachable.contains(d)) {
					reachable.add(d);
					q.add(d);
				}
			}
		}
		return reachable;
	}
	
	public void graphFromEdgeListData(String fileName) throws FileNotFoundException
	{
		Scanner file = new Scanner(new File("/Users/Batman/Documents/workspace/APCS AB Eclipse/src/"  + fileName)); 
		while(file.hasNext())
		{
			String startCity = file.next();
			String endCity = file.next();
			if(!vertices.contains(getVertex(startCity))) //if not already a vertex
				addVertex(startCity);
			if(!vertices.contains(getVertex(endCity)))
				addVertex(endCity);
			addEdge(startCity, endCity); //adds edge
		}
		
	}

	public int edgeCount()
	{
		//for every vertex in each adjacency
		int c = 0;
		for(Vertex v : vertices)
		{
			for(Vertex a : v.getAdjacencies())
				c++;
		}
		return c;
	}

	public boolean isReachable(String source, String target)
	{
		List <Vertex> list = depthFirstSearch(source); //calls dfs to get list of all cities available from that city
		for(Vertex v : list)
			if(v.getName().equals(target))
				return true;
		return false;
		
	}

	//2 for loop through the vertices and check if they are reachable from each other
	//double checked with test data to make sure it works
	public boolean isFullyReachable()
	{
		int c  = 0;
		for(Vertex v : vertices)
		{
			for(Vertex vv : vertices)
			{
				if(isReachable(v.getName(),vv.getName()))
					c++;
			}
		}
		if(c == (vertices.size() * vertices.size()))
			return true;
		return false;
	}

}
/*
 lab 3
 Edge List Representation! 
A [C]
B [A]
C [C, D]
D [C, A]
 
 */
/*
 lab 4
 Edge List Representation! 
A [C]
B [A]
C [C, D]
D [C, A]


Depth First Search
A ---> [A, C, D]
B ---> [B, A, C, D]
C ---> [C, D, A]
D ---> [D, C, A]

Breadth First Search
A ---> [A, C, D]
B ---> [B, A, C, D]
C ---> [C, D, A]
D ---> [D, C, A]

 */

/*
 lab 5
 Edge List with Cities! 
Enter file of cities and edges: cityEdgeList

Adjacency List: 
Pendleton [Pueblo]
Pueblo [Pendleton, Pierre]
Pensacola [Phoenix]
Phoenix [Pittsburgh, Pueblo]
Peoria [Pittsburgh, Pueblo]
Pittsburgh [Pensacola, Phoenix]
Pierre [Pendleton]
Princeton [Pittsburgh, Princeton]

Number of edges: 13

Is this graph fully reachable? false
Is it reachable?  Enter start city (-1 to exit): Pittsburgh
                    Enter end city: Princeton
false

Is it reachable?  Enter start city (-1 to exit): Pensacola
                    Enter end city: Pierre
true

Is it reachable?  Enter start city (-1 to exit): -1

 */
 
/*
test data for lab 5 to make sure fully reachable works: 
Aditya Ben
Ben Charlie
Charlie Aditya

output:
Edge List with Cities! 
Enter file of cities and edges: nameEdgeTest

Adjacency List: 
Aditya [Ben]
Ben [Charlie]
Charlie [Aditya]

Number of edges: 3

Is this graph fully reachable? true
Is it reachable?  Enter start city (-1 to exit): Aditya
                   Enter end city: Charlie
true

Is it reachable?  Enter start city (-1 to exit): 
*/


