import java.util.HashMap;
import java.util.ArrayList;

public class Graph<V> {

 public static final int DIRECTED = 0;
 public static final int UNDIRECTED = 1;

 private HashMap<V, ArrayList<Edge<V>>> adjList;
 private ArrayList<V> vertexList;
 private boolean directed;

 public Graph() {
  this.adjList = new HashMap<>();
 }

 public Graph(ArrayList<V> vertices, ArrayList<Edge<V>> edges, int TYPE) {

  this.adjList = new HashMap<>();
  this.vertexList = new ArrayList<>();
  this.directed = (TYPE == DIRECTED) ? true : false;

  this.vertexList.addAll(vertices);
  for(V v: vertices) { this.adjList.put(v, new ArrayList<Edge<V>>()); }
  for(Edge<V> e: edges) {
   this.adjList.get(e.getSource()).add(e);
   if(TYPE == UNDIRECTED) this.adjList.get(e.getDestination()).add(e);
  }
}

 public void add_vertex(V element) throws AlreadyExistsException {
  this.vertexList.add(element);
  if(this.adjList.get(element) != null) throw new AlreadyExistsException(element);
  this.adjList.put(element, new ArrayList<Edge<V>>());
 }

 public void addEdge(V source, V destination) throws VertexDoesNotExistException {
  System.out.print(this.adjList.get(source));
  if(this.adjList.get(source) == null) throw new VertexDoesNotExistException(source);
  if(this.adjList.get(destination) == null) throw new VertexDoesNotExistException(destination);
  Edge<V> edge = new Edge<>(source, destination);
  this.adjList.get(edge.getSource()).add(edge);
  if(!this.directed) this.adjList.get(edge.getDestination()).add(edge);
 }

 public String toString() {
  return this.adjList + "";
 }

// testing
public static void main(String[] args) {
  ArrayList<Integer> vertices = new ArrayList<>();
  vertices.add(1);
  vertices.add(2);
  vertices.add(3);
  vertices.add(4);
  ArrayList<Edge<Integer>> edges = new ArrayList<>();
  edges.add(new Edge<Integer>(vertices.get(0), vertices.get(1)));
  Graph<Integer> graph = new Graph<Integer>(vertices, edges, UNDIRECTED);
  try {
   graph.addEdge(2, 4);
   graph.addEdge(21, 4);
  }
  catch(VertexDoesNotExistException v) {
   v.printStackTrace();
  }
  System.out.println(graph);
 }
}
