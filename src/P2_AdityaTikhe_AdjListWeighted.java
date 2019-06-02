//name: Aditya Tikhe  date: finished Friday in Class due Sunday May 5th 
//resource classes for Graphs6: Dijkstra
//                   Graphs7: Dijkstra with Cities
/*
 Thoughts: this lab was doable and everything came to me easily because we did similar labs previously. 
 It took some time learning Dijkstra and prims algorithm, but once I understood it, I the lab came to 
 me easy.
 Credits: Youtube, notes 
 */
import java.io.*;
import java.util.*;

class Edge {
 public final wVertex target;
 public final double weight;
 
 public wVertex getTarget() //getter
 {
	 return target;
 }
 
 public double getWeight() //getter
 {
	 return weight;
 }
 
 //constructor
 public Edge(wVertex argTarget, double argWeight) {
    target = argTarget;
    weight = argWeight;
 }
}

interface wVertexInterface 
{
 public String toString();
 public String getName();
 public double getMinDistance();
 public void setMinDistance(double m);
 //public wVertex getPrevious();   //for Dijkstra 7
 //public void setPrevious(wVertex v);  //for Dijkstra 7
 public ArrayList<Edge> getAdjacencies();
 public void addEdge(wVertex v, double weight);   
 public int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
 private final String name;
 private ArrayList<Edge> adjacencies = new ArrayList<Edge>();
 // The reason why we have the two "unexpected" fields in the wVertex
 // class below is that when we run Dijkstra's algorithm, we want to 
 // obtain two things:
 // 1. The minimum distance of a vertex from the source vertex.
 // 2. The path of the minimum distance from the source to the target vertex.

 private double minDistance = Double.POSITIVE_INFINITY;
 
 public wVertex prev = null; //prev 
 //private wVertex previous;  //for building the actual path in Dijkstra 7
                              //uncomment this part when you do Graph 7
 
  /*  enter your code here   */ 
 public wVertex(String n) {
		name = n;
		adjacencies = new ArrayList<Edge>();
	}
 
 public String toString()
 {
	 return name;
 }
 public String getName()
 {
	 return name;
 }
 
 public wVertex getPrev()
 {
	 return prev;
 }
 public void setPrev(wVertex n)
 {
	 prev = n;
 }
 
 public double getMinDistance()
 {
	 return minDistance;
 }
 public void setMinDistance(double m)
 {
	 minDistance = m;
 }
 public ArrayList<Edge> getAdjacencies()
 {
	 return adjacencies;
 }
 public void addEdge(wVertex v, double weight)
 {
	 Edge e = new Edge(v,weight); //make edge
	 adjacencies.add(e);
 }
 public int compareTo(wVertex other)
 {
	 if (this.name.equals(other.name))
			return -1;
		else
			return 1;
 }
}


interface AdjListWeightedInterface 
{
 public List<wVertex> getVertices();
 public Map<String, Integer> getNameToIndex();
 public wVertex getVertex(int i);   
 public wVertex getVertex(String vertexName);
 public void addVertex(String v);
 public void addEdge(String source, String target, double weight);
 // This method does 2 things:
 // 1. Set the minimum distance field of all other vertices from the
 //    source vertex. 
 // 2. Set the previous vertext field. This is for tracing the path
 //    from the source to the target vertex. The method 
 //    getShortestPathTo (target) in line #73 needs to use this field in order
 //    to return the PATH from source to target.
 // SetRead Dijkstra_6_Driver.java to see how this 
 // method is called
 public void minimumWeightPath(String sourceVertexName);   //Dijkstra's
}
 /*  Graphs 7 */
//interface AdjListWeightedInterfaceWithCities 
//{       
 // public List<wVertex> getShortestPathTo(wVertex v);
 // public AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException ;
//}

public class P2_AdityaTikhe_AdjListWeighted implements AdjListWeightedInterface//, AdjListWeightedInterfaceWithCities
{
 private List<wVertex> vertices = new ArrayList<wVertex>();
 private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
//the constructor is no-arg 
 public P2_AdityaTikhe_AdjListWeighted()
 {
	 
 }
 /*  enter your code here   */ 
 public List<wVertex> getVertices()
 {
	 return vertices;
 }
 public Map<String, Integer> getNameToIndex()
 {
	 return nameToIndex;
 }
 public wVertex getVertex(int i)
 {
	 return vertices.get(i);
 }
 public wVertex getVertex(String vertexName) 
 {
	try{
		return getVertex(nameToIndex.get(vertexName)); //gets vertex from name
	}
	catch(Exception e){
		return null;
	}
 }
 public void addVertex(String v)
 {
	 wVertex val = new wVertex(v);

		for (wVertex vv : vertices) // to not add duplicates
		{
			if (vv.compareTo(val) < 0) {
				return;
			}
		}

		vertices.add(val);
		nameToIndex.put(v, vertices.size() - 1);
 }
 public void addEdge(String source, String target, double weight)
 {
	// if source or target not there, add it
	if (getVertex(source) == null)
		addVertex(source);
	if (getVertex(target) == null)
		addVertex(target);
	
	wVertex tar = getVertex(nameToIndex.get(target));
	Edge e = new Edge(tar, weight);
	tar.setPrev(getVertex(nameToIndex.get(source))); //set prev to source
		
	(getVertex(source).getAdjacencies()).add(e); //adds edge with weight
 }
 
 
 public void minimumWeightPath(String sourceVertexName) //Djikstra's algorithm
 {
	 wVertex source = getVertex(nameToIndex.get(sourceVertexName));
	 for(wVertex v: vertices) //sets all to positive infinity
	 	{
		 	v.setMinDistance(Double.POSITIVE_INFINITY);
	 	}
	 source.setMinDistance(0);
	 
	 PriorityQueue <wVertex> q = new PriorityQueue <wVertex>();
	 q.add(source);
	 
	 while(!q.isEmpty())
	 {
		 wVertex temp = q.poll();
		 for(Edge e : temp.getAdjacencies()) //for each neighboring edge of tmep
		 {
			 wVertex neighbor = e.getTarget();
			 double weight = e.getWeight();
			 double minDistance = temp.getMinDistance() + weight;
			 
			 if(minDistance < neighbor.getMinDistance()) // if update is needed
			 {
				 q.remove(neighbor);
				 neighbor.setPrev(temp);// update the “previousVertex” field of v
				 neighbor.setMinDistance(minDistance);// update minDistance of v
				 				
				 q.add(neighbor); // put the update node into the PQ
			 }
		 }
	 }
	 
 }
 
}    

/*
 * output
 Enter start: A
Distance to A: 0.0
Distance to B: 6.0
Distance to C: 3.0
Distance to D: 5.0
 
 */
