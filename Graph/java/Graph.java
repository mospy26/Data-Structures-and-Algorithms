import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap;
import java.util.Scanner;
import java.util.stream.*;
import java.util.Stack;

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

 // get a copy of the current graph
 public Graph(Graph<V> graph) {
  this(graph.directed);
  this.adjList = new HashMap<>();
  this.adjList = (HashMap<V, ArrayList<Edge<V>>>) graph.adjList.entrySet()
    .stream()
    .map(x -> new AbstractMap.SimpleEntry<V, ArrayList<Edge<V>>>(x.getKey(), new ArrayList<Edge<V>>(x.getValue())))
    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
  this.vertexList = (ArrayList<V>) graph.vertexList
    .stream()
    .collect(Collectors.toList());
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

 public List<V> BFS(V start, V end) {

  /*
   * 1. Add the starting vertex to the list "next layer"
   * 2. Mark every vertex except starting as "not seen"
   * 3. while the current layer is not empty, get every neighbour of every vertex in the current layer.
   * 4. The current layer is the next layer now.
   */

   List<V> layers = new ArrayList<>();
   List<V> currentLayer = new ArrayList<>();
   currentLayer.add(start); //step 1
   List<V> nextLayer = new ArrayList<>();
   HashMap<V, Boolean> seen = new HashMap<>();
   seen.put(start, true); //step 2
   for(V vertices: this.vertexList) { if(!vertices.equals(start)) seen.put(vertices, false); }

   while(!currentLayer.isEmpty()) {
    layers.addAll(currentLayer);
    for(V current: currentLayer) {
     for(Edge<V> outgoingEdges: this.adjList.get(current)) {
      V neighbours = outgoingEdges.getDestination();
      if(neighbours == end) {
       layers.add(neighbours);
       seen.put(neighbours, true);
       return layers;
      }
      if(!seen.get(neighbours)) {
       nextLayer.add(neighbours);
       seen.put(neighbours, true);
      }
     }
    }
    currentLayer.clear();
    currentLayer.addAll(nextLayer);
    nextLayer.clear();
   }
   return layers;
 }

 public Graph<V> BFSTransitiveClosure(V start) {

  Graph<V> newGraph = new Graph<>(this);
  V newStartVertex = newGraph.vertexList.get(this.vertexList.indexOf(start));
  List<V> layers = new ArrayList<>();
  List<V> currentLayer = new ArrayList<>();
  currentLayer.add(start); //step 1
  List<V> nextLayer = new ArrayList<>();
  HashMap<V, Boolean> seen = new HashMap<>();
  seen.put(start, true); //step 2
  for(V vertices: this.vertexList) { if(!vertices.equals(start)) seen.put(vertices, false); }

  while(!currentLayer.isEmpty()) {
   layers.addAll(currentLayer);
   for(V current: currentLayer) {
    for(Edge<V> outgoingEdges: this.adjList.get(current)) {
     V neighbours = outgoingEdges.getDestination();
     if(!seen.get(neighbours)) {
      nextLayer.add(neighbours);
      seen.put(neighbours, true);
      newGraph.addEdge(newStartVertex, neighbours);
     }
    }
   }
   currentLayer.clear();
   currentLayer.addAll(nextLayer);
   nextLayer.clear();
  }
  return newGraph;
 }

 public List<V> DFS(V start, V end) {

  /*
   * 1. Mark the start as seen
   * 2. For each edge, if that edge has not been seen, perform a DFS on that vertex which was the end of that edge
   *
   *
   */

   List<V> vertices = new ArrayList<>();
   Stack<V> stack = new Stack<>();
   stack.push(start);
   HashMap<V, Boolean> seen = new HashMap<>();

   for(V v: this.vertexList) { seen.put(v, false); }
   while(!stack.empty()) {
    V vertex = stack.pop();
    if(!seen.get(vertex)) {
     vertices.add(vertex);
     seen.put(vertex, true);
    }
    for(Edge<V> adjEdges: this.adjList.get(vertex)) {
     if(!seen.get(adjEdges.getDestination())) {
      stack.push(adjEdges.getDestination());
     }
     if(adjEdges.getDestination().equals(end)) {
      vertices.add(end);
      return vertices;
     }
    }
   }
   return vertices;
  }

 public String toString() {
  return this.adjList + "";
 }

 // testing
 public static void main(String[] args) {
  Graph<Integer> graph = new Graph<Integer>(DIRECTED);
  graph.addVertex(1);
  graph.addVertex(2);
  graph.addVertex(3);
  graph.addVertex(4);
  graph.addVertex(5);
  graph.addVertex(6);
  graph.addEdge(1, 2);
  graph.addEdge(2, 3);
  graph.addEdge(3, 4);
  graph.addEdge(1, 4);
  graph.addEdge(1, 5);
  graph.addEdge(5, 6);
  graph.addEdge(6, 3);
  //System.out.println(graph.getEdge(1, 2));
  System.out.println(graph.DFS(1, 6));

  Graph<Integer> g = new Graph<Integer>(UNDIRECTED);
  g.addVertex(1);
  g.addVertex(2);
  g.addVertex(3);
  g.addVertex(4);
  g.addVertex(5);
  g.addEdge(1, 2);
  g.addEdge(2, 3);
  g.addEdge(1, 4);
  g.addEdge(4, 5);
  System.out.println(g.BFSTransitiveClosure(1));
  System.out.println(g.DFS(1,5));
 }

}
