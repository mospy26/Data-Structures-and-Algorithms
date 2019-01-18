public interface GraphClass<V> {

 //count the number of vertices in the graph
 public int numVertices();

//count the number of edges in the graph
 public int numEdges();

 //add a vertex to the graph
 public void addVertex(V element);

 //add an edge to the graph
 public void addEdge(V source, V destination) throws Exception;

 //find the degree of a vertex in the graph
 public int degree(V vertex);

 //get the between two specified vertices in the graph
 public Edge<V> getEdge(V v1, V v2);
}
