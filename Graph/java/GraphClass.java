import java.util.List;

public interface GraphClass<V> {

 //count the number of vertices in the graph
 public int numVertices();

//count the number of edges in the graph
 public int numEdges();

 //add a vertex to the graph
 public void addVertex(V element);

 //add an edge to the graph between specified vertices
 //throw an Exception if either or both of the specified vertices does not exist.
 public void addEdge(V source, V destination) throws Exception;

 //find the degree of a vertex in the graph
 public int degree(V vertex);

 //get the between two specified vertices in the graph
 public Edge<V> getEdge(V v1, V v2);

 //perform a breadth first search from one specified vertex to another
 //and return a list containing all vertices visited in doing the search
 public List<V> BFS(V start, V end);
}

//Usage:
// Create a new Graph, default is UNDIRECTED, can use constructor arguments:
// set as Graph.DIRECTED to make it directed.
// Edges have source, destination -> weight and this is how it is stored in adj lists.
