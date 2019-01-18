import java.util.HashMap;
import java.util.ArrayList;

public class Graph<V> implements GraphClass<V> {

 public static final boolean DIRECTED = true;
 public static final boolean UNDIRECTED = false;

 private HashMap<V, ArrayList<Edge<V>>> adjList;
 private ArrayList<V> vertexList;
 private boolean directed;

 public Graph() {
  this.adjList = new HashMap<>();
  this.vertexList = new ArrayList<>();
  this.directed = false;
 }

 public Graph(boolean directed) {
  this();
  this.directed = (directed == DIRECTED) ? true : false;
 }

 public int numVertices() {
  return this.vertexList.size();
 }

 public int numEdges() {
  int sumEdges = 0;
  for(V v: this.vertexList) {
   sumEdges += degree(v);
  }
  return sumEdges/2;
 }

 public void addVertex(V element) {
  this.vertexList.add(element);
  if(this.adjList.get(element) != null) return;
  this.adjList.put(element, new ArrayList<Edge<V>>());
 }

 public void addEdge(V source, V destination) throws VertexNotFoundException {
  if(this.adjList.get(source) == null) throw new VertexNotFoundException(source);
  if(this.adjList.get(destination) == null) throw new VertexNotFoundException(destination);
  if(this.getEdge(source, destination) != null) return;
  Edge<V> edge = new Edge<>(source, destination);
  this.adjList.get(edge.getSource()).add(edge);
  if(!this.directed && this.getEdge(edge.getDestination(), edge.getSource()) == null)
  this.adjList.get(edge.getDestination()).add(new Edge<V>(edge.getDestination(), edge.getSource()));
 }

 public int degree(V vertex) {

  if(!this.directed) return this.adjList.get(vertex).size();

  //for directed graphs
  else {
   int inDegree = 0;
   for(V v: this.vertexList) {
    if(v.equals(vertex)) continue;
    for(Edge<V> elements: this.adjList.get(v)) {
     if(elements.getDestination().equals(vertex)) { inDegree++; }
    }
   }
   return this.adjList.get(vertex).size() + inDegree;
  }
 }

 public Edge<V> getEdge(V v1, V v2) {
  for(Edge<V> e: this.adjList.get(v1)) {
   if(e.getDestination().equals(v2)) {
    return e;
   }
  }
  return null;
 }

 public String toString() {
  return this.adjList + "";
 }

 // testing
 public static void main(String[] args) {
  ArrayList<Integer> vertices = new ArrayList<>();
  Graph<Integer> graph = new Graph<Integer>(DIRECTED);
  try {
   graph.addVertex(1);
   graph.addVertex(2);
   graph.addVertex(3);
   graph.addVertex(4);
   graph.addEdge(1, 2);
   graph.addEdge(2, 1);
   graph.addEdge(2, 4);
   graph.addEdge(21, 4);
  }
  catch(Exception v) {
   v.printStackTrace();
  }
  System.out.println(graph.getEdge(1, 2));
  System.out.println(graph);
  System.out.println(graph.degree(2));
 }
}
